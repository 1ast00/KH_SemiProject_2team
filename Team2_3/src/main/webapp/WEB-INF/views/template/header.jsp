<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>header</title>
<style>
a {
	text-decoration: none;
	color: inherit; /* 링크 기본 색상 제거 */
}

.logo-text {
	display: inline-block; /* h1과 p를 인라인 블록으로 묶어 함께 정렬 */
	vertical-align: middle;
}

.logo img {
	width: 60px; /* 로고 이미지 크기 조정 */
	height: auto;
	vertical-align: middle;
	margin-right: 10px;
}

.logo h1 {
	display: inline-block; /* "다시, 봄"과 이미지를 가로로 정렬 */
	font-size: 2.5em; /* "다시, 봄" 크기 */
	color: #FF69B4; /* 핑크색 계열 */
	margin: 0;
	vertical-align: middle;
}

.logo p {
	font-size: 1.2em; /* "Dasi, Bom" 크기 */
	color: #888;
	margin: 5px 0 0;
	display: block; /* 줄바꿈하여 h1 아래에 오도록 */
	text-align: left; /* Dasi,Bom 텍스트 왼쪽 정렬 */
}

.main-header {
	display: flex;
	flex-direction: column; /* 세로 정렬 */
	align-items: flex-start; /* 로고 왼쪽 정렬 */
	padding: 20px; /* 좌우 패딩 */
	background-color: #fff;
	border-bottom: 1px solid #eee;
}

.main-header .logo {
	text-align: left; /* 로고 텍스트 왼쪽 정렬 */
	margin-bottom: 20px; /* 로고와 메뉴 사이 간격 */
	width: 100%; /* 부모에 맞춰 너비 설정 */
	max-width: 1440px; /* 최대 너비 제한 */
	margin-left: auto; /* 왼쪽 정렬 + 가운데 정렬 보정 */
	margin-right: auto; /* 오른쪽 정렬 + 가운데 정렬 보정 */
	padding-left: 20px; /* 전체 헤더의 패딩을 고려하여 조절 */
	box-sizing: border-box; /* 패딩이 너비에 포함되도록 */
}

/* 메인 네비게이션 */
.main-nav {
	width: 100%; /* 네비게이션 전체 너비 */
	background-color: #FFB6C1; /* 메뉴바 배경색 */
}

.main-nav ul {
	list-style: none;
	padding: 0;
	margin: 0 auto; /* 중앙 정렬 */
	display: flex; /* 메뉴 항목 가로 정렬 */
	justify-content: space-around; /* 메뉴 항목을 넓게 퍼뜨림 */
	max-width: 1440px; /* 최대 너비 설정 */
	box-sizing: border-box; /* 패딩이 너비에 포함되도록 */
}

.main-nav ul li {
	position: relative; /* 드롭다운 위치 지정을 위해 */
	padding: 15px 0; /* 좌우 패딩을 0으로 하고, 내부 링크에 패딩 적용 */
	flex-grow: 1; /* 남은 공간을 균등하게 차지하여 넓게 퍼지도록 */
	text-align: center; /* 텍스트 중앙 정렬 */
}

.main-nav ul li a {
	color: #fff;
	font-weight: bold;
	font-size: 1.1em;
	display: block; /* 링크 영역 확장 */
	padding: 0 10px; /* 링크 자체에 패딩을 주어 클릭 영역 확보 */
}

.main-nav ul li:hover {
	background-color: #FFC0CB; /* 호버 시 배경색 변경 */
}

/* 드롭다운 메뉴 스타일 (초기에는 숨김) */
.dropdown-menu {
	display: none !important; /* 초기에는 숨김 + imp추가한거 */
	position: absolute;
	top: 100%; /* 상위 메뉴 바로 아래에 위치 */
	left: 0;
	background-color: #FFC0CB; /* 드롭다운 배경색 */
	list-style: none;
	padding: 0;
	margin: 0;
	min-width: 100%; /* 상위 메뉴 너비에 맞춤 */
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2); /* 그림자 추가 */
	z-index: 10; /* 다른 요소 위로 오게 */
}

.dropdown-menu li {
	padding: 0; /* 드롭다운 메뉴 li의 패딩 초기화 */
	text-align: center; /* 텍스트 중앙 정렬 */
	white-space: nowrap; /* 텍스트 줄바꿈 방지 */
}

.dropdown-menu li a {
	color: #fff;
	font-weight: normal;
	font-size: 1em;
	padding: 10px 15px; /* 드롭다운 항목 링크 패딩 */
	display: block; /* 링크 영역 확장 */
}

.dropdown-menu li:hover {
	background-color: #FFB6C1; /* 드롭다운 항목 호버 시 색상 */
}

/* 드롭다운 표시 (CSS only approach) */
.has-dropdown:hover .dropdown-menu {
	display: block !important; /* 마우스 오버 시 드롭다운 메뉴 표시 + imp추가함*/
}
</style>
</head>
<body>
	<header class="main-header">
		<div class="logo">
			<img src="./resource/img/logo.png" alt="다시, 봄 로고"> <a
				href="main.do" class="logo-text">
				<h1>다시, 봄</h1>
				<p>Dasi, Bom</p>
			</a>
		</div>
		<nav class="main-nav">
			<ul>
				<li class="has-dropdown"><a
					href="${pageContext.request.contextPath}/main.do">실종 정보</a>
					<ul class="dropdown-menu">
						<li><a
							href="${pageContext.request.contextPath}/missingInsertView.do">실종자
								제보</a></li>
						<li><a
							href="${pageContext.request.contextPath}/missingList.do">실종자
								목록 조회</a></li>
					</ul></li>
				<li class="has-dropdown"><a href="#">목격 정보</a>
					<ul class="dropdown-menu">
						<li><a
							href="${pageContext.request.contextPath}/witnessInsertView.do">목격자
								제보</a></li>
						<li><a
							href="${pageContext.request.contextPath}/witnessList.do">목격정보
								목록 조회</a></li>
					</ul></li>
				<li class="has-dropdown"><a href="/notice">공지사항</a>
					<ul class="dropdown-menu">
						<li><a href="/notice">공지사항</a></li>
						<li><a href="/legal-info">법률 정보</a></li>
					</ul></li>

				<li class="has-dropdown"><a href="#">마이 페이지</a>
					<ul class="dropdown-menu">
						<c:choose>
							<c:when test="${sessionScope.member == null }">
								<li><a
									href="${pageContext.request.contextPath}/memberRegisterView.do">회원가입</a></li>
								<li><a
									href="${pageContext.request.contextPath}/memberLoginView.do">로그인</a></li>
   							 </c:when>
    						<c:otherwise>
								<li><a
									href="${pageContext.request.contextPath}/memberLogout.do">로그아웃</a></li>
								<li><a
									href="${pageContext.request.contextPath}/memberMypageInfoView.do">마이
										페이지</a></li>
    						</c:otherwise>
						</c:choose>
					</ul></li>
			</ul>
		</nav>
	</header>
</body>
</html>