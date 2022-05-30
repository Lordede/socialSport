package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import beans.ExerciseBean;
import beans.SetBean;
import beans.UserBean;
import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
//Cem Durmus
/**
 * Servlet implementation class ExerciseServlet
 */
@WebServlet("/ExerciseServlet")
public class ExerciseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(lookup = "java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExerciseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		UserBean userBean = (UserBean) session.getAttribute("userData");
		
		ExerciseBean exerciseBean = initializeExercise(userBean.getId());
		session.setAttribute("exercise", exerciseBean);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		RequestDispatcher rd = request.getRequestDispatcher("SetServlet");
//		rd.forward(request, response);
		ExerciseBean exerciseBean = new ExerciseBean();
		exerciseBean.setName(request.getParameter("exerciseName"));
		exerciseBean.setMuscleGroup(request.getParameter("muscleGroup"));
		HttpSession session = request.getSession();
		UserBean userBean = (UserBean) session.getAttribute("userData");
		createExcercise(exerciseBean);
		session.setAttribute("excercise", exerciseBean);
		//create(training);
		
		//doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ExerciseBean exercise = new ExerciseBean();
		HttpSession session = request.getSession();
		UserBean userBean = (UserBean) session.getAttribute("userData");
		ExerciseBean exercise = initializeExercise(userBean.getId());
		updateExercise(exercise);
		session.setAttribute("excercise", exercise);
		//update()
	}
	
	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ExerciseBean exerciseBean = (ExerciseBean) session.getAttribute("exercise");
		deleteExcercise(exerciseBean.getId());
	}
	
	

	private void deleteExcercise(Long id) throws ServletException{
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement("DELETE FROM exercise WHERE id = ?")){
			pstmt.setLong(1, id);
			pstmt.executeUpdate();
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
	}
	
	private void updateExercise(ExerciseBean exercise) throws ServletException{
		
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"UPDATE exercise " 
					+ "SET name = ?, muscleGroup = ?"
					+ "WHERE id = ?")){
			
			pstmt.setString(1, exercise.getName());
			//pstmt.setDouble(2, exercise.getPoint());
			pstmt.setString(2, exercise.getMuscleGroup());
			pstmt.setLong(3, exercise.getId());
			/*
			 * List<SetBean> sets = new LinkedList<>(); try (Connection setCon =
			 * ds.getConnection(); PreparedStatement setStatement =
			 * con.prepareStatement("SELECT * FROM set WHERE exerciseId=?")) { try(ResultSet
			 * result = setStatement.executeQuery()) { while(result != null &&
			 * result.next()) { SetBean setBean = new SetBean();
			 * setBean.setRep(result.getInt("rep")); setBean.setKg(result.getDouble("kg"));
			 * sets.add(setBean); } } }
			 */
			/*while(!finished) 
			{
				rd.include(request, response);
				SetBean set = Set.getSet();
				sets.add(set);
			}
			exerciseBean.setSets(sets);*/
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
	}
	
	private LinkedList<ExerciseBean> getListOfExercises() throws ServletException
	{
		LinkedList<ExerciseBean> exercises = new LinkedList<>();
		
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM exercises")){
			
			try(ResultSet rs = pstmt.executeQuery())
			{
				while(rs != null && rs.next()) {
					ExerciseBean exercise = new ExerciseBean();
					exercise.setName(rs.getString("name"));
					//exercise.setPoint(rs.getDouble("point"));
					exercise.setMuscleGroup(rs.getString("muscleGroup"));
					exercises.add(exercise);
				}
			}
		}
		catch (Exception ex) 
		{
			throw new ServletException(ex.getMessage());
		}
		return exercises;
	}
	
	private ExerciseBean initializeExercise(Long id) throws ServletException{
		ExerciseBean exercise = new ExerciseBean();
		
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM exercise WHERE Id = ?")){
			
			pstmt.setLong(1, id);
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs != null && rs.next()) {
					
					exercise.setName(rs.getString("name"));
					//exercise.setPoint(rs.getDouble("point"));
					exercise.setMuscleGroup(rs.getString("muscleGroup"));
					
//					List<SetBean> setList = new LinkedList<>();
//					
//					try(Connection itterateSet = ds.getConnection();
//						PreparedStatement sets = itterateSet.prepareStatement("SELECT * From sets WHERE exerciseId=?"))
//					{
//						sets.setLong(1,id);
//						try(ResultSet result = sets.executeQuery())
//						{
//							while(result != null && result.next()) 
//							{
//								SetBean setBean = new SetBean();
//								setBean.setRep(result.getInt("rep"));
//								setBean.setKg(result.getDouble("kg"));
//								setList.add(setBean);
//							}
//						}
//					}
				}
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		return exercise;
	}
	
	private void createExcercise(ExerciseBean exercise) throws ServletException{
		String[] generatedKeys = new String[] {"id"};
		try(Connection con = ds.getConnection();
			PreparedStatement stmtExercise = con.prepareStatement("INSERT INTO exercises"
														+ "(name, muscleGroup, trainingId)"
														+ "VALUES (?, ?, ?)", generatedKeys))
		{
			LinkedList<ExerciseBean> exercises = getListOfExercises();
			System.out.println("---------------------"+exercises.size()+"----------");
			for(ExerciseBean checkExer: exercises)
			{
				if (checkExer.getName().toLowerCase()
						.equals(exercise.getName().toLowerCase())) throw new ServletException("�bung exsistiert bereits");
				String[] splittedName = exercise.getName().split(" ");
				String result ="";
				for(String s : splittedName)
				{
					result += s;
				}
				if(result.toLowerCase()
						.equals(checkExer.getName().toLowerCase()))  throw new ServletException("�bung exsistiert bereits");
			}
			stmtExercise.setString(1, exercise.getName());
			stmtExercise.setString(2, exercise.getMuscleGroup());
			stmtExercise.executeUpdate();
			//stmtExercise.setLong(3, exercise.getTrainigsId);
			
			try(ResultSet rs = stmtExercise.getGeneratedKeys()){
				while(rs.next()) {
					exercise.setId(rs.getLong(1));
				}
			}
			
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
	}
	
}
