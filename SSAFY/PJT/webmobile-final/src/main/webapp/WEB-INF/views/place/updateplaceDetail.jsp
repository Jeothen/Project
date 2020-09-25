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
	<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>

<body>
	<div class="wrapper__main">
		<ul class="nav">
			<li>
				<a href="#" class="header__logo">
					Awesome Place
				</a>
			</li>

			<li class="header__dropdown">
				<a href="javascript:void(0)" class="header__button">Menu</a>
				<div class="header__content">
					<a href="">SignIn</a>
					<a href="">SignUp</a>
				</div>
			</li>
		</ul>

		<div class="main">
			<div class="place">
				<img class="image" src="${tourInfo.image}">
				<div class="place__detail">
					<div class="mainHeader__left">
						<h1>${tourInfo.title }</h1>
					</div>
					<div class="mainHeader__right rate">
						<h1>4.1</h1>
					</div>
					<div style="clear: both;"></div>
				</div>
			</div>
			<div class="icon">
				<div>
					<a onClick='findWay()'>
						<i class="fas fa-map-marked-alt"></i>
						<span class="icon_text">길찾기</span>
					</a>
				</div>

				<div id="dib">
					<c:if test="${empty ID}">
						<a onClick='nosess()' ; return 'false'> <i class="far fa-heart"></i>
							<span class="icon_text">가고싶어요</span>
						</a>
					</c:if>
					<c:if test="${not empty ID}">
						<a onClick='dib(); return false'>
							<c:if test="${check_dib eq true}">
								<i class="fas fa-heart"></i>
							</c:if>
							<c:if test="${check_dib ne true}">
								<i class="far fa-heart"></i>
							</c:if> <span class="icon_text">가고싶어요</span>
						</a>
					</c:if>

				</div>

				<div>
					<a>
						<i class="far fa-edit"></i>
						<span class="icon_text">후기작성</span>
					</a>
					<a onClick='share(); return false'>
					</a>
				</div>

				<div>
					<div id ="sharebutton">
						<i class="fas fa-share-alt-square"></i>
						<span class="icon_text">공유하기</span>
				    </div>
				    <div id ="sharehide" style="display:none,">
						<span class="sociallink ml-1"  style="text-decoration:none">
						  <a href="javascript:sendLinkFacebook()" title="페이스북으로 공유" style="text-decoration:none">
						    <img src="https://image.flaticon.com/icons/png/512/124/124010.png" width=36 alt="Facebook">
						  </a>
						</span>
						<span class="sociallink ml-1">
						  <a href="javascript:sendLinkTwitter()" title="트위터로 공유"  style="text-decoration:none">
						    <img src="data:image/png;base64,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" width=36 alt="Twitter">
						  </a>
						</span>
						<span class="sociallink ml-1">
						  <a href="javascript:sendLinkNaver()" title="네이버로 공유" style="text-decoration:none">
						    <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMgAAADICAMAAACahl6sAAAAVFBMVEX///8ezQA10hpv31yz7qk81CLE8rwozwth3Eyp7J5P2DiE5HSI5Xie6ZFH1i6N5n7q+ue98LT0/PKX6Inj+d9Y2kJz4GDQ9cp34WXd+Nn5/vjW9tEgwet2AAACFElEQVR4nO3c6U4CQRBF4apBFgHBlUXf/z2NcUVBupshVN2c85t0+JKJmevomBERERERERERERERERERERERERHRsUZdQcOezpmfEXLtJU37OWdyRshVEcRvejlncHmIP6tAFioQX6pA/FYF8v/P4EQQn6lAfK0C6VQgvlKB+EgFMlaBHL7tywbxOxXIoYsrHcSfVCAHpklCiMtA9u7ejBB/UIH4vQpkzzTJCdmze5NC/FEF8ufiygrxjQrEX1QgXcM5ISG/vlViyO7uzQwZV58TFLKze1NDfu7e3BCvPCcuZFp3TlzI9+7NDvl6JJcesqg5JzLk8+LKD/l4JCcAGapA3nevAsS3KpBOBfL2SE4D4msVyNjWGhCfF/291sUhg9nRj4xTQGY2LPlYfMjAblUgNleB2EIFcq8CsQcViE1VICYDOfkuIArEnlQgp15ccSB3KhCbqECs6C43A2SkArGVCsQ6FcijCsSO794kkPbdGw3SvHujQWypAmndvfEgjbs3HsRuVCBtuzcipGmahISU/bdyAkjLNIkJaZgmQSH1uzcoxAYqkOqLKyykdveGhdhGBVK5ewNDtiqQut0bGVK1e0NDan6pEhpS80guNqRi9waHlO/e4JDyaRIdUvxILjykdPfGhxQ+7z0npKcXfJXt3nO+KKyvV64tLv3qNiIiIiIiIiIiIiIiIiIiIiIiIiKVXgE/MyybmvxaBAAAAABJRU5ErkJggg==" width=36 alt="Naver">
						  </a>
						</span>
						<span class="sociallink ml-1">
						  <a href="javascript:sendLinkKakao()" title="카카오스토리 공유"  style="text-decoration:none">
						    <img src="https://lh3.googleusercontent.com/sTug7DxquD6aKrTTnsejKXYoK9CrvZo2i6HiuuSXwx5-hU5cm15DPU6ew_CRvji0WhTL" width=36 alt="Kakao">
						  </a>
						</span>
					</div>
				</div>
			</div>


			<div>
				<div class="info_header">
					<div class="header__left">
						<span>일반정보</span>
					</div>
					<div class="header__right">
						<span><i class="far fa-question-circle"></i></span>
					</div>
				</div>
				<div style="clear:both;"></div>

				<div class="normal_info">
					<!-- 관광지 -->
					<c:if test="${typeId eq 12}">
						<p class="info_name">체험 가능 연령</p>
						<c:if test="${detail.expAgeRange eq ''}">
							<p>전연령 가능</p>
						</c:if>
						<c:if test="${detail.expAgeRange ne ''}">
							<p>${detail.expAgeRange }</p>
						</c:if>
						<p class="info_name">체험 안내</p>
						<c:if test="${detail.expGuide eq ''}">
							<p>현장문의</p>
						</c:if>
						<c:if test="${detail.expGuide ne ''}">
							<p>${detail.expAgeRange }</p>
						</c:if>
						<p class="info_name">이용 시간</p>
						<c:if test="${detail.useTime eq ''}">
							<p>현장문의</p>
						</c:if>
						<c:if test="${detail.useTime ne ''}">
							<p>${detail.useTime }</p>
						</c:if>
					</c:if>
					<!-- 문화 시설 -->
					<c:if test="${typeId eq 14}">
						<p class="info_name">주차 요금</p>
						<c:if test="${detail.parkingFee eq ''}">
							<p>현장 문의</p>
						</c:if>
						<c:if test="${detail.parkingFee ne ''}">
							<p>${detail.parkingFee }</p>
						</c:if>
						<p class="info_name">이용 요금</p>
						<c:if test="${detail.useFee eq ''}">
							<p>현장문의</p>
						</c:if>
						<c:if test="${detail.useFee ne ''}">
							<p>${detail.useFee }</p>
						</c:if>
						<p class="info_name">이용 시간</p>
						<c:if test="${detail.useTimeCulture eq ''}">
							<p>현장문의</p>
						</c:if>
						<c:if test="${detail.useTimeCulture ne ''}">
							<p>${detail.useTimeCulture }</p>
						</c:if>
					</c:if>
					<!-- 페스티벌 -->
					<c:if test="${typeId eq 15}">
						<p class="info_name">관람 가능 연령</p>
						<c:if test="${detail.agelimit eq ''}">
							<p>현장 문의</p>
						</c:if>
						<c:if test="${detail.agelimit ne ''}">
							<p>${detail.agelimit }</p>
						</c:if>
						<p class="info_name">행사 시작일</p>
						<c:if test="${detail.eventStartDate eq ''}">
							<p>현장문의</p>
						</c:if>
						<c:if test="${detail.eventStartDate ne ''}">
							<p>${detail.eventStartDate }</p>
						</c:if>
						<p class="info_name">행사 종료일</p>
						<c:if test="${detail.eventEndDate eq ''}">
							<p>현장문의</p>
						</c:if>
						<c:if test="${detail.eventEndDate ne ''}">
							<p>${detail.eventEndDate }</p>
						</c:if>
						<p class="info_name">이용요금</p>
						<c:if test="${detail.usetimeFestival eq ''}">
							<p>현장문의</p>
						</c:if>
						<c:if test="${detail.usetimeFestival ne ''}">
							<p>${detail.usetimeFestival }</p>
						</c:if>
					</c:if>
					<!-- 코스여행 -->
					<c:if test="${typeId eq 25}">
						<p class="info_name">총 거리</p>
						<c:if test="${detail.distance eq ''}">
							<p>현장 문의</p>
						</c:if>
						<c:if test="${detail.distance ne ''}">
							<p>${detail.distance }</p>
						</c:if>
						<p class="info_name">소요 시간</p>
						<c:if test="${detail.takeTime eq ''}">
							<p>현장문의</p>
						</c:if>
						<c:if test="${detail.takeTime ne ''}">
							<p>${detail.takeTime }</p>
						</c:if>
					</c:if>
					<!-- 레포츠 -->
					<c:if test="${typeId eq 28}">
						<c:if test="${detail.openPeriod ne ''}">
							<p class="info_name">개장 시간</p>
							<p>${detail.openPeriod }</p>
						</c:if>
						<p class="info_name">주차 요금</p>
						<c:if test="${detail.parkingFeeLeports eq ''}">
							<p class="info_name">현장문의</p>
						</c:if>
						<c:if test="${detail.parkingFeeLeports ne ''}">
							<p>${detail.parkingFeeLeports }</p>
						</c:if>
						<c:if test="${detail.reservation ne ''}">
							<p class="info_name">예약 안내</p>
							<p>${detail.reservation }</p>
						</c:if>
						<c:if test="${detail.useFeeLeports ne ''}">
							<p class="info_name">이용요금</p>
							<p>${detail.useFeeLeports }</p>
						</c:if>
						<c:if test="${detail.useTimeLeports ne ''}">
							<p class="info_name">이용시간</p>
							<p>${detail.useTimeLeports }</p>
						</c:if>
					</c:if>
					<!-- 숙박 -->
					<c:if test="${typeId eq 32}">

						<p class="info_name">바베큐장</p>
						<c:if test="${detail.barbecue eq ''}">
							<p>현장 문의</p>
						</c:if>
						<c:if test="${detail.barbecue ne ''}">
							<p>${detail.barbecue }</p>
						</c:if>

						<p class="info_name">체크인</p>
						<c:if test="${detail.checkinTime eq ''}">
							<p>현장문의</p>
						</c:if>
						<c:if test="${detail.checkinTime ne ''}">
							<p>${detail.checkinTime }</p>
						</c:if>

						<p class="info_name">체크아웃</p>
						<c:if test="${detail.checkoutTime eq ''}">
							<p>현장문의</p>
						</c:if>
						<c:if test="${detail.checkoutTime ne ''}">
							<p>${detail.checkoutTime }</p>
						</c:if>

						<c:if test="${detail.reservationUrl ne ''}">
							<p class="info_name">예약 하기</p>
							<p>${detail.reservationUrl }</p>
						</c:if>

						<c:if test="${detail.subfacility ne ''}">
							<p class="info_name">부대시설</p>
							<p>${detail.subfacility }</p>
						</c:if>
					</c:if>
					<!-- 쇼핑 -->
					<c:if test="${typeId eq 38}">
						<c:if test="${detail.openTime ne ''}">
							<p class="info_name">개장 시간</p>
							<p>${detail.openTime }</p>
						</c:if>

						<c:if test="${detail.saleItem ne ''}">
							<p class="info_name">판매 품목</p>
							<p>${detail.saleItem }</p>
						</c:if>

						<c:if test="${detail.shopGuide ne ''}">
							<p class="info_name">매장 안내</p>
							<p>${detail.shopGuide }</p>
						</c:if>
					</c:if>
					<!-- 음식점 -->
					<c:if test="${typeId eq 39}">
						<c:if test="${detail.firstMenu ne ''}">
							<p class="info_name">대표 메뉴</p>
							<p>${detail.firstMenu }</p>
						</c:if>

						<c:if test="${detail.openTimeFood ne ''}">
							<p class="info_name">영업 시간</p>
							<p>${detail.openTimeFood }</p>
						</c:if>

						<c:if test="${detail.treatMenu ne ''}">
							<p class="info_name">취급 메뉴</p>
							<p>${detail.treatMenu }</p>
						</c:if>
					</c:if>
				</div>
			</div>

			<div>
				<div class="info_header">
					<div class="header__left">
						<span>소개</span>
					</div>
				</div>
				<div style="clear:both;"></div>

				<div class="info_text">
					<span style="margin: 5px;">${tourInfo.overview }</span>
				</div>
			</div>

			<div>
				<div class="info_header">
					<div class="header__left">
						<span>위치</span>
					</div>
				</div>
				<div style="clear:both;"></div>
				<div id="map" style="width: 100%; height: 300px; margin-bottom: 15px;"></div>
			</div>

			<div>
				<!-- 사용자 리뷰 헤더 -->
				<div class="info_header">
					<div class="header__left">
						<span>사용자 후기</span>
					</div>
				</div>
				<!-- 사용자 리뷰 목록 -->
				<div class="reviews">
					<div class="review">
						<div class="review__left">
							<span>
								<strong>박한범</strong>님
							</span>
						</div>
						<div class="review__right" id="open" style="cursor: pointer">
							<div class="review__content" style="float: left;">
								<span>안녕하세요! 전 겸댕이 한범이에요. 오늘 현충원에 방문해서 호국영령들에 대한 묵념의 시간을 가지면서 뜻깊은 하루를 보냈어요. 꼭
									방문해보세요!</span>
							</div>
							<div style="float: right; margin-top: 5px;">
								<span style="font-weight: 200; font-size: 12px;">2019년 8월 7일에 방문했습니다.</span>
							</div>
						</div>
					</div>

					<div class="modal-wrapper" style="display: none;">
						<div class="modal">
							<div class="modal-title">
								<h3 style="margin-bottom: 2px;">박한범님의 현충원 후기</h3>
								<span style="display: block; font-size: 12px; color: darkgray; margin: 0;">2019년 8월 7일에 방문했습니다.</span>
								<p>안녕하세요! 전 겸댕이 한범이에요. 오늘 현충원에 방문해서 호국영령들에 대한 묵념의 시간을 가지면서 뜻깊은 하루를 보냈어요. 꼭 방문해보세요!</p>

								<div class="close-wrapper">
									<div id="close">닫기</div>
								</div>
							</div>
						</div>
					</div>

					<div class="review">
						<div class="review__left">
							<span>
								<strong>김동욱</strong>님
							</span>
						</div>
						<div class="review__right" id="open" style="cursor: pointer">
							<div class="review__content" style="float: left;">
								<span>안녕하세요! 인공지능 개발자입니다. 4차 산업혁명 시대가 도래할 것입니다. 다들 인공지능 공부에 몰두합시다. 이상입니다.</span>
							</div>
							<div style="float: right; margin-top: 5px;">
								<span style="font-weight: 200; font-size: 12px;">2097년 11월 7일에 방문했습니다.</span>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="footer">
				<p style="margin: 7px;">Copyright 2019. 공수래공수거. ALL RIGTHS RESERVED.</p>
			</div>
		</div>
		</div>


		<script type="text/javascript"
			src="//dapi.kakao.com/v2/maps/sdk.js?appkey=0128abab124ce04ae17c622f465a5647&libraries=services,clusterer">
			</script>
		<script>
		//주소-좌표 변환 객체를 생성합니다
		var geocoder = new kakao.maps.services.Geocoder();
			var infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
			var container = document.getElementById('map');
 			var options = {
				center: new kakao.maps.LatLng("${tourInfo.mapy}", "${tourInfo.mapx}"),
				level: 6
			};
			var map = new kakao.maps.Map(container, options); 
			var clusterer = new kakao.maps.MarkerClusterer({
				map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
				averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
				minLevel: 10 // 클러스터 할 최소 지도 레벨 
			});
			var marker = new kakao.maps.Marker({
				position: new kakao.maps.LatLng("${tourInfo.mapy}", "${tourInfo.mapx}")
			});

			clusterer.addMarker(marker);
			// 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
			infowindow.setContent('<div style="padding:5px;font-size:12px;">' + "${tourInfo.title}" + '</div>');
			infowindow.open(map, marker);
		</script>
		<script>
			// 사용자 리뷰 모달창 열기
			const open = document.getElementById("open");
			const close = document.getElementById("close");
			const modal = document.querySelector(".modal-wrapper");
			open.onclick = () => {
				modal.style.display = "flex";
			};
			close.onclick = () => {
				modal.style.display = "none";
			};
			function nosess() {
				alert("로그인 후 이용해주세요!");
			}
			//------------------------------------------------------
			function dib() {
				$.ajax({
					type: "GET", //전송방식을 지정한다 (POST,GET)
					url: "changeDib.do?contentId=" + "${tourInfo.contentId}",//호출 URL을 설정한다. GET방식일경우 뒤에 파라티터를 붙여서 사용해도된다.

					dataType: "text",//호출한 페이지의 형식이다. xml,json,html,text등의 여러 방식을 사용할 수 있다.
					error: function () {
						alert("읭이잉?");
					},
					success: function (result) {
						if (result == "true") {
							alert("찜한 목록에 추가되었습니다!");
							tmp = "<a onClick='dib(); return false'><i class='fas fa-heart'></i><span class='icon_text'>가고싶어요</span>"
						} else {
							alert("찜한 목록에서 제거되었습니다!");
							tmp = "<a onClick='dib(); return false'><i class='far fa-heart'></i></i><span class='icon_text'>가고싶어요</span>"
						}
						//var check=${check_dib};
						$("#dib").html(tmp);
					}
				});
			}
			function searchDetailAddrFromCoords(coords, callback) {
			    // 좌표로 법정동 상세 주소 정보를 요청합니다
			    geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
			}
		function findWay(){
			
			var latlng=new kakao.maps.LatLng(${tourInfo.mapy},${tourInfo.mapx});
			searchDetailAddrFromCoords(latlng, function(result, status) {
	            var detailAddr = result[0].address.address_name+"";
			window.open("https://map.kakao.com/?sName=${address}&eName="+detailAddr);
			}); 
		}
		</script>
		<script>
		// 처음 열었으 때 숨김 표시
		$(document).ready(function() { 
			$("#sharehide").toggle();
		});
		// 클릭시 숨김과 보여줌을 반복
		$(function (){ $("#sharebutton").click(function (){
			$("#sharehide").toggle(); 
			}); 
		});

		function sendLinkFacebook(){
		    var raw_url = window.location.href;
		    var Facebook_root_url = "https://www.facebook.com/sharer/sharer.php?u=";
		    var Facebook_share_url = Facebook_root_url + encodeURI(raw_url);
		    window.open(Facebook_share_url,
		                'Share on Facebook',
		                'scrollbars=no, width=500, height=500');    
		}
		function sendLinkTwitter(){
		    var raw_url = "{{ request.path }}";
		    var raw_title = "{{ post.title }}"
		    var twitter_root_url = "https://twitter.com/share?url="
		    var twitter_share_url = twitter_root_url+encodeURI(raw_url)+"&title="+encodeURI(raw_title);
		    window.open(twitter_share_url,
		                'Share on twitter',
		                'scrollbars=no, width=500, height=500');    
		}
		function sendLinkNaver(){
		    var raw_url = "{{ request.path }}";
		    var raw_title = "{{ post.title }}"
		    var naver_root_url = "http://share.naver.com/web/shareView.nhn?url="
		    var naver_share_url = naver_root_url+encodeURI(raw_url)+"&title="+encodeURI(raw_title);
		    window.open(naver_share_url,
		                'Share on Naver',
		                'scrollbars=no, width=500, height=500');    
		}
		function sendLinkKakao(){
			console.log(1)
			var raw_url = window.location.href;
		    var Kakao_root_url = "https://story.kakao.com/share?url=";
		    var Kakao_share_url = Kakao_root_url + encodeURI(raw_url);
		    window.open(Kakao_share_url,
		                'Share on Kakao',
		                'scrollbars=no, width=500, height=500');    
		}
		</script>		
</body>

</html>