<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>회원가입 페이지</title>
<link rel="stylesheet"
    href="${pageContext.request.contextPath }/resource/css/member_register.css">
</head>
<body>

<div class="container">
    <h2>회원가입</h2>
    <form action="memberRegister.do" method="post" onsubmit="return validateForm();">
        <input type="hidden" name="admin_serialNum" value="Admin">

        <!-- 아이디 + 중복확인 버튼 -->
        <div style="display: flex; gap: 10px;">
            <input type="text" name="member_id" id="member_id" placeholder="아이디"
                required style="flex: 1;" />
            <button type="button" id="checkIdBtn" style="width: 100px;">중복 확인</button>
        </div>
        <span id="idCheckResult"></span>
        <input type="password" name="member_pw" placeholder="비밀번호" required />
        <input type="password" name="member_pw_check" placeholder="비밀번호 확인" required />
        <input type="text" name="member_name" placeholder="이름" required />
        <input type="email" id="member_email" name="member_email" placeholder="이메일" required />
        <input type="text" name="member_phone" placeholder="전화번호" required />
        <input type="submit" value="회원가입" />
    </form>
</div>



<script src="${pageContext.request.contextPath}/resource/js/register.js"></script>


</body>
</html>
