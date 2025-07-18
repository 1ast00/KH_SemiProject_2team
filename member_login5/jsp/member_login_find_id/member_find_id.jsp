<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>아이디 찾기</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/find_id.css">

</head>
<body>


<div class="find-id-container">

    <h2>아이디 찾기</h2>  

    <label>이름:</label><br>
    <input type="text" id="member_name" placeholder="이름 입력" required><br><br>

    <label>이메일:</label><br>
    <input type="email" id="member_email" placeholder="이메일 입력" required><br><br>

    <button id="findIdBtn">아이디 찾기</button>

    <p id="resultMsg" style="margin-top:20px; font-weight:bold;"></p>

</div>

<script src="${pageContext.request.contextPath}/resource/js/find_id.js"></script>


</body>
</html>
