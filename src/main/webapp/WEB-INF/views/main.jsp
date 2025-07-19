<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>다시, 봄 - 메인</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resource/css/main.css">
</head>
<body>
	<jsp:include page="./template/header.jsp" />
    <section class="search-section">
        <div class="search-input-container" id="mainSearchContainer">
            <input type="text" id="mainSearchInput" placeholder="이름/나이/성별/실종장소를 입력해주세요">
            <button id="searchButton">
            	<img src="${pageContext.request.contextPath}/resource/img/search_icon.png" alt="검색">
            </button>
        </div>

        <div id="detailedSearchInputs" class="detailed-search-container">
            <input type="text" id="searchName" placeholder="이름">
            <input type="text" id="searchAge" placeholder="나이">
            <%-- 성별 입력 필드를 라디오 버튼으로 변경했어요 --%>
            <div class="gender-radio-group">
	            <span>성별</span>
                <label><input type="radio" name="searchGender" value="남"> 남</label>
                <label><input type="radio" name="searchGender" value="여"> 여</label>
            </div>
            <input type="text" id="searchLocation" placeholder="실종 위치">
            <input type="text" id="searchOther" placeholder="기타">
            <button id="detailedSearchBtn">
            	<img src="${pageContext.request.contextPath}/resource/img/search_icon.png" alt="상세 검색">
            </button>
        </div>
    </section>


	<div class="main-content-wrapper">
		<section class="notice-section">
			<h2>공지사항</h2>
			<table>
				<thead>
					<tr>
						<th>글번호</th>
						<th>제목</th>
						<th>작성일</th>
						<th>조회수</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>1</td>
						<td><a href="#">공지사항 1</a></td>
						<td>2020-01-01</td>
						<td>30</td>
					</tr>
					<tr>
						<td>2</td>
						<td><a href="#">공지사항 2</a></td>
						<td>2020-01-02</td>
						<td>31</td>
					</tr>
					<tr>
						<td>3</td>
						<td><a href="#">공지사항 3</a></td>
						<td>2020-01-03</td>
						<td>32</td>
					</tr>
					<tr>
						<td>4</td>
						<td><a href="#">공지사항 4</a></td>
						<td>2020-01-04</td>
						<td>33</td>
					</tr>
					<tr>
						<td>5</td>
						<td><a href="#">공지사항 5</a></td>
						<td>2020-01-05</td>
						<td>34</td>
					</tr>
				</tbody>
			</table>
		</section>

<section class="missing-list-section">
	<h2>실종자 리스트</h2>

	<div class="missing-cards-container">
		<c:forEach var="person" items="${missingList}" varStatus="status">
			<c:if test="${status.index < 6}">
				<div class="missing-person-card">
					<a href="${pageContext.request.contextPath}/missingView.do?missingSerialNum=${person.missingSerialNum}" class="person-card-link">
						<div class="card-image">
							<img src="${pageContext.request.contextPath}/uploads/${person.image}"  alt="${person.name}" 
    							onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/resource/img/default.jpg';">
						</div>
						<div class="card-details">
							<p><strong>이름:</strong> ${person.name}</p>
							<p><strong>생년월일:</strong> ${person.birth}</p>
							<p><strong>기타 특징:</strong> ${person.etc}</p>
							<p><strong>실종 위치:</strong> ${person.place}</p>
						</div>
					</a>
				</div>
			</c:if>
		</c:forEach>
	</div>
	<div class="view-all-button">
		<a href="${pageContext.request.contextPath}/missingList.do" class="btn-view-all">전체보기</a>
	</div>
</section>
	</div>

	<footer>
		<p>&copy; 2025 다시, 봄. All rights reserved.</p>
	</footer>

	<script>
	    // contextPath를 JavaScript 변수로 정의
	    const contextPath = "${pageContext.request.contextPath}";
	</script>
	<script src="${pageContext.request.contextPath}/resource/js/main.js"></script>
</body>