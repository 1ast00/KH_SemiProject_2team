<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>공지사항 목록</title>
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/notice_list.css">
    
    </head>
<body>
    <jsp:include page="/WEB-INF/views/template/header.jsp" />

    <div class="container">
        <h2 class="page-title">공지사항</h2>

        <div class="search-area">
            <input type="text" placeholder="검색어를 입력하세요">
            <button>검색</button>
        </div>

        <div class="write-btn-container">
            <button onclick="location.href='${pageContext.request.contextPath}/noticeWriteForm.do'">글쓰기</button>
            </div>

        <table>
            <thead>
                <tr>
                    <th>no</th>
                    <th>제목</th>
                    <th>작성일</th>
                    <th>조회수</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${not empty noticeList}">
                        <c:forEach var="notice" items="${noticeList}" varStatus="status">
                            <tr>
                                <td>${notice.num}</td> 
                                <td class="title-col">
                                    <a href="${pageContext.request.contextPath}/noticeView.do?num=${notice.num}">${notice.title}</a>
                                </td>
                                <td><fmt:formatDate value="${notice.writeDate}" pattern="yyyy.MM.dd"/></td>
                                <td>${notice.views}</td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="4" class="no-results">등록된 공지사항이 없습니다.</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>

        <div class="pagination">
            <c:if test="${paging.prev}"> 
                <a href="${pageContext.request.contextPath}/noticeList.do?page=${paging.startPage - 1}">&laquo;</a>
            </c:if>

            <c:forEach var="i" begin="${paging.startPage}" end="${paging.endPage}">
                <a href="${pageContext.request.contextPath}/noticeList.do?page=${i}" class="${i == paging.page ? 'active' : ''}">${i}</a>
            </c:forEach>

            <c:if test="${paging.next}"> 
                <a href="${pageContext.request.contextPath}/noticeList.do?page=${paging.endPage + 1}">&raquo;</a>
            </c:if>
        </div>
    </div> <%-- .container 닫는 태그 --%>
</body>
</html>