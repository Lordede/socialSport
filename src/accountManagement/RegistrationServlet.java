package accountManagement;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

		RegistrationFormBean form = new RegistrationFormBean(); // Erstellung der Bean
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

		final String password = hashPassword(request.getParameter("password")); // Passwort als hash abspeichern
		// final String password = request.getParameter("password"); //Passwort im
		// Klartext speichern
		// passwort nicht in SessionBean abspeichern?
		boolean errorFound = false;

		session.setAttribute("form", form); // Bean in Session abspeichern

		// Überprüfung ob eines der übergebenen Paramter entweder NULL oder Leer ist.

		while (formInputs.hasMoreElements()) {
			String inputName = (String) formInputs.nextElement();
			String inputValue = request.getParameter(inputName);
			if (inputValue.isEmpty() || inputValue == null) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
				errorFound = true;
			}
		}

		if (!errorFound)
		{

			createNewUser(eMail, userName, firstName, lastName, password);
			form.setId(getUserId(form.getUserName()));
			response.sendRedirect("html/registrationSuccsess.jsp");
			
		}

	}

	public String hashPassword(String passwordToHash) { // Funktion zum hashen von Passwörtern
		String generatedPassword = null;

		try {
			// Create MessageDigest instance for MD5
			MessageDigest md = MessageDigest.getInstance("MD5");

			// Add password bytes to digest
			md.update(passwordToHash.getBytes());

			// Get the hash's bytes
			byte[] bytes = md.digest();

			// This bytes[] has bytes in decimal format. Convert it to hexadecimal format
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}

			// Get complete hashed password in hex format
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	}

	public int getUserId(String username) throws ServletException {

		try (Connection con = ds.getConnection(); // Querry erstellen
				PreparedStatement pstmt = con.prepareStatement("SELECT id FROM users WHERE username = ?")) {

			pstmt.setString(1, username); // id anhand des username holen
			
			int id = 0;
			
			try (ResultSet rs = pstmt.executeQuery()) { // Result auslesen
				if (rs.next()) {
				id = (rs.getInt("id")); // id in Bean schreiben
				System.out.println(id);
				}
				return id;
			}
		}

		catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
	}
	
	public void createNewUser(String eMail, String userName, String firstName, String lastName, String password ) throws ServletException {
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(
						"INSERT INTO users (email,username,firstname, lastname, pwd) VALUES(?,?,?,?,?)")) {

			// Datenbank Operationen
			pstmt.setString(1, eMail);
			pstmt.setString(2, userName);
			pstmt.setString(3, firstName);
			pstmt.setString(4, lastName);
			pstmt.setString(5, password); // TODO: Sollte nicht im Klartext in der Datenbank liegen -> Hashen
			pstmt.executeUpdate();
			

		} catch (Exception ex) {

			throw new ServletException(ex.getMessage());
//			if (ex.getMessage().contains("Duplicate entry")) { 	// TODO: Fehlerausgeben bei nicht verfügbarer E-Mail oder Username
//																// Wäre es möglich bei der Eingabe schon die Verfügbarkeit zu testen?
//			}
		}
		
		
	}
	
	
}