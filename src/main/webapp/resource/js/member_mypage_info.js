/**
 * 회원 마이페이지
 */
window.onload = () => {
	// 수정 버튼
	const editButton = document.querySelector('.btn-primary');
	// input
	const inputs = document.querySelectorAll('.detail-value');
	// 식별자
	const memberSerialNum = document.getElementById('memberSerialNum').value;

	let isEditing = false;

	// 수정 버튼 클릭 시
	editButton.onclick = () => {
		if (!isEditing) {
			inputs.forEach(input => input.removeAttribute('readonly')); // readonly 제거
			editButton.textContent = '저장'; // 수정 -> 저장으로 변환
			isEditing = true;
		} else {
			// 저장
			inputs.forEach(input => input.setAttribute('readonly', true));
			editButton.textContent = '수정'; // 저장하면 다시 수정으로
			isEditing = false;

			// JSON 데이터 생성
			const memberData = {
				memberSerialNum: memberSerialNum,
				member_name: inputs[0].value,      // 이름
				member_phone: inputs[1].value,     // 전화번호
				member_email: inputs[2].value      // 이메일
			};

			fetch("memberUpdate.do", {
				method: "POST",
				headers: {
					"Content-Type": "application/json",
				},
				body: JSON.stringify(memberData)
			})
				.then(response => response.json())
				.then(data => {
					console.log(data);
					if (data) { 
						alert('수정이 완료되었습니다.');
					} else {
						alert('수정에 실패했습니다: ' + data.message);
					}
				})
				.catch(error => {
					console.error('Error:', error);
					alert('수정 중 오류가 발생했습니다.');
				});
		}
	};

	// 로그아웃 버튼 클릭 시 메인으로 이동
	document.querySelector('.btn-secondary').onclick = () => {
		location.href = 'memberLogout.do';
	}
}