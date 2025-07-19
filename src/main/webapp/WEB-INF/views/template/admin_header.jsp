<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin_header</title>
<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

body {
	font-family: Arial, sans-serif;
	background-color: #f5f5f5;
}

.header {
	background-color: white;
	padding: 20px 0;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.header-content {
	max-width: 1200px;
	margin: 0 auto;
	padding: 0 20px;
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
    color: #000000; /* 블랙 */
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

.nav-container {
	background-color: #000000;
	border-radius: 8px;
	overflow: hidden;
}

.nav-menu {
	display: flex;
	list-style: none;
}

.nav-menu li {
	flex: 1;
	position: relative;
}

.nav-menu li:not(:last-child):after {
	content: '';
	position: absolute;
	right: 0;
	top: 50%;
	transform: translateY(-50%);
	width: 1px;
	height: 60%;
	background-color: #333;
}

.nav-menu a {
	display: block;
	color: white;
	text-decoration: none;
	padding: 15px 20px;
	text-align: center;
	font-size: 16px;
	font-weight: 500;
	transition: background-color 0.3s ease;
}

.nav-menu a:hover {
	background-color: #333;
}

.sub-nav {
	background-color: #7D7D7D;
	padding: 12px 0;
	text-align: center;
}

.sub-nav-text {
	color: white;
	font-size: 16px;
	font-weight: 500;
}

@media ( max-width : 768px) {
	.nav-menu {
		flex-direction: column;
	}
	.nav-menu li:not(:last-child):after {
		display: none;
	}
	.nav-menu a {
		padding: 12px 15px;
		font-size: 14px;
	}
	.logo-text {
		font-size: 20px;
	}
	.logo-text-en {
		font-size: 16px;
	}
}
</style>
</head>
<body>
	<header class="header">
		<div class="header-content">
			<div class="logo">
				<img src="./resource/img/logo.png" alt="다시, 봄 로고"> <a
					href="/main.do" class="logo-text">
					<h1>다시, 봄</h1>
					<p>Dasi, Bom</p>
				</a>
			</div>

			<nav class="nav-container">
				<ul class="nav-menu">
					<li><a href="#" onclick="showSubNav('게시판 관리')">게시판 관리</a></li>
					<li><a href="#" onclick="showSubNav('회원 관리')">회원 관리</a></li>
					<li><a href="#" onclick="showSubNav('공지 사항')">공지 사항</a></li>
					<li><a href="#" onclick="showSubNav('마이페이지')">마이페이지</a></li>
				</ul>

				<div class="sub-nav">
					<div class="sub-nav-text" id="subNavText">게시판 관리</div>
				</div>
			</nav>
		</div>
	</header>

	<script>
		function showSubNav(text) {
			document.getElementById('subNavText').textContent = text;
		}
	</script>
</body>
</html>