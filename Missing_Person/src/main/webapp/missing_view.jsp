<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>실종자 정보 확인</title>
</head>
<body>
    <h1>아래 정보로 실종자 접수가 완료되었습니다.</h1>

    <div class="container">
        <div class="image-box">
            <c:if test="${not empty serialNum}">
                <img src="${pageContext.request.contextPath}/image?id=${serialNum}" alt="${person.name} 이미지" 
                     onerror="this.onerror=null;this.src='${pageContext.request.contextPath}/images/default.png';"/>
            </c:if>
            <c:if test="${empty serialNum}">
                <img src="${pageContext.request.contextPath}/images/default.png" alt="이미지 없음" />
            </c:if>
        </div>
        <div class="info-box">
            <p><strong>이름 :</strong> ${person.name}</p>
            <p><strong>성별 :</strong> ${person.gender}</p>
            <p><strong>생년월일 :</strong> ${person.birth}</p>
            <p><strong>마지막 목격 장소 :</strong> ${person.place}</p>
            <p><strong>실종 날짜 :</strong> ${person.date}</p>
            <p><strong>기타 특징 :</strong> ${person.etc}</p>
        </div>
    </div>

    <div class="button-group">
        <a href="${pageContext.request.contextPath}/missing/list">실종자 목록 보기</a> <!-- 정보등록 마친 후 MissingListServlet으로 이동 -->
    </div>
</body>
</html>