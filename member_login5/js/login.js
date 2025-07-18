document.addEventListener('DOMContentLoaded', function() {

    //  비밀번호 표시 토글
    const pwInput  = document.getElementById('member_pw');
    const togglePw = document.getElementById('togglePassword');

    if (pwInput && togglePw) {
        togglePw.addEventListener('change', function() {
            pwInput.setAttribute('type', this.checked ? 'text' : 'password');
        });
    }

    //  Caps Lock 감지
    const capsWarn = document.getElementById('caps-warning');

    if (pwInput && capsWarn) {
        const checkCaps = e => {
            capsWarn.style.display = e.getModifierState('CapsLock') ? 'block' : 'none';
        };
        pwInput.addEventListener('keydown', checkCaps);
        pwInput.addEventListener('keyup',   checkCaps);
        pwInput.addEventListener('blur',    () => capsWarn.style.display = 'none');
    }

    //  로그인 실패 시 에러 메시지 표시
    const params = new URLSearchParams(window.location.search);
    if (params.get("error") === "1") {
        const errorMsg = document.getElementById("error-msg");
        if (errorMsg) {
            errorMsg.style.display = "block";
        }
    }

    //  회원가입 전용 코드 (member_id 있을 때만 실행)
    const memberIdInput = document.getElementById("member_id");

    if (memberIdInput) {
        let idCheckPassed = false;
        const form = document.querySelector("form");

        form.onsubmit = function(e) {
            const pw      = document.querySelector("input[name='member_pw']").value;
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

        const checkIdBtn = document.getElementById("checkIdBtn");
        if (checkIdBtn) {
            checkIdBtn.onclick = function() {
                const memberId   = memberIdInput.value;
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
        }
    }

});


