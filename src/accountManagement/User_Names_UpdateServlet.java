package accountManagement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
 * Servlet implementation class User_Names_update
 */
@WebServlet("/User_Names_update")
public class User_Names_UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public User_Names_UpdateServlet() {
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
		HttpSession session = request.getSession();
		User_Bean user =(User_Bean) session.getAttribute("userData");
		updateNames(user);
	}
	
	private void updateNames(User_Bean user) throws ServletException
	{
		try (Connection con = ds.getConnection();
				PreparedStatement statementNames = con.prepareStatement("UPDATE users SET firstname = ?, lastname = ? WHERE id = ?"))
		{
			statementNames.setString(0, user.getFirstname());
			statementNames.setString(1, user.getLastname());
		}	
		catch (Exception exception)
		{
			throw new ServletException(exception.getMessage());
		}
	}
}
