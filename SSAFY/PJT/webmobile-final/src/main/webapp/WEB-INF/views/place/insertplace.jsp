<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="resources/css/map.css">
<link rel="stylesheet" href="resources/css/main.css">
</head>
<body>
<table>
	<tr>
		<td>관광타입</td>
		<td><select name="contenttype" id="contenttype" onchange="changeSelect()" style="width: 100%;">
			<option value="all" selected="selected" style="width: 100%;">타입선택</option>
			<option value="tourplace">관광지</option>
			<option value="cultural">문화시설</option>
			<option value="festival">축제공연행사</option>
			<option value="course">여행코스</option>
			<option value="leports">레포츠</option>
			<option value="lodgment">숙박</option>
			<option value="shopping">쇼핑</option>
			<option value="restaurant">음식점</option>
		</select>
	</tr>
	<tr>
		<td>지역</td>
		<td>
		<select name="place" id="place" onchange="changearea()" style="width: 100%;">
			<option style="width: 100%;">지역선택</option>
			<c:forEach items="${arealist }" var="area">
				<option value="${area.areaNum}">${area.name}</option>
			</c:forEach>
		</select>
		<script type="text/javascript">
			function changearea(){
				var area = $("#place").val();
				
				$.ajax({
					type: "GET",
					url: "sigo.do",
					dataType: 'json',
					data: "area="+area,
					success: function(data){
						for( var _idx = 0;_idx <data.sigungu.length;_idx++){
							$('#sigo').append("<option value='"+data.sigungu[_idx].sigunguCode+"'>"+data.sigungu[_idx].name+"</option>");
						}
					},
					error: function(request,status,error){
					}
				});
			}
		</script>
		<td>
		<select name="sigo" id="sigo" style="width: 100%;">
			<option value="all" selected="selected"style="width: 100%;" >시군구 선택</option>
		</select>
		</td>
		</tr>
	<tr>
		<td>
			<button id="open">다른 위치에서 알아보기</button>
				<div class="modal-wrapper" style="display: none;">
					<div class="modal">
						<div class="modal-title">다른 위치에서 알아보기</div>
						<div class="map_wrap">
							<div id="map" style="width: 100%; height: 100%; position: relative; overflow: hidden;"></div>
							<div id="clickLatlng"></div>
							<div id="menu_wrap" class="bg_white">
								<div class="option">
									<form onsubmit="searchPlaces(); return false;">
										<div> 키워드 : <input type="text" value="" id="keyword" size="15">
											<button type="submit">검색하기</button>
										</div>
									</form>
									<div class="close-wrapper">
										<button id="close">닫기</button>
									</div>
								</div>
								<hr>
							</div>
						</div>
					</div>
					<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=0128abab124ce04ae17c622f465a5647&libraries=services,clusterer"></script>
					<script>
						var geocoder = new kakao.maps.services.Geocoder();
						var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
				    	mapOption = { 
				        	center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
				        	level: 3 // 지도의 확대 레벨
				    	};

						var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

						// 지도를 클릭한 위치에 표출할 마커입니다
						var marker = new kakao.maps.Marker({ 
				   			// 지도 중심좌표에 마커를 생성합니다 
				    		position: map.getCenter() 
						}); 
						// 지도에 마커를 표시합니다
						marker.setMap(map);

						// 지도에 클릭 이벤트를 등록합니다
						// 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
						kakao.maps.event.addListener(map, 'click', function(mouseEvent) {            
				    		// 클릭한 위도, 경도 정보를 가져옵니다 
				    		var latlng = mouseEvent.latLng; 
				    
				    		// 마커 위치를 클릭한 위치로 옮깁니다
				    		var detailAddr;
				    		marker.setPosition(latlng);
				    		searchDetailAddrFromCoords(latlng, function(result, status) {
			            		detailAddr = result[0].address.address_name;
				    			var message = '위도: ' + latlng.getLat() + '\n';
				    			message += '경도: ' + latlng.getLng() + '\n';
				    			message += '주소: ' + detailAddr + '\n';
				    			var resultDiv = document.getElementById('clickLatlng'); 
				    			resultDiv.innerHTML = message;
				    			document.getElementById('address').value=detailAddr;
				    			document.getElementById('lat').value=latlng.getLat();
				    			document.getElementById('lng').value=latlng.getLng();
			   		 		});   
						});
						remap=window.setInterval(function(){map.relayout();},1000);
					
						function searchDetailAddrFromCoords(coords, callback) {
				    		// 좌표로 법정동 상세 주소 정보를 요청합니다
				    		geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
						}
						function searchPlaces() {
				    		var keyword = document.getElementById('keyword').value;

				    		if (!keyword.replace(/^\s+|\s+$/g, '')) {
				        		alert('키워드를 입력해주세요!');
				        		return false;
				    		}
				    		geocoder.addressSearch(keyword, function(result, status) {
				        		// 정상적으로 검색이 완료됐으면 
				        		if (status === kakao.maps.services.Status.OK) {
				            	var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
				            	// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
				            	map.setCenter(coords);
				        		}else if (status === kakao.maps.services.Status.ZERO_RESULT) {
				            		alert('검색 결과가 존재하지 않습니다.');
				            		return;
				        		}else if (status === kakao.maps.services.Status.ERROR) {
				            		alert('검색 결과 중 오류가 발생했습니다.');
				            		return;
				        		}
				    		});   
						}
					</script>
				</div>
		</td>
	</tr>
	<tr>
		<td>위도(lat,mapx)</td>
		<td><input id="lat" type="text" name="lat"/></td>
	</tr>
	<tr>
		<td>경도(lng,mapy)</td>
		<td><input id="lng" type="text" name="lng"/></td>
	</tr>
	<tr>
		<td>제목</td>
		<td><input type="text" id="title"/></td>
	</tr>
	<tr>
		<td>개요</td>
		<td><input type="text" id="overview"/></td>
	</tr>
	<tr>
		<td>전화번호</td>
		<td><input type="text" id="tel"/></td>
	</tr>
	<tr>
		<td>주소</td>
		<td><input type="text" id="address"/></td>
	</tr>
	<tr>
		<td>우편번호</td>
		<td><input type="text" id="zipcode"/></td>
	</tr>
	<tr>
		<td>대표이미지</td>
		<td><input type="text" id="image"/></td>
	</tr>
	<tr>
		<td>홈페이지</td>
		<td><input type="text" id="homepage"/></td>
	</tr>
	<tr>
		<td>수용인원</td>
		<td><input type="text" id="accomcount"/></td>
	</tr>
	<tr>
		<td>유모차 대여 여부</td>
		<td><input type="text" id="chkbabycarriage"/></td>
	</tr>
	<tr>
		<td>신용카드 가능 여부</td>
		<td><input type="text" id="chkcreditcard"/></td>
	</tr>
	<tr>
		<td>애완동물 동반 가능 여부</td>
		<td><input type="text" id="chkpet"/></td>
	</tr>
	<tr>
		<td>문의 및 안내</td>
		<td><input type="text" id="infocenter"/></td>
	</tr>
	<tr>
		<td>주차시설</td>
		<td><input type="text" id="parking"/></td>
	</tr>
	<tr>
		<td>쉬는날</td>
		<td><input type="text" id="restdate"/></td>
	</tr>
	</table>
	<div id="tourplace" hidden="hidden">
		<table>
			<tr>
				<td>체험가능연령</td>
				<td><input type="text" id="expagerange"/></td>
			</tr>
			<tr>
				<td>체험안내</td>
				<td><input type="text" id="expguide"/></td>
			</tr>
			<tr>
				<td>이용시간</td>
				<td><input type="text" id="usetime"/></td>
			</tr>
		</table>
	</div>
	<div id="cultural" hidden="hidden">
		<table>
			<tr>
				<td>주차요금</td>
				<td><input type="text" id="parkingfee"/></td>
			</tr>
			<tr>
				<td>이용요금</td>
				<td><input type="text" id="usefee"/></td>
			</tr>
			<tr>
				<td>이용시간</td>
				<td><input type="text" id="usetimeculture"/></td>
			</tr>
		</table>
	</div>
	<div id="festival" hidden="hidden">
		<table>
			<tr>
				<td>관람가능연령</td>
				<td><input type="text" id="agelimit"/></td>
			</tr>
			<tr>
				<td>예매처</td>
				<td><input type="text" id="bookingplace"/></td>
			</tr>
			<tr>
				<td>행사시작일</td>
				<td><input type="text" id="eventstartdate"/></td>
			</tr>
			<tr>
				<td>행사종료일</td>
				<td><input type="text" id="eventenddate"/></td>
			</tr>
			<tr>
				<td>행사장소</td>
				<td><input type="text" id="eventplace"/></td>
			</tr>
			<tr>
				<td>행사장 위치안내</td>
				<td><input type="text" id="placeinfo"/></td>
			</tr>
			<tr>
				<td>공연시간</td>
				<td><input type="text" id="playtime"/></td>
			</tr>
			<tr>
				<td>주최자 정보</td>
				<td><input type="text" id="sponsor1"/></td>
			</tr>
			<tr>
				<td>주최자 연락처</td>
				<td><input type="text" id="sponsor1tel"/></td>
			</tr>
			<tr>
				<td>주관자 정보</td>
				<td><input type="text" id="sponsor2"/></td>
			</tr>
			<tr>
				<td>주관자 연락처</td>
				<td><input type="text" id="sponsor2tel"/></td>
			</tr>
			<tr>
				<td>프로그램</td>
				<td><input type="text" id="program"/></td>
			</tr>
			<tr>
				<td>부대행사</td>
				<td><input type="text" id="subevent"/></td>
			</tr>
			<tr>
				<td>이용요금</td>
				<td><input type="text" id="usetimefestival"/></td>
			</tr>
			<tr>
				<td>할인정보</td>
				<td><input type="text" id="discountinfofestival"/></td>
			</tr>
			<tr>
				<td>관람소요시간</td>
				<td><input type="text" id="spendtimefestival"/></td>
			</tr>
		</table>
	</div>
	<div id="course" hidden="hidden">
		<table>
			<tr>
				<td>총 거리</td>
				<td><input type="text" id="distance"/></td>
			</tr>
			<tr>
				<td>소요시간</td>
				<td><input type="text" id="taketime"/></td>
			</tr>
		</table>
	</div>
	<div id="leports" hidden="hidden">
		<table>
			<tr>
				<td>개장시간</td>
				<td><input type="text" id="openperiod"/></td>
			</tr>
			<tr>
				<td>주차요금</td>
				<td><input type="text" id="parkingfeeleports"/></td>
			</tr>
			<tr>
				<td>예약안내</td>
				<td><input type="text" id="reservation"/></td>
			</tr>
			<tr>
				<td>이용요금</td>
				<td><input type="text" id="usefeeleports"/></td>
			</tr>
			<tr>
				<td>이용시간</td>
				<td><input type="text" id="usetimeleports"/></td>
			</tr>
		</table>
	</div>
	<div id="lodgment" hidden="hidden">
		<table>
			<tr>
				<td>바베큐장 여부</td>
				<td><input type="text" id="barbecue"/></td>
			</tr>
			<tr>
				<td>캠프파이어 여부</td>
				<td><input type="text" id="campfire"/></td>
			</tr>
			<tr>
				<td>체크인</td>
				<td><input type="text" id="checkintime"/></td>
			</tr>
			<tr>
				<td>체크아웃</td>
				<td><input type="text" id="checkouttime"/></td>
			</tr>
			<tr>
				<td>조리 가능 여부</td>
				<td><input type="text" id="chkcooking"/></td>
			</tr>
			<tr>
				<td>식음료장 여부</td>
				<td><input type="text" id="foodplace"/></td>
			</tr>
			<tr>
				<td>픽업서비스</td>
				<td><input type="text" id="pickup"/></td>
			</tr>
			<tr>
				<td>공용샤워실 여부</td>
				<td><input type="text" id="publicbath"/></td>
			</tr>
			<tr>
				<td>예약 안내</td>
				<td><input type="text" id="reservationlodgin"/></td>
			</tr>
			<tr>
				<td>예약안내 홈페이지</td>
				<td><input type="text" id="reservationurl"/></td>
			</tr>
			<tr>
				<td>객실 수</td>
				<td><input type="text" id="roomcount"/></td>
			</tr>
			<tr>
				<td>객실 유형</td>
				<td><input type="text" id="roomtype"/></td>
			</tr>
			<tr>
				<td>부대 시설</td>
				<td><input type="text" id="subfacility"/></td>
			</tr>
		</table>
	</div>
	<div id="shopping" hidden="hidden">
		<table>
			<tr>
				<td>영업시간</td>
				<td><input type="text" id="opentime"/></td>
			</tr>
			<tr>
				<td>판매품목</td>
				<td><input type="text" id="saleitem"/></td>
			</tr>
			<tr>
				<td>매장안내</td>
				<td><input type="text" id="shopguide"/></td>
			</tr>
		</table>
	</div>
	<div id="restaurant" hidden="hidden">
		<table>
			<tr>
				<td>대표 메뉴</td>
				<td><input type="text" id="firstmenu"/></td>
			</tr>
			<tr>
				<td>어린이놀이방 여부</td>
				<td><input type="text" id="kidsfacility"/></td>
			</tr>
			<tr>
				<td>영업 시간</td>
				<td><input type="text" id="opentimefood"/></td>
			</tr>
			<tr>
				<td>포장 가능</td>
				<td><input type="text" id="packing"/></td>
			</tr>
			<tr>
				<td>예약안내</td>
				<td><input type="text" id="reservationfood"/></td>
			</tr>
			<tr>
				<td>흡연 여부</td>
				<td><input type="text" id="smoking"/></td>
			</tr>
			<tr>
				<td>취급 메뉴</td>
				<td><input type="text" id="treatmenu"/></td>
			</tr>
		</table>
	</div>
	<div>
		<button id="submit">추가</button>
		<button id="cancel">취소</button>
	</div>
<script type="text/javascript">
	const submit = document.getElementById("submit");
	const cancel = document.getElementById("cancel")
	submit.onclick = () => {
		var contenttype = $("#contenttype").val();
		var data = {
			area:$("#place").val(),
		    sigo:$("#sigo").val(),
		    lat:$("#lat").val(),
		    lng:$("#lng").val(),
		    title:$("#title").val(),
		    overview:$("#overview").val(),
		    tel:$("#tel").val(),
		    address:$("#address").val(),
		    zipcode:$("#zipcode").val(),
		    image:$("#image").val(),
		    homepage:$("#homepage").val(),
		    accomcount:$("#accomcount").val(),
		    chkbabycarriage:$("#chkbabycarriage").val(),
		    chkcreditcard:$("#chkcreditcard").val(),
		    chkpet:$("#chkpet").val(),
		    infocenter:$("#infocenter").val(),
		    parking:$("#parking").val(),
		    restdate:$("#restdate").val()
		}
        var urls = "";
        switch(contenttype){
        case "tourplace":
        	data.contenttypeid = 12;
        	data.expagerange = $("#expagerange").val();
        	data.expguide = $("#expguide").val();
        	data.usetime = $("#usetime").val();
        	break;
        case "cultural":
        	data.contenttypeid = 14;
        	data.parkingfee = $("#parkingfee").val();
        	data.usefee = $("#usefee").val();
        	data.usetimeculture = $("#usetimeculture").val();
        	break;
        case "festival":
        	data.contenttypeid = 15;
        	data.agelimit = $("#agelimit").val();
        	data.bookingplace = $("#bookingplace").val();
        	data.discountinfofestival = $("#discountinfofestival").val();
        	data.eventstartdate = $("#eventstartdate").val();
        	data.eventenddate = $("#eventenddate").val();
        	data.eventplace = $("#eventplace").val();
        	data.placeinfo = $("#placeinfo").val();
        	data.playtime = $("#playtime").val();
        	data.sponsor1 = $("#sponsor1").val();
        	data.sponsor1tel = $("#sponsor1tel").val();
        	data.sponsor2 = $("#sponsor2").val();
        	data.sponsor2tel = $("#sponsor2tel").val();
        	data.program = $("#program").val();
        	data.subevent = $("#subevent").val();
        	data.usetimefestival = $("#usetimefestival").val();
        	data.spendtimefestival = $("#spendtimefestival").val();
        	break;
        case "course":
        	data.contenttypeid = 25;
        	data.distance = $("#distance").val();
        	data.taketime = $("#taketime").val();
        	break;
        case "leports":
        	data.contenttypeid = 28;
        	data.openperiod = $("#openperiod").val();
        	data.parkingfeeleports = $("#parkingfeeleports").val();
        	data.reservation = $("#reservation").val();
        	data.usefeeleports = $("#usefeeleports").val();
        	data.usetimeleports = $("#usetimeleports").val();
        	break;
        case "lodgment":
        	data.contenttypeid = 32;
        	data.barbecue = $("#barbecue").val();
        	data.campfire = $("#campfire").val();
        	data.checkintime = $("#checkintime").val();
        	data.checkouttime = $("#checkouttime").val();
        	data.chkcooking = $("#chkcooking").val();
        	data.foodplace = $("#foodplace").val();
        	data.pickup = $("#pickup").val();
        	data.publicbath = $("#publicbath").val();
        	data.reservationlodgin = $("#reservationlodgin").val();
        	data.reservationurl = $("#reservationurl").val();
        	data.roomcount = $("#roomcount").val();
        	data.roomtype = $("#roomtype").val();
        	data.subfacility = $("#subfacility").val();
        	break;
        case "shopping":
        	data.contenttypeid = 38;
        	data.opentime = $("#opentime").val();
        	data.saleitem = $("#saleitem").val();
        	data.shopguide = $("#shopguide").val();
        	break;
        case "restaurant":
        	data.contenttypeid = 39;
        	data.firstmenu = $("#firstmenu").val();
        	data.kidsfacility = $("#kidsfacility").val();
        	data.opentimefood = $("#opentimefood").val();
        	data.packing = $("#packing").val();
        	data.reservationfood = $("#reservationfood").val();
        	data.smoking = $("#smoking").val();
        	data.treatmenu = $("#treatmenu").val();
        	break;
        }
        $.ajax({
        	type:"POST",
        	url: "insertDetail.do",
        	dataType:"json",
        	data:data,
        	success:function(args){
        		alert("성공");
        		location.href = "placeDetail.do?id="+args.contentid+"&type="+args.contenttypeid;
        	},
        	error:function(e){
        		alert(e.responseText);
        	}
        });
    };
    cancel.onclick = () => {
    	location.href="tourlist.do";
    };
	function changeSelect(){
		var typeselect = $("#contenttype").val();

		if(typeselect == 'tourplace'){
			$("#tourplace").removeAttr("hidden");
			$("#cultural").attr("hidden",true);
			$("#festival").attr("hidden",true);
			$("#course").attr("hidden",true);
			$("#leports").attr("hidden",true);
			$("#lodgment").attr("hidden",true);
			$("#shopping").attr("hidden",true);
			$("#restaurant").attr("hidden",true);
		}else if(typeselect == 'cultural'){
			$("#cultural").removeAttr("hidden");
			$("#tourplace").attr("hidden",true);
			$("#festival").attr("hidden",true);
			$("#course").attr("hidden",true);
			$("#leports").attr("hidden",true);
			$("#lodgment").attr("hidden",true);
			$("#shopping").attr("hidden",true);
			$("#restaurant").attr("hidden",true);
		}else if(typeselect == 'festival'){
			$("#festival").removeAttr("hidden");
			$("#cultural").attr("hidden",true);
			$("#tourplace").attr("hidden",true);
			$("#course").attr("hidden",true);
			$("#leports").attr("hidden",true);
			$("#lodgment").attr("hidden",true);
			$("#shopping").attr("hidden",true);
			$("#restaurant").attr("hidden",true);
		}else if(typeselect == 'course'){
			$("#course").removeAttr("hidden");
			$("#cultural").attr("hidden",true);
			$("#festival").attr("hidden",true);
			$("#tourplace").attr("hidden",true);
			$("#leports").attr("hidden",true);
			$("#lodgment").attr("hidden",true);
			$("#shopping").attr("hidden",true);
			$("#restaurant").attr("hidden",true);
		}else if(typeselect == 'leports'){
			$("#leports").removeAttr("hidden");
			$("#cultural").attr("hidden",true);
			$("#festival").attr("hidden",true);
			$("#course").attr("hidden",true);
			$("#tourplace").attr("hidden",true);
			$("#lodgment").attr("hidden",true);
			$("#shopping").attr("hidden",true);
			$("#restaurant").attr("hidden",true);
		}else if(typeselect == 'lodgment'){
			$("#lodgment").removeAttr("hidden");
			$("#cultural").attr("hidden",true);
			$("#festival").attr("hidden",true);
			$("#course").attr("hidden",true);
			$("#leports").attr("hidden",true);
			$("#tourplace").attr("hidden",true);
			$("#shopping").attr("hidden",true);
			$("#restaurant").attr("hidden",true);
		}else if(typeselect == 'shopping'){
			$("#shopping").removeAttr("hidden");
			$("#cultural").attr("hidden",true);
			$("#festival").attr("hidden",true);
			$("#course").attr("hidden",true);
			$("#leports").attr("hidden",true);
			$("#lodgment").attr("hidden",true);
			$("#tourplace").attr("hidden",true);
			$("#restaurant").attr("hidden",true);
		}else if(typeselect == 'restaurant'){
			$("#restaurant").removeAttr("hidden");
			$("#cultural").attr("hidden",true);
			$("#festival").attr("hidden",true);
			$("#course").attr("hidden",true);
			$("#leports").attr("hidden",true);
			$("#lodgment").attr("hidden",true);
			$("#shopping").attr("hidden",true);
			$("#tourplace").attr("hidden",true);
		}else{
			$("#tourplace").attr("hidden",true);
			$("#cultural").attr("hidden",true);
			$("#festival").attr("hidden",true);
			$("#course").attr("hidden",true);
			$("#leports").attr("hidden",true);
			$("#lodgment").attr("hidden",true);
			$("#shopping").attr("hidden",true);
			$("#restaurant").attr("hidden",true);
		}
	}
    const open = document.getElementById("open");
    const close = document.getElementById("close");
    const modal = document.querySelector(".modal-wrapper");

    open.onclick = () => {
        modal.style.display = "flex";
    };
    close.onclick = () => {
        modal.style.display = "none";
    };
</script>
</body>
</html>