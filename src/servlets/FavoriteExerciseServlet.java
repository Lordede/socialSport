
/**
 * @author Cem Durmus
 * */
package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import beans.ExerciseBean;
import beans.TrainingBean;
import beans.UserBean;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class FavoriteExerciseServlet
 */
@WebServlet("/FavoriteExerciseServlet")
public class FavoriteExerciseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(lookup = "java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FavoriteExerciseServlet() {
		super();
	}

	/**
	 * Servlet durch Servlet Mapping im Webcontainer angesprochen
	 * 
	 * @param request:  beinhaltet übergebene Parameterwerte
	 * @param response: sendet die Antwort vom Servlet zurück an den Client
	 *                  {@summary: angesprochen aus der dashboard.jsp, zum
	 *                  überprüfen ob Exercise schon ausgewählt wurde}
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute("userData");
		if (request.getParameter("checkExisting") != null) {
			long userId = user.getId();
			String exerciseName = request.getParameter("name");
			Boolean isExisting = checkExistingExercise(userId, exerciseName);

			response.getWriter().write(isExisting.toString());
		} else {
			ArrayList<ExerciseBean> favoriteExercises = getFavoriteExercises(user.getId());
			String json = convertListToJson(favoriteExercises);
			response.getWriter().write(json);
		}

	}

	/**
	 * Servlet durch Servlet Mapping im Webcontainer angesprochen
	 * 
	 * @param request:  beinhaltet übergebene Parameterwerte
	 * @param response: sendet die Antwort vom Servlet zurück an den Client
	 *                  {@summary: angesprochen aus der dashboard.jsp, hinzufügen
	 *                  von Lieblingsübungen}
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		UserBean user = (UserBean) request.getSession().getAttribute("userData");
		setFavoriteExercise(name, user.getId());
		response.getWriter().write("ok");
	}

	/**
	 * Servlet durch Servlet Mapping im Webcontainer angesprochen
	 * 
	 * @param request:  beinhaltet übergebene Parameterwerte
	 * @param response: sendet die Antwort vom Servlet zurück an den Client
	 *                  {@summary: angesprochen aus der accountSetting.jsp, zum
	 *                  entfernen der Lieblingsexercises aus der Datenbank}
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		UserBean user = (UserBean) request.getSession().getAttribute("userData");
		delFavoriteExercise(name, user.getId());
		response.getWriter().write("ok");
	}

	/**
	 * 
	 * @param userId:   zum Ansprechen eines expliziten Users zur Überprüfung ob
	 *                  Exercise schon favorisiert
	 * @param exerciseName: enthält die den Input für den Namen der Exercise
	 *                  {@summary: angesprochen aus der dashboard.jsp, zum
	 *                  überprüfen ob Exercise schon ausgewählt wurde}
	 */
	private boolean checkExistingExercise(Long userId, String exerciseName) throws ServletException {

		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(
						"SELECT * FROM favoriteexercises WHERE exerciseId = (SELECT id FROM exercises WHERE name = ? ) AND userId = ?")) {

			pstmt.setString(1, exerciseName);
			pstmt.setLong(2, userId);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs != null && rs.next()) {
					return true;

				}
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		return false;
	}

	/**
	 * @param id: extrahierte id aus dem Session-Scope, welche den expliziten User
	 *            anspricht {@summary: extraktion aller Liebiningsübungen eines
	 *            spezifischen Users}
	 */
	private ArrayList<ExerciseBean> getFavoriteExercises(Long id) throws ServletException {
		ArrayList<ExerciseBean> exercises = new ArrayList<>();

		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement("SELECT * FROM favoriteexercises WHERE userId=?")) {
			pstmt.setLong(1, id);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs != null && rs.next()) {
					Long exerciseId = rs.getLong("exerciseId");
					try (Connection conEx = ds.getConnection();
							PreparedStatement exercisePstmt = conEx
									.prepareStatement("SELECT * FROM exercises WHERE id=?")) {
						exercisePstmt.setLong(1, exerciseId);

						try (ResultSet res = exercisePstmt.executeQuery()) {
							while (res != null && res.next()) {
								ExerciseBean exercise = new ExerciseBean();
								exercise.setName(res.getString("name"));
								exercise.setId(res.getLong("id"));
								exercise.setMuscleGroup(res.getString("muscleGroup"));
								exercises.add(exercise);
							}
						}
					}
				}
			}

		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		return exercises;
	}

	/**
	 * @param name: extrahierter name aus dem Request-Scope, welcher eine explizite
	 *              Übung anspricht
	 * @param id:   extrahierte id aus dem Session-Scope, welche den expliziten User
	 *              anspricht {@summary: setzen einer Liebiningsübungen in der
	 *              Datenbank}
	 */
	private void setFavoriteExercise(String name, Long id) throws ServletException {
		ExerciseBean exercise = new ExerciseBean();
		System.out.print("istDrin1");
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement("INSERT INTO favoriteexercises (exerciseId, userId) "
						+ "VALUES( " + "(SELECT id From exercises WHERE name = ?), ?" + ")")) {
			pstmt.setString(1, name);
			pstmt.setLong(2, id);
			pstmt.executeUpdate();

		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
	}

	/**
	 * @param name: extrahierte name aus dem Request-Scope, welche die explizite
	 *              Übung anspricht
	 * @param id:   extrahierte id des Users aus dem Session-Scope, zur
	 *              identifikation der Lieblingsübungen eines spezifischen Users
	 *              {@summary: entfernen einer Liebiningsübungen durch den namen der
	 *              Übung angesprochen in der Datenbank}
	 */
	private void delFavoriteExercise(String name, Long id) throws ServletException {
		try (Connection con = ds.getConnection();
				PreparedStatement favCon = con.prepareStatement(
						"DELETE FROM favoriteexercises WHERE exerciseId = (SELECT id From exercises WHERE name = ?) AND userId=?")) {
			favCon.setString(1, name);
			favCon.setLong(2, id);
			favCon.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param ArrayList<ExerciseBean> arr: summe aller Übungen
	 *                                {@summary: verwandelung der Nutzerdaten in
	 *                                einen String um ihn dann als Antwort an den
	 *                                Client weiterzuleiten}
	 * @return jsonString: Rückgabe für den Client
	 */
	private String convertListToJson(ArrayList<ExerciseBean> arr) {
		StringBuilder jsonString = new StringBuilder();
		ArrayList<ExerciseBean> exercises = arr;

		jsonString.append("[");
		for (int i = 0; i < exercises.size(); i++) {
			jsonString.append("{");
			jsonString.append("\"name\":");
			jsonString.append("\"" + exercises.get(i).getName() + "\",");
			jsonString.append("\"muscleGroup\":");
			jsonString.append("\"" + exercises.get(i).getMuscleGroup() + "\",");
			jsonString.append("\"id\":");
			jsonString.append("\"" + exercises.get(i).getId() + "\"");
			if (i + 1 == exercises.size()) {
				jsonString.append("}");
			} else {
				jsonString.append("},");
			}
		}
		jsonString.append("]");

		return jsonString.toString();
	}

}
