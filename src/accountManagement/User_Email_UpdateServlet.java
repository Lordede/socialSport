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
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class User_Email_UpdateServlet
 */
@WebServlet("/User_Email_UpdateServlet")
public class User_Email_UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public User_Email_UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
 
 	@Resource(lookup = "java:jboss/datasources/MySqlThidbDS")
 	private DataSource ds;
    private String eMail;

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
		HttpSession session = request.getSession();
		User_Bean user =(User_Bean) session.getAttribute("userData");
		updateUsername(read(user.getId()));
		
	}

	private List<String> getAllMails() throws ServletException
	{
		try (Connection con = ds.getConnection();
				PreparedStatement statementName = con.prepareStatement("SELECT eMail FROM users"))
		{
			List<String> retList = new LinkedList();
			try (ResultSet rs = statementName.executeQuery())
			{
				while(rs.next()) 
				{
					retList.add(rs.getString("eMail"));
				}
				return retList;
			}
		}
			
		catch (Exception exception)
		{
			throw new ServletException(exception.getMessage());
		}
	}
	
	public void updateUsername(User_Bean user) throws ServletException
	{
		try (Connection con = ds.getConnection();
				PreparedStatement statementName = con.prepareStatement("UPDATE users SET username WHERE id = ?"))
		{
			List<String> users = getAllMails();
			if (!user.getUsername().equals(eMail) && !users.contains(user.geteMail())) 
			{
				statementName.setString(0, user.geteMail());
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
	
	public User_Bean read(Long id) throws ServletException
	{
		User_Bean user = new User_Bean();
		user.setId(id);
		try(Connection readCon = ds.getConnection();
				PreparedStatement readStmt = readCon.prepareStatement("SELECT eMail FROM user WHERE id = ?"))
		{
			readStmt.setLong(1,id);
			try(ResultSet rs = readStmt.executeQuery())
			{
				if(rs != null && rs.next()) 
				{
					this.eMail = user.geteMail();
				}
			}
		}
		catch (Exception exception)
		{
			throw new ServletException(exception.getMessage());
		}
		return user;
	}
}
