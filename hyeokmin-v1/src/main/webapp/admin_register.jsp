<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>관리자 회원가입</title>
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

  <div class="container">
    <div class="logo">
      <img src="img/logo.png" alt="로고">
      <div class="logo-text">
        <h1>다시, 봄</h1>
        <p>Dasi, Bom</p>
      </div>
    </div>

    
    <form action="adminRegister" method="post">
    <input type="text" name="admin_serialNum" placeholder="관리자 고유번호" required />
    <input type="text" name="admin_id" placeholder="아이디" required />
    <input type="password" name="admin_pw" placeholder="비밀번호" required />
    <input type="text" name="admin_name" placeholder="이름" required />
    <input type="text" name="admin_phone" placeholder="전화번호" required />
    <input type="email" name="admin_email" placeholder="이메일 (선택)" />
    <input type="submit" value="회원가입" />
</form>


    <script>
      // 비밀번호 일치 확인
      document.querySelector("form").onsubmit = function(e) {
        let pw = document.querySelector("[name=admin_pw]").value;
        let pwCheck = document.querySelector("[name=admin_pw_check]").value;

        if (pw !== pwCheck) {
          alert("비밀번호가 일치하지 않습니다.");
          e.preventDefault();
        }
      };

      // 아이디 중복 확인 버튼
      document.querySelector(".check-btn").onclick = function () {
        const idInput = document.querySelector("[name=admin_id]");
        const id = idInput.value.trim();

        if (id === "") {
          alert("아이디를 입력하세요.");
          idInput.focus();
          return;
        }

        // 테스트용 중복 확인 (AJAX로 교체 가능)
        if (id === "admin1") {
          alert("이미 사용 중인 아이디입니다.");
          idInput.focus();
        } else {
          alert("사용 가능한 아이디입니다.");
        }
      };
    </script>

  </div>

</body>
</html>
