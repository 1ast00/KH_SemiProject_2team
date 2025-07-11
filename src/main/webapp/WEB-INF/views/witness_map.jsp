<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>${place}지도</title>
<script
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1953e4a69f69ba521e119d6eca3efa47&libraries=services"></script>
<script>
window.onload = () => {
    const lat = parseFloat("${lat}");
    const lng = parseFloat("${lng}");
    const m_lat = parseFloat("${m_lat}");
    const m_lng = parseFloat("${m_lng}");

    // 유효한 좌표값이 있을 때만 지도 표시
    if (!isNaN(lat) && !isNaN(lng)) {
        const mapContainer = document.getElementById('map');
        mapContainer.style.display = 'block'; // ← 지도 보이기

        const mapOption = {
            center: new kakao.maps.LatLng(lat, lng),
            level: 8
        };
        const map = new kakao.maps.Map(mapContainer, mapOption);

        // 제보 위치
        const witnessCircle = new kakao.maps.Circle({
            center: new kakao.maps.LatLng(lat, lng),
            radius: 1000,
            strokeWeight: 3,
            strokeColor: '#00aaff',
            fillColor: '#aaffff',
            fillOpacity: 0.5
        });
        witnessCircle.setMap(map);

        // 실종자 위치
        if (!isNaN(m_lat) && !isNaN(m_lng)) {
            const missingCircle = new kakao.maps.Circle({
                center: new kakao.maps.LatLng(m_lat, m_lng),
                radius: 1000,
                strokeWeight: 3,
                strokeColor: '#ff0000',
                fillColor: '#ffaaaa',
                fillOpacity: 0.5
            });
            missingCircle.setMap(map);
        }
    } else {
        console.log("입력 값이 없어서 지도 비활성 상태입니다.");
    }
};

    </script>

</head>
<body>
	 <div class="witness-container">
	<h2>목격자 제보</h2>
	<div class="report-wrapper">
    <img src="${pageContext.request.contextPath}/resource/img/missing_person_1" alt="아가">
    <div class="report-info">
        <p>목격 날짜 : 2024-10-08 · 14pm</p>
        <p>목격 장소 : 오목교역 (근처)</p>
        <p>추정 성별 : 남</p>
        <p>추정 나이 : 5</p>
        <p>기타</p>
    </div>
</div>

	<form method="get" action="witnessMapSearch.do">
		<input type="text" name="keyword" placeholder="장소를 입력하세요" required>
		<button type="submit">전송</button>
	</form>

	<div id="map"></div>
	 </div>
</body>
</html>