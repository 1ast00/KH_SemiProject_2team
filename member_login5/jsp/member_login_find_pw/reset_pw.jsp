<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>비밀번호 재설정</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/reset_pw.css">
</head>
<body>

<div class="reset-pw-container">

    <h2>비밀번호 재설정</h2>

    <form action="${pageContext.request.contextPath}/memberResetPwAction.do" method="post">

       
        <input type="hidden" name="member_id" value="${member_id}">
        <input type="hidden" name="member_email" value="${member_email}">

        <input type="password" name="new_pw" placeholder="새 비밀번호" required><br>
        <input type="password" name="confirm_pw" placeholder="새 비밀번호 확인" required><br>

        <button type="submit">변경하기</button>
    </form>

    <c:if test="${not empty error}">
        <p class="error-msg">${error}</p>
    </c:if>

</div>

</body>
</html>
