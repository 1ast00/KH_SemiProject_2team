<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>목격자 제보 목록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/witness_list.css">
</head>
<body>
<jsp:include page="./template/header.jsp" />
<h2>목격자 제보 목록</h2>

<div class="search-box">
	<input type="text" placeholder="이름, 나이, 성별, 위치, 기타로 검색">
	<button class="submit">전송</button>
</div>

<div class="grid-container">
	<c:forEach var="dto" items="${witnessList}">
		<div class="card">
			<a href="witnessView.do?witnessSerialNum=${dto.witnessSerialNum}">
				<img
					src="${pageContext.request.contextPath}/img/witness.do?name=${dto.image}"
					onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/img/witness.do?name=default.jpg';"
					alt="제보 이미지"
					style="width:100%; height:200px; object-fit:cover; border-radius:8px;">
			</a>
			<a href="witnessDelete.do?witnessSerialNum=${dto.witnessSerialNum}"
				class="delete-btn" onclick="return confirm('정말 삭제하시겠습니까?');">삭제</a>
			<div class="info">
				<div><span>성별:</span> ${dto.gender}</div>
				<div><span>나이:</span> ${dto.age}</div>
				<div><span>위치:</span> ${dto.place}</div>
			</div>
		</div>
	</c:forEach>
</div>

<div class="pagination">
	<c:if test="${page > 1}">
		<a href="?page=${page - 1}">&laquo; 이전</a>
	</c:if>

	<c:forEach var="i" begin="1" end="${totalPages}">
		<c:if test="${i >= page - 2 && i <= page + 2}">
			<a href="?page=${i}" class="${i == page ? 'current' : ''}">${i}</a>
		</c:if>
	</c:forEach>

	<c:if test="${page < totalPages}">
		<a href="?page=${page + 1}">다음 &raquo;</a>
	</c:if>
</div>

<div style="text-align: center; margin-top: 30px; color: red;">
	현재 페이지: ${page} / 전체 페이지: ${totalPages}
</div>
</body>
</html>