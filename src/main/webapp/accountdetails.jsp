<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.io.*, java.sql.*, java.util.regex.Pattern" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="accoundetails.css"/>
<script	src="https://code.jquery.com/jquery-3.4.1.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<title>Account</title>
</head>

<body>
<%! String emailorphno; 
	String psw;
	Connection con;
	Pattern emailregex;
	Pattern phnoregex;
	boolean is_email;
	boolean is_phno;
	String checking;
	PreparedStatement check;
	ResultSet rs;
	String email;
	String phno;
	String address;
	String pname; 
%>

<%
try{
emailorphno=request.getParameter("emailorphno");
psw=request.getParameter("psw");
session.setAttribute("emailorphno",emailorphno );
session.setAttribute("psw",psw ); 


//needs to be uncommented
if(emailorphno==null)
{
	response.sendRedirect("index.html");
}
else if(psw==null)
{
	response.sendRedirect("index.html");
}





/*
if(session.getAttribute("emailorphno").equals(emailorphno))
{
	System.out.println("works");
} 
*/
//login servlet





Class.forName("com.mysql.jdbc.Driver");
con=DriverManager.getConnection("jdbc:mysql://localhost:3306/authentication","root","pass2019");


//Regex string variables
emailregex=Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
phnoregex=Pattern.compile("\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}");

//check password equality
//Check if it is an email or phone number
is_email=emailregex.matcher(emailorphno).matches();
is_phno=phnoregex.matcher(emailorphno).matches();


if(is_email)
{
	//Email has been entered
	
	//check if the email already exists
	checking="select * from authinfo where email=?";
	check=con.prepareStatement(checking);
	check.setString(1, emailorphno);
	rs =check.executeQuery();

	if(rs.next())
	{
		if(psw.equals(rs.getString("pass")))
		{ 
			email=emailorphno;
			phno=rs.getString("phno");
			address=rs.getString("address");
			pname=rs.getString("pname");
			
 		}
		else
		{
			response.sendRedirect("index.html"); 
		}
	}
	else
	{
		response.sendRedirect("index.html");
	} 
}
else if(is_phno)
{
	//phone number has been entered
	
	//check if the phone number already exists
	checking="select * from authinfo where phno=?";
	check=con.prepareStatement(checking);
	check.setString(1, emailorphno);
	rs =check.executeQuery();
	
	if(rs.next())
	{
		if(psw.equals(rs.getObject("pass")))
		{ 
			email=rs.getString("email");
			phno=emailorphno;
			address=rs.getString("address");
			pname=rs.getString("pname");
			
		}
		else
		{
			response.sendRedirect("index.html");//wrong password
		}
	}
	else
	{
		response.sendRedirect("index.html");// account does not exist
	}
	
}
else
{
	response.sendRedirect("index.html");
}

session.setAttribute("username", pname);
session.setAttribute("pass", psw);

%> 


<h1>Welcome</h1>
<div class="row">
		<div class="col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
		<h3>Account Details</h3>
	<div id="ajaxupdatereply"></div>
	<form action="" method="post" id="changedetails">
		<fieldset>
		Name <input type="text" name="username" value="<%=pname %>">
		<button type="button" id ="namechange">Change the user name</button><br><br>
		Email <input type="text" name="email" id="emailinput" value="<%=email %>">
		<button type="button" id ="emailchange">Change the email id</button><br><br>
		Phone Number <input type="text" id="phnoinput" name="phno" value="<%=phno %>">
		<button type="button" id ="phonechange">Change the phone number</button><br><br>
		Address <textarea name="addre" id="addre" cols="30" rows="10"><%=address %></textarea>
		<button type="button" id ="addresschange">Change the address</button><br><br>
		<input type="hidden" class="oldphno" name="old_phno" value="<%=phno %>">
		<input type="hidden" class="oldemail" name="old_email" value="<%=email %>">
		</fieldset>
	</form>
	 <br>
	
</div>
	<div class="col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
		<form action="" method="post" id="changepasswordform">
			<fieldset>
				<legend>Change Password</legend>
				Existing Password <input type="password" id="existingpassword" name="existingpassword"><br><br>
				New Password <input type="password" id="newpassword" name="newpassword"><br><br>
				Re-type New Password <input type="password" id="renewpassword" name="renewpassword"><br><br>
				<input type="hidden" id="actualpassword" name="actualpassword" value="<%=psw %>">
				<input type="hidden" class="oldphno" id="phnopass" name="phno" value="<%=phno %>">
				<input type="hidden" class="oldemail" id="emailpass" name="email" value="<%=email %>">
				
			</fieldset>
		</form>
		<button id="passwordbutton">Change Password</button><br><br>
		<div id="ajaxpassword"></div>
	</div>
</div>
 <script type="text/javascript"	src="accountdetails.js"></script>
 
</body>
</html>
<%

con.close();
}
catch(Exception e)
{
	e.printStackTrace();
	
}
%>