document.querySelector("#verifyBtn").onclick = function(e) {
    e.preventDefault();

    const code = document.querySelector("#verifyCode").value;

    fetch("/MissingProject/EmailCodeVerifyController.do", {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: "verifyCode=" + encodeURIComponent(code)
    })
    .then(res => res.json())
    .then(data => {
        if (data.result === "success") {
            alert("인증 성공! 비밀번호 재설정 페이지로 이동합니다.");

          
            window.location.href = "memberResetPwForm.do?member_id=" 
                + memberId + "&member_email=" + memberEmail;

        } else {
            document.querySelector("#errorMsg").innerText = data.message;
        }
    });
};
