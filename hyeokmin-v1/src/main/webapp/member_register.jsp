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
    <h2>회원가입</h2>
    <form action="memberRegister" method="post">
        <input type="text" name="member_serialNum" placeholder="회원 고유번호 (예: M0001)" required />

        <!-- 아이디 + 중복확인 버튼 -->
        <div style="display: flex; gap: 10px;">
            <input type="text" name="member_id" id="member_id" placeholder="아이디" required style="flex: 1;" />
            <button type="button" id="checkIdBtn" style="width: 100px;">중복 확인</button>
        </div>
        <span id="idCheckResult"></span>

        <input type="password" name="member_pw" placeholder="비밀번호" required />
        <input type="password" name="member_pw_check" placeholder="비밀번호 확인" required />
        <input type="text" name="member_name" placeholder="이름" required />
        <input type="email" name="member_email" placeholder="이메일 (선택)" />
        <input type="text" name="member_phone" placeholder="전화번호" required />
        <input type="text" name="admin_serialNum" placeholder="소속 관리자 번호" required />
        <input type="submit" value="회원가입" />
    </form>
</div>

<!--  JavaScript는 body 맨 아래에 배치 -->
<script>
  window.onload = function () {
    // 비밀번호 확인
    document.querySelector("form").onsubmit = function(e) {
      const pw = document.querySelector("input[name='member_pw']").value;
      const pwCheck = document.querySelector("input[name='member_pw_check']").value;
      if (pw !== pwCheck) {
        alert("비밀번호가 일치하지 않습니다.");
        e.preventDefault();
        return false;
      }
    };

    // 아이디 중복 검사
    document.getElementById("checkIdBtn").onclick = function () {
      const memberId = document.getElementById("member_id").value;
      const resultSpan = document.getElementById("idCheckResult");

      if (memberId.trim() === "") {
        resultSpan.textContent = "아이디를 입력해주세요.";
        resultSpan.style.color = "red";
        return;
      }

      fetch("checkMemberId?member_id=" + encodeURIComponent(memberId))
        .then(res => res.text())
        .then(data => {
          if (data === "duplicate") {
            resultSpan.textContent = "이미 사용 중인 아이디입니다.";
            resultSpan.style.color = "red";
          } else {
            resultSpan.textContent = "사용 가능한 아이디입니다!";
            resultSpan.style.color = "green";
          }
        })
        .catch(err => {
          resultSpan.textContent = "오류 발생: " + err;
          resultSpan.style.color = "red";
        });
    };
  };
</script>

</body>
</html>