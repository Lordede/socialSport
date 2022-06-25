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

//Cem Durmus orientiert an JBDC Folien
/**
 * Servlet implementation class BildServlet
 */
@WebServlet("/BildServlet")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 10
		* 10, location = "/tmp", fileSizeThreshold = 1024 * 1024)
public class BildServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(lookup = "java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;

	public BildServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Servlet zum Auslesen eines Bildes aus einer DB und R�ckgabe als bin�rer
		// Datenstrom
		request.setCharacterEncoding("UTF-8"); // In diesem Format erwartet das Servlet jetzt die Formulardaten
		Long id = Long.valueOf(request.getParameter("id"));

		if (id == null) {
			System.out.println("ID ist NULL!");
		}

		// DB-Zugriff
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement("SELECT exerciseImage FROM exercises Where id = ?")) {
			pstmt.setLong(1, id);
			try (ResultSet rs = pstmt.executeQuery()) {

				if (rs != null && rs.next()) {
					Blob bild = rs.getBlob("exerciseImage");
					response.reset();
					long length = bild.length();
					response.setHeader("Content-Length", String.valueOf(length));

					try (InputStream in = bild.getBinaryStream()) {
						final int bufferSize = 256;
						byte[] buffer = new byte[bufferSize];

						ServletOutputStream out = response.getOutputStream();
						while ((length = in.read(buffer)) != -1) {
							out.write(buffer, 0, (int) length);
						}
						out.flush();
					}
				}
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
	}

}
