<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <meta charset="UTF-8" />
  <head>
    <meta charset="UTF-8" />
    <title>로그인</title>
    <style>
      body {
        background-color: #f9f9f9;
        margin: 0;
        font-family: "Noto Sans KR", sans-serif;
      }

      .container {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        height: 100vh;
      }

      .logo {
        text-align: center;
        margin-bottom: 20px;
      }

      .logo img {
        width: 50px;
      }

      .logo h1 {
        font-size: 24px;
        margin: 5px 0 0;
        font-weight: bold;
      }

      .logo span {
        font-size: 16px;
        color: #555;
      }

      .login-box {
        background-color: #ffeef1;
        padding: 40px;
        border-radius: 10px;
        width: 360px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.05);
      }

      .login-box input {
        width: 100%;
        padding: 12px;
        margin-bottom: 16px;
        border: none;
        border-radius: 4px;
        font-size: 14px;
      }

      .login-box button {
        width: 100%;
        padding: 12px;
        border: none;
        font-size: 16px;
        color: white;
        background-color: #f88caa;
        border-radius: 4px;
        margin-bottom: 12px;
        cursor: pointer;
      }

      .login-box button.signup {
        background-color: #fbb3c7;
      }
    </style>
  </head>
  <body>
    <div class="container">
      <div class="logo">
        <img src="img/logo.png" alt="로고" />
        <h1>다시, 봄</h1>
        <span>Dasi, Bom</span>
      </div>

      <div class="login-box">
        <form action="/login" method="post">
          <input
            type="text"
            name="member_id"
            placeholder="아이디를 입력하세요"
            required
          />
          <input
            type="password"
            name="member_pw"
            placeholder="비밀번호를 입력하세요"
            required
          />
          <button type="submit">로그인</button>
        </form>
        <form action="member_join.jsp">
          <button type="submit" class="signup">회원가입</button>
        </form>
      </div>
    </div>
  </body>
</html>
