package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Enumeration;
import java.util.List;

import javax.sql.DataSource;

import beans.LeaderboardBean;
import beans.UserBean;
import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utilities.HashPassword;

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

		response.sendError(HttpServletResponse.SC_FORBIDDEN);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		final String username = request.getParameter("userName");
		final String password = HashPassword.hashPassword(request.getParameter("password")); // erledigen?
		if (UserExists(username, password)) {

			UserBean userData = readUserData(username, password); // UserData Anhand der von Username und Passwort
																	// auslesen

			HttpSession session = request.getSession();
			session.setAttribute("userData", userData); // UserData in Session Scope hinterlegen

			// forward to HomePage
			RequestDispatcher disp = request.getRequestDispatcher("html/dashboard.jsp");
			disp.forward(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED); // TODO: Fail more userfriendly
		}

	}

	// Logout
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		// Eigentlich unnötig aber um flexibel zu bleiben...
		Enumeration<String> params = request.getParameterNames();
		while (params.hasMoreElements()) {
			String paramNames = params.nextElement();
			System.out.println(paramNames);

			switch (paramNames) {
			case "logout":
				session.invalidate(); // Logout
				break;
			}
		}

		//Nicht notwendig bei einem Ajax aufruf
		//RequestDispatcher disp = request.getRequestDispatcher("html/login.html");
		//disp.forward(request, response);

	}

	private boolean UserExists(String username, String password) throws ServletException {

		try (Connection con = ds.getConnection(); // Querry erstellen
				PreparedStatement pstmt = con.prepareStatement("SELECT * FROM users WHERE username = ? AND pwd = ?")) {

			pstmt.setString(1, username); //
			pstmt.setString(2, password);

			try (ResultSet rs = pstmt.executeQuery()) { // Result auslesen
				return rs.next();
			}
		}

		catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
	}

	private UserBean readUserData(String username, String password) throws ServletException {

		UserBean userData = new UserBean();

		try (Connection con = ds.getConnection(); // Querry erstellen
				PreparedStatement pstmt = con.prepareStatement("SELECT * FROM users WHERE username = ? AND pwd = ?")) {

			pstmt.setString(1, username);
			pstmt.setString(2, password);

			try (ResultSet rs = pstmt.executeQuery()) { // Result auslesen
				if (rs.next()) {
					userData.setId(rs.getLong("id"));
					userData.seteMail(rs.getString("email"));
					userData.setUsername(rs.getString("username"));
					userData.setLastName(rs.getString("lastname"));
					userData.setFirstName(rs.getString("firstname"));
					System.out.print(userData.getFirstName());
					userData.setPassword(rs.getString("pwd"));
					userData.setAdmin(rs.getBoolean("isAdmin"));
				}
				return userData;
			}
		}

		catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
	}

}
