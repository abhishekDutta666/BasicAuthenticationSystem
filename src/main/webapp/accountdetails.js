$(document).ready(function(){
	$('#namechange').click(function(){
		console.log("namechange");
		$.ajax({
			url:'updatename',
			type:'POST',
			data:$('#changedetails').serialize(),
			success: function(responsetext){
				console.log("namechanges");
				$('#ajaxupdatereply').text(responsetext).fadeIn(1000);
				$('#ajaxupdatereply').fadeOut(5000);
				
			}
		});
		
	});
	$('#emailchange').click(function(){
		console.log("emailchange");
		
		$.ajax({
			url:'updateemail',
			type:'POST',
			data:$('#changedetails').serialize(),
			success: function(responsetext){
				console.log("emailchanges");
				if(responsetext==="success")
				{
					$(".oldemail").val($("#emailinput").val());
					$('#ajaxupdatereply').text("Email changed successfully").fadeIn(1000);
					$('#ajaxupdatereply').fadeOut(5000);	
					
				}
				else if(responsetext==="failure1")
				{
					$('#ajaxupdatereply').text("Email already exists").fadeIn(1000);
					$('#ajaxupdatereply').fadeOut(5000);
				}
				else if(responsetext==="failure2")
				{
					$('#ajaxupdatereply').text("new email is the same as the old email").fadeIn(1000);
					$('#ajaxupdatereply').fadeOut(5000);
				}
				else if(responsetext==="failure3")
				{
					$('#ajaxupdatereply').text("Please enter a proper email").fadeIn(1000);
					$('#ajaxupdatereply').fadeOut(5000);
				}
			}
		});
		
	});
	$('#phonechange').click(function(){
		console.log("phonechange");
		var temp=$('#phno').val();
		$.ajax({
			url:'updatephone',
			type:'POST',
			data:$('#changedetails').serialize(),
			success: function(responsetext){
				console.log("phonechanges");
				if(responsetext==="success")
				{
					$(".oldphno").val($("#phnoinput").val());
					$('#ajaxupdatereply').text("Phone number changed successfully").fadeIn(1000);
					$('#ajaxupdatereply').fadeOut(5000);
					
				}
				else if(responsetext==="failure1")
				{
					$('#ajaxupdatereply').text("Phone number already exists").fadeIn(1000);
					$('#ajaxupdatereply').fadeOut(5000);
				}
				else if(responsetext==="failure2")
				{
					$('#ajaxupdatereply').text("new phone number is the same as the old phone number").fadeIn(1000);
					$('#ajaxupdatereply').fadeOut(5000);
				}
				else if(responsetext==="failure3")
				{
					$('#ajaxupdatereply').text("Please enter a proper phone number").fadeIn(1000);
					$('#ajaxupdatereply').fadeOut(5000);
				}
				
			}
		});
		
	});
	$('#addresschange').click(function(){
		console.log("addresschange");
		$.ajax({
			url:'updateaddress',
			type:'POST',
			data:$('#changedetails').serialize(),
			success: function(responsetext){
				console.log("addresschanges");
				$('#ajaxupdatereply').text(responsetext).fadeIn(1000);
				$('#ajaxupdatereply').fadeOut(5000);
				
			}
		});
		
	});
	
	
	$('#passwordbutton').click(function(){
		console.log("password change");
		$.ajax({
			url:'changepassword',
			type:'POST',
			data:$('#changepasswordform').serialize(),
			success: function(responsetext){
				console.log("password change");
				if(responsetext==="success")
				{
					$('#ajaxpassword').text(responsetext).fadeIn(1000);
					$('#ajaxpassword').fadeOut(5000);
					$("#actualpassword").val($("#newpassword").val());
					$("#existingpassword").val("");
					$("#newpassword").val("");
					$("#renewpassword").val("");
					console.log("success");
				}
				else if(responsetext==="failure1")
				{
					$('#ajaxpassword').text("the existing password that is entered is wrong").fadeIn(1000);
					$('#ajaxpassword').fadeOut(5000);
					$("#existingpassword").val("");
					$("#newpassword").val("");
					$("#renewpassword").val("");
					console.log("failure1");
				}
				else if(responsetext==="failure2")
				{
					$('#ajaxpassword').text("the new password is the same as the old password").fadeIn(1000);
					$('#ajaxpassword').fadeOut(5000);
					$("#existingpassword").val("");
					$("#newpassword").val("");
					$("#renewpassword").val("");
					console.log("failure2");
				}
				else if(responsetext==="failure3")
				{
					$('#ajaxpassword').text("the new password entered and the rewritten password do not match").fadeIn(1000);
					$('#ajaxpassword').fadeOut(5000);
					$("#existingpassword").val("");
					$("#newpassword").val("");
					$("#renewpassword").val("");
					console.log("failure3");
				}
				
			}
		});
		
	});
});