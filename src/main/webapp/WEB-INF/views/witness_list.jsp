<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

.submit {
    width: 80px;
    border-radius: 10px;
    background-color: pink;
    border: none;
    margin-left: 10px;
    cursor: pointer;
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
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.05);
    text-decoration: none;
    color: #333;
    position: relative;
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

/* Delete button styles */
.delete-btn {
    position: absolute;
    top: 10px;
    right: 10px;
    background-color: #ff4d4d;
    color: white;
    padding: 5px 10px;
    border: 2px solid #ff4d4d;
    font-size: 14px;
    font-weight: bold;
    border-radius: 5px;
    text-decoration: none;
    transition: background-color 0.3s, color 0.3s;
}

.delete-btn:hover {
    background-color: #e60000;
    color: #fff;
    border-color: #e60000;
}

/* Pagination styles */
.pagination {
    text-align: center;
    margin-top: 30px;
    clear: both; /* Ensures pagination is placed after all cards */
}

.pagination a {
    padding: 6px 12px;
    margin: 0 4px;
    border: 1px solid #ccc;
    text-decoration: none;
    color: #333;
    border-radius: 5px;
}

.pagination a.current {
    background-color: #333;
    color: white;
}

.pagination a:hover {
    background-color: #ddd;
}
</style>
</head>
<body>
    <h2>목격자 제보 목록</h2>

    <div class="search-box">
        <input type="text" placeholder="이름, 나이, 성별, 위치, 기타로 검색">
        <button class="submit">전송</button>
    </div>

    <div class="grid-container">
        <c:forEach var="dto" items="${witnessList}">
            <div class="card">
                <a href="witnessView.do?witnessSerialNum=${dto.witnessSerialNum}">
                    <img src="${pageContext.request.contextPath}/resource/upload/${dto.image}"
                         onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/resource/img/default.jpg';"
                         alt="제보 이미지">
                </a>
                <!-- Correctly positioned delete button -->
                <a href="witnessDelete.do?witnessSerialNum=${dto.witnessSerialNum}" class="delete-btn"
                   onclick="return confirm('정말 삭제하시겠습니까?');">
                   삭제
                </a>
                <div class="info">
                    <div><span>성별:</span> ${dto.gender}</div>
                    <div><span>나이:</span> ${dto.age}</div>
                    <div><span>위치:</span> ${dto.place}</div>
                </div>
            </div>
        </c:forEach>
    </div>

    <!-- Pagination at the bottom -->
    <div class="pagination">
        <c:if test="${page > 1}">
            <a href="?page=${page - 1}">&laquo; 이전</a>
        </c:if>

        <!-- Page number links -->
        <c:forEach var="i" begin="1" end="${totalPages}">
            <c:if test="${i >= page - 2 && i <= page + 2}">
                <a href="?page=${i}" class="${i == page ? 'current' : ''}">${i}</a>
            </c:if>
        </c:forEach>

        <c:if test="${page < totalPages}">
            <a href="?page=${page + 1}">다음 &raquo;</a>
        </c:if>
    </div>
    <div style="text-align:center; margin-top:30px; color:red;">
    현재 페이지: ${page} / 전체 페이지: ${totalPages}
</div>
</body>
</html>
