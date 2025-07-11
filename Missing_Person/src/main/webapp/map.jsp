<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>${place} 지도</title>
<script
    src="//dapi.kakao.com/v2/maps/sdk.js?appkey=fbb4499e82e86a601a0016e65194691c&libraries=services"></script>
</head>
<body>
    <h2>"${place}"의 위치 (1km 반경)</h2>

    <div id="map" style="width: 100%; height: 500px;"></div>

    <form action="missing_insert.jsp" method="get" style="margin-top: 20px;">
        <input type="hidden" name="place" value="${place}" />
        <input type="hidden" name="name" value="${sessionScope.tempMissingPerson.name}" />
        <input type="hidden" name="gender" value="${sessionScope.tempMissingPerson.gender}" />
        <input type="hidden" name="birth" value="${sessionScope.tempMissingPerson.birth}" />
        <input type="hidden" name="etc" value="${sessionScope.tempMissingPerson.etc}" />
        <input type="hidden" name="date" value="${sessionScope.tempMissingPerson.date}" />

        <button type="submit">이 위치로 설정</button>
    </form>
    <script>
        const lat = parseFloat("${lat}");
        const lng = parseFloat("${lng}");
        const mapContainer = document.getElementById('map');
        const mapOption = {
            center : new kakao.maps.LatLng(lat, lng),
            level : 3
        };
        const map = new kakao.maps.Map(mapContainer, mapOption);
        const circle = new kakao.maps.Circle({
            center : new kakao.maps.LatLng(lat, lng),
            radius : 1000,
            strokeWeight : 3,
            strokeColor : '#00aaff',
            fillColor : '#aaffff',
            fillOpacity : 0.5
        });
        circle.setMap(map);
        const marker = new kakao.maps.Marker({
            position : new kakao.maps.LatLng(lat, lng)
        });
        marker.setMap(map);
    </script>
</body>
</html>