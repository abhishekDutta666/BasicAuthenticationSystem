# BasicAuthenticationSystem
This is a basic authentication system implemented using servlets and JSPs.

No. of pages: 2
-login and signup page
-account details page

Functionality:
-We can create an account
-we can login into the account
-we can access and modify our information (name, email, phone number, address)
-we can change password from within the account by providing the existing password

Detailed Description:
The opening page contains a login form and a sign up form. The signup form has fields to input mobile number or 
email id which it recognizes automatically. Apart from that has a name field and a password field. We have to retype the password
to confirm that the password entered did not have a typing error. If the email id or the phone number do not exist
already, the account is created. An appropriate message is displayed about the success or failure of the operation.
If successful, you can log into the account using the login form, where you have to enter your credentials. The credentials 
will be verified and you will be redirected to the account page. In the account page you will have the user details namely,
email, phone number, address, name. Each of these fields will be modifiable. We can also change the password using the password change 
form where you have to provide the existing password and the new password. If the existing password is correct, the password
change is successful.
