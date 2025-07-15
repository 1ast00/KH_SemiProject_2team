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
		<form action="memberRegister" method="post">
			<input type="text" name="member_serialNum"
				placeholder="회원 고유번호 (예: M0001)" required />

			<!-- 아이디 + 중복확인 버튼 -->
			<div style="display: flex; gap: 10px;">
				<input type="text" name="member_id" id="member_id" placeholder="아이디"
					required style="flex: 1;" />
				<button type="button" id="checkIdBtn" style="width: 100px;">중복
					확인</button>
			</div>
			<span id="idCheckResult"></span> <input type="password"
				name="member_pw" placeholder="비밀번호" required /> <input
				type="password" name="member_pw_check" placeholder="비밀번호 확인"
				required /> <input type="text" name="member_name" placeholder="이름"
				required /> <input type="email" name="member_email"
				placeholder="이메일 (선택)" /> <input type="text" name="member_phone"
				placeholder="전화번호" required /> <input type="text"
				name="admin_serialNum" placeholder="소속 관리자 번호" required /> <input
				type="submit" value="회원가입" />
		</form>
	</div>


	<script>
window.onload = function () {
    // 비밀번호 확인
    document.querySelector("form").onsubmit = function (e) {
    const pw = document.querySelector("input[name='member_pw']").value;
    const pwCheck = document.querySelector("input[name='member_pw_check']").value;

    if (pw !== pwCheck) {
        alert("비밀번호가 일치하지 않습니다.");
        e.preventDefault();
        return false;
    }
};


    // 아이디 중복 검사
    document.getElementById("checkIdBtn").onclick = function () {
        const memberId = document.getElementById("member_id").value;
        const resultSpan = document.getElementById("idCheckResult");

        if (memberId.trim() === "") {
            resultSpan.textContent = "아이디를 입력해주세요.";
            resultSpan.style.color = "red";
            return;
        }

        fetch(`${pageContext.request.contextPath}/checkMemberID.do?member_id=` + encodeURIComponent(memberId))
            .then(res => res.text())
            .then(data => {
                if (data === "duplicate") {
                    resultSpan.textContent = "이미 사용 중인 아이디입니다.";
                    resultSpan.style.color = "red";
                } else {
                    resultSpan.textContent = "사용 가능한 아이디입니다!";
                    resultSpan.style.color = "green";
                }
            })
            .catch(err => {
                resultSpan.textContent = "오류 발생: " + err;
                resultSpan.style.color = "red";
            });
    };
};

</script>

</body>
</html>