<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>다시, 봄 - 실종자 목록</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/missing_list.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/template/header.jsp" />
<div class="container">
    <main>
        <h2>실종자 목록</h2>

        <div class="person-grid">
            <c:forEach var="person" items="${missingList}">
                <a href="${pageContext.request.contextPath}/missingView.do?missingSerialNum=${person.missingSerialNum}" class="person-card-link">
                    <div class="person-card">
                        <div class="card-image">
                            <c:choose>
                                <c:when test="${not empty person.image}">
                                	<!-- Tomcat 서버 설정에서 이미지 저장위해 만든 폴더를 지정하고 사용할 URL경로를 /uploads로 지정했음 -->
                                    <img src="/uploads/${person.image}" alt="${person.name} 이미지">
                                </c:when>
                                <c:otherwise>
                                    <img src="${pageContext.request.contextPath}/resource/images/default_avatar.png" alt="기본 이미지">
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="card-details">
                            <p><strong>이름:</strong> ${person.name}</p>
                            <p><strong>성별:</strong> ${person.gender == 'M' ? '남' : '여'}</p>
                            <p><strong>생년월일:</strong> ${person.birth}</p>
                            <p><strong>기타 특징:</strong> ${person.etc}</p>
                            <p><strong>마지막 목격장소:</strong> ${person.place}</p>

                            <c:if test="${sessionScope.loginRole eq 'admin' or (sessionScope.loginRole eq 'member' and sessionScope.loginSerialNum eq person.memberSerialNum)}">
                                <form action="${pageContext.request.contextPath}/missingDelete.do" method="post" style="display:inline;" onclick="event.stopPropagation();">
                                    <input type="hidden" name="missingSerialNum" value="${person.missingSerialNum}" />
                                    <button type="submit" onclick="return confirm('정말 삭제하시겠습니까?')">삭제</button>
                                </form>
                            </c:if>
                        </div>
                    </div>
                </a>
            </c:forEach>
        </div>

        <div class="pagination">
            <%-- 이전 버튼 --%>
            <c:if test="${paging.prev}">
                <a href="missingList.do?page=${paging.startPage - 1}">&laquo; 이전</a>
                									   		     <%-- &laquo;는 엔티티 임(화살표모양) --%>
            </c:if>

            <%-- 페이지 번호 목록 --%>
            <c:forEach var="num" begin="${paging.startPage}" end="${paging.endPage}">
                <c:choose>
                    <c:when test="${num == paging.page}">
                        <span class="active">${num}</span>
                    </c:when>
                    <c:otherwise>
                        <a href="missingList.do?page=${num}">${num}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <%-- 다음 버튼 --%>
            <c:if test="${paging.next}">
                <a href="missingList.do?page=${paging.endPage + 1}">다음 &raquo;</a>
            </c:if>
        </div>
    </main>
</div>
</body>
</html>