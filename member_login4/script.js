document.addEventListener('DOMContentLoaded', function() {

    // 1. 메인 검색창 상세 검색 전환
    const mainSearchInput = document.getElementById('mainSearchInput');
    if (mainSearchInput) {
        const searchButton = document.getElementById('searchButton');
        const detailedSearchInputs = document.getElementById('detailedSearchInputs');
        const detailedSearchBtn = document.getElementById('detailedSearchBtn');

        mainSearchInput.addEventListener('focus', function() {
            detailedSearchInputs.style.display = 'flex';
        });

        detailedSearchBtn.addEventListener('click', function() {
            const name   = document.getElementById('searchName').value;
            const age    = document.getElementById('searchAge').value;
            const gender = document.getElementById('searchGender').value;
            const other  = document.getElementById('searchOther').value;

            const queryParams = new URLSearchParams();
            if (name)   queryParams.append('name', name);
            if (age)    queryParams.append('age', age);
            if (gender) queryParams.append('gender', gender);
            if (other)  queryParams.append('other', other);

            window.location.href = `/missing/search-results?${queryParams.toString()}`;
        });

        document.addEventListener('click', function(event) {
            const searchContainer = document.querySelector('.search-input-container');
            if (searchContainer &&
                !searchContainer.contains(event.target) &&
                detailedSearchInputs.style.display === 'flex') {
                detailedSearchInputs.style.display = 'none';
            }
        });
    }

    // 2. 드롭다운 메뉴
    const dropdownToggles = document.querySelectorAll('.has-dropdown > a');
    dropdownToggles.forEach(toggle => {
        toggle.addEventListener('click', function(event) {
            if (this.getAttribute('href') === '#') {
                event.preventDefault();
            }
        });
    });

    // 3. 실종자 카드 동적 추가
    const missingCardsContainer = document.querySelector('.missing-cards-container');
    const additionalMissingPersons = [ /* 너가 보낸 데이터 그대로 유지 */ ];
    if (missingCardsContainer) {
        additionalMissingPersons.forEach(person => {
            const card = document.createElement('div');
            card.classList.add('missing-person-card');
            card.innerHTML = `
                <a href="/missing-person/detail/${person.id}">
                    <img src="${person.img}" alt="${person.name}">
                    <div class="person-info">
                        <p>이름 : ${person.name}</p>
                        <p>나이 : ${person.age}</p>
                        <p>실종장소 : ${person.location}</p>
                        <p>특징 : ${person.feature}</p>
                    </div>
                </a>
            `;
            missingCardsContainer.appendChild(card);
        });
    }

    // 4. 로그인 환영 메시지
    const loggedInUser = document.body.getAttribute('data-user');
    if (loggedInUser) {
        document.write(loggedInUser + "님 환영합니다!");
    }

    // 5. 회원가입 (member_id 필드가 있을 때만)
    if (document.getElementById("member_id")) {
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
                alert("아이디 중복확인을 해주세요.");
                e.preventDefault();
                return false;
            }
        };

        document.getElementById("checkIdBtn").onclick = function() {
            const memberId  = document.getElementById("member_id").value;
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
                });
        };
    }

    // 6. URL 파라미터 에러 메시지
    const params = new URLSearchParams(window.location.search);
    if (params.get("error") === "1") {
        const errorMsg = document.getElementById("error-msg");
        if (errorMsg) {
            errorMsg.style.display = "block";
        }
    }

    // 7. 로그인 전용 스크립트
    if (document.body.classList.contains('login-page')) {
        const pwInput  = document.querySelector('input[name="member_pw"]');
        const togglePw = document.getElementById('togglePassword');
        const capsWarn = document.getElementById('caps-warning');

        if (pwInput && togglePw) {
            togglePw.addEventListener('change', () => {
                pwInput.type = togglePw.checked ? 'text' : 'password';
            });
        }

        if (pwInput && capsWarn) {
            const checkCaps = (e) => {
                capsWarn.style.display = e.getModifierState('CapsLock') ? 'block' : 'none';
            };
            pwInput.addEventListener('keydown', checkCaps);
            pwInput.addEventListener('keyup',   checkCaps);
            pwInput.addEventListener('blur',    () => { capsWarn.style.display = 'none'; });
        }
    }

    // 8. 비밀번호 찾기/재설정 전용
    if (window.findPwConfig) {
        const { verified, count, resetSuccess, memberId } = window.findPwConfig;
        const verifySection = document.getElementById('verify-section');
        const resetSection  = document.getElementById('reset-section');
        const verifyError   = document.getElementById('verify-error');
        const resetMessage  = document.getElementById('reset-message');
        const resetForm     = document.getElementById('reset-form');

        if (verified === true || verified === 'true') {
            verifySection.style.display = 'none';
            resetSection.style.display  = 'block';
            resetForm.member_id.value    = memberId;
        } else {
            verifySection.style.display = 'block';
            resetSection.style.display  = 'none';
            if (count === 0) {
                verifyError.style.display = 'block';
            }
        }

        if (resetSuccess !== null && resetSuccess !== 'null') {
            const msg = (resetSuccess === true || resetSuccess === 'true')
                ? '비밀번호가 성공적으로 변경되었습니다.'
                : '비밀번호 변경에 실패했습니다. 다시 시도해주세요.';
            resetMessage.textContent   = msg;
            resetMessage.style.display = 'block';
        }
    }

});
