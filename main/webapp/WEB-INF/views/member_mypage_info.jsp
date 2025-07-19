<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 마이페이지</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resource/css/member_mypage_info.css">
	<script src="${pageContext.request.contextPath}/resource/js/member_mypage_info.js"></script>
</head>
<body>
	<div class="container">
		<div class="content">
			<div class="sidebar">
				<!-- 사이드 메뉴 -->
				<h3>내 정보</h3>
				<ul>
					<li>내가 제보한 실종자</li>
					<li>내가 작성한 목격 정보</li>
					<li>회원 탈퇴</li>
				</ul>
			</div>
			<div class="main-content">
				<h1 class="page-title">내 정보</h1>

				<div class="profile-section">
					<img class="profile-image"
						src="${pageContext.request.contextPath}/resource/img/default.jpg"></img>

					<div class="profile-info">

						<div class="profile-name">${member.member_id}님</div>
						<div class="profile-welcome">
							안녕하세요!<br>다시, 봄과 함께 해 주셔서 감사합니다!
						</div>

						<div class="profile-details">
							<div class="detail-row">
								<div class="detail-label">이름</div>
								<input type="text" class="detail-value"
									value="${member.member_name}" readonly />
							</div>
							<div class="detail-row">
								<div class="detail-label">전화번호</div>
								<input type="text" class="detail-value"
									value="${member.member_phone}" readonly />
							</div>
							<div class="detail-row">
								<div class="detail-label">이메일</div>
								<c:choose>
									<c:when test="${member.member_email == null}">
									</c:when>
									<c:otherwise>
										<input type="text" class="detail-value" value="${member.member_email}" readonly />
									</c:otherwise>
								</c:choose>
							</div>
						</div>

						<div class="button-group">
							<button class="btn btn-primary">수정</button>
							<button class="btn btn-secondary">로그아웃</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>