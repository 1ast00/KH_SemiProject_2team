<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>목격자 제보</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/witness_insert.css">
<jsp:include page="./template/header.jsp" />
</head>
<body>
<div class="report-container">
    <h2>목격 제보</h2>
    <form action="${pageContext.request.contextPath}/witnessInsert.do" method="post" enctype="multipart/form-data">

        <label>목격 날짜:</label>
        <input type="date" name="date" required>

        <label>목격 위치:</label>
        <input type="text" name="place" placeholder="예: 서울 강남구 역삼동" required>

        <label>추정 성별:</label>
        <select name="gender" required>
            <option value="">선택하세요</option>
            <option value="M">남성</option>
            <option value="F">여성</option>
        </select>

        <label>추정 나이:</label>
        <select name="age" required>
            <option value="">선택하세요</option>
            <option value="5">0~10세</option>
            <option value="15">10~20세</option>
            <option value="25">20~30세</option>
            <option value="35">30~40세</option>
            <option value="45">40~50세</option>
            <option value="55">50~60세</option>
            <option value="65">60~70세</option>
            <option value="75">70~80세</option>
            <option value="85">80~90세</option>
            <option value="95">90~100세</option>
            <option value="100">100세 이상</option>
        </select>

        <label>이름 (추정):</label>
        <input type="text" name="name" placeholder="이름을 입력하세요 (선택 사항)">

        <label>기타:</label>
        <textarea name="etc" rows="3" placeholder="목격 당시 상황 등을 입력해주세요."></textarea>

        <label>이미지 업로드:</label>
        <input type="file" name="image" accept="image/*">
        <p class="file-note">※ 이미지 파일은 선택사항입니다.</p>

        <input type="hidden" name="latitude" value="${lat}">
        <input type="hidden" name="longitude" value="${lng}">

        <div class="btn-box">
            <button type="submit" class="btn btn-submit">등록</button>
            <button type="button" onclick="history.back()" class="btn btn-cancel">취소</button>
        </div>
    </form>
</div>
</body>
</html>