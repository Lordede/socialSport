package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import beans.SetBean;
import beans.TrainingExerciseSetJoinBean;
import beans.UserBean;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//von Lukas Edmüller

/**
 * Servlet implementation class SatzServlet
 */
@WebServlet("/SetServlet")
public class SetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// Verbindung zur Datenbank deklarieren
	@Resource(lookup = "java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    SetBean set = new SetBean();
    
    public SetBean getSet() 
	{
		return this.set;
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//Long id = Long.parseLong(request.getParameter("id"));
		HttpSession session = request.getSession();
		UserBean userBean = (UserBean) session.getAttribute("userData");
		
		List<TrainingExerciseSetJoinBean> sets = getSets(userBean.getId());
		
		
		
		//TODO: read muss über id erfolgen
		//TODO: search muss über exerciseId erfolgen
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		SetBean set = new SetBean();
		
		set.setRep(Integer.parseInt(request.getParameter("rep")));
		set.setKg(Double.parseDouble(request.getParameter("kg")));
		set.setExerciseId(Long.parseLong(request.getParameter("exerciseId")));
		set.setTrainingId(Long.parseLong(request.getParameter("trainingId")));
				
		create(set);
		
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		SetBean set = new SetBean();
		
		set.setId(Long.parseLong(request.getParameter("id")));
		set.setRep(Integer.parseInt(request.getParameter("rep")));
		set.setKg(Double.parseDouble(request.getParameter("kg")));
		set.setExerciseId(Long.parseLong(request.getParameter("exerciseId")));
		set.setTrainingId(Long.parseLong(request.getParameter("trainingId")));
				
		update(set);
		
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Long id = Long.parseLong(request.getParameter("id"));
		delete(id);
	}
	
	private void delete(Long id) throws ServletException{
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement("DELETE FROM sets WHERE id = ?")){
			pstmt.setLong(1, id);
			pstmt.executeUpdate();
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
	}
	
	private void update(SetBean form) throws ServletException{
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"UPDATE sets " 
					+ "SET rep = ?, kg = ?"
					+ "WHERE id = ?")){
			pstmt.setInt(1, form.getRep());
			pstmt.setDouble(2, form.getKg());
			pstmt.setLong(3, form.getId());
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
	}
		
	private SetBean read(Long id) throws ServletException{
		SetBean form = new SetBean();
		
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM sets WHERE id = ?")){
			
			pstmt.setLong(1, id);
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs != null && rs.next()) {
					form.setRep(rs.getInt("rep"));
					form.setKg(rs.getDouble("kg"));
					form.setExerciseId(rs.getLong("exerciseId"));
					form.setTrainingId(rs.getLong("trainingId"));
					
				}
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		return form;
	}

	private List<TrainingExerciseSetJoinBean> getSets(Long userId) throws ServletException{
		List<TrainingExerciseSetJoinBean> joinBeans = new ArrayList<TrainingExerciseSetJoinBean>();
		
		try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement("SELECT sets.id, kg, rep, exerciseId, exercises.name AS exerciseName, muscleGroup, exerciseImage, trainingId, trainings.name as trainingName, points, userId\r\n"
						+ "FROM sets\r\n"
						+ "JOIN exercises ON sets.exerciseId = exercises.id\r\n"
						+ "JOIN trainings ON sets.trainingId = trainings.id\r\n"
						+ "WHERE userId = ?")){
				
				pstmt.setLong(1, userId);
				try(ResultSet rs = pstmt.executeQuery()){
					while(rs.next()) {
						TrainingExerciseSetJoinBean joinBean = new TrainingExerciseSetJoinBean();
						
						Long id = Long.valueOf(rs.getLong("id"));
						double kg = Double.valueOf(rs.getDouble("kg"));
						int rep = Integer.valueOf(rs.getInt("rep"));
						Long exerciseId = Long.valueOf(rs.getLong("exerciseId"));
						String exerciseName = rs.getString("exerciseName");
						String muscleGroup = rs.getString("muscleGroup");
						String exerciseImage = rs.getString("exerciseImage");
						Long trainingId = Long.valueOf(rs.getLong("trainingId"));
						String trainingName = rs.getString("trainingName");
						double points = Double.valueOf(rs.getDouble("points"));
						
						
						
						joinBean.setId(id);
						joinBean.setKg(kg);
						joinBean.setRep(rep);
						joinBean.setExerciseId(exerciseId);
						joinBean.setExerciseName(exerciseName);
						joinBean.setMuscleGroup(muscleGroup);
						joinBean.setExerciseImage(exerciseImage);
						joinBean.setTrainingId(trainingId);
						joinBean.setTrainingName(trainingName);
						joinBean.setPoints(points);
						joinBean.setUserId(userId);
						
						joinBeans.add(joinBean);
					}
				}
				
			}catch (Exception ex) {
				throw new ServletException(ex.getMessage());
			}
		
		return joinBeans;
	}
	
	private List<SetBean> search(Long exerciseId) throws ServletException{
		List<SetBean> sets = new ArrayList<SetBean>();
		
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM sets WHERE exerciseId = ?")){
			
			pstmt.setLong(0, exerciseId);
			try(ResultSet rs = pstmt.executeQuery()){
				while(rs.next()) {
					SetBean set = new SetBean();
					
					Long id = Long.valueOf(rs.getLong("id"));
					double kg = Double.valueOf(rs.getDouble("kg"));
					int rep = Integer.valueOf(rs.getInt("rep"));
					Long trainingId = Long.valueOf(rs.getLong("trainingId"));
					
					
					set.setId(id);
					set.setKg(kg);
					set.setRep(rep);
					set.setExerciseId(exerciseId);
					set.setTrainingId(trainingId);
					
					sets.add(set);
				}
			}
			
		}catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		
		return sets;
	}
	
	private void create(SetBean form) throws ServletException{
		String[] generatedKeys = new String[] {"id"};
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO sets"
														+ "(rep, kg, exerciseId, trainingId) "
														+ "VALUES (?, ?, ?, ?)", generatedKeys)){
			pstmt.setInt(1, form.getRep());
			pstmt.setDouble(2, form.getKg());
			pstmt.setLong(3, form.getExerciseId());
			pstmt.setLong(4, form.getTrainingId());
			
			pstmt.executeUpdate();
			
			try(ResultSet rs = pstmt.getGeneratedKeys()){
				while(rs.next()) {
					form.setId(rs.getLong(1));
				}
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
	}

}
