package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import beans.ExerciseBean;
import beans.ExerciseToTrainingBean;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class ExerciseToTrainingServlet
 */
@WebServlet("/ExerciseToTrainingServlet")
public class ExerciseToTrainingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(lookup = "java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;
    /**
     * @see HttpServlet#HttpServlet()
     */
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExerciseToTrainingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long trainingId =Long.valueOf(request.getParameter("trainingId"));
		ArrayList<ExerciseToTrainingBean> exercisesToTraining = getAll(trainingId);
		ExerciseServlet exerciseServlet = new ExerciseServlet();
		ArrayList<ExerciseBean> exercises = exerciseServlet.getExercisesById(exercisesToTraining, this.ds);
		String jsonExercises = convertListToJson(exercises);
		response.getWriter().write(jsonExercises);
		System.out.println(jsonExercises);
		
		
	}
	
	private ArrayList<ExerciseToTrainingBean> getAll(Long trainingId) throws ServletException
	{
		ArrayList<ExerciseToTrainingBean> exercisesToTraining = new ArrayList<>();
		
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement("SELECT * FROM exercisestotrainings WHERE trainingId = ?")) 
		{
			pstmt.setLong(1, trainingId);
			try (ResultSet result = pstmt.executeQuery())
			{
				while (result.next()) 
				{
					ExerciseToTrainingBean exerciseToTraining = new ExerciseToTrainingBean();
					exerciseToTraining.setExerciseId(result.getLong("exerciseId"));
					exerciseToTraining.setTrainingId(trainingId);
					
					exercisesToTraining.add(exerciseToTraining);
				}
				return exercisesToTraining;
			}
			
		}
		catch (Exception ex) 
		{
			throw new ServletException(ex.getMessage()); 
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

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ExerciseToTrainingBean exerciseToTraining = new ExerciseToTrainingBean();
		exerciseToTraining.setExerciseId(Long.valueOf(request.getParameter("exerciseId")));
		exerciseToTraining.setTrainingId(Long.valueOf(request.getParameter("trainingId")));
		HttpSession session = request.getSession();
		create(exerciseToTraining);
		session.setAttribute("exercisesToTraining", exerciseToTraining);
		
		doGet(request, response);
	}
	
	private void create(ExerciseToTrainingBean exToTr) throws ServletException{
		
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO exercisestotrainings"
														+ "(exerciseId, trainingId)"
														+ "VALUES (?, ?)"))
		{
			pstmt.setLong(1, exToTr.getExerciseId());
			pstmt.setLong(2, exToTr.getTrainingId());
			
			pstmt.executeUpdate();
						
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
	}

	
	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ExerciseToTrainingBean exerciseToTraining = new ExerciseToTrainingBean();
		exerciseToTraining.setExerciseId(Long.valueOf(request.getParameter("exerciseId")));
		exerciseToTraining.setTrainingId(Long.valueOf(request.getParameter("trainingId")));
		delete(exerciseToTraining);
	}

	private void delete(ExerciseToTrainingBean exerciseToTraining) throws ServletException {
		
		try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement("DELETE FROM exercisestotrainings WHERE exerciseId = ? AND trainingId = ?")){
				pstmt.setLong(1, exerciseToTraining.getExerciseId());
				pstmt.setLong(2, exerciseToTraining.getTrainingId());
				
				pstmt.executeUpdate();
				
			} catch (Exception ex) {
				throw new ServletException(ex.getMessage());
			}
		
	}

}
