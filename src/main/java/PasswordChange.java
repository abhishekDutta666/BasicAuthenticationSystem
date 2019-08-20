

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class PasswordChange
 */
@WebServlet(description ="changing password with the help of existing password", urlPatterns = { "/changepassword" })
public class PasswordChange extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException {
		try {
			response.sendRedirect("index.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try 
		{
			HttpSession session=request.getSession();
			if(session.getAttribute("username")==null)
			{
				response.sendRedirect("index.html");
			}
			String oldpassword=request.getParameter("existingpassword");
			String actualpassword=request.getParameter("actualpassword");
			String newpassword=request.getParameter("newpassword");
			String renewpassword=request.getParameter("renewpassword");
			String phno=request.getParameter("phno");
			String email=request.getParameter("email");
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/authentication","root","pass2019");
			
			response.setContentType("text/plain");
			
			
			if(oldpassword.equals(actualpassword))
			{
				if(oldpassword.equals(newpassword))
				{
					response.getWriter().write("failure2");//new password same as old password
				}
				else
				{
					if(newpassword.equals(renewpassword))
					{
						if(email!=null)
						{
							String updating="update authinfo set pass=? where email=?";
							PreparedStatement updated=con.prepareStatement(updating);
							updated.setString(1, newpassword);
							updated.setString(2, email);
							updated.executeUpdate();
							response.getWriter().write("success");
						}
						else
						{
							String updating="update authinfo set pass=? where phno=?";
							PreparedStatement updated=con.prepareStatement(updating);
							updated.setString(1, newpassword);
							updated.setString(2, phno);
							updated.executeUpdate();
							response.getWriter().write("success");
						}
					}
					else
					{
						response.getWriter().write("failure3");//the entered passwords do not match
					}
				}
			}
			else
			{
				response.getWriter().write("failure1");//the existing password that is entered is wrong
			}
			con.close();
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}

}
