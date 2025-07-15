<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>관리자 로그인</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resource/css/admin_login.css">
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

    <form action="./adminloginView.do" method="post">
      <div class="form-group">
        <label for="admin_id">아이디 입력</label>
        <input type="text" id="admin_id" name="admin_id" placeholder="아이디 입력" required>
      </div>
      <div class="form-group">
        <label for="admin_pw">비밀번호 입력</label>
        <input type="password" id="admin_pw" name="admin_pw" placeholder="비밀번호 입력" required>
      </div>
      <button type="submit" class="submit-btn">로그인</button>
    </form>

	<!-- 로그인 실패 시 alert 출력 -->
    <c:if test="${param.error eq '1'}">
      <script>
        window.onload = function () {
          alert("아이디 또는 비밀번호가 올바르지 않습니다.");
        };
      </script>
    </c:if>
  </div>

</body>
</html>