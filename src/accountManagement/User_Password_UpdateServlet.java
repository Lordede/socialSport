package accountManagement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class User_Management_Servlet
 */
@WebServlet("/User_Management_Servlet")
public class User_Password_UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private String password;
	/**
     * @see HttpServlet#HttpServlet()
     */
    public User_Password_UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Resource(lookup = "java:jboss/datasources/MySqlThidbDS")
 	private DataSource ds;
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	private User_Bean read(Long id) throws ServletException
	{
		User_Bean user = new User_Bean();
		user.setId(id);
		try(Connection readCon = ds.getConnection();
				PreparedStatement readStmt = readCon.prepareStatement("SELECT * FROM user WHERE id = ?"))
		{
			readStmt.setLong(1,id);
			try(ResultSet rs = readStmt.executeQuery())
			{
				if(rs != null && rs.next()) 
				{
					this.password = user.getPassword();
				}
			}
		}
		catch (Exception exception)
		{
			throw new ServletException(exception.getMessage());
		}
		return user;
	}
	private void updatePassword(User_Bean user) throws ServletException
	{
		try (Connection conDs = ds.getConnection();
				PreparedStatement statementEmail = conDs.prepareStatement("UPDATE users SET password = ? WHERE id = ?"))
		{
			if(!user.geteMail().equals(password)) 
			{
				statementEmail.setString(0, user.getPassword());
				statementEmail.executeUpdate();
			}
			else 
			{
				throw new ServletException("new passwort equals old passwort");
			}
		}
		catch (Exception exception)
		{
			throw new ServletException(exception.getMessage());
		}
	}
	
}
