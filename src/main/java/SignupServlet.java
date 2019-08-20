

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(description="signup", urlPatterns= {"/SignupServlet"})
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/authentication","root","pass2019");
			
			String emailorphno=request.getParameter("emailorphno");
			String username=request.getParameter("username");
			String psw=request.getParameter("psw");
			String repsw=request.getParameter("repsw");
			PrintWriter out=response.getWriter();
			
			//Regex string variables
			Pattern emailregex=Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
			Pattern phnoregex=Pattern.compile("\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}");
			
			//check password equality
			if(psw.equals(repsw))
			{
				//Check if it is an email or phone number
				boolean is_email=emailregex.matcher(emailorphno).matches();
				boolean is_phno=phnoregex.matcher(emailorphno).matches();
				
				response.setContentType("text/plain");
				
				if(is_email)
				{
					//Email has been entered
					
					//check if the email already exists
					String checking="select * from authinfo where email=?";
					PreparedStatement check=con.prepareStatement(checking);
					check.setString(1, emailorphno);
					ResultSet rs =check.executeQuery();
					if(rs.next())
					{
						response.getWriter().write("Account Already Exists"); 
						//even if a single record is encountered it means account already exists
					}
					else
					{
						//creating an account with email
						String registerwithemailsql="Insert into authinfo(email,pname,pass,verified) values(?,?,?,?)";
						PreparedStatement registerwithemail=con.prepareStatement(registerwithemailsql);
						registerwithemail.setString(1, emailorphno);
						registerwithemail.setString(2, username);
						registerwithemail.setString(3, psw);
						registerwithemail.setBoolean(4, false);
						registerwithemail.executeUpdate();
						response.getWriter().write("Account created with email");
					}
				}
				else if(is_phno)
				{
					//phone number has been entered
					
					//check if the phone number already exists
					String checking="select * from authinfo where phno=?";
					PreparedStatement check=con.prepareStatement(checking);
					check.setString(1, emailorphno);
					ResultSet rs =check.executeQuery();
					if(rs.next())
					{
						response.getWriter().write("Account Already Exists");
						//even if a single record is encountered it means account already exists
					}
					else
					{
						//creating an account with phone number
						String registerwithphnosql="Insert into authinfo(phno,pname,pass,verified) values(?,?,?,?)";
						PreparedStatement registerwithphno=con.prepareStatement(registerwithphnosql);
						registerwithphno.setString(1, emailorphno);
						registerwithphno.setString(2, username);
						registerwithphno.setString(3, psw);
						registerwithphno.setBoolean(4, false);
						registerwithphno.executeUpdate();
						response.getWriter().write("Account created with phone number");
					}
				}
				else
				{
					response.getWriter().write("Enter a proper phone number or email id");
				}
			}
			else
			{
				response.getWriter().write("Passwords do not match");
			}
			out.close();
			con.close();
		}
		catch(Exception e)
		{
			System.out.print(e);
		}
	}

}
