package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import beans.ExerciseBean;
import beans.UserBean;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class ExerciseServlet
 */
@WebServlet("/ExerciseServlet")
public class ExerciseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ExerciseServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		UserBean userBean = (UserBean) session.getAttribute("userData");
		//ExerciseBean exerciseBean = read(userBean.getId());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ExerciseBean exerciseBean = new ExerciseBean();
		
		exerciseBean.setName(request.getParameter("name"));
		//exerciseBean.setExerciseImage(Double.parseDouble(request.getParameter("exerciseImage")));
		
		// TODO: Wie Enum übergeben
				
		//create(training);
		
		doGet(request, response);
	}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	

}
