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
        <th>관리</th> <!-- 관리 헤더 추가(작성한 회원이나 관리자만 삭제 가능하게하기위해 필요 -->
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
            <c:if test="${not empty person.image}">
              <img src="${pageContext.request.contextPath}${person.image}" width="100" height="100" alt="${person.name} 이미지" />
            </c:if>
            <c:if test="${empty person.image}">
              이미지 없음
            </c:if>
          </td>
          <td> <!-- 삭제 버튼을 포함한 관리 셀 추가 -->
            <!-- 현재 로그인한 사용자가 관리자 또는, 해당 글 작성한 회원일 경우에만 삭제 버튼 보이기 --> <!-- confirm()함수 : 경고창띄우기 -->
            <c:if test="${sessionScope.loginType == 'ADMIN' or sessionScope.loginSerial == person.memberSerialNum}">
              <form action="missingDelete.do" method="post" onsubmit="return confirm('정말로 이 정보를 삭제하시겠습니까?');">
                <!-- 삭제할 대상의 고유 번호를 hidden 타입으로 같이 전송 -->
                <input type="hidden" name="missingSerialNum" value="${person.missingSerialNum}" />
                <button type="submit">삭제</button>
              </form>
            </c:if>
          </td>
        </tr>
      </c:forEach>
    </tbody>
  </table>

</body>
</html>
