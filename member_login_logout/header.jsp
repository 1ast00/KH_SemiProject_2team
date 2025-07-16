<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>header</title>
<style>
.main-header {
	display: flex;
	flex-direction: column;
	align-items: flex-start;
	padding: 20px;
	background-color: #fff;
	border-bottom: 1px solid #eee;
}

.main-header .logo {
	text-align: left;
	margin-bottom: 20px;
	width: 100%;
	max-width: 1440px;
	margin-left: auto;
	margin-right: auto;
	padding-left: 20px;
	box-sizing: border-box;
}
</style>
</head>
<body>

<%
    Object loginMember = session.getAttribute("loginMember");
%>

<header class="main-header">
    <div class="logo">
        <img src="${pageContext.request.contextPath}/resource/img/logo.png" alt="다시, 봄 로고">
        <a href="${pageContext.request.contextPath}/main.do" class="logo-text">
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
                </ul>
            </li>
            <li class="has-dropdown"><a href="#">목격 정보</a>
                <ul class="dropdown-menu">
                    <li><a href="${pageContext.request.contextPath}/witnessInsertView.do">목격자 제보</a></li>
                    <li><a href="${pageContext.request.contextPath}/witnessList.do">목격정보 목록 조회</a></li>
                </ul>
            </li>
            <li class="has-dropdown"><a href="${pageContext.request.contextPath}/noticeList.do">공지사항</a>
                <ul class="dropdown-menu">
                    <li><a href="${pageContext.request.contextPath}/noticeList.do">공지사항</a></li>
                    <li><a href="${pageContext.request.contextPath}/lawList.do">법률 정보</a></li>
                </ul>
            </li>           
            <li class="has-dropdown"><a href="#">마이 페이지</a>
                <ul class="dropdown-menu">
                    <% if (loginMember == null) { %>
                        <li><a href="${pageContext.request.contextPath}/memberRegisterView.do">회원가입</a></li>
                        <li><a href="${pageContext.request.contextPath}/memberLoginView.do">로그인</a></li>
                    <% } else { %>
                        <li><a href="${pageContext.request.contextPath}/logout.do">로그아웃</a></li>
                    <% } %>
                    <li><a href="${pageContext.request.contextPath}/mypage.do">마이 페이지</a></li>
                </ul>
            </li>
           
        </ul>
    </nav>
</header>

</body>
</html>
	
</body>
</html>