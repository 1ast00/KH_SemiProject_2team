<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>다시, 봄 - 실종자 목록</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/missing_list.css">
</head>
<body>
<div class="container">
    <header class="main-header">
        <div class="logo">
            <a href="#">다시, 봄 Dasi, Bom</a>
        </div>
        <nav class="main-nav">
            <ul>
                <li><a href="#" class="active">실종 정보</a></li>
                <li><a href="#">목격 정보</a></li>
                <li><a href="#">공지사항</a></li>
                <li><a href="#">마이 페이지</a></li>
            </ul>
        </nav>
    </header>

    <main>
        <h2>실종자 목록</h2>

        <div class="search-box">
            <input type="text" placeholder="이름">
            <input type="text" placeholder="나이">
            <input type="text" placeholder="성별">
            <input type="text" placeholder="실종 위치">
            <input type="text" placeholder="기타">
            <button type="submit">🔍</button>
        </div>

        <div class="person-grid">
            <c:forEach var="person" items="${missingList}">
                <div class="person-card">
                    <div class="card-image">
                        <c:choose>
                            <c:when test="${not empty person.image}">
                                <img src="${pageContext.request.contextPath}/uploads/${person.image}" alt="${person.name} 이미지">
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

                        <!-- 삭제 버튼 (관리자 or 작성자만 표시) -->
                        <c:if test="${sessionScope.loginRole eq 'admin'}">
                            <form action="${pageContext.request.contextPath}/missingDelete.do" method="post" style="display:inline;">
                                <input type="hidden" name="missingSerialNum" value="${person.missingSerialNum}" />
                                <button type="submit" onclick="return confirm('정말 삭제하시겠습니까?')">삭제</button>
                            </form>
                        </c:if>

                        <c:if test="${sessionScope.loginRole eq 'member' and sessionScope.loginSerialNum eq person.memberSerialNum}">
                            <form action="${pageContext.request.contextPath}/missingDelete.do" method="post" style="display:inline;">
                                <input type="hidden" name="missingSerialNum" value="${person.missingSerialNum}" />
                                <button type="submit" onclick="return confirm('정말 삭제하시겠습니까?')">삭제</button>
                            </form>
                        </c:if>
                    </div>
                </div>
            </c:forEach>
        </div>
    </main>
</div>
</body>
</html>
