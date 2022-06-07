package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//Created by Cem inspired by Volker Stiehl <3
//special thanks to hubertus seitz and not to Lukas edmüller what a morone 
// hahaha  ~H
/**
 * Servlet implementation class BildServlet
 */
@WebServlet("/BildServlet")
@MultipartConfig(
		maxFileSize=1024*1024*10,
		maxRequestSize=1024*1024*10*10,
		location= "/tmp",
		fileSizeThreshold=1024*1024)
public class BildServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(lookup = "java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;
  
    public BildServlet() {
        super();
        
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Servlet zum Auslesen eines Bildes aus einer DB und Rückgabe als binärer Datenstrom
		request.setCharacterEncoding("UTF-8");	// In diesem Format erwartet das Servlet jetzt die Formulardaten
		Long id = Long.valueOf(request.getParameter("id"));
		 
		if(id == null) {
			System.out.println("ID ist NULL!");
		}

		
		// DB-Zugriff
		try (Connection con = ds.getConnection();
			 PreparedStatement pstmt = con.prepareStatement("SELECT exerciseImage FROM exercises Where id = ?") ) {
			pstmt.setLong(1, id);
			try (ResultSet rs = pstmt.executeQuery()) {
			
				if (rs != null && rs.next()) {
					Blob bild = rs.getBlob("exerciseImage");
					response.reset();
					long length = bild.length();
					response.setHeader("Content-Length",String.valueOf(length));
					
					try (InputStream in = bild.getBinaryStream()) {
						final int bufferSize = 256;
						byte[] buffer = new byte[bufferSize];
						
						ServletOutputStream out = response.getOutputStream();
						while ((length = in.read(buffer)) != -1) {
							out.write(buffer,0,(int) length);
						}
						out.flush();
					}
				}
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
	}

	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//
//		request.setCharacterEncoding("UTF-8");
//		Long id = Long.valueOf(request.getParameter("id"));
//		try (Connection con = ds.getConnection();
//				PreparedStatement stmt = con.prepareStatement("SELECT exerciseImage FROM exercises Where id = ?")) 
//		{
//			stmt.setLong(1, id);
//			try (ResultSet rs = stmt.executeQuery())
//			{
//				if (rs != null && rs.next()) 
//				{
//					Blob bild = rs.getBlob("exerciseImage");
//					response.reset();
//					long length = bild.length();
//					response.setHeader("Content-Length", String.valueOf(length));
//					
//					try (InputStream in = bild.getBinaryStream())
//					{
//						final int bufferSize = 256;
//						byte[] buffer = new byte[bufferSize];
//						
//						ServletOutputStream out = response.getOutputStream();
//						while ((length = in.read()) != -1) 
//						{
//							out.write(buffer,0,(int) length);
//						}
//						out.flush();
//					}
//					catch(Exception ex) 
//					{
//						throw new ServletException("1    "+ex.getMessage());
//					}
//				} 
//			}
//			catch(Exception ex) 
//			{
//				throw new ServletException("  2   "+ex.getMessage());
//			}
////			RequestDispatcher disp = request.getRequestDispatcher("html/success.jsp");
////			disp.forward(request, response);
//		}
//		catch (Exception ex) 
//		{
//			throw new ServletException(" ---- 3 ----"+ex.getMessage());
//		}
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	

}
