<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>실종자 접수 목록</title>
    
 <style>
 body {
        font-family: 'Noto Sans KR', sans-serif;
        margin: 0;
        padding: 0;
        background-color: #fff;
        text-align: center;
    }

    h1 {
        margin-top: 30px;
        font-size: 30px;
        font-weight: bold;
    }

.container {
    width: 800px;
    margin: 30px auto;
    padding: 0px 0px 0px 350px; 
    display: flex;
    justify-content: flex-start;
    align-items: flex-start;
    gap: 30px;
    text-align: left;
}


    .image-box img {
        width: 250px;
        height: auto;
        border: 1px solid #ccc;
        box-shadow: 0 0 5px rgba(0,0,0,0.2);
    }

    .info-box {
        font-size: 16px;
        line-height: 1.8;
        flex-grow: 1;
        margin-top: -10px; /* 이 값을 조절하여 위로 이동시킵니다. */
    }

    .info-box p {
        margin: 5px 0;
    }

    .caption {
        margin-top: 20px;
        font-size: 14px;
        color: #555;
    }

    .button-group {
        margin-top: 30px;
    }

    .button-group a,
    .button-group form button {
        background-color: #f9a8d4;
        color: #fff;
        padding: 10px 20px;
        border: none;
        margin: 0 10px;
        border-radius: 5px;
        text-decoration: none;
        cursor: pointer;
        font-weight: bold;
    }

    .button-group form {
        display: inline;
    }

    .button-group a:hover,
    .button-group form button:hover {
        background-color: #f472b6;
    }
</style>

</head>
<body>
    <h1>실종자</h1>

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
        <% if (missingImageUrl != null && !missingImageUrl.isEmpty()) { %>
            <img src="<%= missingImageUrl %>" alt="실종자 이미지">
        <% } else { %>
            <p><strong>이미지 없음</strong></p>
        <% } %>
    </div>

    <div class="info-box">
        <p><strong>이름 :</strong> <%= missingName != null ? missingName : "데이터 없음" %></p>
        <p><strong>성별 :</strong> <%= missingGender != null ? missingGender : "데이터 없음" %></p>
        <p><strong>생년월일 :</strong> <%= missingBirth != null ? missingBirth : "데이터 없음" %></p>
        <p><strong>실종 당시 나이 :</strong> <%= ageAtMissing %></p>
        <p><strong>현재 나이 :</strong> <%= currentAge %></p>
        <p><strong>기타 특징 :</strong> <%= missingEtc != null ? missingEtc : "데이터 없음" %></p>
        <p><strong>마지막 목격 장소 :</strong> <%= missingPlace != null ? missingPlace : "정보 없음" %></p>
        <p><strong>실종 날짜 :</strong> <%= missingDate != null ? missingDate : "데이터 없음" %></p>
    </div>
</div>

<p class="caption">함께 찾아요, 돌아올 봄을 기다리는 소중한 가족을</p>

<div class="button-group">
    <a href="missing_insert.jsp">실종자 접수</a>
    <a href="#">제보하기</a>
    <form action="<%= request.getContextPath() %>/missing/list" method="get">
        <button type="submit">실종자 목록 보기</button>
    </form>
</div>

    
</body>
</html>