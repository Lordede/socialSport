package servlets;

//von Lukas Edm�ller
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.sql.DataSource;

import beans.JoinBean;
import beans.LeaderboardBean;
import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class JoinServlet
 */
@WebServlet("/JoinServlet")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Verbindung zur Datenbank deklarieren
	@Resource(lookup = "java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;

	
	public JoinServlet() {
		super();

	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		/*
		 * UserBean userBean = (UserBean) session.getAttribute("userData");
		 * 
		 * List<JoinBean> joins = read(userBean.getId());
		 * 
		 * session.setAttribute("set", joins);
		 */

		Enumeration<String> params = request.getParameterNames();
		while (params.hasMoreElements()) {
			String paramNames = params.nextElement();
			System.out.println(paramNames);
			switch (paramNames) {
			case "getLeaderboard":
				List<LeaderboardBean> leaderboard = getLeaderboard();
				session.setAttribute("leaderboard", leaderboard); // <- das muss eine List sein
				RequestDispatcher disp = request.getRequestDispatcher("html/leaderboardAusgabe.jsp");
				disp.forward(request, response);

				break;
			}
			// --- Hier weitere Cases einfügen
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	private List<JoinBean> read(Long userId) throws ServletException {

		List<JoinBean> joinBeans = new ArrayList<JoinBean>();

		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(
						"SELECT sets.id, kg, rep, exerciseId, exercises.name AS exerciseName, muscleGroup, exerciseImage, trainingId, trainings.name as trainingName, points, userId\r\n"
								+ "FROM sets\r\n" + "JOIN exercises ON sets.exerciseId = exercises.id\r\n"
								+ "JOIN trainings ON sets.trainingId = trainings.id\r\n" + "WHERE userId = ?")) {

			pstmt.setLong(1, userId);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					JoinBean joinBean = new JoinBean();

					Long id = rs.getLong("id");
					double kg = rs.getDouble("kg");
					int rep = rs.getInt("rep");
					Long exerciseId = rs.getLong("exerciseId");
					String exerciseName = rs.getString("exerciseName");
					String muscleGroup = rs.getString("muscleGroup");
					String exerciseImage = rs.getString("exerciseImage");
					Long trainingId = rs.getLong("trainingId");
					String trainingName = rs.getString("trainingName");
					double points = rs.getDouble("points");

					joinBean.setId(id);
					joinBean.setKg(kg);
					joinBean.setRep(rep);
					joinBean.setExerciseId(exerciseId);
					joinBean.setExerciseName(exerciseName);
					joinBean.setMuscleGroup(muscleGroup);
					joinBean.setExerciseImage(exerciseImage);
					joinBean.setTrainingId(trainingId);
					joinBean.setTrainingName(trainingName);
					joinBean.setPoints(points);
					joinBean.setUserId(userId);

					joinBeans.add(joinBean);
				}
			}

		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}

		return joinBeans;
	}

	//Hubertus Seitz
	private List<LeaderboardBean> getLeaderboard() throws ServletException {
		List<LeaderboardBean> leaderboardBean = new ArrayList<LeaderboardBean>();
		
		

		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(
						"SELECT users.username, users.id, SUM(trainings.points) as points FROM users JOIN trainings ON trainings.userId = users.id GROUP BY ID ORDER BY points DESC;")) {

			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs != null && rs.next()) {
					beans.LeaderboardBean leaderboard = new LeaderboardBean();
					leaderboard.setPoints(rs.getDouble("points"));
					leaderboard.setUsername(rs.getString("username"));
					leaderboard.setId(rs.getLong("id"));
					
					leaderboardBean.add(leaderboard);
					

				}
				
			} catch (Exception ex) {
				throw new ServletException(ex.getMessage());
			}

		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		return leaderboardBean;
		
	}
}
