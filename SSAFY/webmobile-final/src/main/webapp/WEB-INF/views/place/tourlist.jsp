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
							<a href="memberlist.do">adminpage</a>
						</c:if>
					</div>
				</c:if>
			</li>
		</ul>
		<div style="float: left;">
				<a href="memberlist.do">회원관리</a>
				<a href="#">관광지관리</a>
		</div>
		<!-- 메인 -->
		<div class="main" id="Member">
			
			<div style="float: right;">
				<form method="get" action="tourlist.do">
					<div class="searchForm__wrapper">
						<div class="searchForm__left">
							<select name="searchOption">
								<c:choose>
									<c:when test="${searchOption eq 'TITLE'}">
										<option value="CONTENTID">번호</option>
										<option value="TITLE" selected="selected">제목</option>
										<option value="CONTENTTYPE">타입</option>
										<option value="ADDRESS">주소</option>
									</c:when>
									<c:when test="${searchOption eq 'CONTENTTYPE'}">
										<option value="CONTENTID">번호</option>
										<option value="TITLE">제목</option>
										<option value="CONTENTTYPE" selected="selected">타입</option>
										<option value="ADDRESS">주소</option>
									</c:when>
									<c:when test="${searchOption eq 'ADDRESS'}">
										<option value="CONTENTID">번호</option>
										<option value="TITLE">제목</option>
										<option value="CONTENTTYPE">타입</option>
										<option value="ADDRESS" selected="selected">주소</option>
									</c:when>
									<c:otherwise>
										<option value="CONTENTID" selected="selected">번호</option>
										<option value="TITLE">제목</option>
										<option value="CONTENTTYPE">타입</option>
										<option value="ADDRESS">주소</option>
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
			<div>
				<a href="insertplace.do">관광지 추가</a>
			</div>
			<div
				style="min-height: 350px; border-top: 1px solid black; border-bottom: 1px solid black; margin: 10px 0;">
				<div id="table">
					<div class="row">
						<span class="cell table__id">번호</span>
						<span class="cell table__name">제목</span>
						<span class="cell table__phone">타입</span>
						<span class="cell table__email">주소</span>
						<span class="cell table__userType">권한관리</span>
					</div>

					<c:forEach items="${tourlist}" var="tour">
						<div class="row">
							<span class="cell table__id">${tour.contentId}</span>
							<span class="cell table__name">${tour.title}</span>
							<c:forEach items="${contenttype}" var="type"> 
								<c:if test="${type.contentTypeId eq tour.contentTypeId}">
									<span class="cell table__phone">${type.name}</span>
								</c:if>
							</c:forEach>
							
							<span class="cell table__email">${tour.address}</span>
							<span class="cell table__userType">
							<button onclick="location.href='placeDetail.do?id=${tour.contentId}&type=${tour.contentTypeId}'">자세히보기</button>
							<button onclick="location.href='deleteTour.do?id=${tour.contentId}'">삭제</button>
							</span>
						</div>
					</c:forEach>
				</div>
			</div>

			<div class="pagination" style="display: flex; align-items: center; justify-content: center;">
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
				var url = "tourlist.do";
				url = url + "?page=" + page;
				url = url + "&range=" + range;
				url = url + "&searchOption=" + searchOption;
				url = url + "&keyword=" + keyword;
				location.href = url;
			}
			function fn_pagination(page, range, rangeSize, searchOption, keyword) {
				var url = "tourlist.do";
				url = url + "?page=" + page;
				url = url + "&range=" + range;
				url = url + "&searchOption=" + searchOption;
				url = url + "&keyword=" + keyword;
				location.href = url;
			}
			function fn_next(page, range, rangeSize, searchOption, keyword) {
				var page = parseInt((range * rangeSize)) + 1;
				var range = parseInt(range) + 1;
				var url = "tourlist.do";
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