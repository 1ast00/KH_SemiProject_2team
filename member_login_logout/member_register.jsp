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
        <input type="email" name="member_email" placeholder="이메일 (선택)" />
        <input type="text" name="member_phone" placeholder="전화번호" required />
        <input type="submit" value="회원가입" />
    </form>
</div>


<script>
let idCheckPassed = false;  // 중복확인 완료 여부

window.onload = function () {
    const form = document.querySelector("form");

    // 비밀번호 일치 검사 + 중복확인 여부 검사
    form.onsubmit = function (e) {
        const pw = document.querySelector("input[name='member_pw']").value;
        const pwCheck = document.querySelector("input[name='member_pw_check']").value;

        if (pw !== pwCheck) {
            alert("비밀번호가 일치하지 않습니다.");
            e.preventDefault();
            return false;
        }

        if (!idCheckPassed) {
            alert("회원가입 전에 아이디 중복확인을 해주세요.");
            e.preventDefault();
            return false;
        }

        return true;
    };

    // 아이디 중복 검사
    document.getElementById("checkIdBtn").onclick = function () {
        const memberId = document.getElementById("member_id").value;
        const resultSpan = document.getElementById("idCheckResult");

        if (memberId.trim() === "") {
            resultSpan.textContent = "아이디를 입력해주세요.";
            resultSpan.style.color = "red";
            idCheckPassed = false;
            return;
        }

        fetch("checkMemberId.do?member_id=" + encodeURIComponent(memberId))
            .then(res => res.text())
            .then(data => {
                if (data === "duplicate") {
                    resultSpan.textContent = "이미 사용 중인 아이디입니다.";
                    resultSpan.style.color = "red";
                    idCheckPassed = false;
                } else {
                    resultSpan.textContent = "사용 가능한 아이디입니다!";
                    resultSpan.style.color = "green";
                    idCheckPassed = true;
                }
            })
            .catch(err => {
                resultSpan.textContent = "오류 발생: " + err;
                resultSpan.style.color = "red";
                idCheckPassed = false;
            });
    };
};
</script>

</body>
</html>
