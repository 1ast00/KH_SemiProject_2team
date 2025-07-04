<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
  <meta charset="UTF-8">
  <title>회원가입 페이지</title>
  <style>
    body {
      background-color: #f9f9f9;
      margin: 0;
      font-family: 'Noto Sans KR', sans-serif;
    }

    .header {
      background-color: #fff;
      padding: 10px 30px;
      display: flex;
      align-items: center;
      border-bottom: 1px solid #eee;
    }

    .header img {
      width: 40px;
      margin-right: 10px;
    }

    .header h1 {
      margin: 0;
      font-size: 20px;
    }

    .nav {
      background-color: #ffb6c1;
      display: flex;
      justify-content: center;
      gap: 60px;
      padding: 12px 0;
      font-weight: bold;
    }

    .nav a {
      color: white;
      text-decoration: none;
      font-size: 15px;
      padding: 6px 12px;
    }

    .container {
      display: flex;
      justify-content: center;
      margin-top: 40px;
    }

    .form-box {
      background-color: #fff0f3;
      padding: 50px;
      border-radius: 10px;
      width: 400px;
      box-shadow: 0 0 10px rgba(0,0,0,0.05);
    }

    .form-box input {
      width: 100%;
      padding: 12px;
      margin-bottom: 16px;
      border: none;
      border-radius: 4px;
      font-size: 14px;
    }

    .form-box button {
      width: 100%;
      padding: 12px;
      border: none;
      font-size: 16px;
      color: white;
      background-color: #fbb3c7;
      border-radius: 4px;
      cursor: pointer;
    }

    .form-box button:hover {
      background-color: #f88caa;
    }
  </style>
</head>
<body>

  <div class="header">
    <img src="img/logo.png" alt="로고">
    <div>
      <div style="font-size: 20px;">다시, 봄</div>
      <div style="font-size: 14px;">Dasi, Bom</div>
    </div>
  </div>

  <div class="nav">
    <a href="#">실종 정보</a>
    <a href="#">목격 정보</a>
    <a href="#">공지사항</a>
    <a href="#">마이 페이지</a>
  </div>

  <div class="container">
    <div class="form-box">
      <form action="/join" method="post">
        <input type="text" name="member_id" placeholder="아이디" required>
        <input type="password" name="member_pw" placeholder="비밀번호" required>
        <input type="email" name="member_email" placeholder="이메일" required>
        <input type="text" name="member_name" placeholder="이름" required>
        <button type="submit">Signup</button>
      </form>
    </div>
  </div>

</body>
</html>