<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>비밀번호 재설정</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/member_login.css">
  <script src="${pageContext.request.contextPath}/resource/js/member_reset_pw.js"></script>
</head>
<body>
  <h2>비밀번호 재설정</h2>

  <form action="memberResetPwAction.do" method="post" id="resetPwForm">
    <input type="hidden" name="member_id" value="${member_id}" />
    <input type="hidden" name="member_email" value="${member_email}" />

    <input type="password" name="new_pw" placeholder="새 비밀번호 입력" required />
    <button type="submit">비밀번호 변경</button>
  </form>

</body>
</html>
