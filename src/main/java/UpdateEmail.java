

import java.io.IOException;
import java.sql.*;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = { "/updateemail" })
public class UpdateEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException {
		try {
			response.sendRedirect("index.html");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		System.out.println("came here 1");
		
		try 
		{
			HttpSession session=request.getSession();
			if(session.getAttribute("username")==null)
			{
				response.sendRedirect("index.html");
			}
			
			String new_email=request.getParameter("email");
			System.out.println("came here 1");
			String old_email=request.getParameter("old_email");
			System.out.println("came here 1");
			String old_phno=request.getParameter("old_phno");
			System.out.println("came here 1");
			
			
			Pattern emailregex=Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
			boolean is_email=emailregex.matcher(new_email).matches();
			
			
			System.out.println("came here 1");
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/authentication","root","pass2019");
			
			String checking;
			
			response.setContentType("text/plain");
			
			System.out.println("came here 1");
			if(is_email)
			{
				if(!new_email.equals(old_email))
				{
					System.out.println("came here 2");
					checking="select * from authinfo where email=?";
					PreparedStatement check=con.prepareStatement(checking);
					check.setString(1, new_email);
					ResultSet rs =check.executeQuery();
					if(!rs.next())
					{
						if(old_email!=null)
						{
							System.out.println("came here 3");
							String updating="update authinfo set email=? where email=?";
							PreparedStatement updated=con.prepareStatement(updating);
							updated.setString(1, new_email);
							updated.setString(2, old_email);
							updated.executeUpdate();
							response.getWriter().write("success");
							
						}
						else
						{
							System.out.println("came here 3");
							String updating="update authinfo set email=? where phno=?";
							PreparedStatement updated=con.prepareStatement(updating);
							updated.setString(1, new_email);
							updated.setString(2, old_phno);
							updated.executeUpdate();
							response.getWriter().write("success");
							
						}
					}
					else
					{
						response.getWriter().write("failure1"); //Email already exists
					}
				}
				else
				{
					response.getWriter().write("failure2"); //new email is the same as the old email
				}
			}
			else
			{
				response.getWriter().write("failure3"); //Please enter a proper email
			}
			
			
			
			
			con.close();
			
		}
		catch(Exception e)
		{
			System.out.println("Error"+e);
		}
	}

}
