
//Cem Durmus
package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import beans.ExerciseBean;
import beans.UserBean;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FavoriteExerciseServlet
 */
@WebServlet("/FavoriteExerciseServlet")
public class FavoriteExerciseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(lookup = "java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FavoriteExerciseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserBean user = (UserBean) request.getSession().getAttribute("userData");
		ArrayList<ExerciseBean> favoriteExercises = getFavoriteExercises(user.getId());
		String json = convertListToJson(favoriteExercises);
		response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		System.out.print("istDrin");
		UserBean user = (UserBean) request.getSession().getAttribute("userData");
		setFavoriteExercise(name, user.getId());
		response.getWriter().write("ok");
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		delFavoriteExercise(name);
		response.getWriter().write("ok");
	}
	
	private ArrayList<ExerciseBean> getFavoriteExercises(Long id) throws ServletException
	{
		ArrayList<ExerciseBean> exercises = new ArrayList<>();
		
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM favoriteexercises WHERE userId=?")){
			pstmt.setLong(1, id);
			try(ResultSet rs = pstmt.executeQuery())
			{
				while(rs != null && rs.next()) {
					Long exerciseId = rs.getLong("exerciseId");
					try(Connection conEx = ds.getConnection();
							PreparedStatement exercisePstmt = conEx.prepareStatement("SELECT * FROM exercises WHERE id=?"))
					{
						exercisePstmt.setLong(1, exerciseId);
						
						try(ResultSet res = exercisePstmt.executeQuery())
						{
							while(res != null && res.next()) {
								ExerciseBean exercise = new ExerciseBean();
								exercise.setName(res.getString("name"));
								exercise.setId(res.getLong("id"));
								exercise.setMuscleGroup(res.getString("muscleGroup"));
								exercises.add(exercise);
							}
						}
					}
				}
			}
			
		}
		catch (Exception ex) 
		{
			throw new ServletException(ex.getMessage());
		}
		return exercises;
	}

	private void setFavoriteExercise(String name,Long id) throws ServletException 
	{
		ExerciseBean exercise = new ExerciseBean();
		System.out.print("istDrin1");
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO favoriteexercises (exerciseId, userId) "
					+ "VALUES( "
					+ "(SELECT id From exercises WHERE name = ?), ?"
					+ ")")){
			pstmt.setString(1, name);
			pstmt.setLong(2, id);
			pstmt.executeUpdate();
			
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
	}
	
	private void delFavoriteExercise(String name) throws ServletException
	{
		try(Connection con = ds.getConnection();
				PreparedStatement favCon = con.prepareStatement("DELETE FROM favoriteexercises "
						+ "WHERE (SELECT exercises.id FROM exercises "
						+ "WHERE name=?)"))
		{
			favCon.setString(1, name);
			favCon.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private String convertListToJson(ArrayList<ExerciseBean> arr) 
	{
		StringBuilder jsonString = new StringBuilder();
		ArrayList<ExerciseBean> exercises = arr;
		
		jsonString.append("[");
		for(int i = 0;i < exercises.size(); i++) 
		{			
			jsonString.append("{");
			jsonString.append("\"name\":");
			jsonString.append("\""+exercises.get(i).getName()+"\",");
			jsonString.append("\"muscleGroup\":");
			jsonString.append("\""+exercises.get(i).getMuscleGroup()+"\",");
			jsonString.append("\"id\":");
			jsonString.append("\""+exercises.get(i).getId()+"\"");
			if( i+1 == exercises.size()) 
			{
				jsonString.append("}");
			} else {
				jsonString.append("},");
				}
		}
		jsonString.append("]");
		
		return jsonString.toString();
	}

}
