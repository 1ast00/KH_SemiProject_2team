<%@ page contentType="text/html; charset=UTF-8" %>
<%
    String error = request.getParameter("error");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>로그인 페이지</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath }/resource/css/main.css">
</head>
<body>
  <div class="container">
    <div class="logo">
      <img src="resource/img/logo.png" alt="로고">
      <h2>다시, 봄<br><span>Dasi, Bom</span></h2>
    </div>

    <% if ("1".equals(error)) { %>
      <div class="error-msg">아이디 또는 비밀번호가 올바르지 않습니다.</div>
    <% } %>

    <form action="memberLogin.do" method="post">
        <input type="text" name="member_id" placeholder="아이디" required />
        <input type="password" name="member_pw" placeholder="비밀번호" required />
        <input type="submit" value="로그인" />
    </form>
  </div>
</body>
</html>