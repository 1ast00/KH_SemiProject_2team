<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>관리자 회원가입</title>
  <!--  webapp -> css 폴더에 css삽입 오네가이 -->
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
      margin: 10px 0;
      text-align: left;
    }

    .form-group input {
      width: 100%;
      padding: 12px;
      border: none;
      border-radius: 5px;
      background: white;
    }

    .check-wrap {
      display: flex;
      justify-content: space-between;
      align-items: center;
    }

    .check-wrap input {
      flex: 1;
    }

    .check-btn {
      background: #666;
      color: white;
      border: none;
      padding: 12px;
      margin-left: 10px;
      border-radius: 5px;
      cursor: pointer;
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

  <h2>관리자 회원가입</h2>

  <form action="admin_register" method="post">
    <input type="text" name="admin_id" id="admin_id" placeholder="아이디" required />
    <button type="button" id="checkIdBtn">아이디 중복확인</button>
    <span id="idCheckResult"></span>

    <input type="password" name="admin_pw" placeholder="비밀번호" required />
    <input type="password" name="admin_pw_check" placeholder="비밀번호 확인" required />
    <input type="text" name="admin_name" placeholder="이름" required />
    <input type="email" name="admin_email" placeholder="이메일" required />
    <input type="text" name="admin_phone" placeholder="전화번호" required />

    <button type="submit">관리자 가입</button>
  </form>

  <script>
    window.onload = function () {
      // 비밀번호 확인
      document.querySelector("form").onsubmit = function (e) {
        const pw = document.querySelector("input[name='admin_pw']").value;
        const pwCheck = document.querySelector("input[name='admin_pw_check']").value;

        if (pw !== pwCheck) {
          alert("비밀번호가 일치하지 않습니다.");
          e.preventDefault();
          return false;
        }
      };

      // AJAX 중복 확인
      document.getElementById("checkIdBtn").onclick = function () {
        const input = document.getElementById("admin_id");
        const adminId = input.value.trim();
        const resultSpan = document.getElementById("idCheckResult");

        if (adminId === "") {
          resultSpan.textContent = "아이디를 입력하세요.";
          resultSpan.style.color = "red";
          return;
        }

        fetch("checkAdminId?admin_id=" + encodeURIComponent(adminId))
          .then(response => response.text())
          .then(data => {
            if (data === "duplicate") {
              resultSpan.textContent = "이미 사용 중인 아이디입니다.";
              resultSpan.style.color = "red";
            } else if (data === "available") {
              resultSpan.textContent = "사용 가능한 아이디입니다.";
              resultSpan.style.color = "green";
            } else {
              resultSpan.textContent = "예상치 못한 응답: " + data;
              resultSpan.style.color = "orange";
            }
          })
          .catch(err => {
            resultSpan.textContent = "중복확인 실패: " + err;
            resultSpan.style.color = "red";
          });
      };
    };
  </script>

</body>
</html>