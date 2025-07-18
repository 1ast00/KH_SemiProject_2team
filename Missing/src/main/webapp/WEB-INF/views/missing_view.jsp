<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>실종자 접수 완료</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/missing_view.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/template/header.jsp" />
	<h1>실종자 정보</h1>

	<c:if test="${not empty errorMessage}">
		<p style="color: red;">${errorMessage}</p>
	</c:if>

	<div class="container">
		<div class="image-box">
			<c:choose>
				<c:when test="${not empty missingPerson.image}">
					<!-- 이미지는 'contextPath/uploads/파일명' 형태로 접근 -->
					<img src="${pageContext.request.contextPath}/uploads/${missingPerson.image}" alt="실종자 이미지">
				</c:when>
				<c:otherwise>
					<p><strong>이미지 없음</strong></p>
				</c:otherwise>
			</c:choose>
		</div>

		<div class="info-box">
			<p><strong>이름 :</strong> <c:out value="${missingPerson.name}" default="데이터 없음"/></p>
			<p><strong>성별 :</strong> 
				<c:choose>
					<c:when test="${missingPerson.gender == 'M'}">남</c:when>
					<c:when test="${missingPerson.gender == 'F'}">여</c:when>
					<c:otherwise>데이터 없음</c:otherwise>
				</c:choose>
			</p>
			<p><strong>생년월일 :</strong> <c:out value="${missingPerson.birth}" default="데이터 없음"/></p>
			<p><strong>실종 당시 나이 :</strong> <c:out value="${ageAtMissing}"/></p>
			<p><strong>현재 나이 :</strong> <c:out value="${currentAge}"/></p>
			<p><strong>기타 특징 :</strong> <c:out value="${missingPerson.etc}" default="데이터 없음"/></p>
			<p><strong>마지막 목격 장소 :</strong> <c:out value="${missingPerson.place}" default="정보 없음"/></p>
			<p><strong>실종 날짜 :</strong> <c:out value="${missingPerson.missingDate}" default="데이터 없음"/></p>
		</div>
	</div>

	<p class="caption">함께 찾아요, 돌아올 봄을 기다리는 소중한 가족을</p>

	<div class="button-group">
		<a href="missingInsertView.do">새로 접수하기</a> 
		<!-- 목격자 제보 페이지로 연결 -->
		<a href="witnessInsert.do">제보하기</a>
		<form action="missingList.do" method="get" style="display:inline;">
			<button type="submit">실종자 목록 보기</button>
		</form>
	</div>
</body>
</html>