<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>실종자 접수 페이지</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/missing_insert.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/template/header.jsp" />
	<header>
		<h1 id="formTitle">실종자 접수</h1>
	</header>
	<main>
											<!-- missingView에서 missingInsert.do로 수정 -->
		<form id="missingPersonForm" action="missingInsert.do" method="post" enctype="multipart/form-data">
			<div class="form_group">
				<label for="missingName">이름:</label>
				<input type="text" id="missingName" name="missingName" value="${param.missingName}" required />
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
				<input type="date" id="missingBirth" name="missingBirth" value="${param.missingBirth}" required />
			</div>

			<div class="form_group">
				<label for="missingDate">실종 날짜:</label>
				<input type="date" id="missingDate" name="missingDate" value="${param.missingDate}" required />
			</div>
			
			<div class="form_group">
				<label for="missingEtc">기타 특징:</label>
				<textarea id="missingEtc" name="missingEtc" rows="3">${param.missingEtc}</textarea>
			</div>

			<div class="form_group">
				<label for="missingPlace">실종 추정 위치:</label>
				<div class="location-search-container">
					<input type="text" id="missingPlace" name="missingPlace" value="${param.place}" required />
					<button type="submit" formaction="missingMapSearch.do" formmethod="get" class="submit-button">위치<br>검색</button>
				</div>
			</div>

			<div class="form_group">
				<label for="missingImg">이미지 업로드:</label>
				<input type="file" id="missingImg" name="missingImg" accept="image/*" />
			</div>
			
			<div class="button-group">
				<button type="submit" class="submit-button">접수</button>
				<button type="button" onclick="location.href='${pageContext.request.contextPath}'" class="cancel-button">취소</button>
			</div>
		</form>
	</main>
</body>
</html>