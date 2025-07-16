<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>목격자 제보 목록</title>
<style>
body {
	font-family: '맑은 고딕', sans-serif;
	background-color: #fefefe;
	padding: 40px;
}

h2 {
	color: #333;
	margin-bottom: 20px;
	text-align: center;
}

.search-box {
	display: flex;
	justify-content: center;
	margin-bottom: 30px;
}

.search-box input {
	padding: 8px;
	border: 1px solid #ddd;
	border-radius: 20px;
	width: 300px;
	font-size: 14px;
	padding-left: 15px;
}

.submit {
	width: 80px;
	border-radius: 10px;
	background-color: pink;
	border: none;
	margin-left: 10px;
	cursor: pointer;
}

.grid-container {
	display: grid;
	grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
	gap: 20px;
}

.card {
	background-color: #fff;
	border: 1px solid #eee;
	border-radius: 10px;
	padding: 15px;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.05);
	text-decoration: none;
	color: #333;
}

.card img {
	width: 100%;
	height: 200px;
	object-fit: cover;
	border-radius: 8px;
	margin-bottom: 10px;
}

.info {
	font-size: 14px;
	color: #444;
	line-height: 1.6;
}

.info span {
	font-weight: bold;
}
</style>
</head>
<body>
	<h2>목격자 제보 목록</h2>
	<div class="search-box">
		<input type="text" placeholder="이름, 나이, 성별, 위치, 기타로 검색">
		<button class="submit">전송</button>
	</div>
	<div class="grid-container">
		<c:forEach var="dto" items="${witnessList}">
			<a href="witnessView.do?witnessSerialNum=${dto.witnessSerialNum}">
				<div class="card">
					<img
						src="${pageContext.request.contextPath}/resource/upload/${dto.image}"
						onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/resource/img/default.jpg';"
						alt="제보 이미지">
					<div class="info">
						<div>
							<span>성별:</span> ${dto.gender}
						</div>
						<div>
							<span>나이:</span>
							<c:choose>
								<c:when test="${dto.age != null && dto.age <= 10}">0~10세</c:when>
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
						</div>
						<div>
							<span>위치:</span> ${dto.place}
						</div>
						<div>
							<span>기타:</span> ${dto.etc}
						</div>
					</div>
				</div>
			</a>
		</c:forEach>
	</div>
</body>
</html>
