<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>목격자 제보 상세</title>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap">
<script type="text/javascript"
	src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=1953e4a69f69ba521e119d6eca3efa47&libraries=services"></script>
<style>
body {
	font-family: 'Noto Sans KR', sans-serif;
	margin: 0;
	padding: 0;
	background-color: #fff;
}

.container {
	max-width: 900px;
	margin: 50px auto;
	padding: 40px;
	background-color: #fdfdfd;
	border-radius: 20px;
	box-shadow: 0 4px 16px rgba(0, 0, 0, 0.1);
}

.title {
	text-align: center;
	font-size: 24px;
	font-weight: bold;
	margin-bottom: 30px;
}

.content {
	display: flex;
	justify-content: center;
	align-items: center;
	gap: 60px;
	margin-bottom: 30px;
}

.content img {
	width: 220px;
	height: 280px;
	object-fit: cover;
	border-radius: 12px;
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.info {
	display: flex;
	flex-direction: column;
	justify-content: center;
}

.info p {
	font-size: 17px;
	margin: 12px 0;
}

.info span {
	font-weight: bold;
	color: #333;
}

#map-wrapper {
	position: relative;
	margin-top: 30px;
}

#legend {
	position: absolute;
	top: 10px;
	left: 10px;
	background: rgba(255, 255, 255, 0.85);
	padding: 8px 14px;
	border-radius: 10px;
	font-size: 14px;
	z-index: 10;
	border: 1px solid #ccc;
}

#map {
	width: 100%;
	height: 400px;
	border: 1px solid #ccc;
	border-radius: 12px;
}
</style>
</head>
<body>
	<div class="container">
		<div class="title">목격자 제보</div>
		<div class="content">
			<img
				src="${pageContext.request.contextPath}/resource/upload/${dto.image}"
				onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/resource/img/default.jpg';"
				alt="제보 이미지">

			<div class="info">
				<p>
					<span>추정 나이  :  </span>
					<c:choose>
						<c:when test="${dto.age <= 10}">0~10세</c:when>
						<c:when test="${dto.age <= 20}">10~20세</c:when>
						<c:when test="${dto.age <= 30}">20~30세</c:when>
						<c:when test="${dto.age <= 40}">30~40세</c:when>
						<c:when test="${dto.age <= 50}">40~50세</c:when>
						<c:when test="${dto.age <= 60}">50~60세</c:when>
						<c:when test="${dto.age <= 70}">60~70세</c:when>
						<c:when test="${dto.age <= 80}">70~80세</c:when>
						<c:when test="${dto.age <= 90}">80~90세</c:when>
						<c:when test="${dto.age <= 100}">90~100세</c:when>
						<c:otherwise>100세 이상</c:otherwise>
					</c:choose>
				</p>

				<p>
					<span>추정 성별  :  </span> ${dto.gender}
				</p>
				<p>
					<span>목격 장소  :  </span> ${dto.place}
				</p>
				<p>
					<span>목격 날짜  :  </span> ${dto.date}
				</p>
				<p>
				<span>기타 특징  :  </span> ${dto.etc}
				</p>
			</div>
		</div>

		<div id="map-wrapper">
			<div id="legend">
				<span style="color: red;">●</span> 실종 장소 &nbsp;&nbsp; <span
					style="color: blue;">●</span> 목격 장소
			</div>
			<div id="map"></div>
		</div>
	</div>

	<script>
		const place = "${dto.place}";
		const m_lat = parseFloat("${m_lat}");
		const m_lng = parseFloat("${m_lng}");

		const mapContainer = document.getElementById('map');
		const map = new kakao.maps.Map(mapContainer, {
			center : new kakao.maps.LatLng(37.5665, 126.9780),
			level : 7
		});

		const ps = new kakao.maps.services.Places();

		ps.keywordSearch(place, function(result, status) {
			if (status === kakao.maps.services.Status.OK) {
				const lat = parseFloat(result[0].y);
				const lng = parseFloat(result[0].x);

				const witnessPos = new kakao.maps.LatLng(lat, lng);
				map.setCenter(witnessPos);

				const witnessCircle = new kakao.maps.Circle({
					center : witnessPos,
					radius : 800,
					strokeWeight : 2,
					strokeColor : '#0033cc',
					strokeOpacity : 0.8,
					fillColor : '#3399ff',
					fillOpacity : 0.4,
					map : map
				});

				if (!isNaN(m_lat) && !isNaN(m_lng)) {
					const missingPos = new kakao.maps.LatLng(m_lat, m_lng);

					const missingCircle = new kakao.maps.Circle({
						center : missingPos,
						radius : 800,
						strokeWeight : 2,
						strokeColor : '#cc0000',
						strokeOpacity : 0.8,
						fillColor : '#ff6666',
						fillOpacity : 0.4,
						map : map
					});

					const bounds = new kakao.maps.LatLngBounds();
					bounds.extend(witnessPos);
					bounds.extend(missingPos);
					map.setBounds(bounds);
				}

			} else {
				alert("장소를 찾을 수 없습니다: " + place);
			}
		});
	</script>
</body>
</html>
