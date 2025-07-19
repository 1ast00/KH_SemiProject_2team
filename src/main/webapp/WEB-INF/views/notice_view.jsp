<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>공지사항 상세</title>
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/notice_view.css">
</head>
<body>
    <jsp:include page="/WEB-INF/views/template/header.jsp" />

    <div class="container">
        <h2 class="page-title">공지사항 상세</h2>

        <c:choose>
            <c:when test="${not empty notice}">
                <div class="notice-details">
                    <div class="detail-row title-row">
                        <span class="label">제목:</span>
                        <span class="value">${notice.title}</span>
                    </div>
                    <div class="detail-row info-row">
                        <span class="label">작성일:</span>
                        <span class="value"><fmt:formatDate value="${notice.writeDate}" pattern="yyyy.MM.dd HH:mm"/></span>
                        <span class="separator">|</span>
                        <span class="label">조회수:</span>
                        <span class="value">${notice.views}</span>
                        <%-- 관리자 정보가 필요하면 아래 주석을 풀고 사용 (NoticeDTO에 adminName 필드가 추가되었다고 가정)
                        <span class="separator">|</span>
                        <span class="label">작성자:</span>
                        <span class="value">${notice.adminName}</span>
                        --%>
                    </div>
                    <div class="detail-row content-row">
                        <pre class="content">${notice.content}</pre> <%-- <pre> 태그로 줄바꿈 유지 --%>
                    </div>
                </div>

                <div class="button-container">
                    <button type="button" onclick="location.href='${pageContext.request.contextPath}/noticeList.do'">목록으로</button>
                </div>
            </c:when>
            <c:otherwise>
                <p class="no-notice">해당 공지사항을 찾을 수 없습니다.</p>
                <div class="button-container">
                    <button type="button" onclick="location.href='${pageContext.request.contextPath}/noticeList.do'">목록으로</button>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>