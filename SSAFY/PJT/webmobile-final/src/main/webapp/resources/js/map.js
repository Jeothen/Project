
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(0,0), // 지도의 중심좌표
        level: 6 // 지도의 확대 레벨
    };  

//지도를 생성합니다    

var map = new kakao.maps.Map(mapContainer, mapOption); 
var infowindow = new kakao.maps.InfoWindow({zIndex:1
	,disableAutoPan:true});
var tmpinfowindow = new kakao.maps.InfoWindow({zIndex:1});

//주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();
var circle = new kakao.maps.Circle({
	map: map, // 원을 표시할 지도 객체
	center : new kakao.maps.LatLng(37.566826, 126.9786567 ), // 지도의 중심 좌표
	radius : 1000, // 원의 반지름 (단위 : m)
    strokeColor: '#39f',
    fillColor: '#39f',
    fillOpacity: 0.5,
	strokeWeight: 3, // 선의 두께
	strokeStyle: 'solid' // 선 스타일
});	 

var myMarker = new kakao.maps.Marker({
});
myMarker.setZIndex(3);
myMarker.setMap(map);
var tmpMarker = new kakao.maps.Marker({
	position: new kakao.maps.LatLng(0,0)
});
kakao.maps.event.addListener(map, 'click', function(mouseEvent) {        
	 
	//var r = confirm("현재 위치로 좌표를 이동하시겠습니까?");
	 
	 // if (r == true) {
		  //alert("클릭됨");
		  //document.getElementById("checkbox").checked = false;
		  
		  var latlng = mouseEvent.latLng; 
		  tmpMarker.setPosition(latlng);
		  tmpMarker.setZIndex(3);
		  tmpMarker.setMap(map);
			searchDetailAddrFromCoords(latlng, function(result, status) {
	            var detailAddr = '<div>' + result[0].address.address_name + '</div>';
	            
	            var content ='<div style="padding:5px;font-size:12px;">' + "변경할 나의 위치 : " +
	                            detailAddr + 
	                        '</div>';


	            // 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
	            tmpinfowindow.setContent(content);
	            tmpinfowindow.setPosition(latlng);
	            tmpinfowindow.open(map, tmpMarker);
	            tmpMarker.setMap(map);
	    }); 
		  // 마커 위치를 클릭한 위치로 옮깁니다
			tmpMarker.setPosition(latlng);
		  
		  
		  
	 // } else {
			circle.setOptions({
				center : latlng,
			}); 
	//  }
});
function searchDetailAddrFromCoords(coords, callback) {
    // 좌표로 법정동 상세 주소 정보를 요청합니다
    geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
}
function setMarker(x,y,distance){
	var latlng=new kakao.maps.LatLng(y,x);
	searchDetailAddrFromCoords(latlng, function(result, status) {
            var detailAddr = '<div>' + result[0].address.address_name + '</div>';
            
            var content ='<div style="padding:5px;font-size:12px;">' + "현재 나의 위치 : " +
                            detailAddr + 
                        '</div>';
        	var form_data ={
        			address:result[0].address.address_name
        	};
            $.ajax({
            	type : "POST",
            	url : "setAddress.do",
            	data : form_data,
            	success : function(data){
            			//alert(data);
            	},
            	error : function(error){
            		alert("주소 표시 에러가 발생했습니다.");
            	}
            });

            // 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
            infowindow.setContent(content);
            infowindow.setPosition(latlng);
            infowindow.open(map, myMarker);
    }); 
	myMarker.setPosition(latlng);
	myMarker.setMap(map);
	y=parseFloat(y)+0.0175;
	x=parseFloat(x)-0.014;
	map.setCenter(new kakao.maps.LatLng(y,x));
	// 지도에 원을 표시한다
		circle.setOptions({
			center : latlng,
		    radius: distance*1000,
		}); 
	//window.setTimeout(function(){ map.relayout(); }, 2000);
	remap=window.setInterval(function(){map.relayout();},1000);

}
function setRadius(distance){
	circle.setOptions({
	    radius: distance*1000,
	});
	
}

// 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다

// 키워드로 장소를 검색합니다
//searchPlaces();

// 키워드 검색을 요청하는 함수입니다
function searchPlaces() {

    var keyword = document.getElementById('keyword').value;

    if (!keyword.replace(/^\s+|\s+$/g, '')) {
        alert('키워드를 입력해주세요!');
        return false;
    }

    // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
    //ps.keywordSearch( keyword, placesSearchCB); 
    geocoder.addressSearch(keyword, function(result, status) {

        // 정상적으로 검색이 완료됐으면 
         if (status === kakao.maps.services.Status.OK) {

            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);


            // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
            map.setCenter(coords);
        }  else if (status === kakao.maps.services.Status.ZERO_RESULT) {

            alert('검색 결과가 존재하지 않습니다.');
            return;

        } else if (status === kakao.maps.services.Status.ERROR) {

            alert('검색 결과 중 오류가 발생했습니다.');
            return;

        }
    });   
}

function changeLocation() {
	var latlng=tmpMarker.getPosition();
	if(latlng==null){
		return;
	}
	searchDetailAddrFromCoords(latlng, function(result, status) {
    	var form_data ={
    			address:result[0].address.address_name
    	};
        $.ajax({
        	type : "POST",
        	url : "setAddress.do",
        	data : form_data,
        	success : function(data){
        			//alert(data);
        	},
        	error : function(error){
        		alert("주소 표시 에러가 발생했습니다.");
        	}
        });
	}); 
	if(latlng.getLat()==0||latlng.getLat()==0){
		return;
	}
	var form_data ={
		lat:latlng.getLat(),
		lon:latlng.getLng(),
		distance:document.getElementById("range").value
};
$.ajax({
	type : "POST",
	url : "setLocation.do",
	data : form_data,
	success : function(data){
		if (data.chk == 1) {
			alert("현재 위치를 사용할 수 없습니다.");
		}else if(data.check != 1){
			 document.location.href="main.do";
		}
	},
	error : function(error){
		alert("error");
	}
});

}

