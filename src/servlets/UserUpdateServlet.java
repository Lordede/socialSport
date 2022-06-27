package servlets;

import java.io.IOException;
import java.lang.module.ResolutionException;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.sql.DataSource;

import beans.TrainingBean;
import beans.UserBean;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.SessionScoped;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utilities.HashPassword;

//Cem Durmus
/**
 * Servlet implementation class User_UpdateServlet
 */
@WebServlet("/UserUpdateServlet")
@SessionScoped
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(lookup = "java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserUpdateServlet() {
		super();

	}

	/**
	 * Servlet  durch Servlet Mapping im Webcontainer angesprochen
	 * @param request: beinhaltet übergebene Parameterwerte
	 * @param response: sendet die Antwort vom Servlet zurück an den Client
	 * {@summary: Bearbeitet Nutzeranfragen einerseits von accountSetting.jsp & administrationsInterface,
	 *  diese Methode dient dabei aktuelle Werte des users an den Server zurückzugeben, oder auch den
	 *  Benutzer zu löschen (HTTP-Methode DELETE hat denselben Funktionsaufruf, kann aber nicht aus HTML
	 *  vom Servlet angesprochen werden)}
	 * */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Enumeration<String> paramNames = request.getParameterNames();
		HttpSession session = request.getSession();
		while (paramNames.hasMoreElements()) {
			String param = paramNames.nextElement();
			switch (param) {
			case "getUsers":
				ArrayList<UserBean> users = listOfAllUsers();
				String usersJson = convertListToJson(users);
				response.getWriter().write(usersJson);
				break;
			case "getSpecificUser":
				UserBean user = getUser(Long.parseLong(request.getParameter("getSpecificUser")));
				break;
			case "searchUser":
				ArrayList<UserBean> usersSearched = search(request.getParameter("searchUser"));
				String userSearchJson = convertListToJson(usersSearched);
				response.getWriter().write(userSearchJson);
				break;
			case "deleteUser":
				UserBean userId = (UserBean) session.getAttribute("userData");
				deleteUser(userId.getId());
				response.sendRedirect("html/accountDeletion.jsp");
				break;
			}
		}
	}

	/**
	 * Servlet  durch Servlet Mapping im Webcontainer angesprochen
	 * @param request: beinhaltet übergebene Parameterwerte
	 * @param response: sendet die Antwort vom Servlet zurück an den Client
	 * {@summary: Bearbeitet Nutzeranfragen von accountSetting.jsp, welche durch den Nutzer in den Forms 
	 * gestellt werden können und dem zweck dienen die Nutzerdaten zu bearbeiten}
	 * */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Enumeration<String> buttonNames = request.getParameterNames();
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute("userData");
		while (buttonNames.hasMoreElements()) {
			String buttonName = buttonNames.nextElement();

			switch (buttonName) {
			case "changeMail":
				updateEmail(user, request.getParameter("changeMail"));
				user = getUser(user.getId());
				session.setAttribute("userData", user);
				break;
			case "changeFirstName":
				updateFirstName(user, request.getParameter("changeFirstName"));
				user = getUser(user.getId());
				session.setAttribute("userData", user);
				break;
			case "changeLastName": // TODO : namen bitte zum laufen kriegen du kek
				updateLastName(user, request.getParameter("changeLastName"));
				user = getUser(user.getId());
				session.setAttribute("userData", user);
				break;
			case "changeUsername":
				updateUsername(user, request.getParameter("changeUsername"));
				user = getUser(user.getId());
				session.setAttribute("userData", user);
				break;
			case "password":
				String s = request.getParameter("password");
				System.out.println(s);
				updatePassword(user, request.getParameter("password"));
				user = getUser(user.getId());
				session.setAttribute("userData", user);
				break;
			case "setAdmin":
				setAdmin(Long.parseLong(request.getParameter("setAdmin")));
				user = getUser(user.getId());
				session.setAttribute("userData", user);
				response.getWriter().write("ok");
				break;
			default:
				return;
			}
		}
		response.sendRedirect("html/accountSetting.jsp");
	}

	/**
	 * Servlet  durch Servlet Mapping im Webcontainer angesprochen
	 * @param request: beinhaltet übergebene Parameterwerte
	 * @param response: sendet die Antwort vom Servlet zurück an den Client
	 * {@summary: Bearbeitet Nutzeranfragen von administrationsInteface.jsp, welche durch einen 
	 * Admin einen Benutzer löschen kann.}
	 * */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		deleteUser(Long.parseLong(request.getParameter("id")));
		response.getWriter().write("ok");
	}
	
	/**
	 * @param user: um extrahierte id aus dem Session-Scope zu verwenden welche den expliziten User anspricht
	 * @param eMail: aus dem Request-Scope übergebene Parameter zur Aktualisierung in der Datenbank
	 * {@summary: Änderung der E-mail durch den Nutzer}
	 * */
	public void updateEmail(UserBean user, String eMail) throws ServletException {
		try (Connection con = ds.getConnection();
				PreparedStatement statementName = con
						.prepareStatement("UPDATE users " + "SET email = ? " + "WHERE id = ?")) {
			user.getId();
			statementName.setString(1, eMail);
			statementName.setLong(2, user.getId());
			statementName.executeUpdate();

		} catch (Exception exception) {
			throw new ServletException(exception.getMessage());
		}
	}

	/**
	 * @param user: um extrahierte id aus dem Session-Scope zu verwenden welche den expliziten User anspricht
	 * @param newName: aus dem Request-Scope übergebene Parameter zur Aktualisierung in der Datenbank
	 * {@summary: Änderung des Benutuzernamens durch den Nutzer}
	 * */
	public void updateUsername(UserBean user, String newName) throws ServletException {
		try (Connection con = ds.getConnection();
				PreparedStatement statementName = con
						.prepareStatement("UPDATE users " + "SET username = ? " + "WHERE id = ?")) {
			statementName.setString(1, newName);
			statementName.setLong(2, user.getId());
			statementName.executeUpdate();
		} catch (Exception exception) {
			throw new ServletException(exception.getMessage());
		}
	}

	/**
	 * @param id: extrahierte id aus dem Request-Scope welche den expliziten User anspricht
	 * {@summary: Änderung der Nutzer-Status durch den Admin}
	 * */
	public void setAdmin(Long id) throws ServletException {
		try (Connection con = ds.getConnection();
				PreparedStatement statementName = con
						.prepareStatement("UPDATE `users` SET `isAdmin` = '1' WHERE `users`.`id` = ?");) {
			// statementName.setBoolean(1,true);
			statementName.setLong(1, id);
			statementName.executeUpdate();
		} catch (Exception exception) {
			throw new ServletException(exception.getMessage());
		}
	}

	/**
	 * @param user: um extrahierte id aus dem Session-Scope zu verwenden welche den expliziten User anspricht
	 * @param firstName: aus dem Request-Scope übergebene Parameter zur Aktualisierung in der Datenbank
	 * {@summary: Änderung des Vornamen durch den Nutzer}
	 * */
	private void updateFirstName(UserBean user, String firstName) throws ServletException {
		try (Connection con = ds.getConnection();
				PreparedStatement statementNames = con
						.prepareStatement("UPDATE users " + "SET firstname = ? " + "WHERE id = ?")) {
			statementNames.setString(1, firstName);
			statementNames.setLong(2, user.getId());
			statementNames.executeUpdate();
		} catch (Exception exception) {
			throw new ServletException(exception.getMessage());
		}
	}

	/**
	 * @param user: um extrahierte id aus dem Session-Scope zu verwenden welche den expliziten User anspricht
	 * @param lastName: aus dem Request-Scope übergebene Parameter zur Aktualisierung in der Datenbank
	 * {@summary: Änderung des Nachnamen durch den Nutzer}
	 * */
	private void updateLastName(UserBean user, String lastName) throws ServletException {
		try (Connection con = ds.getConnection();
				PreparedStatement statementNames = con
						.prepareStatement("UPDATE users " + "SET lastname = ? " + "WHERE id = ?")) {
			statementNames.setString(1, lastName);
			statementNames.setLong(2, user.getId());
			statementNames.executeUpdate();
		} catch (Exception exception) {
			throw new ServletException(exception.getMessage());
		}
	}

	/**
	 * @param user: um extrahierte id aus dem Session-Scope zu verwenden welche den expliziten User anspricht
	 * @param firstName: aus dem Request-Scope übergebene Parameter zur Aktualisierung in der Datenbank
	 * {@summary: Änderung des Passwortes mit zusätzlichem Hashen des neuen Passwortes durch den Nutzer}
	 * */
	private void updatePassword(UserBean user, String password) throws ServletException {
		try (Connection conDs = ds.getConnection();
				PreparedStatement statementEmail = conDs
						.prepareStatement("UPDATE users " + "SET pwd = ? " + "WHERE id = ?")) {
			user.setPassword(HashPassword.hashPassword(password));
			statementEmail.setString(1, user.getPassword());// hash methode
			statementEmail.setLong(2, user.getId());
			statementEmail.executeUpdate();
		} catch (Exception exception) {
			throw new ServletException(exception.getMessage());
		}
	}

	/**
	 * @param id: extrahierte id aus dem Request-Scope welche den expliziten User anspricht
	 * {@summary: Löschen des Nutzer mit allen dazu verbundenen Daten aus der Datenbank,
	 *  durch den Nutzer selbst oder den Admin}
	 * */
	private void deleteUser(Long id) throws ServletException {
		try (Connection bondTrainings = ds.getConnection();
				PreparedStatement delSetofTraining = bondTrainings.prepareStatement(
						"DELETE FROM sets " + "WHERE trainingId = " + "(SELECT id FROM trainings WHERE userId = ?)");
				PreparedStatement delExcercisesToTraning = bondTrainings
						.prepareStatement("DELETE FROM exercisestotrainings " + "WHERE trainingId = "
								+ "(SELECT id FROM trainings WHERE userId = ?)");
				PreparedStatement delTrainingsSessions = bondTrainings.prepareStatement("DELETE FROM trainingsessions "
						+ "WHERE trainingId = " + "(SELECT id FROM trainings WHERE userId = ?)")) {
			delSetofTraining.setLong(1, id);
			delTrainingsSessions.setLong(1, id);
			delExcercisesToTraning.setLong(1, id);
			delSetofTraining.executeUpdate();
			delTrainingsSessions.executeUpdate();
			delExcercisesToTraning.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement("DELETE FROM favoriteexercises WHERE userId=?");
				PreparedStatement delTraining = con.prepareStatement("DELETE FROM trainings WHERE userId=?");
				PreparedStatement delUser = con.prepareStatement("DELETE FROM users WHERE id=?")) {
			pstmt.setLong(1, id);
			delTraining.setLong(1, id);
			delUser.setLong(1, id);
			pstmt.executeUpdate();

			delTraining.executeUpdate();
			delUser.executeUpdate();
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
	}

	/**
	 * @param id: extrahierte id aus dem Request-Scope welche den expliziten User anspricht
	 * {@summary: Auswahl eines Spezifischen Users}
	 * */
	private UserBean getUser(Long id) throws ServletException {
		UserBean user = new UserBean();
		try (Connection conDs = ds.getConnection();
				PreparedStatement statement = conDs.prepareStatement("SELECT * FROM users WHERE id = ?")) {
			statement.setLong(1, id);
			try (ResultSet rs = statement.executeQuery()) {
				if (rs != null && rs.next()) {
					user.setUsername(rs.getString("username"));
					user.setCreationDate(rs.getDate("creationDate"));
					user.setFirstName(rs.getString("firstname"));
					user.setLastName(rs.getString("lastname"));
					user.seteMail(rs.getString("eMail"));
					user.setId(rs.getLong("id"));
					user.setIsAdmin(rs.getBoolean("isAdmin"));
				}
			}
			return user;
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
	}

	/*
	 * {@summary: Extraktion aller Nutzer zur weiteren Verarbeitung}
	 * */
	private ArrayList<UserBean> listOfAllUsers() throws ServletException {
		ArrayList<UserBean> userList = new ArrayList<>();
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement("SELECT * FROM users")) {
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					UserBean user = new UserBean();
					user.setId(rs.getLong("id"));
					user.setUsername(rs.getString("username"));
					user.setFirstName(rs.getString("firstname"));
					user.seteMail(rs.getString("eMail"));
					user.setLastName(rs.getString("lastname"));
					user.setIsAdmin(rs.getBoolean("isAdmin"));
					userList.add(user);
				}
			}
			return userList;
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
	}

	/**
	 * @param username: extrahierter username aus dem Request-Scope
	 * {@summary: suche eines spezifischen users aus der Datenbank}
	 * */
	private ArrayList<UserBean> search(String username) throws ServletException {
		username = (username == null || username == "") ? "%" : "%" + username + "%";
		ArrayList<UserBean> users = new ArrayList<>();

		try (Connection con = ds.getConnection();
				PreparedStatement search = con.prepareStatement("SELECT * FROM users WHERE username LIKE ?")) {
			search.setString(1, username);
			try (ResultSet result = search.executeQuery()) {
				while (result.next()) {
					UserBean user = new UserBean();
					user.setId(result.getLong("id"));
					user.setUsername(result.getString("username"));
					user.setFirstName(result.getString("firstname"));
					user.seteMail(result.getString("eMail"));
					user.setLastName(result.getString("lastname"));
					user.setIsAdmin(result.getBoolean("isAdmin"));
					users.add(user);

				}
				return users;
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
	}

	/**
	 * @param ArrayList<UserBean> arr: summe aller user
	 * {@summary: verwandelung der Nutzerdaten in einen String um ihn dann als Antwort an den 
	 * Client weiterzuleiten}
	 * */
	private String convertListToJson(ArrayList<UserBean> arr) {
		StringBuilder jsonString = new StringBuilder();
		ArrayList<UserBean> users = arr;

		jsonString.append("[");
		for (int i = 0; i < users.size(); i++) {
			jsonString.append("{");
			jsonString.append("\"vorname\":");
			jsonString.append("\"" + users.get(i).getFirstName() + "\",");
			jsonString.append("\"nachname\":");
			jsonString.append("\"" + users.get(i).getLastName() + "\",");
			jsonString.append("\"eMail\":");
			jsonString.append("\"" + users.get(i).geteMail() + "\",");
			jsonString.append("\"benutzername\":");
			jsonString.append("\"" + users.get(i).getUsername() + "\",");
			jsonString.append("\"id\":");
			jsonString.append("\"" + users.get(i).getId() + "\"");
			if (i + 1 == users.size()) {
				jsonString.append("}");
			} else {
				jsonString.append("},");
			}
		}
		jsonString.append("]");

		return jsonString.toString();
	}
}
