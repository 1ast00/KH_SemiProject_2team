<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>아이디 찾기</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/member_login.css">
</head>
<body>
  <h2>아이디 찾기</h2>
  <form action="memberFindIdAction.do" method="post">
    <input type="text" name="member_name" placeholder="이름" required />
    <input type="email" name="member_email" placeholder="이메일" required />
    <button type="submit">찾기</button>
  </form>
</body>
</html>

