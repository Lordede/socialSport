package servlets;

import java.io.IOException;
import java.lang.module.ResolutionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	@Resource(lookup="java:jboss/datasources/MySqlThidbDS")
    private DataSource ds;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdateServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Enumeration<String> paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) 
		{
			String param = paramNames.nextElement();
			switch (param) 
			{
			case "getUsers":
				ArrayList<UserBean> users = listOfAllUsers();
				String usersJson = convertListToJson(users);
				response.getWriter().write(usersJson);
				break;
			case "getSpecificUser":
				UserBean user = getUser(Long.parseLong(request.getParameter("getSpecificUser")));
				//insert functionality
			}
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute("userData");
		deleteUser(user);
	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Enumeration<String> buttonNames = request.getParameterNames();
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute("userData");
		while (buttonNames.hasMoreElements())
		{
			String buttonName = buttonNames.nextElement();
			
			switch(buttonName) 
			{
			case "changeMail":
				updateEmail(user, request.getParameter("changeMail"));
				break;
			case "changeFirstName":
				System.out.println(request.getParameter("changeFirstName"));
				updateFirstName(user, request.getParameter("changeFirstName"));
				
				break;
			case "changeLastName": // TODO : namen bitte zum laufen kriegen du kek
				updateLastName(user, request.getParameter("changeLastName"));
				break;
			case "changeUsername":
				updateUsername(user,request.getParameter("changeUsername"));
				break;
			case "password":
				updatePassword(user, request.getParameter("password"));
				break;
			case "changeImage":
				updateImage(user);
				break;
			case "deleteUser":
				deleteUser(user);
				response.sendRedirect("html/accountDeletion.jsp");
				break;
			case "searchUser":
				ArrayList<UserBean> users = search(request.getParameter("searchUser"));
				String userSearchJson = convertListToJson(users);
				response.getWriter().write(userSearchJson);
			default:
				break;
			}
		}
		response.sendRedirect("html/accountDataChanged.jsp");
	}
	
	private void updateImage(UserBean user) {
		// TODO Bildhochladen
		
	}

	public void updateEmail(UserBean user, String eMail) throws ServletException
	{
		try (Connection con = ds.getConnection();
				PreparedStatement statementName = con.prepareStatement("UPDATE users "
																		+ "SET email = ? "
																		+ "WHERE id = ?"))
		{
			user.getId();
			statementName.setString(1, eMail);
			statementName.setLong(2, user.getId());
			statementName.executeUpdate();
		}	
		catch (Exception exception)
		{
			throw new ServletException(exception.getMessage());
		}
	}
	
	public void updateUsername(UserBean user, String newName) throws ServletException
	{
		try (Connection con = ds.getConnection();
				PreparedStatement statementName = con.prepareStatement("UPDATE users "
																		+ "SET username = ? "
																		+ "WHERE id = ?"))
		{	
			statementName.setString(1, newName);
			System.out.println(newName);
			statementName.setLong(2, user.getId());
			statementName.executeUpdate();
		}	
		catch (Exception exception)
		{
			throw new ServletException(exception.getMessage());
		}
	}
	
	
	private void updateFirstName(UserBean user, String firstName) throws ServletException
	{
		try (Connection con = ds.getConnection();
				PreparedStatement statementNames = con.prepareStatement("UPDATE users "
																		+ "SET firstname = ? "
																		+ "WHERE id = ?"))
		{
			statementNames.setString(1, firstName);
			System.out.println(firstName);
			statementNames.setLong(2, user.getId());
		}	
		catch (Exception exception)
		{
			throw new ServletException(exception.getMessage());
		}
	}
	
	private void updateLastName(UserBean user, String lastName) throws ServletException
	{
		try (Connection con = ds.getConnection();
				PreparedStatement statementNames = con.prepareStatement("UPDATE users "
																		+ "SET lastname = ? "
																		+ "WHERE id = ?"))
		{
			statementNames.setString(1, lastName);
			statementNames.setLong(2, user.getId());
		}	
		catch (Exception exception)
		{
			throw new ServletException(exception.getMessage());
		}
	}
	

	private void updatePassword(UserBean user, String password) throws ServletException
	{
		try (Connection conDs = ds.getConnection();
				PreparedStatement statementEmail = conDs.prepareStatement("UPDATE users SET pwd = ? WHERE id = ?"))
		{
			statementEmail.setString(1, HashPassword.hashPassword(user.getPassword()));//hash methode
			statementEmail.setLong(2, user.getId());
			statementEmail.executeUpdate();
		}
		catch (Exception exception)
		{
			throw new ServletException(exception.getMessage());
		}
	}

	private void deleteUser(UserBean user) throws ServletException 
	{
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement("DELETE FROM users WHERE id = ?")){
			pstmt.setLong(1, user.getId());
			pstmt.executeUpdate();
		} 
		catch (Exception ex) 
		{
			throw new ServletException(ex.getMessage());
		}
	}
	
	private UserBean getUser(Long id) throws ServletException
	{
		UserBean user = new UserBean();
		try (Connection conDs = ds.getConnection();
				PreparedStatement statement = conDs.prepareStatement("SELECT * FROM users WHERE id = ?"))
		{
			try(ResultSet rs = statement.executeQuery())
			{
				user.setUsername(rs.getString("username"));
				user.setCreationDate(rs.getDate("creationDate"));
				user.setFirstName(rs.getString("firstname"));
				user.setLastName(rs.getString("lastname"));
				user.seteMail(rs.getString("eMail"));
				user.setId(id);
			}
			return user;
		}
		catch (Exception ex) 
		{
			throw new ServletException(ex.getMessage());
		}
	}
	private ArrayList<UserBean> listOfAllUsers() throws ServletException 
	{
		ArrayList<UserBean> userList = new ArrayList<>();
		try(Connection con = ds.getConnection();
		PreparedStatement pstmt = con.prepareStatement("SELECT * FROM users"))
		{
			try(ResultSet rs = pstmt.executeQuery()){
				while(rs.next()) {
					UserBean user = new UserBean();
					user.setUsername(rs.getString("username"));
					user.setFirstName(rs.getString("firstname"));
					user.seteMail(rs.getString("eMail"));
					user.setLastName(rs.getString("lastname"));
					userList.add(user);
				}
			}
			return userList;
		}
		catch(Exception ex)
		{
			throw new ServletException(ex.getMessage());
		}
	}
	private ArrayList<UserBean> search(String exerciseName) throws ServletException
	{
		exerciseName = (exerciseName == null || exerciseName == "") ? "%" : "%" + exerciseName + "%";
		ArrayList<UserBean> users = new ArrayList<>();
		
		try (Connection con = ds.getConnection();
				PreparedStatement search = con.prepareStatement("SELECT * FROM exercises WHERE name LIKE ?")) 
		{
			search.setString(1, exerciseName);
			try (ResultSet result = search.executeQuery())
			{
				while (result.next()) 
				{
					UserBean user = new UserBean();
					user.setUsername(result.getString("username"));
					user.setFirstName(result.getString("firstname"));
					user.seteMail(result.getString("eMail"));
					user.setLastName(result.getString("lastname"));
					users.add(user);
				}
				return users;
			}
			
		}
		catch (Exception ex) 
		{
			throw new ServletException(ex.getMessage()); 
		}
	}
	private String convertListToJson(ArrayList<UserBean> arr) 
	{
		StringBuilder jsonString = new StringBuilder();
		ArrayList<UserBean> users = arr;
		
		jsonString.append("[");
		for(int i = 0;i < users.size(); i++) 
		{			
			jsonString.append("{");
			jsonString.append("\"vorname\":");
			jsonString.append("\""+users.get(i).getFirstName()+"\",");
			jsonString.append("\"nachname\":");
			jsonString.append("\""+users.get(i).getLastName()+"\",");
			jsonString.append("\"eMail\":");
			jsonString.append("\""+users.get(i).geteMail()+"\",");
			jsonString.append("\"benutzername\":");
			jsonString.append("\""+users.get(i).getUsername()+"\",");
			jsonString.append("\"id\":");
			jsonString.append("\""+users.get(i).getId()+"\"");
			if( i+1 == users.size()) 
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
