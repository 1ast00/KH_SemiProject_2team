<!-- 관리자 전용 메인 페이지 (관리자 로그인 후 이동) 추후 만들 예정 -->
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>관리자 로그인</title>
  <!-- CSS추가해야됨 -->
</head>
<body>

  <div class="container">
    <div class="logo">
      <img src="img/logo.png" alt="로고">
      <div class="logo-text">
        <h1>다시, 봄</h1>
        <p>Dasi, Bom</p>
      </div>
    </div>

   
     <form action="adminLogin" method="post">
    <input type="text" name="admin_id" placeholder="아이디" required />
    <input type="password" name="admin_pw" placeholder="비밀번호" required />
    <input type="submit" value="로그인" />
</form>

    
    <%
      String error = request.getParameter("error");
      if ("1".equals(error)) {
    %>
      <p style="color:red; margin-top:15px;">아이디 또는 비밀번호가 올바르지 않습니다.</p>
    <% } %>

  </div>

</body>
</html>