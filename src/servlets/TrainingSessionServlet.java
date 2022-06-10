package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import beans.TrainingBean;
import beans.TrainingSessionBean;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class TrainingSessionServlet
 */
@WebServlet("/TrainingSessionServlet")
public class TrainingSessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	// Verbindung zur Datenbank deklarieren
		@Resource(lookup = "java:jboss/datasources/MySqlThidbDS")
		private DataSource ds;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TrainingSessionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		TrainingBean training =(TrainingBean) session.getAttribute("training");
		TrainingSessionBean trainingSession = read(training.getId());
		session.setAttribute("trainingSession", trainingSession);
		
		response.getWriter().write(trainingSession.getId().toString());
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		TrainingSessionBean trainingSession = new TrainingSessionBean();
		TrainingBean training =(TrainingBean) session.getAttribute("training");
		trainingSession.setCreationDate(new java.sql.Date(new java.util.Date().getTime()));
		trainingSession.setTrainingId(Long.parseLong(request.getParameter("trainingId")));
		
		create(trainingSession);
		System.out.println("TS Id: " + trainingSession.getId());
		
		if(session.getAttribute("trainingSession") != null) 
		{
			session.removeAttribute("trainingSession");
			System.out.println("ts war vorhanden");
			
		}
		session.setAttribute("trainingSession", trainingSession);
		
		response.getWriter().write(trainingSession.getId().toString());
		
		
		
		
		doGet(request, response);
	}
	
	private void create(TrainingSessionBean form) throws ServletException {
		String[] generatedKeys = new String[] {"id"};
		try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement("INSERT INTO trainingsessions"
															+ "(creationDate, trainingId) "
															+ "VALUES (?, ?)", generatedKeys)){
				pstmt.setDate(1,form.getCreationDate());
				pstmt.setLong(2, form.getTrainingId());
				
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

	private TrainingSessionBean read(Long id) throws ServletException{
		TrainingSessionBean form = new TrainingSessionBean();
		
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM trainingsessions WHERE trainingId = ?")){
			
			pstmt.setLong(1, id);
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs != null && rs.next()) {
					form.setCreationDate(rs.getDate("creationDate"));
					form.setId(rs.getLong("id"));
					form.setTrainingId(rs.getLong("trainingId"));
				}
			}
			
		}catch(Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		return form;
	}
	

}
