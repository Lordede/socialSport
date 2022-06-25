package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.sql.DataSource;

import beans.ExerciseBean;
import beans.ExerciseToTrainingBean;
import beans.UserBean;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

//Cem Durmus
/**
 * Servlet implementation class ExerciseServlet
 */
@WebServlet("/ExerciseServlet")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 10
		* 10, location = "/tmp", fileSizeThreshold = 1024 * 1024)
public class ExerciseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(lookup = "java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExerciseServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		int sessionCounter = 0;
		// UserBean userBean = (UserBean) session.getAttribute("userData");
		Enumeration<String> params = request.getParameterNames();
		while (params.hasMoreElements()) {
			String paramNames = params.nextElement();
			System.out.println(paramNames);
			switch (paramNames) {
			case "addButton":
				ArrayList<ExerciseBean> exercises = getListOfExercises();
				String jsonExercises = convertListToJson(getListOfExercises());
				response.getWriter().write(jsonExercises);
				System.out.println(jsonExercises);
				break;
			case "exerciseInputField":
				ArrayList<ExerciseBean> exercisesSearched = search(request.getParameter("exerciseInputField"));
				String jsonSearch = convertListToJson(exercisesSearched);
				response.getWriter().write(jsonSearch);

				break;
			case "selectedExercise":
				ExerciseBean exercise = (ExerciseBean) initializeExercise(
						Long.parseLong(request.getParameter("selectedExercise")), response);
				if (sessionCounter > 0) {
					session.removeAttribute("exercise");
				}
				session.setAttribute("exercise", exercise);
				sessionCounter++;
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		ExerciseBean exerciseBean = new ExerciseBean();
		exerciseBean.setName(request.getParameter("exerciseName"));
		exerciseBean.setMuscleGroup(request.getParameter("muscleGroup"));
		HttpSession session = request.getSession();
		Part filepart = request.getPart("image");
		exerciseBean.setExerciseImage(filepart.getSubmittedFileName());
		System.out.println(filepart.getSubmittedFileName().toString());
		createExcercise(exerciseBean, filepart);
		session.setAttribute("exercise", exerciseBean);
		response.sendRedirect("html/success.jsp");

	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserBean userBean = (UserBean) session.getAttribute("userData");
		ExerciseBean exercise = initializeExercise(userBean.getId(), response);
		updateExercise(exercise);
		session.setAttribute("excercise", exercise);
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		ExerciseBean exerciseBean = (ExerciseBean) session.getAttribute("exercise");
		deleteExcercise(exerciseBean.getId());
	}

	private void deleteExcercise(Long id) throws ServletException {
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement("DELETE FROM exercise WHERE id = ?")) {
			pstmt.setLong(1, id);
			pstmt.executeUpdate();
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
	}

	private void updateExercise(ExerciseBean exercise) throws ServletException {

		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con
						.prepareStatement("UPDATE exercise " + "SET name = ?, muscleGroup = ?" + "WHERE id = ?")) {

			pstmt.setString(1, exercise.getName());
			pstmt.setString(2, exercise.getMuscleGroup());
			pstmt.setLong(3, exercise.getId());
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
	}

	private ArrayList<ExerciseBean> getListOfExercises() throws ServletException {
		ArrayList<ExerciseBean> exercises = new ArrayList<>();

		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement("SELECT * FROM exercises")) {

			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs != null && rs.next()) {
					ExerciseBean exercise = new ExerciseBean();
					exercise.setId(rs.getLong("id"));
					exercise.setName(rs.getString("name"));
					exercise.setExerciseImage(rs.getString("filename"));
					exercise.setMuscleGroup(rs.getString("muscleGroup"));
					exercises.add(exercise);
				}
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		return exercises;
	}

	private ExerciseBean initializeExercise(Long id, HttpServletResponse response) throws ServletException {
		ExerciseBean exercise = new ExerciseBean();

		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement("SELECT * FROM exercises WHERE Id = ?")) {

			pstmt.setLong(1, id);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs != null && rs.next()) {
					exercise.setName(rs.getString("name"));
					exercise.setMuscleGroup(rs.getString("muscleGroup"));
				}
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		return exercise;
	}

	private void createExcercise(ExerciseBean exercise, Part filepart) throws ServletException {
		String[] generatedKeys = new String[] { "id" };

		try (Connection con = ds.getConnection();
				PreparedStatement stmtExercise = con.prepareStatement("INSERT INTO exercises"
						+ "(name, muscleGroup, filename, exerciseImage)" + "VALUES (?, ?, ?, ?)", generatedKeys)) {
			ArrayList<ExerciseBean> exercises = getListOfExercises();
			for (ExerciseBean checkExer : exercises) {
				if (checkExer.getName().toLowerCase().equals(exercise.getName().toLowerCase()))
					throw new ServletException("Übung exsistiert bereits");
				String[] splittedName = exercise.getName().split(" ");
				String result = "";
				for (String s : splittedName) {
					result += s;
				}
				if (result.toLowerCase().equals(checkExer.getName().toLowerCase()))
					throw new ServletException("Übung exsistiert bereits");
			}
			stmtExercise.setString(1, exercise.getName());
			stmtExercise.setString(2, exercise.getMuscleGroup());
			stmtExercise.setString(3, exercise.getExerciseImage());
			stmtExercise.setBinaryStream(4, filepart.getInputStream());
			stmtExercise.executeUpdate();

			try (ResultSet rs = stmtExercise.getGeneratedKeys()) {
				while (rs.next()) {
					exercise.setId(rs.getLong(1));
				}
			}

		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
	}

	private ArrayList<ExerciseBean> search(String exerciseName) throws ServletException {
		exerciseName = (exerciseName == null || exerciseName == "") ? "%" : "%" + exerciseName + "%";
		ArrayList<ExerciseBean> exercises = new ArrayList<>();

		try (Connection con = ds.getConnection();
				PreparedStatement search = con.prepareStatement("SELECT * FROM exercises WHERE name LIKE ?")) {
			search.setString(1, exerciseName);
			try (ResultSet result = search.executeQuery()) {
				while (result.next()) {
					ExerciseBean exercise = new ExerciseBean();
					exercise.setId(result.getLong("id"));
					exercise.setName(result.getString("name"));
					exercise.setMuscleGroup(result.getString("muscleGroup"));
					exercises.add(exercise);
				}
				return exercises;
			}

		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
	}

	protected ArrayList<ExerciseBean> getExercisesById(ArrayList<ExerciseToTrainingBean> list, DataSource dsParameter)
			throws ServletException {
		ArrayList<ExerciseBean> exercises = new ArrayList<>();

		for (ExerciseToTrainingBean exerciseToTraining : list) {
			try (Connection con = dsParameter.getConnection();
					PreparedStatement pstmt = con.prepareStatement("SELECT * FROM exercises WHERE id = ?")) {
				pstmt.setLong(1, exerciseToTraining.getExerciseId());
				try (ResultSet result = pstmt.executeQuery()) {
					while (result.next()) {
						ExerciseBean exercise = new ExerciseBean();
						exercise.setId(result.getLong("id"));
						exercise.setName(result.getString("name"));
						exercise.setMuscleGroup(result.getString("muscleGroup"));
						exercises.add(exercise);
					}
				}

			} catch (Exception ex) {
				throw new ServletException(ex.getMessage());
			}
		}
		return exercises;

	}

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
