<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>Awesome Place - Admin</title>
	<link rel="stylesheet" href="resources/css/memberlist.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

</head>

<body>
	<div class="wrapper__main">
		<!-- 네비게이션 바 -->
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
		<div style="float: left;">
				<a href="#">회원관리</a>
				<a href="tourlist.do">관광지관리</a>
		</div>
		<!-- 메인 -->
		<div class="main" id="Member">
			
			<div style="float: right;">
				<form method="get" action="memberlist.do">
					<div class="searchForm__wrapper">
						<div class="searchForm__left">
							<select name="searchOption">
								<c:choose>
									<c:when test="${searchOption eq 'NAME'}">
										<option value="ID">아이디</option>
										<option value="NAME" selected="selected">이름</option>
										<option value="PHONENUM">전화번호</option>
										<option value="EMAIL">이메일</option>
									</c:when>
									<c:when test="${searchOption eq 'PHONENUM'}">
										<option value="ID">아이디</option>
										<option value="NAME">이름</option>
										<option value="PHONENUM" selected="selected">전화번호</option>
										<option value="EMAIL">이메일</option>
									</c:when>
									<c:when test="${searchOption eq 'EMAIL'}">
										<option value="ID">아이디</option>
										<option value="NAME">이름</option>
										<option value="PHONENUM">전화번호</option>
										<option value="EMAIL" selected="selected">이메일</option>
									</c:when>
									<c:otherwise>
										<option value="ID" selected="selected">아이디</option>
										<option value="NAME">이름</option>
										<option value="PHONENUM">전화번호</option>
										<option value="EMAIL">이메일</option>
									</c:otherwise>
								</c:choose>
							</select>
						</div>
						<div class="searchForm__right">
							<input id="searchForm__input" name="keyword" value="${keyword}" />
							<button id="searchForm__submit" type="submit">조회</button>
						</div>
					</div>
				</form>
			</div>
			
			<div style="clear: both;"></div>

			<div
				style="min-height: 350px; border-top: 1px solid black; border-bottom: 1px solid black; margin: 10px 0;">
				<div id="table">
					<div class="row">
						<span class="cell table__id">아이디</span>
						<span class="cell table__name">이름</span>
						<span class="cell table__phone">전화번호</span>
						<span class="cell table__email">이메일</span>
						<span class="cell table__userType">권한관리</span>
					</div>

					<c:forEach items="${memberlist}" var="member">
						<div class="row">
							<span class="cell table__id">${member.id}</span>
							<span class="cell table__name">${member.name}</span>
							<span class="cell table__phone">${member.phonenum}</span>
							<span class="cell table__email">${member.email}</span>
							<span class="cell table__userType">
								<c:choose>
									<c:when test="${member.membertype eq 'admin'}">
										<button
											onclick="location.href='updateMemberType.do?id=${member.id}'">강등</button>
									</c:when>
									<c:otherwise>
										<button
											onclick="location.href='updateMemberType.do?id=${member.id}'">승격</button>
										<button onclick="location.href='deleteMember.do?id=${member.id}'">탈퇴</button>
									</c:otherwise>
								</c:choose>
							</span>
						</div>
					</c:forEach>
				</div>
			</div>

			<div class="pagination" style="display: flex; align-items: center; justify-content: center;" id="Member">
				<!-- 이전버튼 -->
				<c:if test="${pagination.prev }">
					<a href="#"
						onclick="fn_prev('${pagination.page}','${pagination.range}','${pagination.rangeSize}','${searchOption}','${keyword}')">&laquo;</a>
				</c:if>
				<!-- 인덱스버튼 -->
				<c:forEach begin="${pagination.startPage}" end="${pagination.endPage}" var="idx">
					<a href="#" class="${pagination.page==idx ? 'active' : '' }"
						onclick="fn_pagination('${idx}','${pagination.range}','${pagination.rangeSize}','${searchOption}','${keyword}')">
						${idx}
					</a>
				</c:forEach>
				<!-- 이후버튼 -->
				<c:if test="${pagination.next}">
					<a href="#" class="page-link" href="#"
						onclick="fn_next('${pagination.page}','${pagination.range}','${pagination.rangeSize}','${searchOption}','${keyword}')">&raquo;</a>
				</c:if>
			</div>

			<div class="footer">
				<p style="margin: 7px;">Copyright 2019. 공수래공수거. ALL RIGTHS RESERVED.</p>
			</div>
		</div>

		<script type="text/javascript">
			function place(){
				$("#Member").attr("hidden");
				$("#Place").removeAttr("hidden");
			}
			function fn_prev(page, range, rangeSize, searchOption, keyword) {
				var page = ((range - 2) * rangeSize) + 1;
				var range = range - 1;
				var url = "memberlist.do";
				url = url + "?page=" + page;
				url = url + "&range=" + range;
				url = url + "&searchOption=" + searchOption;
				url = url + "&keyword=" + keyword;
				location.href = url;
			}
			function fn_pagination(page, range, rangeSize, searchOption, keyword) {
				var url = "memberlist.do";
				url = url + "?page=" + page;
				url = url + "&range=" + range;
				url = url + "&searchOption=" + searchOption;
				url = url + "&keyword=" + keyword;
				location.href = url;
			}
			function fn_next(page, range, rangeSize, searchOption, keyword) {
				var page = parseInt((range * rangeSize)) + 1;
				var range = parseInt(range) + 1;
				var url = "memberlist.do";
				url = url + "?page=" + page;
				url = url + "&range=" + range;
				url = url + "&searchOption=" + searchOption;
				url = url + "&keyword=" + keyword;
				location.href = url;
			}
		</script>
	</div>
</body>

</html>