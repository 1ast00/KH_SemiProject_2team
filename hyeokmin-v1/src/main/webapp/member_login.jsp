<%@ page contentType="text/html; charset=UTF-8" %>
<%
    String error = request.getParameter("error");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>로그인 페이지</title>
  <style>
    body {
      margin: 0;
      font-family: 'Noto Sans KR', sans-serif;
      background-color: #fff;
    }

    .container {
      display: flex;
      flex-direction: column;
      align-items: center;
      padding-top: 40px;
    }

    .logo {
      text-align: center;
    }

    .logo img {
      width: 60px;
      height: 60px;
    }

    .logo h2 {
      margin: 10px 0 0 0;
      font-size: 24px;
      color: #333;
      line-height: 1.4;
    }

    .login-box {
      margin-top: 30px;
      background-color: #ffeaea;
      padding: 40px 30px;
      border-radius: 8px;
      width: 320px;
      display: flex;
      flex-direction: column;
      gap: 15px;
    }

    .login-box input {
      padding: 12px;
      border: 1px solid #ccc;
      border-radius: 6px;
      font-size: 14px;
    }

    .login-btn,
    .join-btn {
      padding: 12px;
      border: none;
      border-radius: 6px;
      font-size: 16px;
      background-color: #f99db5;
      color: #fff;
      cursor: pointer;
    }

    .login-btn:hover,
    .join-btn:hover {
      background-color: #f07fa1;
    }

    .error-msg {
      margin-top: 15px;
      color: red;
      font-size: 14px;
    }
  </style>
</head>
<body>
  <div class="container">
    <div class="logo">
      <img src="img/logo.png" alt="로고">
      <h2>다시, 봄<br><span>Dasi, Bom</span></h2>
    </div>

    <% if ("1".equals(error)) { %>
      <div class="error-msg">아이디 또는 비밀번호가 올바르지 않습니다.</div>
    <% } %>

    
    <form action="memberLogin" method="post">
        <input type="text" name="member_id" placeholder="아이디" required />
        <input type="password" name="member_pw" placeholder="비밀번호" required />
        <input type="submit" value="로그인" />
    </form>
  </div>
</body>
</html>

