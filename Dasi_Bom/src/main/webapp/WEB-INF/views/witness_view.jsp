<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="template/header.jsp" %> <!-- 상단 메뉴 포함 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제보 상세 보기</title>
</head>
<body>

<h2>제보 상세 정보</h2>
<table border="1">
    <tr><th>날짜</th><td>${dto.date}</td></tr>
    <tr><th>장소</th><td>${dto.place}</td></tr>
    <tr><th>성별</th><td>${dto.gender}</td></tr>
    <tr><th>나이</th><td>${dto.age}</td></tr>
    <tr><th>기타</th><td>${dto.etc}</td></tr>
    <tr><th>이미지</th>
        <td>
            <c:if test="${not empty dto.image}">
                <img src="/upload/${dto.image}" width="200px">
            </c:if>
        </td>
    </tr>
</table>

</body>
</html>
