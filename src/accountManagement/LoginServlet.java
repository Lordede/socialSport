package accountManagement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 * 
 * @author Hubertus Seitz
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(lookup = "java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Request über get ist nicht erlaubt!
		response.sendError(HttpServletResponse.SC_FORBIDDEN);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		final String username = request.getParameter("userName");
		String passwortUebergeben = request.getParameter("password");
		final String password = HashPassword.hashPassword(passwortUebergeben); // TODO: wie könnte man das durch
																				// Methodenverkettung in einer Zeile																				// erledigen?
		if (UserExists(username, password)) {

			
			RegistrationFormBean userData = readUserData(username, password); //UserData Anhand der von Username und Passwort auslesen
			
			HttpSession session = request.getSession();
			session.setAttribute("userData", userData); 						//UserData in Session Scope hinterlegen
			
			//forward to HomePage
			RequestDispatcher disp = request.getRequestDispatcher("html/dashboard.html");
			disp.forward(request, response);
		}
		else {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED); //TODO: Fail more userfriendly 
		}

	
	}

	private boolean UserExists(String username, String password) throws ServletException {

		try (Connection con = ds.getConnection(); // Querry erstellen
				PreparedStatement pstmt = con.prepareStatement("SELECT * FROM users WHERE username = ? AND pwd = ?")) {

			pstmt.setString(1, username); //
			pstmt.setString(2, password);

			try (ResultSet rs = pstmt.executeQuery()) { // Result auslesen
				if (rs.next()) {
					return true; 	// Wenn Result nicht leer ist, gibt es die username pwd Kombination und er login
									// ist erfolgreich!
				} else {
					return false;
				}
			}
		}

		catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
	}

	private RegistrationFormBean readUserData(String username, String password) throws ServletException {
		
		RegistrationFormBean userData = new RegistrationFormBean();
		
		try (Connection con = ds.getConnection(); // Querry erstellen
				PreparedStatement pstmt = con.prepareStatement("SELECT * FROM users WHERE username = ? AND pwd = ?")) {

			pstmt.setString(1, username); //
			pstmt.setString(2, password);

			try (ResultSet rs = pstmt.executeQuery()) { // Result auslesen
				if (rs.next()) {
					userData.setId(rs.getInt("id"));
					userData.seteMail(rs.getString("email"));
					userData.setUserName(rs.getString("username"));
					userData.setLastName(rs.getString("lastname"));
					userData.setFirstName(rs.getString("firstname"));
					userData.setPassword(rs.getString("pwd"));
			}
				return userData;
		}
			}

		catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
	}
		
		
	}
