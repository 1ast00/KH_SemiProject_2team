<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>이메일 인증</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/verify_email.css">
</head>
<body>

<div class="verify-container">
    <h2>이메일 인증</h2>

    <p>입력하신 이메일로 인증코드가 발송되었습니다.<br>
       받은 코드를 아래에 입력해주세요.</p>

    <form id="verifyForm">
        <input type="text" id="verifyCode" placeholder="인증코드 입력" required><br><br>
        <button id="verifyBtn" type="button">인증 확인</button>
    </form>

    <p id="errorMsg" style="color:red;"></p>

    
    <input type="hidden" id="memberId" value="${member_id}">
    <input type="hidden" id="memberEmail" value="${member_email}">

</div>

<script src="${pageContext.request.contextPath}/resource/js/verify_email.js"></script>

</body>
</html>

