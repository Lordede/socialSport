package accountManagement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class User_Name_update
 */
@WebServlet("/User_Name_update")
public class User_Username_UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private String username;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public User_Username_UpdateServlet() {
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
					this.username = user.getUsername();
				}
			}
		}
		catch (Exception exception)
		{
			throw new ServletException(exception.getMessage());
		}
		return user;
	}
	
	private void updateUsername(User_Bean user) throws ServletException
	{
		try (Connection con = ds.getConnection();
				PreparedStatement statementName = con.prepareStatement("UPDATE users SET username WHERE id = ?"))
		{
			List<String> users = getAllNames();
			if (!user.getUsername().equals(username) && !users.contains(user.getUsername())) 
			{
				statementName.setString(0, user.getUsername());
				statementName.executeUpdate();
			}
			else 
			{
				throw new ServletException("no changes could be submitted because text is Equal");
			}
		}	
		catch (Exception exception)
		{
			throw new ServletException(exception.getMessage());
		}
	}
	
	private List<String> getAllNames() throws ServletException
	{
		try (Connection con = ds.getConnection();
				PreparedStatement statementName = con.prepareStatement("SELECT username FROM users"))
		{
			List<String> retList = new LinkedList();
			try (ResultSet rs = statementName.executeQuery())
			{
				while(rs.next()) 
				{
					retList.add(rs.getString("username"));
				}
				return retList;
			}
		}
			
		catch (Exception exception)
		{
			throw new ServletException(exception.getMessage());
		}
	}

}
