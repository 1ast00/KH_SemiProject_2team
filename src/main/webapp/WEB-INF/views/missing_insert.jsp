<%@ page contentType="text/html;charset=UTF-8" %>
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
			action="missingView.do"
			method="post" enctype="multipart/form-data"> <div class="form_group">
				<label for="missingName">이름:</label>
				<input type="text" id="missingName" name="missingName"
					   value="${param.missingName}" required />
			</div>

			<div class="form_group">
    			<label for="missingGender">성별:</label>
    			<select id="missingGender" name="missingGender" required>
        		<option value="">선택</option>
        		<option value="M" ${param.missingGender == 'M' ? 'selected' : ''}>남</option>
        		<option value="F" ${param.missingGender == 'F' ? 'selected' : ''}>여</option>
			    </select>
			</div>

			<div class="form_group">
    			<label for="missingBirth">생년월일:</label>
    			<input type="date" id="missingBirth" name="missingBirth"
           				value="${param.missingBirth}" required />
			</div>
			<div class="form_group">
				<label for="missingDate">실종 날짜:</label>
				<input type="date" id="missingDate" name="missingDate"
					   value="${param.missingDate}" required />
			</div>

			<div class="form_group">
				<label for="missingEtc">기타 특징:</label>
				<textarea id="missingEtc" name="missingEtc" rows="3">${param.missingEtc}</textarea>
			</div>

			<div class="form_group">
				<label for="missingImg">이미지 업로드:</label>
				<input type="file" id="missingImg" name="missingImg" accept="image/*" />
			</div>

			<input type="hidden" id="missingPlaceText" name="missingPlace"
				value="${param.place != null ? param.place : ''}" />

		</form>

		<form action="missingMapSearch.do"
			method="get" id="locationSearchForm">
			<div class="form_group">
				<label for="placeInput">실종 추정 위치:</label>
				<input type="text" id="placeInput" name="place" required
					   value="${param.place != null ? param.place : ''}" />
				<button type="submit">위치 검색</button>
			</div>
			<input type="hidden" name="missingName" />
			<input type="hidden" name="missingGender" />
			<input type="hidden" name="missingBirth" />
			<input type="hidden" name="missingEtc" />
			<input type="hidden" name="missingDate" />
		</form>

		<div class="button-group">
			<button type="submit" form="missingPersonForm" class="submit-button">접수</button>
			<button type="button" onclick="location.href='missingInsertView.do'"
				class="cancel-button">취소</button>
		</div>
	</main>

	<script>
		document.getElementById('locationSearchForm').addEventListener('submit', function(event) {
			event.preventDefault(); 

			// 1. '실종자 정보' 폼에 있는 현재 값들을 가져오기
			const name = document.getElementById('missingName').value;
			const gender = document.getElementById('missingGender').value;
			const birth = document.getElementById('missingBirth').value;
			const etc = document.getElementById('missingEtc').value;
			const date = document.getElementById('missingDate').value;

			// 2. 가져온 값들을 '위치 검색' 폼 안의 hidden 필드에 각각 넣기
			const form = event.target; 
			form.querySelector('input[name="missingName"]').value = name;
			form.querySelector('input[name="missingGender"]').value = gender;
			form.querySelector('input[name="missingBirth"]').value = birth;
			form.querySelector('input[name="missingEtc"]').value = etc;
			form.querySelector('input[name="missingDate"]').value = date;

			// 3. 모든 값을 채운 후, 위치 검색 폼 제출
			form.submit();
		});

		// 접수버튼을 누를 때, 위치 입력 필드의 값을 최종 폼의 hidden 필드에 복사하기
		document.getElementById('missingPersonForm').addEventListener('submit', function(event) {
			const placeInputValue = document.getElementById('placeInput').value;
			document.getElementById('missingPlaceText').value = placeInputValue;
		});
	</script>
</body>
</html>