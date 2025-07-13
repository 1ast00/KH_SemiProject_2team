<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>실종자 목록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resource/css/missing_list.css">
</head>
<body>

  <h1>실종자 목록</h1>

  <table>
    <thead>
      <tr>
        <th>번호</th>
        <th>이름</th>
        <th>성별</th>
        <th>생년월일</th>
        <th>실종일</th>
        <th>실종장소</th>
        <th>기타</th>
        <th>이미지</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="person" items="${missingList}" varStatus="status">
        <tr>
          <td>${status.index + 1}</td>
          <td>${person.name}</td>
          <td>${person.gender}</td>
          <td>${person.birth}</td>
          <td>${person.missingDate}</td>
          <td>${person.place}</td>
          <td>${person.etc}</td>
          <td>
            <c:choose>
                <c:when test="${not empty person.image}"> <!-- 수정 예정(이미지 저장경로, 이미지가 저장되는 폴더 생성하기 -->
                    <img src="${person.image}" width="100" height="100" alt="${person.name} 이미지" />
                </c:when>
                <c:otherwise>
                    이미지 없음
                </c:otherwise>
            </c:choose>
          </td>
        </tr>
      </c:forEach>
    </tbody>
  </table>

</body>
</html>