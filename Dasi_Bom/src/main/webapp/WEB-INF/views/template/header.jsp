<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>header</title>
<style>
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
	padding-left: 20px; /* 전체 헤더의 패딩을 고려해 조절 */
	box-sizing: border-box; /* 패딩이 너비에 포함되도록 */
}
</style>
</head>
<body>
	<header class="main-header">
		<div class="logo">
			<img src="./resources/img/logo.png" alt="다시, 봄 로고"> <a
				href="/main.do" class="logo-text">
				<h1>다시, 봄</h1>
				<p>Dasi, Bom</p>
			</a>
		</div>
		<nav class="main-nav">
			<ul>
				<li class="has-dropdown"><a href="#">실종 정보</a>
					<ul class="dropdown-menu">
						<li><a href="${pageContext.request.contextPath}/missingInsertView.do">실종자 제보</a></li>
						<li><a href="${pageContext.request.contextPath}/missingList.do">실종자 목록 조회</a></li>
					</ul></li>
				<li class="has-dropdown"><a href="#">목격 정보</a>
					<ul class="dropdown-menu">
						<li><a href="${pageContext.request.contextPath}/witnessInsertView.do">목격자 제보</a></li>
						<li><a href="${pageContext.request.contextPath}/witnessList.do">목격정보 목록 조회</a></li>
					</ul></li>
				<li class="has-dropdown"><a href="/notice">공지사항</a>
					<ul class="dropdown-menu">
						<li><a href="/notice">공지사항</a></li>
						<li><a href="/legal-info">법률 정보</a></li>
					</ul></li>
				<li class="has-dropdown"><a href="/mypage">마이 페이지</a>
					<ul class="dropdown-menu">
						<li><a href="memnerRegisterView">회원가입</a></li>
						<li><a href="memberLoginView.do">로그인</a></li>
						<li><a href="/mypage">마이 페이지</a></li>
					</ul></li>
			</ul>
		</nav>
	</header>
</body>
</html>