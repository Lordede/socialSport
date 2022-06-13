package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import beans.ExerciseBean;
import beans.TrainingBean;
import beans.UserBean;
import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


/**
 * Servlet implementation class TrainingServlet
 */
@WebServlet("/TrainingServlet")
public class TrainingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// Verbindung zur Datenbank deklarieren
	@Resource(lookup = "java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TrainingServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
    	//Long id = Long.parseLong(request.getParameter("id"));
    	HttpSession session = request.getSession();
    	UserBean user = (UserBean) session.getAttribute("userData");
    	//Cem Durmus
    	Enumeration<String> params = request.getParameterNames();
		while(params.hasMoreElements()) 
		{
			String paramNames = params.nextElement();
//			System.out.println(paramNames);
			switch(paramNames) 
			{
			case "loadTrainings":
				ArrayList<TrainingBean> allTrainings = listAllTrainings(user.getId());
				String json = convertListToJson(allTrainings);
//				System.out.println(json);	
				response.getWriter().write(json);
				break;
			case "exerciseInputField":
				ArrayList<TrainingBean> trainingSearched = search(request.getParameter("exerciseInputField"));
				String jsonSearch = convertListToJson(trainingSearched);
				response.getWriter().write(jsonSearch);
				break;
			case "selectedTraining":
				TrainingBean training =(TrainingBean) read(Long.parseLong(request.getParameter("selectedTraining")));
				if(session.getAttribute("training") != null) 
				{
					session.removeAttribute("training");
//					System.out.println("mehr als ein attriubt");
					
				}
				session.setAttribute("training", training);
				

				
				response.getWriter().write("hasi");
//				RequestDispatcher disp = request.getRequestDispatcher("html/training.jsp");
//				disp.forward(request, response);
			}
		}
		
		//TrainingBean training = read(userBean.getId());
	
		//TODO: read muss �ber TrainingId erfolgen
		//TODO: search muss �ber trainingsplanId erfolgen

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session = request.getSession();
		String name = request.getParameter("name");
		double points = Double.parseDouble(request.getParameter("points"));
		UserBean user = (UserBean) session.getAttribute("userData");
		long userId = user.getId();
		java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
		
		TrainingBean training = new TrainingBean();
		training.setName(name);
		training.setPoints(points);
		training.setUserId(userId);
		training.setCreationDate(date);
		
//		TrainingBean training = create(name, points, userid, date);
		create(training);
		session.setAttribute("training", training);
		response.getWriter().write("hasi");
		//response.sendRedirect("html/training.jsp");
		//doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		TrainingBean training = new TrainingBean();
		training.setId(Long.parseLong(request.getParameter("id")));
		training.setName(request.getParameter("name"));
		training.setPoints(Double.parseDouble(request.getParameter("points")));
				
		update(training);
		request.setAttribute("training", training);
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long id = Long.parseLong(request.getParameter("id")) ;
		delete(id);
	}
	
	private void delete(Long id) throws ServletException{
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement("DELETE FROM trainings WHERE id = ?")){
			pstmt.setLong(1, id);
			pstmt.executeUpdate();
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
	}
	
	private void update(TrainingBean form) throws ServletException{
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"UPDATE trainings " 
					+ "SET name = ?, points = ?"
					+ "WHERE id = ?")){
			pstmt.setString(1, form.getName());
			pstmt.setDouble(2, form.getPoints());
			pstmt.setLong(3, form.getId());
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
	}
	
	private TrainingBean read(Long id) throws ServletException{
		TrainingBean form = new TrainingBean();
		
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM trainings WHERE id = ?")){
			
			pstmt.setLong(1, id);
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs != null && rs.next()) {
					form.setName(rs.getString("name"));
					form.setId(rs.getLong("id"));
					form.setPoints(rs.getDouble("points"));
					form.setUserId(rs.getLong("userId"));
					form.setCreationDate(rs.getDate("creationDate"));
					
				}
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		return form;
	}
	
	private ArrayList<TrainingBean> search(String input) throws ServletException{
		ArrayList<TrainingBean> trainings = new ArrayList<TrainingBean>();
		input = (input == null || input == "") ? "%" : "%" + input + "%";
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM trainings WHERE name LIKE ?")) 
		{
			
			pstmt.setString(1, input);
			try(ResultSet rs = pstmt.executeQuery()){
				while(rs.next()) {
					TrainingBean training = new TrainingBean();
					Long id = Long.valueOf(rs.getLong("id"));
					String name = rs.getString("name");
					double points = Double.valueOf(rs.getDouble("points"));					
					training.setId(id);
					training.setName(name);
					training.setPoints(points);
					trainings.add(training);
				}
			}
			
		}catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		
		return trainings;
	}
	
	private ArrayList<TrainingBean> listAllTrainings(Long userId) throws ServletException{
		ArrayList<TrainingBean> trainings = new ArrayList<TrainingBean>();
		
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM trainings Where userId = ?")){
			
			pstmt.setLong(1, userId);
			try(ResultSet rs = pstmt.executeQuery()){
				while(rs.next()) {
					TrainingBean training = new TrainingBean();
					
					Long id = rs.getLong("id");
					String name = rs.getString("name");
					double points = rs.getDouble("points");
					java.sql.Date creationDate = rs.getDate("creationDate");
					training.setId(id);
					training.setName(name);
					training.setPoints(points);
					training.setUserId(userId);
					training.setCreationDate(creationDate);
					
					trainings.add(training);
				}
			}
			
		}catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		
		return trainings;
	}
	
	private void create(TrainingBean form) throws ServletException{
		String[] generatedKeys = new String[] {"id"};
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO trainings"
														+ "(name, points, userId, creationDate) "
														+ "VALUES (?, ?, ?, ?)", generatedKeys)){
			pstmt.setString(1,form.getName());
			pstmt.setDouble(2, form.getPoints());
			pstmt.setLong(3, form.getUserId());
			pstmt.setDate(4, form.getCreationDate());
			
			pstmt.executeUpdate();
			
			try(ResultSet rs = pstmt.getGeneratedKeys()){
				while(rs.next()) {
					form.setId(rs.getLong(1));
				}
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
	}
//Cem Durmus
	private String convertListToJson(ArrayList<TrainingBean> arr) 
	{
		StringBuilder jsonString = new StringBuilder();
		ArrayList<TrainingBean> trainings = arr;
		
		jsonString.append("[");
		for(int i = 0;i < trainings.size(); i++) 
		{			
			jsonString.append("{");
			jsonString.append("\"name\":");
			jsonString.append("\""+trainings.get(i).getName()+"\",");
			jsonString.append("\"points\":");
			jsonString.append("\""+trainings.get(i).getPoints()+"\",");
			jsonString.append("\"id\":");
			jsonString.append("\""+trainings.get(i).getId()+"\"");
			if( i+1 == trainings.size()) 
			{
				jsonString.append("}");
			} else {
				jsonString.append("},");
				}
		}
		jsonString.append("]");
		
		return jsonString.toString();
	}
}
