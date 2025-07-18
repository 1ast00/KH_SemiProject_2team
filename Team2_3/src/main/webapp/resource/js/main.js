document.addEventListener('DOMContentLoaded', function() {
    const mainSearchInput = document.getElementById('mainSearchInput');
    const searchButton = document.getElementById('searchButton');
    const detailedSearchInputs = document.getElementById('detailedSearchInputs');
    const detailedSearchBtn = document.getElementById('detailedSearchBtn');
    const mainSearchContainer = document.getElementById('mainSearchContainer');

    // 초기 상태 설정: 상세 검색창은 숨김
    detailedSearchInputs.style.display = 'none';

    mainSearchInput.addEventListener('click', function(event) {
        event.stopPropagation();
        mainSearchContainer.style.display = 'none';
        detailedSearchInputs.style.display = 'flex';
        document.getElementById('searchName').focus();
    });

    document.addEventListener('click', function(event) {
        if (!mainSearchContainer.contains(event.target) && !detailedSearchInputs.contains(event.target)) {
            if (detailedSearchInputs.style.display === 'flex') {
                detailedSearchInputs.style.display = 'none';
                mainSearchContainer.style.display = 'flex';
                mainSearchInput.value = '';
            }
        }
    });

    detailedSearchInputs.addEventListener('click', function(event) {
        event.stopPropagation();
    });
    mainSearchContainer.addEventListener('click', function(event) {
        event.stopPropagation();
    });


    // 2. 상세 검색 버튼 클릭 시 (실제 백엔드와 통신해 검색)
    detailedSearchBtn.addEventListener('click', function() {
        const name = document.getElementById('searchName').value;
        const age = document.getElementById('searchAge').value;

        // --- 성별 라디오 버튼 값 가져오는 부분 수정 ---
        let gender = '';
        const selectedGender = document.querySelector('input[name="searchGender"]:checked');
        if (selectedGender) {
            gender = selectedGender.value;
        }
        // ---------------------------------------------

        const location = document.getElementById('searchLocation').value;
        const other = document.getElementById('searchOther').value;

        const queryParams = new URLSearchParams();
        if (name) queryParams.append('name', name);
        if (age) queryParams.append('age', age);
        if (gender) queryParams.append('gender', gender);
        if (location) queryParams.append('place', location); // missing_list.jsp에서 'place'로 사용하므로 통일
        if (other) queryParams.append('etc', other);         // missing_list.jsp에서 'etc'로 사용하므로 통일

        // --- 목적지 URL을 missingList.do로 변경(수정전) ---
        // 백엔드에서 missingList.do 요청 시 이 파라미터들을 받아 검색 쿼리를 실행하도록 구현해야 함.
		// 목적지 URL을 missingSearch.do로 변경!!!!!!!!
		// main.jsp에서 정의한 const contextPath  변수를 사용해야.
		window.location.href = `${contextPath}/missingSearch.do?${queryParams.toString()}`;
    });

    // 3. 드롭다운 메뉴 기능
    const dropdownToggles = document.querySelectorAll('.has-dropdown > a');
    dropdownToggles.forEach(toggle => {
        toggle.addEventListener('click', function(event) {
            if (this.getAttribute('href') === '#') {
                event.preventDefault();
            }
        });
    });

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
            const pw = document.querySelector("input[name='member_pw']").value;
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
        const pwInput = document.querySelector('input[name="member_pw"]');
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
            pwInput.addEventListener('keyup', checkCaps);
            pwInput.addEventListener('blur', () => { capsWarn.style.display = 'none'; });
        }
    }

    // 8. 비밀번호 찾기/재설정 전용 
    if (window.findPwConfig) {
        const { verified, count, resetSuccess, memberId } = window.findPwConfig;
        const verifySection = document.getElementById('verify-section');
        const resetSection = document.getElementById('reset-section');
        const verifyError = document.getElementById('verify-error');
        const resetMessage = document.getElementById('reset-message');
        const resetForm = document.getElementById('reset-form');

        if (verified === true || verified === 'true') {
            verifySection.style.display = 'none';
            resetSection.style.display = 'block';
            resetForm.member_id.value = memberId;
        } else {
            verifySection.style.display = 'block';
            resetSection.style.display = 'none';
            if (count === 0) {
                verifyError.style.display = 'none'; // 'block' 대신 'none'으로 변경, 오류가 있을때만 표시되도록
            }
        }

        if (resetSuccess !== null && resetSuccess !== 'null') {
            const msg = (resetSuccess === true || resetSuccess === 'true')
                ? '비밀번호가 성공적으로 변경되었습니다.'
                : '비밀번호 변경에 실패했습니다. 다시 시도해주세요.';
            resetMessage.textContent = msg;
            resetMessage.style.display = 'block';
        }
    }
});