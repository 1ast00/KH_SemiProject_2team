<%@ page contentType="text/html;charset=UTF-8" %> 
<!DOCTYPE html>
<html>
<head>
    <title>${place} 지도</title>
    <script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=fbb4499e82e86a601a0016e65194691c&libraries=services"></script>
</head>
<body>

<h2>"${place}"의 위치 (1km 반경)</h2>

<div id="map" style="width:100%;height:500px;"></div>

	<!-- 수정 중 위치 검색시 -> missingUpdateView.do로 '수정 폼' 유지 -->
	<!-- 새로 접수 중 위치검색시 -> missingInsertView.do로 '접수 폼' 유지 -->
	<form action="${not empty missingSerialNum ? 'missingUpdateView.do' : 'missingInsertView.do'}" method="get" id="locationForm">
	
	<!-- MissingMapSearchController에서 쏴준 값들로 다시 hidden타입으로 만들어서 missing_insert로 보내기 -> 입력값 유지됨 -->
	<input type="hidden" name="missingSerialNum" value="${missingSerialNum}" />
	
    <input type="hidden" name="place" value="${place}" />
    <input type="hidden" name="missingName" value="${missingName}" />
    <input type="hidden" name="missingGender" value="${missingGender}" />
    <input type="hidden" name="missingBirth" value="${missingBirth}" />
    <input type="hidden" name="missingEtc" value="${missingEtc}" />
    <input type="hidden" name="missingDate" value="${missingDate}" />

    <button type="submit">위치 선택</button>
</form>

<script>
    const lat = parseFloat("${lat}");
    const lng = parseFloat("${lng}");

    const mapContainer = document.getElementById('map');
    const mapOption = {
        center: new kakao.maps.LatLng(lat, lng),
        level: 6
    };

    const map = new kakao.maps.Map(mapContainer, mapOption);

    const circle = new kakao.maps.Circle({
        center: new kakao.maps.LatLng(lat, lng),
        radius: 800,
        strokeWeight: 3,
        strokeColor: '#00aaff',
        fillColor: '#aaffff',
        fillOpacity: 0.5
    });

    circle.setMap(map);

    const marker = new kakao.maps.Marker({
        position: new kakao.maps.LatLng(lat, lng)
    });
    marker.setMap(map);
</script>

</body>
</html>