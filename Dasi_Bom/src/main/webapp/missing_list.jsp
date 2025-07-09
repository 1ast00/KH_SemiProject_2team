<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<!-- missing_list로 바로 실행하면 안되고 missing_insert에서 접수 -> view에서 실종자목록 클릭 -->
  <title>실종자 목록</title>
  <style>
    table {
      width: 100%;
      border-collapse: collapse;
    }

    th, td {
      border: 1px solid #ccc;
      padding: 8px;
      text-align: center;
    }

    th {
      background-color: #f2f2f2;
    }
  </style>
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
            <img src="/upload/${person.image}" width="100" height="100" alt="이미지" />
          </td>
        </tr>
      </c:forEach>
    </tbody>
  </table>

</body>
</html>