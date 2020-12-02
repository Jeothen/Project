<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Awesome Place</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<link rel="stylesheet" href="resources/css/signup.css">
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
					<a href="javascript:void(0)" class="header__button" style="padding: 15px 10px 15px 29px;">
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
			<form id="signupForm" method="post">
				 <div>
				   <input type="text" name="id" id="userID" class="inputForm idForm" placeholder="Enter ID...">
				   <button type="button" id="checkBtn" class="checkbutton" onclick="idCheck()">중복체크</button>
				 </div>
				 <div>
				   <input type="password" class="passwordForm" name="password" id="password01" placeholder="Enter Password..." />
				   <input type="password" class="passwordForm2" id="password02"  onkeyup="passChk()" placeholder="Repeat Password..."/>
				 </div>
					<small id="passEq" hidden="true" class="warnMessage" style="color:#0058ff">비밀번호가 일치 합니다</small>
					<small id="passNEq" hidden="true" class="warnMessage" style="color: #dc0000">비밀번호가 불일치 합니다</small>
				 <div>
					<input type="text" id="phone" class="inputForm" onKeyup="inputPhoneNumber(this);" maxlength="13" placeholder="Enter Your PhoneNumber"/>
					<input type="text" id="name" class="inputForm" name="name" placeholder="Enter Your Name..."/>
					<input type="email" id="mail" class="inputForm" name="mail" placeholder="Enter Your E-mail..."/>
				 </div>
			</form>
		<button type="button" class="formBtn left-btn" onclick="submit()">Join</button>
		<button type="button" class="formBtn btn-warn" onclick="history.back()">Cancel</button>
		
		</div>
		<div class="footer">
		    <p>Copyright 2019. 공수래공수거. ALL RIGTHS RESERVED.</p>
		</div>
</div>
		
	<script type="text/javascript">
  	
  	var flag = 0;
  	var passFlag = 0;
  		function idCheck(){
  			var id = $("#userID").val();
  			
  			if (id == null || id =="") {
				alert("아이디를 입력 하세요");
				$("#userID").focus();
			}else{
				var form_data ={
						user_id:id
					};
				
				$.ajax({
					type : "POST",
					url : "idCheck.do",
					data : form_data,
					success : function(data){
						if (data.chk == 1) {
							alert("이미 사용중인 아이디 입니다.");
						}else if(data.check != 1){
							var con_test = confirm("사용 가능한 아이디 입니다. \n사용 하시겠습니까?");
							if(con_test == true){
								$("#userID").attr("readonly",true);
								$("#checkBtn").attr("disabled",true);
								flag = 1;
							}
							else if(con_test == false){
								return false;
							}
						}
					},
					error : function(error){
						alert("error");
					}
				});
			}
  		};
  		
  		
  		function passChk(){
  			var pass1 = $("#password01").val();
  			var pass2 = $("#password02").val();
  			
  			if(pass1 == pass2){
  				$("#passNEq").attr("hidden",true);
  				$("#passEq").removeAttr("hidden");
  				passFlag = 1;
  			}else{
  				$("#passEq").attr("hidden",true);
  				$("#passNEq").removeAttr("hidden");
  				passFlag = 0;
  			}
  		}
  		
  		function submit(){
			var id = $("#userID").val();
			var pass = $("#password01").val();
			var name = $("#name").val();
			var mail = $("#mail").val();
  			var phone = $("#phone").val();
  			
			if (id == null || id =="") {
				alert("아이디를 입력해주세요 ")
			}else if(pass == null || pass ==""){
				alert("비밀번호는 필수 입니다")
			}else if(name == null || name ==""){
				alert("이름은 필수 입니다")
			}else if(mail == null || mail ==""){
				alert("이메일은 필수 입니다")
			}else if (flag != 1) {
				alert("아이디 중복검사를 해주세요");
				return false;
			}else if(passFlag != 1){
				alert("비밀번호가 일치하지 않습니다");
				return false;
			}else{
				
				var form_data ={
						user_id:id,
						user_pw:pass,
						user_name:name,
						user_mail:mail,
						user_phone:phone
					};
				
				$.ajax({
					type : "POST",
					url : "signUp.do",
					data : form_data,
					success : function(data){
						if (data.chk == 1) {
							alert("회원가입 실패");
							return false;
						}else if(data.chk != 1){
							alert("회원가입 성공! \n메인화면으로 이동합니다.")
							location.href="main.do"
						}
					},
					error : function(error){
						alert("error");
					}
				});
			}
  		}
  		
  		
  		function inputPhoneNumber(obj) {



  		    var number = obj.value.replace(/[^0-9]/g, "");
  		    var phone = "";



  		    if(number.length < 4) {
  		        return number;
  		    } else if(number.length < 7) {
  		        phone += number.substr(0, 3);
  		        phone += "-";
  		        phone += number.substr(3);
  		    } else if(number.length < 11) {
  		        phone += number.substr(0, 3);
  		        phone += "-";
  		        phone += number.substr(3, 3);
  		        phone += "-";
  		        phone += number.substr(6);
  		    } else {
  		        phone += number.substr(0, 3);
  		        phone += "-";
  		        phone += number.substr(3, 4);
  		        phone += "-";
  		        phone += number.substr(7);
  		    }
  		    obj.value = phone;
  		}
  	</script>
  	
</body>
</html>