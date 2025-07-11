<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>다시, 봄 - 실종자 목록</title>
</head>
<body>
<div class="container">
    <div class="header">
        <div class="logo">다시, 봄</div>
    </div>
    
    <div class="nav">
        <div class="nav-item active">실종 정보</div>
        <div class="nav-item">목격 정보</div>
        <div class="nav-item">공지사항</div>
        <div class="nav-item">마이 페이지</div>
    </div>
    
    <h2 class="list-title">실종자 목록</h2>

    <div class="search-bar">
        <input type="text" placeholder="이름">
        <input type="text" placeholder="성별">
        <input type="text" placeholder="실종 위치">
        <button>검색</button>
    </div>

    <div class="missing-list-grid">
        <c:forEach var="person" items="${missingList}">
            <div class="person-card">
                <%-- DTO의 serialNum 필드 접근 --%>
                <img src="${pageContext.request.contextPath}/image?id=${person.serialNum}"
                     alt="${person.name} 이미지" 
                     onerror="this.onerror=null;this.src='${pageContext.request.contextPath}/images/default.png';"/>
                
                <div class="person-details">
                    <%-- DTO 필드명 변경에 따른 EL 수정 --%>
                    <p><strong>이름:</strong> ${person.name}</p>
                    <p><strong>성별:</strong> ${person.gender}</p>
                    <p><strong>생년월일:</strong> ${person.birth}</p>
                    <p><strong>실종날짜:</strong> ${person.date}</p>
                    <p><strong>마지막 목격장소:</strong> ${person.place}</p>
                    <p><strong>기타 특징:</strong> ${person.etc}</p>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>