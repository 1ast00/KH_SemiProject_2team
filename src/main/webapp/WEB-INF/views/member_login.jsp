<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>로그인 페이지</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath }/resource/css/main.css">
</head>
<body>
  <div class="container">
    <div class="logo">
      <img src="${pageContext.request.contextPath}/resource/img/logo.png" alt="로고">
      <h2>다시, 봄<br><span>Dasi, Bom</span></h2>
    </div>

    <div id="error-msg" class="error-msg" style="display:none;">
      아이디 또는 비밀번호가 올바르지 않습니다.
    </div>

    <form action="memberLogin.do" method="post">
        <input type="text" name="member_id" placeholder="아이디" required />
        <input type="password" name="member_pw" placeholder="비밀번호" required />
        <input type="submit" value="로그인" />
    </form>
  </div>

  <script>
    const params = new URLSearchParams(window.location.search);
    if (params.get("error") === "1") {
      document.getElementById("error-msg").style.display = "block";
    }
  </script>
</body>
</html>
