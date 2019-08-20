$(document).ready(function(){
	$('#signupbutton').click(function(){
		$.ajax({
			url:'signup',
			type:'POST',
			data:$('#forsignup').serialize(),
//			contentType: ";charset=UTF-8",
			success: function(responsetext){
				$('#ajaxsignupresponse').text(responsetext).fadeIn(1000);
				$('#ajaxsignupresponse').fadeOut(5000);
				
			}
		});
		
	});
	
	
	$('#loginbutton').click(function(){
		$.ajax({
			url:'login',
			type:'POST',
			data:$('#forlogin').serialize(),
//			contentType: ";charset=UTF-8",
			success: function(responsetext){
				if(responsetext==="Login")
				{
					$("#again_emailorphno").val($("#emailorphno").val());
					$("#again_psw").val($("#psw").val());
					
					console.log($("#emailorphno").val());
					console.log($("#psw").val());
					$("#loginform").submit();
				}
				else
					$('#ajaxloginresponse').text(responsetext).fadeIn(1000);
					$('#ajaxloginresponse').fadeOut(5000);
				
			}
		});
		
	});
});