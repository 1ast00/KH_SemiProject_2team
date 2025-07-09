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

<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

body {
	font-family: 'Arial', sans-serif;
	width: 80%;
	margin: 50px auto;
	border: 1px solid #eee;
	padding: 20px;
	color: #333;
}

h2 {
	font-size: 24px;
	text-align: center;
	margin-bottom: 30px;
	font-weight: bold;
	
}

.report-wrapper {
	display: flex;
	align-items: center;
	gap: 30px;
	margin-bottom: 30px;
}

.report-wrapper img {
	width: 144px;
	height: 212px;
	border-radius: 8px;
	object-fit: cover;
}

.report-info p {
	font-size: 16px;
	margin-bottom: 8px;
	color: #444;
}

form {
	display: flex;
	justify-content: center;
	gap: 10px;
	margin: 40px 0;
}

form input[type="text"] {
	width: 300px;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 4px;
}

form button {
	padding: 10px 20px;
	background-color: #007bff;
	color: white;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

form button:hover {
	background-color: #0056b3;
}

#map {
    display: none; 
    width: 100%;
    height: 500px;
    border: 1px solid #ccc;
    border-radius: 8px;
    background-color: #f0f0f0;
}
.witness-container {
    text-align: left;
    max-width: 700px;
    margin: 0 auto;
}

.profile-image {
    display: block;
    margin: 20px auto;
    width: 150px;
    height: auto;
    border-radius: 8px;
    border: 1px solid #ccc;
}
</style>

</head>
<body>
	 <div class="witness-container">
	<h2>목격자 제보</h2>
	<div class="report-wrapper">
    <img src="${pageContext.request.contextPath}/실종 아동.jpg" alt="아가">
    <div class="report-info">
        <p>목격 날짜 : 2024-10-08 · 14pm</p>
        <p>목격 장소 : 오목교역 (근처)</p>
        <p>추정 성별 : 남</p>
        <p>추정 나이 : 5</p>
        <p>기타</p>
    </div>
</div>

	<form method="get" action="${pageContext.request.contextPath}/map">
		<input type="text" name="keyword" placeholder="장소를 입력하세요" required>
		<button type="submit">전송</button>
	</form>

	<div id="map"></div>
	 </div>
</body>
</html>