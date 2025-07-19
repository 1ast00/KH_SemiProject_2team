/**
 * 회원 마이페이지
 */
window.onload = () => {
	// 수정 버튼
	const editButton = document.querySelector('.btn-primary');
	// input
	const inputs = document.querySelectorAll('.detail-value');

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

			// fetch(memberUpdate.do?)
		}
	};

	// 로그아웃 버튼 클릭 시 메인으로 이동
	document.querySelector('.btn-secondary').onclick = () => {
		location.href = 'memberLogout.do';
	}
}