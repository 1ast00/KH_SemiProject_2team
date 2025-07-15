<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>제보 상세 보기</title>
  <style>
    .view-container {
      max-width: 600px;
      margin: 50px auto;
      padding: 30px;
      border: 1px solid #ddd;
      border-radius: 12px;
      background-color: #fdfdfd;
      font-family: sans-serif;
    }
    .view-container h2 {
      text-align: center;
      margin-bottom: 20px;
      color: #cc0066;
    }
    table {
      width: 100%;
      border-collapse: collapse;
    }
    th {
      background-color: #eee;
      text-align: left;
      padding: 10px;
      width: 30%;
    }
    td {
      padding: 10px;
    }
    img {
      max-width: 100%;
      border-radius: 8px;
      margin-top: 15px;
    }
  </style>
</head>
<body>

<div class="view-container">
  <h2>제보 상세 정보</h2>
  <table border="1">
    <tr><th>날짜</th><td>${dto.date}</td></tr>
    <tr><th>장소</th><td>${dto.place}</td></tr>
    <tr><th>성별</th><td>${dto.gender}</td></tr>
    <tr><th>나이</th><td>${dto.age}</td></tr>
    <tr><th>기타</th><td>${dto.etc}</td></tr>
    <tr>
      <th>이미지</th>
      <td>
        <c:if test="${not empty dto.image}">
         <img src="${pageContext.request.contextPath}/resource/upload/${dto.image}" alt="제보 이미지">
        </c:if>
      </td>
    </tr>
  </table>
</div>

</body>
</html>
