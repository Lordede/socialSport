package accountManagement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Enumeration;

import javax.sql.DataSource;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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


	protected void doGet(HttpServletRequest request, HttpServletResponse response) // doGet ist hier verboten
			throws ServletException, IOException {

		response.sendError(HttpServletResponse.SC_FORBIDDEN);

	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		// Als erstes werden alle vorhergesehenen Paramter extrahiert.

		
		RegistrationFormBean form = new RegistrationFormBean(); //Erstellung der Bean
		HttpSession session = request.getSession();
		
		
		final Enumeration<String> formInputs = request.getParameterNames();
		final String eMail = request.getParameter("email");
		form.seteMail(eMail);
		final String userName = request.getParameter("userName");
		form.setUserName(userName);
		final String firstName = request.getParameter("firstName");
		form.setFirstName(firstName);
		final String lastName = request.getParameter("lastName");
		form.setLastName(lastName);
		final String password = request.getParameter("password");
		//passwort nicht in SessionBean abspeichern?
		boolean errorFound = false;
		
		session.setAttribute("form", form); //Bean in Session abspeichern
		

		
		// �berpr�fung ob eines der �bergebenen Paramter entweder NULL oder Leer ist.
		
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
				response.sendRedirect("html/registrationSuccsess.jsp");
				
			} catch (Exception ex) {
				
				throw new ServletException(ex.getMessage());
//				if (ex.getMessage().contains("Duplicate entry")) { 	// TODO: Fehlerausgeben bei nicht verf�gbarer E-Mail oder Username
//																	// W�re es m�glich bei der Eingabe schon die Verf�gbarkeit zu testen?
//				}
			}
			
			try(Connection con = ds.getConnection();		// Querry erstellen
				PreparedStatement pstmt = con.prepareStatement("SELECT id FROM users WHERE username = ?")
				){

				pstmt.setString(1, form.getUserName());		// id anhand der Email holen
				pstmt.executeUpdate();
				
				try(ResultSet rs = pstmt.executeQuery()){ 	// Result auslesen
					form.setId(rs.getInt("id"));			// id in Bean schreiben
					
				}
				
				
			}
			
			catch (Exception ex) {
				throw new ServletException(ex.getMessage());
			}
			
			
			


		}

	}
}