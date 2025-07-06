<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>회원가입 페이지</title>
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

.join-box {
  margin-top: 30px;
  background-color: #ffeaea;
  padding: 40px 30px;
  border-radius: 8px;
  width: 400px;
  display: flex;
  flex-direction: column;
  gap: 15px;
}

.join-box input {
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 6px;
  font-size: 14px;
  width: 100%;
  box-sizing: border-box;
}

.id-check-wrap {
  display: flex;
  gap: 10px;
}

.id-check-wrap input {
  flex: 1;
}

.check-btn {
  padding: 12px;
  background-color: #2196f3;
  color: #fff;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  white-space: nowrap;
}

.submit-btn {
  padding: 12px;
  background-color: #f99db5;
  color: #fff;
  border: none;
  border-radius: 6px;
  font-size: 16px;
  cursor: pointer;
}

.submit-btn:hover {
  background-color: #f07fa1;
}
  </style>
 </head>
<body>
  <div class="container">
    <div class="logo">
      <img src="img/logo.png" alt="로고">
      <h2>다시, 봄<br><span>Dasi, Bom</span></h2>
    </div>

    
    <form action="memberRegister" method="post" class="join-box">
      <div class="id-check-wrap">
       
      </div>
      <input type="text" name="member_id" placeholder="아이디를 입력하세요" required>
      <input type="password" name="member_pw" placeholder="비밀번호를 입력하세요" required>
      <input type="password" name="member_pw_check" placeholder="비밀번호를 다시 입력하세요" required>
      <input type="text" name="member_name" placeholder="이름을 입력하세요" required>
      <input type="text" name="member_phone" placeholder="전화번호를 입력하세요" required>
      <input type="email" name="member_email" placeholder="이메일을 입력하세요" required>
      
      <button type="submit" class="submit-btn">회원가입</button>
    </form>
  </div>

  <script>
    document.querySelector("form").onsubmit = function(e) {
      const pw = document.querySelector("input[name='member_pw']").value;
      const pwCheck = document.querySelector("input[name='member_pw_check']").value;
      if (pw !== pwCheck) {
        alert("비밀번호가 일치하지 않습니다.");
        e.preventDefault();
        return false;
      }
    };
  </script>
</body>
</html>
