<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>목격자 제보</title>
<style>
    body {
        background-color: #fafafa;
        margin: 0;
        padding: 0;
    }

    .report-container {
        width: 500px;
        margin: 50px auto;
        background-color: #fff;
        border: 2px solid #f7b9cf;
        border-radius: 10px;
        padding: 40px;
    }

    h2 {
        text-align: center;
        color: #cc0066;
        margin-bottom: 30px;
    }

    form {
        display: flex;
        flex-direction: column;
        gap: 15px;
    }

    label {
        font-weight: bold;
        color: #444;
        margin-bottom: 5px;
    }

    input[type="text"],
    input[type="number"],
    textarea {
        padding: 10px;
        border: 1px solid #ddd;
        border-radius: 5px;
        font-size: 15px;
    }

    input[type="file"] {
        margin-top: 5px;
    }

    .btn-box {
        display: flex;
        justify-content: center;
        margin-top: 20px;
        gap: 10px;
    }

    .btn {
        padding: 8px 20px;
        border-radius: 20px;
        border: none;
        font-weight: bold;
        cursor: pointer;
        transition: 0.2s;
    }

    .btn-submit {
        background-color: #ff7cb3;
        color: white;
    }

    .btn-cancel {
        background-color: #eee;
        color: #333;
    }

    .btn:hover {
        opacity: 0.9;
    }

    .file-note {
        font-size: 12px;
        color: #999;
    }
</style>
</head>
<body>
<div class="report-container">
    <h2>목격 제보</h2>
    <form action="${pageContext.request.contextPath}/witnessInsert.do" method="post" enctype="multipart/form-data">
        <label>목격 날짜:</label>
        <input type="text" name="date" placeholder="예: 2025-07-09" required>

        <label>목격 위치:</label>
        <input type="text" name="place" placeholder="예: 서울 강남구 역삼동">

        <label>추정 성별:</label>
        <input type="radio" name="gender" value="M">남성
		<input type="radio" name="gender" value="F">여성

        <label>추정 나이:</label>
        <input type="text" name="age" placeholder="예: 30대 초반">

        <label>이름 (추정):</label> <input type="text" name="name" placeholder="이름을 입력하세요 (선택 사항)"> <label>기타:</label>
        <textarea name="etc" rows="3" placeholder="목격 당시 상황 등을 입력해주세요."></textarea>

        <label>이미지 업로드:</label>
        <input type="file" name="image" accept="image/*">
        <p class="file-note">※ 이미지 파일은 선택사항입니다.</p>
		
		<input type="hidden" name="latitude" value="${lat}">
    	<input type="hidden" name="longitude" value="${lng}">
    	<input type="hidden" name="place" value="${place}">
	
        <div class="btn-box">
            <button type="submit" class="btn btn-submit">등록</button>
            <button type="reset" class="btn btn-cancel">취소</button>
        </div>
    </form>
</div>


</body>
</html>
