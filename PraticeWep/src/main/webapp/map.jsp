<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>${place} 지도</title>
   <script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1953e4a69f69ba521e119d6eca3efa47&libraries=services"></script>
	<script>
	window.onload = () => {
		const lat = parseFloat("${lat}"); //소수 인트로 위도 경도 변환해서 담아두기
	    const lng = parseFloat("${lng}");

	    const mapContainer = document.getElementById('map'); // map servlet에 잇는 위경도 데이터 넣기
	    const mapOption = {
	        center: new kakao.maps.LatLng(lat, lng),
	        level: 6 // 처음 나오는 지도 확대 정도
	    };

	    // map 안에 정보 다 넣기
	    
	    const map = new kakao.maps.Map(mapContainer, mapOption);

	    // 원 js
	    const circle = new kakao.maps.Circle({
	        center: new kakao.maps.LatLng(lat, lng),
	        radius: 300, // 원 범위
	        strokeWeight: 3,
	        strokeColor: '#00aaff',
	        fillColor: '#aaffff',
	        fillOpacity: 0.5
	    });

	    circle.setMap(map);
	
	    // 마커 부분 셋팅해서 set하기
	    const marker = new kakao.maps.Marker({
	        position: new kakao.maps.LatLng(lat, lng)
	    });
	    marker.setMap(map);
		
	}
</script>

	<style>
		* {
			margin : 0
			padding : 0
		}
		
		body {
			width : 100%;
			margin : 100 auto;
			border : 1px solid black;
		}
		
		#map {
			width: 50%;
			height: 30%;
			margin : 100 auto;
			margin-top : 400;
			border: 1px solid black;
		}
		
	
	</style>

</head>
<body>


<h2>목격자 제보</h2>

<p>추정 나이 : 5세</p>
<p>성별 : 남</p>
<p>목격 장소 : 어디역 근처 </p>
<p>목격 날짜 : 2024-10-08 </p>

<form method="get" action="/map">
<input type="text" name="keyword" placeholder="장소를 입력하세요" requ>

	
	

</body>
</html>
