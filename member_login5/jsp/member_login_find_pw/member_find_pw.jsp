<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>비밀번호 찾기</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/find_pw.css">

</head>
<body>

<div class="find-pw-container">
    <h2>비밀번호 찾기</h2>

    <label>아이디:</label><br>
    <input type="text" id="member_id" placeholder="아이디 입력" required="required"><br><br>

    <label>이메일:</label><br>
    <input type="email" id="member_email" placeholder="이메일 입력" required><br><br>

    <button id="findPwBtn">비밀번호 재설정 이메일 받기</button>

    <p id="resultMsg" style="margin-top:20px; font-weight:bold;"></p>

</div>

<script src="${pageContext.request.contextPath}/resource/js/find_pw.js?v=1.0"></script>


</body>
</html>