<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>실종자 접수 페이지</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resource/css/missing_insert.css">
</head>
<body>
	<header>
		<h1 id="formTitle">실종자 접수</h1>
	</header>
	<main>
		<form id="missingPersonForm"
			action="MissingView.do"
			method="post" enctype="multipart/form-data">
			<div class="form_group">
				<label for="missingName">이름:</label> <input type="text"
					id="missingName" name="missingName" required />
			</div>
			<div class="form_group">
				<label for="missingGender">성별:</label> <select id="missingGender"
					name="missingGender" required>
					<option value="">선택</option>
					<option value="남">남</option>
					<option value="여">여</option>
				</select>
			</div>
			<div class="form_group">
				<label for="missingBirth">생년월일:</label> <input type="text"
					id="missingBirth" name="missingBirth" placeholder="주민번호 앞자리 6자리 입력"
					maxlength="6" required />
			</div>
			<div class="form_group">
				<label for="missingEtc">기타 특징:</label>
				<textarea id="missingEtc" name="missingEtc" rows="3"></textarea>
			</div>
			<div class="form_group">
				<label for="missingImg">이미지 업로드:</label> <input type="file"
					id="missingImg" name="missingImg" accept="image/*" />
			</div>

			<input type="hidden" id="missingPlaceText" name="missingPlace"
				value="${param.place != null ? param.place : ''}" />

			<div class="form_group">
				<label for="missingDate">실종 날짜:</label> <input type="date"
					id="missingDate" name="missingDate" />
			</div>
		</form>

		<form action="missingMapSearch.do"
			method="get" id="locationSearchForm">
			<div class="form_group">
				<label for="placeInput">실종 추정 위치:</label> <input type="text"
					id="placeInput" name="place" required
					value="${param.place != null ? param.place : ''}" />
				<button type="submit">위치 검색</button>
			</div>
		</form>

		<div class="button-group">
			<button type="submit" form="missingPersonForm" class="submit-button">접수</button>
			<button type="button" onclick="location.href='missingInsertView.do'"
				class="cancel-button">취소</button>
		</div>

	</main>

	<script>
		// 실종자 위치 정보 값을 txt로 넘겨버리기
		document.addEventListener('DOMContentLoaded', function() {
			const missingPersonForm = document.getElementById('missingPersonForm');
			missingPersonForm.addEventListener('submit', function(event) {
				const placeInputValue = document.getElementById('placeInput').value;
				document.getElementById('missingPlaceText').value = placeInputValue;
				});
		});
	</script>
</body>
</html>