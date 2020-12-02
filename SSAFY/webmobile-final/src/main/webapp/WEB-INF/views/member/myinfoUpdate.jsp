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
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@8"></script>
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
					  <button class="tablinks active" onclick="openMenu(event, 'MyInfo')">기본 정보</button>
					  <button class="tablinks" onclick="openMenu(event, 'Password')">비밀번호 변경</button>
					  <button class="tablinks" onclick="openMenu(event, 'Delete')">회원 탈퇴</button>
					</div>
					
					<div id="MyInfo" class="tabcontent">
						<div class="info__form">
							<p>아이디</p>
							<input type="text" class="inputForm" value="${member.id }" disabled="disabled">
							
							<p>휴대폰 번호</p>
							<input type="text" id="phoneNum" class="inputForm" value="${member.phonenum }" onKeyup="inputPhoneNumber(this);">
							<small>※ 정확한 연락처 정보를 입력해 주세요</small>
							
							<p>E-Mail</p>
							<input type="text" id="mail" class="inputForm" value="${member.email }">
							<small>※ 비밀번호 찾기에 활용되니 정확한  정보를 입력해 주세요</small>
						</div>
						<button onclick="submit()" class="btn submit">저장</button>
						<button onclick="" class="btn cancel">취소</button>
					</div>
					
					<div id="Password" class="tabcontent">
						<div class="info__form">
							<p>현재 비밀번호</p>
							<input type="password" id="password00" class="inputForm pass" placeholder="Now Password"/>
							<p>비밀번호</p>
							<input type="password" id="password01" class="inputForm pass" placeholder="Change Password"/>
							<input type="password" id="password02" class="inputForm"  onkeyup="passChk()" placeholder="Repeate Password"/>
							<small id="passEq" hidden="true" class="warnMessage" style="color:#0058ff">비밀번호가 일치 합니다</small>
							<small id="passNEq" hidden="true" class="warnMessage" style="color: #dc0000">비밀번호가 불일치 합니다</small>
						</div>
						
						<button onclick="passChange()" class="btn submit">저장</button>
						<button onclick="" class="btn cancel">취소</button>
					</div>

					<div id="Delete" class="tabcontent">
						<div class="info__form">
							<p>아이디</p>
							<input type="text" id="delete__id" class="inputForm pass" value="${member.id }" disabled="disabled"/>
							<p>비밀번호</p>
							<input type="password" id="delete__pass01" class="inputForm pass" placeholder="Password"/>
							<input type="password" id="delete__pass02" class="inputForm"   onkeyup="deletePassChk()" placeholder="Repeate Password"/>
							<small id="passEq1" hidden="true" class="warnMessage" style="color:#0058ff">비밀번호가 일치 합니다</small>
							<small id="passNEq1" hidden="true" class="warnMessage" style="color: #dc0000">비밀번호가 불일치 합니다</small>
						</div>
						
						<button onclick="deleteAccount()" class="btn submit">저장</button>
						<button onclick="" class="btn cancel">취소</button>
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
			
	  	
	  	function submit(){
			var mail = $("#mail").val();
  			var phone = $("#phoneNum").val();
  			
				var form_data ={
						user_mail:mail,
						user_phone:phone
					};
				
				$.ajax({
					type : "POST",
					url : "updateMyInfo.do",
					data : form_data,
					success : function(data){
						if (data.chk == 1) {
							alert("정보가 수정 되었습니다.\n메인화면으로 이동합니다.");
							location.href="main.do"
						}else if(data.chk != 1){
							alert("정보 수정 실패");
							return false;
						}
					},
					error : function(error){
						alert("error");
					}
				});
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
  		
		function openMenu(evt, menuName) {
		  var i, tabcontent, tablinks;
		  tabcontent = document.getElementsByClassName("tabcontent");
		  for (i = 0; i < tabcontent.length; i++) {
		    tabcontent[i].style.display = "none";
		  }
		  tablinks = document.getElementsByClassName("tablinks");
		  for (i = 0; i < tablinks.length; i++) {
		    tablinks[i].className = tablinks[i].className.replace(" active", "");
		  }
		  document.getElementById(menuName).style.display = "block";
		  evt.currentTarget.className += " active";
		}
		
		
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
		
		
		function deletePassChk(){
  			var pass1 = $("#delete__pass01").val();
  			var pass2 = $("#delete__pass02").val();
  			
  			if(pass1 == pass2){
  				$("#passNEq1").attr("hidden",true);
  				$("#passEq1").removeAttr("hidden");
  				passFlag = 1;
  			}else{
  				$("#passEq1").attr("hidden",true);
  				$("#passNEq1").removeAttr("hidden");
  				passFlag = 0;
  			}
  		}  
		
		
		function passChange(){
			
			var nowpass = $("#password00").val();
			var pass = $("#password01").val();
			if(nowpass == null || nowpass ==''){
				alert("현재 비밀번호를 입력해 주세요");
				$("#password00").focus();
				return false;
			}else if(passFlag != 1){
				alert("바꿀 비밀번호가 일치하지 않습니다");
				return false;
			}else{
				var form_data={
						now_pw:nowpass,
						user_pw:pass
				};
				
				$.ajax({
					type : "POST",
					url : "changePass.do",
					data : form_data,
					success : function(data){
						if (data.chk == 0) {
							alert("서버오류 \n잠시후에 다시 시도해 주세요");
							return false;
						}else if(data.chk == 1){
							Swal.fire({
								  type: 'success',
								  title: 'Success',
								  text: '비밀번호 변경 성공! \n메인화면으로 이동합니다',
								});
							location.href="main.do"
						}else if(data.chk ==2) {
							alert("현재 비밀번호가 일치하지 않습니다.")
							$("#password00").focus();
							return false;
						}
					},
					error : function(error){
						alert("error");
					}
				});
			}
		}
		
		function deleteAccount() {
			var id = $("#delete__id").val();
			var pass = $("#delete__pass01").val();
			var pass02 = $("#delete__pass02").val();
			
			if(id == null || id ==''){
				alert("아이디를 입력해 주세요");
				$("#delete__id").focus();
				return false;
			}else if(pass ==null || pass == ''){
				alert("비밀번호를 입력해 주세요");
				$("#delete__pass01").focus();
				return false;
			}else if(pass != pass02){
				alert("비밀번호가 일치하지 않습니다.");
				return false;
			}else{
				
				Swal.fire({
					  title: '탈퇴 하시겠습니까?',
					  text: "탈퇴하시면 데이터를 복구 할 수 없습니다.",
					  type: 'warning',
					  showCancelButton: true,
					  confirmButtonColor: '#3085d6',
					  cancelButtonColor: '#d33',
					  cancelButtonText: '취소',
					  confirmButtonText: '탈퇴하기'
					}).then((result) => {
					  if (result.value) {
						  var form_data={
									user_id:id,
									user_pw:pass
							};
							
							$.ajax({
								type : "POST",
								url : "deleteAccount.do",
								data : form_data,
								success : function(data){
									if (data.chk == 0) {
										Swal.fire({
											  type: 'error',
											  title: 'Fail',
											  text: '입력하신 정보가 일치하지 않습니다.',
											});
										return false;
									}else if(data.chk == 1){
										Swal.fire({
											  type: 'success',
											  title: 'Success',
											  text: '탈퇴 처리 되었습니다. \n이용해주셔서 감사합니다.',
											}).then((result)=>{
												location.href="main.do"
											});
									}else if(data.chk ==2) {
										alert("서버 오류입니다.")
										return false;
									}
								},
								error : function(error){
									alert("error");
								}
							});
					  }
					});
			}
		}
</script>
</body>
</html>