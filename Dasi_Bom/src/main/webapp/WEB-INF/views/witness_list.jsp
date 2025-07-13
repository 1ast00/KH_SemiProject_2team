<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>목격자 제보 목록</title>
<style>
    body {
        font-family: '맑은 고딕', sans-serif;
        background-color: #fefefe;
        padding: 40px;
    }

    h2 {
        color: #333;
        margin-bottom: 20px;
        text-align: center;
    }

    .search-box {
        display: flex;
        justify-content: center;
        margin-bottom: 30px;
    }

    .search-box input {
        padding: 8px;
        border: 1px solid #ddd;
        border-radius: 20px;
        width: 300px;
        font-size: 14px;
        padding-left: 15px;
    }

    .grid-container {
        display: grid;
        grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
        gap: 20px;
    }

    .card {
        background-color: #fff;
        border: 1px solid #eee;
        border-radius: 10px;
        padding: 15px;
        box-shadow: 0 2px 5px rgba(0,0,0,0.05);
    }

    .card img {
        width: 100%;
        height: 200px;
        object-fit: cover;
        border-radius: 8px;
        margin-bottom: 10px;
    }

    .info {
        font-size: 14px;
        color: #444;
        line-height: 1.6;
    }

    .info span {
        font-weight: bold;
    }
    
    .submit {
    	width : 80px;
    	border-radius: 10px;
    	background-color: pink;
    	
    }
    
</style>
</head>
<body>

<h2>목격자 제보 목록</h2>

<div class="search-box">
    <input type="text" placeholder="이름, 나이, 성별, 위치, 기타로 검색">
    <button class = "submit">전송</button>
</div>
<!-- 예시임 ㅎㅎ -->
<div class="grid-container">
<a href="witnessView.do?id=${dto.id}">
	<div class = "card">
		<img alt="아가" src="./resource/img/missing_person_1.jpg">
		<p>이름 : 김지원</p>
		<p>남자</p>
		<p>나이 : 5</p>
		<p>위치 : 오목교역</p>
		<p>기타 : 청바지를 입고 뛰어다녓슴</p>
		<p>이미지 파일 명 : 1234.jpg</p>
	</div>

    <c:forEach var="dto" items="${witnessList}">
    <a href="witnessView.do?id=${dto.id}">
        <div class="card">
            <img src="upload/${dto.imageFileName}" alt="목격 이미지">
            <div class="info">
                <div><span>이름:</span> ${dto.name}</div>
                <div><span>성별:</span> ${dto.gender}</div>
                <div><span>나이:</span> ${dto.age}</div>
                <div><span>위치:</span> ${dto.place}</div>
                <div><span>기타:</span> ${dto.etc}</div>
                <div><span>이미지 파일명:</span> ${dto.imageFileName}</div>
            </div>
        </div>
    </c:forEach>

</div>

</body>
</html>
