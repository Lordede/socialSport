package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import beans.SetBean;
import beans.TrainingsplanBean;
import beans.UserBean;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//von Lukas Edmüller

/**
 * Servlet implementation class SatzServlet
 */
@WebServlet("/SetServlet")
public class SetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// Verbindung zur Datenbank deklarieren
	@Resource(lookup = "java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    SetBean set = new SetBean();
    
    public SetBean getSet() 
	{
		return this.set;
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//Long id = Long.parseLong(request.getParameter("id"));
		HttpSession session = request.getSession();
		UserBean userBean = (UserBean) session.getAttribute("userData");
TrainingsplanTrainingSetServletFunction
		//SetBean set = read(userBean.getId());
		
		//TODO: read muss über id erfolgen
		//TODO: search muss über exerciseId erfolgen
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		SetBean set = new SetBean();
		
		set.setRep(Integer.parseInt(request.getParameter("rep")));
		set.setKg(Double.parseDouble(request.getParameter("kg")));
		set.setExersiceId(Long.parseLong(request.getParameter("exerciseId")));
				
		create(set);
		
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		SetBean set = new SetBean();
		
		set.setId(Long.parseLong(request.getParameter("id")));
		set.setRep(Integer.parseInt(request.getParameter("rep")));
		set.setKg(Double.parseDouble(request.getParameter("kg")));
				
		update(set);
		
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Long id = Long.parseLong(request.getParameter("id"));
		delete(id);
	}
	
	private void delete(Long id) throws ServletException{
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement("DELETE FROM sets WHERE id = ?")){
			pstmt.setLong(1, id);
			pstmt.executeUpdate();
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
	}
	
	private void update(SetBean form) throws ServletException{
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"UPDATE sets " 
					+ "SET rep = ?, kg = ?"
					+ "WHERE id = ?")){
			pstmt.setInt(1, form.getRep());
			pstmt.setDouble(2, form.getKg());
			pstmt.setLong(3, form.getId());
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
	}
	
	private SetBean read(Long id) throws ServletException{
		SetBean form = new SetBean();
		
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM sets WHERE id = ?")){
			
			pstmt.setLong(1, id);
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs != null && rs.next()) {
					form.setRep(rs.getInt("rep"));
					form.setKg(rs.getDouble("kg"));
					form.setExersiceId(rs.getLong("exerciseId"));
					
				}
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		return form;
	}
	
	private List<SetBean> search(Long exerciseId) throws ServletException{
		List<SetBean> sets = new ArrayList<SetBean>();
		
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM sets WHERE exerciseId = ?")){
			
			pstmt.setLong(0, exerciseId);
			try(ResultSet rs = pstmt.executeQuery()){
				while(rs.next()) {
					SetBean set = new SetBean();
					
					Long id = Long.valueOf(rs.getLong("id"));
					double kg = Double.valueOf(rs.getDouble("kg"));
					int rep = Integer.valueOf(rs.getInt("rep"));
					
					
					set.setId(id);
					set.setKg(kg);
					set.setRep(rep);
					set.setExersiceId(exerciseId);
					
					sets.add(set);
				}
			}
			
		}catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		
		return sets;
	}
	
	private void create(SetBean form) throws ServletException{
		String[] generatedKeys = new String[] {"id"};
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO sets"
														+ "(rep, kg, exerciseId) "
														+ "VALUES (?, ?, ?)", generatedKeys)){
			pstmt.setInt(1, form.getRep());
			pstmt.setDouble(2, form.getKg());
			pstmt.setLong(3, form.getExersiceId());
			
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

}
