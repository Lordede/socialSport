package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import beans.TrainingsplanBean;
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
 * Servlet implementation class TrainingServlet
 */
@WebServlet("/TrainingPlanServlet")
public class TrainingPlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// Verbindung zur Datenbank deklarieren
	@Resource(lookup = "java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TrainingPlanServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
    	
    	//Long id = Long.parseLong(request.getParameter("id"));
		HttpSession session = request.getSession();
		UserBean userBean = (UserBean) session.getAttribute("userData");
    	
		List<TrainingsplanBean> trainingplans = search(userBean.getId());
		
		//TODO: read muss über id erfolgen
		//TODO: search muss über exerciseId erfolgen
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		TrainingsplanBean trainingPlan = new TrainingsplanBean();
		//trainingPlan.setId(Long.parseLong(request.getParameter("id")));
		trainingPlan.setName(request.getParameter("name"));
		trainingPlan.setUserId(Long.parseLong(request.getParameter("userId")));
		
		
		// TODO: DB anlegen und anschließend mit Schleife in Funktion Trainings anheften
		//trainingPlan.setTrainingList();
		
		// WIE MIT LISTE VORGEHEN???		
		create(trainingPlan);
		
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		TrainingsplanBean trainingPlan = new TrainingsplanBean();
		trainingPlan.setId(Long.parseLong(request.getParameter("id")));
		trainingPlan.setName(request.getParameter("name"));
		//trainingPlan.setTrainingList();
		
		
		
		update(trainingPlan);
		
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Long id = Long.parseLong(request.getParameter("id")) ;
		delete(id);
	}
	
	private void delete(Long id) throws ServletException{
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement("DELETE FROM trainingsplan WHERE id = ?")){
			pstmt.setLong(1, id);
			pstmt.executeUpdate();
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
	}
	
	private void update(TrainingsplanBean form) throws ServletException{
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"UPDATE trainingsplan " 
					+ "SET name = ?"
					+ "WHERE id = ?")){ 
			pstmt.setString(1, form.getName());
			//pstmt.setString(2, form.getTrainingList());
			pstmt.setLong(3, form.getId());
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
	}
	
	private TrainingsplanBean read(Long id) throws ServletException{
		TrainingsplanBean form = new TrainingsplanBean();
		
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM trainingsplan WHERE userId = ?")){
			
			pstmt.setLong(1, id);
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs != null && rs.next()) {
					form.setName(rs.getString("name"));
					form.setId(rs.getLong("id"));
					form.setUserId(rs.getLong("userId"));
					//form.setPoints(rs.getDouble("points"));
					
				}
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		return form;
	}
	
	private List<TrainingsplanBean> search(Long userId) throws ServletException{
		List<TrainingsplanBean> trainingplans = new ArrayList<TrainingsplanBean>();
		
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM trainingsplan WHERE userId = ?")){
			
			pstmt.setLong(0, userId);
			try(ResultSet rs = pstmt.executeQuery()){
				while(rs.next()) {
					TrainingsplanBean trainingsplan = new TrainingsplanBean();
					
					Long id = Long.valueOf(rs.getLong("id"));
					String name = rs.getString("name");
					
					trainingsplan.setId(id);
					trainingsplan.setName(name);
					trainingsplan.setUserId(userId);
					
					trainingplans.add(trainingsplan);
				}
			}
			
		}catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		
		return trainingplans;
	}
	
	private void create(TrainingsplanBean form) throws ServletException{
		String[] generatedKeys = new String[] {"id"};
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO trainingsplan"
														+ "(name, userId) "
														+ "VALUES (?, ?)", generatedKeys)){
			pstmt.setString(1, form.getName());
			pstmt.setLong(2, form.getUserId());
			//pstmt.setDouble(2, 0);
			
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
