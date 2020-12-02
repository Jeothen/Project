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
		<div class="wrapper__main">
		<ul class="nav">
			<li><a href="main.do" class="header__logo"> Awesome Place </a></li>

			<li class="header__dropdown">
				<c:if test="${sessionScope.memberType eq 'admin'}">
					<a href="javascript:void(0)" class="header__button" style="padding: 15px 10px 15px 65px;">
						Menu
					</a>
				</c:if>
				<c:if test="${sessionScope.memberType ne 'admin'}">
					<a href="javascript:void(0)" class="header__button" style="padding: 15px 10px 15px 28px;">
						Menu
					</a>
				</c:if>

				<c:if test="${sessionScope.ID eq null}">
					<div class="header__content">
						<a href="loginForm.do">SignIn</a>
						<a href="signUpForm.do">SignUp</a>
					</div>
				</c:if>
				<c:if test="${sessionScope.ID ne null}">
					<div class="header__content">
						<a href="logout.do">Signout</a>
						<a href="myinfo.do">MyInfo</a>
						<c:if test="${sessionScope.memberType eq 'admin'}">
							<a href="memberlist.do">AdminPage</a>
						</c:if>
					</div>
				</c:if>
			</li>
		</ul>
		
		<div class="main">
			<div>
					<div class="tab">
					  <button class="tablinks active" onclick="openMenu(event, 'MyInfo')">아이디 찾기</button>
					  <button class="tablinks" onclick="openMenu(event, 'Password')">비밀번호 찾기</button>
					</div>
					
					<div id="MyInfo" class="tabcontent">
						<div class="info__form">
							<p>이름</p>
							<input type="text" id="id__name" class="inputForm" placeholder="Insert Your Name">
							
							<p>이메일</p>
							<input type="text" id="id__mail" class="inputForm" placeholder="Insert Your Mail">
							<small>※ 가입시 사용한 메일 주소를 입력해 주세요</small>
							<br/>
						<small>※ 서버 응답에 시간이 걸릴 수 있습니다. ※</small>
						</div>
						<button onclick="findID()" class="btn submit">아이디 찾기</button>
						<button onclick="location.href='main.do'" class="btn cancel">취소</button>
					</div>
					
					<div id="Password" class="tabcontent">
						<div class="info__form">
							<p>ID</p>
							<input type="text" id="pass__id" class="inputForm" placeholder="Insert Your ID"/>
							<p>이메일</p>
							<input type="text" id="pass__mail" class="inputForm" placeholder="Insert Your Mail"/>
							<small>※ 가입시 사용한 메일 주소를 입력해 주세요</small>
							<br/>
						<small>※ 서버 응답에 시간이 걸릴 수 있습니다. ※</small>
						</div>
						
						<button onclick="findPassword()" class="btn submit">비밀번호 찾기</button>
						<button onclick="location.href='main.do'" class="btn cancel">취소</button>
					</div>
			</div>
		</div>
		<div class="footer">
		    <p>Copyright 2019. 공수래공수거. ALL RIGTHS RESERVED.</p>
		</div>
</div>
	
	<script type="text/javascript">
	  	var flag = 0;
	  	var passFlag = 0;
  	
  		function findID(){
			var name = $("#id__name").val();
  			var mail = $("#id__mail").val();
  			
  			if(name == null || name ==''){
				alert("이름을 입력해 주세요");
				$("#id__name").focus();
				return false;
			}else if(mail == null || mail==''){
				alert("이메일을 입력해 주세요");
				$("#id__mail").focus();
				return false;
			}else{
  				var form_data ={
						user_name:name,
						user_mail:mail
					};
				
				$.ajax({
					type : "POST",
					url : "findMyID.do",
					data : form_data,
					success : function(data){
						if (data.chk == 1) {
							alert("가입하신 메일로 아이디를 전송했습니다.\n로그인 화면으로 이동합니다.")
							location.href="loginForm.do"
						}else if(data.chk != 1){
							alert("가입한 정보가 없습니다.");
							return false;
						}
					},
					error : function(error){
						alert("error");
					}
				});
 	 		}
		}
		
		function findPassword(){
			var id = $("#pass__id").val();
			var mail = $("#pass__mail").val();
			if(id == null || id ==''){
				alert("아이디를 입력해 주세요");
				$("#pass__id").focus();
				return false;
			}else if(mail == null || mail ==''){
				alert("이메일을 입력해 주세요");
				$("#pass__mail").focus();
				return false;
			}else{
				var form_data={
						user_id:id,
						user_mail:mail
				};
				
				$.ajax({
					type : "POST",
					url : "findMyPass.do",
					data : form_data,
					success : function(data){
						if (data.chk == 1) {
							alert("임시 비밀번호를 전송 했습니다. \n로그인 화면으로 이동합니다.")
							location.href="loginForm.do"
						}else if(data.chk != 1){
							alert("정보가 일치하지 않거나 존재하지 않습니다.");
							return false;
						}
					},
					error : function(error){
						alert("error");
					}
				});
			}
		}
  		
		function openMenu(evt, Menuname) {
		  var i, tabcontent, tablinks;
		  tabcontent = document.getElementsByClassName("tabcontent");
		  for (i = 0; i < tabcontent.length; i++) {
		    tabcontent[i].style.display = "none";
		  }
		  tablinks = document.getElementsByClassName("tablinks");
		  for (i = 0; i < tablinks.length; i++) {
		    tablinks[i].className = tablinks[i].className.replace(" active", "");
		  }
		  document.getElementById(Menuname).style.display = "block";
		  evt.currentTarget.className += " active";
		}
</script>
</body>
</html>