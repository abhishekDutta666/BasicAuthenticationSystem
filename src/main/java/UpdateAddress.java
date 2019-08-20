

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class UpdateAddress extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try {
			response.sendRedirect("index.html");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/plain");
		System.out.println("came here 1");
		
		try 
		{
			HttpSession session=request.getSession();
			if(session.getAttribute("username")==null)
			{
				response.sendRedirect("index.html");
			}
			
			String new_address=request.getParameter("addre");
			System.out.println("came here 1");
			String old_email=request.getParameter("old_email");
			System.out.println("came here 1");
			String old_phno=request.getParameter("old_phno");
			System.out.println("came here 1");
			
			
			
			
			
			
			System.out.println("came here 1");
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/authentication","root","pass2019");
			
			response.setContentType("text/plain");
			
			if(old_email!=null)
			{
				System.out.println("came here 6");
				String updating="update authinfo set address=? where email=?";
				PreparedStatement updated=con.prepareStatement(updating);
				updated.setString(1, new_address);
				updated.setString(2, old_email);
				updated.executeUpdate();
				System.out.println("came here 8");
			}
			else
			{
				System.out.println("came here 7");
				String updating="update authinfo set address=? where phno=?";
				PreparedStatement updated=con.prepareStatement(updating);
				updated.setString(1, new_address);
				updated.setString(2, old_phno);
				updated.executeUpdate();
				System.out.println("came here 9");
			}
			response.getWriter().write("Address successfully changed");
			con.close();
			
		}
		catch(Exception e)
		{
			System.out.println("Error"+e);
		}
		
	}

}
