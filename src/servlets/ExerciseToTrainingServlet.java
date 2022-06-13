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
import jakarta.servlet.http.Part;

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
		// TODO Auto-generated method stub
	}

}
