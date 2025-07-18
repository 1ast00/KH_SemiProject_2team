<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>
    <c:choose>
        <c:when test="${not empty missingPerson}">실종자 정보 수정</c:when>
        <c:otherwise>실종자 접수</c:otherwise>
    </c:choose>
</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/missing_insert.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/template/header.jsp" />
	<header>
		<h1 id="formTitle">
            <c:choose>
                <c:when test="${not empty missingPerson}">실종자 정보 수정</c:when>
                <c:otherwise>실종자 접수</c:otherwise>
            </c:choose>
        </h1>
	</header>
	<main>
		<form id="missingPersonForm" 
              action="${not empty missingPerson ? 'missingUpdate.do' : 'missingInsert.do'}" 
              method="post" enctype="multipart/form-data">
            
			<c:if test="${not empty missingPerson}">
				<input type="hidden" name="missingSerialNum" value="${missingPerson.missingSerialNum}">
			</c:if>

			<div class="form_group">
				<label for="missingName">이름:</label>
				<input type="text" id="missingName" name="missingName" value="${param.missingName != null ? param.missingName : missingPerson.name}" required />
			</div>

			<div class="form_group">
				<label for="missingGender">성별:</label>
				<select id="missingGender" name="missingGender" required>
					<option value="">선택</option>
					<option value="M" ${ (param.missingGender == 'M' or missingPerson.gender == 'M') ? 'selected' : ''}>남</option>
					<option value="F" ${ (param.missingGender == 'F' or missingPerson.gender == 'F') ? 'selected' : ''}>여</option>
				</select>
			</div>

			<div class="form_group">
				<label for="missingBirth">생년월일:</label>
				<input type="date" id="missingBirth" name="missingBirth" value="${param.missingBirth != null ? param.missingBirth : missingPerson.birth}" required />
			</div>

			<div class="form_group">
				<label for="missingDate">실종 날짜:</label>
				<input type="date" id="missingDate" name="missingDate" value="${param.missingDate != null ? param.missingDate : missingPerson.missingDate}" required />
			</div>
			
			<div class="form_group">
				<label for="missingEtc">기타 특징:</label>
				<textarea id="missingEtc" name="missingEtc" rows="3">${param.missingEtc != null ? param.missingEtc : missingPerson.etc}</textarea>
			</div>

			<div class="form_group">
				<label for="missingPlace">실종 추정 위치:</label>
				<div class="location-search-container">
					<input type="text" id="missingPlace" name="missingPlace" value="${param.place != null ? param.place : missingPerson.place}" required />
					<button type="submit" formaction="missingMapSearch.do" formmethod="get" class="submit-button">위치<br>검색</button>
				</div>
			</div>

			<div class="form_group">
				<label for="missingImg">이미지 업로드:</label>
				<input type="file" id="missingImg" name="missingImg" accept="image/*" />
			</div>
			
			<div class="button-group">
				<button type="submit" class="submit-button">
                    ${not empty missingPerson ? '수정 완료' : '접수'}
                </button>
				<button type="button" onclick="history.back()" class="cancel-button">취소</button>
			</div>
		</form>
	</main>
</body>
</html>