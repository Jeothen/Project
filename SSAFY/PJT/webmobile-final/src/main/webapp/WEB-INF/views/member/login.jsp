<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Awesome Place</title>
    <link rel="stylesheet" href="resources/css/login.css">
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
     <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
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
                <div class="header__content">
                    <a href="loginForm.do">SignIn</a>
                    <a href="signUpForm.do">SignUp</a>
                </div>
            </li>
        </ul>

        <div class="main">
        	<div class="main__image">
        		<div class="main__title__back">
	        		<span class="main__title"><b>당신을 위한 최고의 장소</b></span>
        		</div>
        	</div>
        		<input type="text" class="inputForm" id="userID" name="id" placeholder="ID..."/>
        		<input type="password" class="inputForm" id="userPass" name="password" placeholder="Password..." onkeydown="JavaScript:Enter_Check();"/>
        		<button onclick="login()" class="loginBtn">Login</button>
        	<hr/>
        		<div class="login__menu">
	        		<a onclick="location.href='findMyInfo.do'">Find My Info</a>
	        		<a onclick="location.href='signUpForm.do'">Sign Up</a>
        		</div>
        </div>
        <div class="footer">
            <p>Copyright 2019. 공수래공수거. ALL RIGTHS RESERVED.</p>
        </div>
    </div>
    
    <script type="text/javascript">
//     	로그인 함수 ajax
    	function login(){
    			var id = $("#userID").val();
    			var pass = $("#userPass").val();
      			
    			if (id == null || id =="") {
    				alert("아이디를 입력해주세요 ")
    				$("#userID").focus();
    				return false;
    			}else if(pass == null || pass ==""){
    				$("#userPass").focus();
    				alert("비밀번호는 필수 입니다")
    				return false;
    			}else{
    				var form_data ={
    						user_id:id,
    						user_pw:pass,
    					};

    				$.ajax({
    					type : "POST",
    					url : "login.do",
    					data : form_data,
    					success : function(data){
    						if (data.chk == 1) {
    							location.href="main.do"
    						}else if(data.chk != 1){
    							alert("입력하신 정보가 일치하지 않습니다.")
    							return false;
    						}
    					},
    					error : function(error){
    						alert("error");
    					}
    				});
    			}
    	}
    	
    	function Enter_Check(){
	        if(event.keyCode == 13){
	        	login();
	        }
	    }
	</script>
</body>
</html>