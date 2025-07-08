<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>관리자 로그인</title>
  <style>
    * {
      box-sizing: border-box;
      margin: 0;
      padding: 0;
      font-family: 'Arial', sans-serif;
    }

    body {
      background-color: #f4f4f4;
    }

    .container {
      width: 400px;
      background: #eaeaea;
      padding: 40px;
      margin: 100px auto;
      text-align: center;
    }

    .logo {
      display: flex;
      align-items: center;
      justify-content: center;
      margin-bottom: 10px;
    }

    .logo img {
      width: 60px;
      height: 60px;
      margin-right: 10px;
    }

    .logo-text {
      text-align: left;
    }

    .logo-text h1 {
      font-size: 24px;
      font-weight: bold;
    }

    .logo-text p {
      font-size: 16px;
      color: #444;
    }

    .form-group {
      margin: 15px 0;
      text-align: left;
    }

    .form-group label {
      display: block;
      font-size: 14px;
      margin-bottom: 5px;
      margin-left: 5px;
    }

    .form-group input {
      width: 100%;
      padding: 12px;
      border: none;
      border-radius: 5px;
      background: white;
    }

    .submit-btn {
      width: 100%;
      padding: 12px;
      background: black;
      color: white;
      font-weight: bold;
      border: none;
      border-radius: 5px;
      margin-top: 25px;
      cursor: pointer;
    }

    .submit-btn:hover {
      opacity: 0.9;
    }
  </style>
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
