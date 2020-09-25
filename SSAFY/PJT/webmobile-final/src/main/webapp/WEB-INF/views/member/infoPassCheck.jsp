<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
 <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Awesome Place</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<link rel="stylesheet" href="resources/css/myinfo.css">
</head>
<body>
<div class="wrapper__main">
		<ul class="nav">
		    <li>
		        <a href="main.do" class="header__logo">
		            Awesome Place
		        </a>
		    </li>
		
		    <li class="header__dropdown">
				<a href="javascript:void(0)" class="header__button">Menu</a> 
				<c:if test="${sessionScope.ID eq null}">
					<div class="header__content">
						<a href="loginForm.do">SignIn</a> 
						<a href="signUpForm.do">SignUp</a>
					</div>
				</c:if> 
				<c:if test="${sessionScope.ID ne null}">
					<div class="header__content">
						<a href="logout.do">logout</a> 
						<a href="myinfo.do">MyInfo</a>
						<c:if test="${sessionScope.memberType eq 'admin'}">
							<a href="memberlist.do">memberlist</a>
							<a href="insertplace.do">insertplace</a>
						</c:if>
					</div>
				</c:if>
			</li>
		</ul>
		
		<div class="main">
			<div class="info__form">
			<p>My Information</p>
			<p>반갑습니다 ${member.name }님</p>
			<p>개인 정보 확인을 원하시면 비밀번호를 입력해 주세요</p>
			
			<input type="password" class="inputForm" id="password" placeholder="Insert Your Password" onkeydown="JavaScript:Enter_Check();">
			
			<button onclick="checkPass()" class="btn__success">확인</button>
			</div>
		</div>
		<div class="footer">
		    <p>Copyright 2019. 공수래공수거. ALL RIGTHS RESERVED.</p>
		</div>
</div>
		
	<script type="text/javascript">
		function Enter_Check(){
	        if(event.keyCode == 13){
	        	checkPass();
	        }
	    }
		function checkPass(){
				var pass = $("#password").val();
	  			
				if(pass == null || pass ==""){
					alert("비밀번호는 필수 입니다");
					$("#password").focus();
				}else{
					
					var form_data ={
							user_pw:pass
						};
					
					$.ajax({
						type : "POST",
						url : "passCheck.do",
						data : form_data,
						success : function(data){
							if (data.chk == 1) {
								location.href="infoUpdate.do"
							}else if(data.chk != 1){
								alert("비밀번호를 확인해 주세요");
								$("#password").val('');
								$("#password").focus();
								return false;
							}
						},
						error : function(error){
							alert("error");
						}
					});
				}
		
		}
	</script>
</body>
</html>