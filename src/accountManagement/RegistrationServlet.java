package accountManagement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Enumeration;

import javax.sql.DataSource;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Registatration_Servlet
 */
@WebServlet("/registrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Verbindung zur Datenbank deklarieren
	@Resource(lookup = "java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;


	public RegistrationServlet() {
		super();
	}

	/*
	 * doGet ist hier verboten
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.sendError(HttpServletResponse.SC_FORBIDDEN);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		// Als erstes werden alle vorhergesehenen Paramter extrahiert.

		final Enumeration<String> formInputs = request.getParameterNames();
		final String eMail = request.getParameter("email");
		final String userName = request.getParameter("userName");
		final String firstName = request.getParameter("firstName");
		final String lastName = request.getParameter("lastName");
		final String password = request.getParameter("password");
		boolean errorFound = false;

		
		// Überprüfung ob eines der übergebenen Paramter entweder NULL oder Leer ist.
		
		while (formInputs.hasMoreElements()) {
			String inputName = (String) formInputs.nextElement();
			String inputValue = request.getParameter(inputName);
			if (inputValue.isEmpty() || inputValue == null) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
				errorFound = true;
			}
		}

		if (!errorFound) // debugging
		{
			String[] generatedKeys = new String[] {"id"}; //id generation -> in Datenbank kein "auto_incemrent"?
			
			// Verbindung zur Datenbank aufbauen
			
			try ( 	Connection con = ds.getConnection();
					PreparedStatement pstmt = con.prepareStatement(
							"INSERT INTO users (email,username,firstname, lastname, pwd) VALUES(?,?,?,?,?)")){
				
				
				//Datenbank Operationen
				pstmt.setString(1, eMail);
				pstmt.setString(2, userName);
				pstmt.setString(3, firstName);
				pstmt.setString(4, lastName);
				pstmt.setString(5, password); //TODO: Sollte nicht im Klartext in der Datenbank liegen -> Hashen
				pstmt.executeUpdate();
				
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				throw new ServletException(ex.getMessage());
			}
			
			
			

			
			
			
			
//			System.out.println(" RegistrationServlet: baaasst schooo");
//			System.out.println("RegistrationServlet: " + eMail + " " + userName + " " + firstName + " " + lastName + " "
//					+ password);
//			response.sendRedirect("html/registrationSuccsess.html");

		}

	}
}