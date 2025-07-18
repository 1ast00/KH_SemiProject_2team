<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>목격자 제보 상세</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/witness_view.css">
<script src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=1953e4a69f69ba521e119d6eca3efa47&libraries=services"></script>
</head>
<body>
<jsp:include page="./template/header.jsp" />
<div class="container">
	<div class="title">목격자 제보</div>
	<div class="content">
		<img
			src="${pageContext.request.contextPath}/img/witness.do?name=${dto.image}"
			onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/img/witness.do?name=default.jpg';"
			alt="제보 이미지"
			style="max-width:100%; height:auto; border-radius:10px;">

		<div class="info">
			<p><span>추정 나이:</span>
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

			<p><span>추정 성별:</span> ${dto.gender}</p>
			<p><span>목격 장소:</span> ${dto.place}</p>
			<p><span>목격 날짜:</span> ${dto.date}</p>
		</div>
	</div>

	<div id="map-wrapper">
		<div id="legend">
			<span style="color: red;">●</span> 실종 장소 &nbsp;&nbsp;
			<span style="color: blue;">●</span> 목격 장소
		</div>
		<div id="map"></div>
	</div>
</div>

<script>
const witnessPlace = "${dto.place}";
const missingPlace = "서울역";

console.log("✅ 목격 장소:", witnessPlace);
console.log("✅ 실종 장소:", missingPlace);

const mapContainer = document.getElementById('map');
const map = new kakao.maps.Map(mapContainer, {
	center: new kakao.maps.LatLng(37.5665, 126.9780),
	level: 7
});

const ps = new kakao.maps.services.Places();
const bounds = new kakao.maps.LatLngBounds();

// 목격 장소
if (witnessPlace) {
	ps.keywordSearch(witnessPlace, function(result, status) {
		if (status === kakao.maps.services.Status.OK) {
			const pos = new kakao.maps.LatLng(result[0].y, result[0].x);
			new kakao.maps.Circle({
				center: pos,
				radius: 800,
				strokeWeight: 2,
				strokeColor: '#0033cc',
				strokeOpacity: 0.8,
				fillColor: '#3399ff',
				fillOpacity: 0.4,
				map: map
			});
			bounds.extend(pos);
			map.setBounds(bounds);
		}
	});
}

// 실종 장소
if (missingPlace) {
	ps.keywordSearch(missingPlace, function(result, status) {
		if (status === kakao.maps.services.Status.OK) {
			const pos = new kakao.maps.LatLng(result[0].y, result[0].x);
			new kakao.maps.Circle({
				center: pos,
				radius: 800,
				strokeWeight: 2,
				strokeColor: '#cc0000',
				strokeOpacity: 0.8,
				fillColor: '#ff6666',
				fillOpacity: 0.4,
				map: map
			});
			bounds.extend(pos);
			map.setBounds(bounds);
		}
	});
}
</script>

</body>
</html>