<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>실종자 접수 목록</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resource/css/missing_view.css">
</head>
<body>
	<h1>실종자</h1> <!-- JSTL + EL태그로 코드 변경예정!! -->

	<%
	// 서블릿에서 전달된 데이터 받기
	String missingName = (String) request.getAttribute("missingName");
	String missingGender = (String) request.getAttribute("missingGender");
	String missingBirth = (String) request.getAttribute("missingBirth");
	String ageAtMissing = (String) request.getAttribute("ageAtMissing");
	String currentAge = (String) request.getAttribute("currentAge");
	String missingEtc = (String) request.getAttribute("missingEtc");
	String missingPlace = (String) request.getAttribute("missingPlace");
	String missingDate = (String) request.getAttribute("missingDate");
	String missingImageUrl = (String) request.getAttribute("missingImageUrl");

	String errorMessage = (String) request.getAttribute("errorMessage");
	if (errorMessage != null) {
		out.println("<p style='color: red;'>" + errorMessage + "</p>");
	}
	%>

	<div class="container">
		<div class="image-box">
			<%
			if (missingImageUrl != null && !missingImageUrl.isEmpty()) {
			%>
			<img src="<%=missingImageUrl%>" alt="실종자 이미지">
			<%
			} else {
			%>
			<p>
				<strong>이미지 없음</strong>
			</p>
			<%
			}
			%>
		</div>

		<div class="info-box">
			<p>
				<strong>이름 :</strong>
				<%=missingName != null ? missingName : "데이터 없음"%></p>
			<p>
				<strong>성별 :</strong>
				<%=missingGender != null ? missingGender : "데이터 없음"%></p>
			<p>
				<strong>생년월일 :</strong>
				<%=missingBirth != null ? missingBirth : "데이터 없음"%></p>
			<p>
				<strong>실종 당시 나이 :</strong>
				<%=ageAtMissing%></p>
			<p>
				<strong>현재 나이 :</strong>
				<%=currentAge%></p>
			<p>
				<strong>기타 특징 :</strong>
				<%=missingEtc != null ? missingEtc : "데이터 없음"%></p>
			<p>
				<strong>마지막 목격 장소 :</strong>
				<%=missingPlace != null ? missingPlace : "정보 없음"%></p>
			<p>
				<strong>실종 날짜 :</strong>
				<%=missingDate != null ? missingDate : "데이터 없음"%></p>
		</div>
	</div>

	<p class="caption">함께 찾아요, 돌아올 봄을 기다리는 소중한 가족을</p>

	<div class="button-group">
		<a href="missingInsertView.do">실종자 접수</a> <a href="#">제보하기</a>
		<form action="missingList.do" method="get">
			<button type="submit">실종자 목록 보기</button>
		</form>
	</div>
</body>
</html>