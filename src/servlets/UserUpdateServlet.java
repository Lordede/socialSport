package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Enumeration;

import javax.sql.DataSource;

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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
				updateEmail(user);
				break;
			case "changeNames":
				updateNames(user);
				break;
			case "changeUsername":
				updateUsername(user,request.getParameter("changeUsername"));
				break;
			case "changePassword":
				updatePassword(user);
				break;
			case "changeImage":
				updateImage(user);
				break;
			case "deleteUser":
				deleteUser(user);
				break;
			default:
				continue;
			}
		}
		response.sendRedirect("html/accountDataChanged.jsp");
	}
	
	private void updateImage(UserBean user) {
		// TODO Bildhochladen
		
	}

	public void updateEmail(UserBean user) throws ServletException
	{
		try (Connection con = ds.getConnection();
				PreparedStatement statementName = con.prepareStatement("UPDATE users SET eMail=? WHERE id = ?"))
		{
			user.getId();
			statementName.setString(1, user.geteMail());
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
			statementName.setLong(2, user.getId());
			statementName.executeUpdate();
		}	
		catch (Exception exception)
		{
			throw new ServletException(exception.getMessage());
		}
	}
	
	
	private void updateNames(UserBean user) throws ServletException
	{
		try (Connection con = ds.getConnection();
				PreparedStatement statementNames = con.prepareStatement("UPDATE users SET firstname = ?, lastname = ? WHERE id = ?"))
		{
			statementNames.setString(1, user.getFirstName());
			statementNames.setString(2, user.getLastName());
			statementNames.setLong(3, user.getId());
		}	
		catch (Exception exception)
		{
			throw new ServletException(exception.getMessage());
		}
	}
	

	private void updatePassword(UserBean user) throws ServletException
	{
		try (Connection conDs = ds.getConnection();
				PreparedStatement statementEmail = conDs.prepareStatement("UPDATE users SET password = ? WHERE id = ?"))
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
}
