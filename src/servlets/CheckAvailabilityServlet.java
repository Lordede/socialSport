package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import beans.AvailabilityBean;
import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckAvailabilityServlet
 */
@WebServlet("/CheckAvailabilityServlet")
public class CheckAvailabilityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Verbindung zur Datenbank deklarieren
	@Resource(lookup = "java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;

	public CheckAvailabilityServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		final String email = request.getParameter("email");
		final String username = request.getParameter("username");
		
		AvailabilityBean availability = new AvailabilityBean();

		if (email != null) {			
			if(getEmailAvailability(email)) {				// true == email available
				availability.setEmail(true);
				request.setAttribute("availability", availability);
				
				
			}
			else {
				request.setAttribute("availability", availability);
				
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("html/availabilityAusgabe.jsp"); //hier liegt wahrscheinlich der fehler
			dispatcher.forward(request, response);
			return;
		}
		if (username != null || username.length() > 0) {
			if(getUsernameAvailability(username)) {			// true == username available
				availability.setUsername(true);
				request.setAttribute("availability", availability);
				RequestDispatcher dispatcher = request.getRequestDispatcher("html/availabilityAusgabe.jsp");
				dispatcher.forward(request, response);
			}
			else {
				request.setAttribute("availability", availability);
			}
			return;
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public boolean getEmailAvailability(String email) throws ServletException {

		try (Connection con = ds.getConnection(); // Querry erstellen
				PreparedStatement pstmt = con.prepareStatement("SELECT email FROM users WHERE email = ?")) {

			pstmt.setString(1, email);

			try (ResultSet rs = pstmt.executeQuery()) { // Result auslesen
				if (rs.next()) { // Anscheinend ganz wichtig
					return false;
				}
				return true;
			}
		}

		catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
	}

	public boolean getUsernameAvailability(String username) throws ServletException {

		try (Connection con = ds.getConnection(); // Querry erstellen
				PreparedStatement pstmt = con.prepareStatement("SELECT username FROM users WHERE username = ?")) {

			pstmt.setString(1, username);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return false;
				}
				return true;
			}
		}

		catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
	}
}
