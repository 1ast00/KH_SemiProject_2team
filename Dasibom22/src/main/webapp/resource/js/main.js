document.addEventListener('DOMContentLoaded', function() {
	const mainSearchInput = document.getElementById('mainSearchInput');
	const searchButton = document.getElementById('searchButton');
	const detailedSearchInputs = document.getElementById('detailedSearchInputs');
	const detailedSearchBtn = document.getElementById('detailedSearchBtn');
	const mainSearchContainer = document.getElementById('mainSearchContainer');

	// 초기 상태 설정: 상세 검색창은 숨김
	detailedSearchInputs.style.display = 'none';

	// mainSearchInput의 'focus' 이벤트를 'click' 이벤트로 변경
	mainSearchInput.addEventListener('click', function(event) {
		// 이 클릭 이벤트가 상위(document)로 전파되는 것을 즉시 막음
		event.stopPropagation();

		// 메인 검색창을 숨기고 상세 검색창을 표시
		mainSearchContainer.style.display = 'none'; // 메인 검색 바 컨테이너 숨김
		detailedSearchInputs.style.display = 'flex'; // 상세 검색 입력칸 표시 (flex로)

		// (선택 사항) 상세 검색창이 나타난 후 첫 번째 입력 필드에 바로 포커스 이동
		// 이렇게 하면 사용자가 바로 입력할 수 있어 편리합니다.
		document.getElementById('searchName').focus();
	});

	// 상세 검색창 외부 클릭 시 다시 숨기기
	document.addEventListener('click', function(event) {
		// 클릭된 요소가 mainSearchContainer나 detailedSearchInputs 안에 포함되지 않는 경우
		// 즉, 검색 영역(메인 또는 상세) 외부를 클릭했을 때만 숨김
		// mainSearchInput을 클릭하여 focus가 발생한 클릭은 이미 위에서 stopPropagation()으로 막았으므로,
		// 여기서는 외부 클릭만 처리
		if (!mainSearchContainer.contains(event.target) && !detailedSearchInputs.contains(event.target)) {
			// 상세 검색창이 현재 보이고 있다면 숨김
			if (detailedSearchInputs.style.display === 'flex') {
				detailedSearchInputs.style.display = 'none'; // 상세 검색 입력칸 숨김
				mainSearchContainer.style.display = 'flex'; // 메인 검색 바 컨테이너 다시 표시 (flex로)
				mainSearchInput.value = ''; // 메인 검색창 내용 초기화 (선택 사항)
			}
		}
	});

	// 상세 검색 입력 필드나 버튼 클릭 시에도 닫히지 않도록 이벤트 전파 막기
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
		const gender = document.getElementById('searchGender').value; // 라디오 버튼 적용 안된 상태로 유지
		const location = document.getElementById('searchLocation').value;
		const other = document.getElementById('searchOther').value;

		const queryParams = new URLSearchParams();
		if (name) queryParams.append('name', name);
		if (age) queryParams.append('age', age);
		if (gender) queryParams.append('gender', gender);
		if (location) queryParams.append('location', location);
		if (other) queryParams.append('other', other);

		// 실제 검색을 수행하는 URL로 이동
		window.location.href = `/missing/search-results?${queryParams.toString()}`;
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
				verifyError.style.display = 'block';
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