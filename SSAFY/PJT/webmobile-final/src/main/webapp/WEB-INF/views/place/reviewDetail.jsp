<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">

<head>
	<meta charset="UTF-8">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>Awesome Place</title>
	<link rel="stylesheet" href="resources/css/detail.css">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>

<body>
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
				<div class="info_header">
					<div class="header__left">
						<span>사용자 후기</span>
					</div>
				</div>
			</div>
		</div>

		<div class="footer">
			<p style="margin: 7px;">Copyright 2019. 공수래공수거. ALL RIGTHS RESERVED.</p>
		</div>
	</div>
	<script>
		// 사용자가 남긴 리뷰 사진들 슬라이드쇼
		function currentDiv(n) {
			showDivs(slideIndex = n);
		}

		function showDivs(n) {
			var i;
			var x = document.getElementsByClassName("mySlides");
			var dots = document.getElementsByClassName("demo");
			if (n > x.length) { slideIndex = 1 }
			if (n < 1) { slideIndex = x.length }
			for (i = 0; i < x.length; i++) {
				x[i].style.display = "none";
			}
			for (i = 0; i < dots.length; i++) {
				console.log('Hi')
				dots[i].className = dots[i].className.replace(" w3-opacity-off", "");
			}
			x[slideIndex-1].style.display = "block";
			dots[slideIndex-1].className += " w3-opacity-off";
		}

		function nosess() {
			alert("로그인 후 이용해주세요!");
		}
	</script>

</body>

</html>