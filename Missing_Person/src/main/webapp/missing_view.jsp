<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>실종자 접수 목록</title>
</head>
<body>
    <h1>실종자 접수 정보 확인</h1>

    <%
        // 서블릿에서 request.setAttribute로 전달된 데이터 받기
        String missingName = (String) request.getAttribute("missingName"); // 이름
        String missingGender = (String) request.getAttribute("missingGender"); // 성별
        String missingBirth = (String) request.getAttribute("missingBirth"); // 생년월일
        String ageAtMissing = (String) request.getAttribute("ageAtMissing"); // 실종 당시 나이
        String currentAge = (String) request.getAttribute("currentAge");     // 현재 나이
        String missingEtc = (String) request.getAttribute("missingEtc"); // 기타 특징
        String missingPlace = (String) request.getAttribute("missingPlace"); // 위치 정보(마지막 목격 장소)
        String missingDate = (String) request.getAttribute("missingDate"); // 실종 날짜
        String missingImageUrl = (String) request.getAttribute("missingImageUrl"); // 이미지 URL

        // 에러 메시지가 있다면 출력
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) {
            out.println("<p style='color: red;'>" + errorMessage + "</p>");
        }
    %>

    <p><strong>이름:</strong> <%= missingName != null ? missingName : "데이터 없음" %></p>
    <p><strong>성별:</strong> <%= missingGender != null ? missingGender : "데이터 없음" %></p>
    <p><strong>생년월일 :</strong> <%= missingBirth != null ? missingBirth : "데이터 없음" %></p>
    <p><strong>실종 당시 나이 (만 나이):</strong> <%= ageAtMissing %></p>
    <p><strong>현재 나이 (만 나이):</strong> <%= currentAge %></p>
    <p><strong>기타 특징:</strong> <%= missingEtc != null ? missingEtc : "데이터 없음" %></p>
    <p><strong>실종 추정 위치:</strong> <%= missingPlace != null && !missingPlace.isEmpty() ? missingPlace : "정보 없음" %></p>
    <p><strong>실종 날짜:</strong> <%= missingDate != null ? missingDate : "데이터 없음" %></p>


    <% if (missingImageUrl != null && !missingImageUrl.isEmpty()) { %>
        <p><strong>업로드된 이미지:</strong></p>
        <img src="<%= missingImageUrl %>" alt="실종자 이미지" style="max-width: 300px; height: auto;">
    <% } else { %>
        <p><strong>업로드된 이미지:</strong> 없음</p>
    <% } %>

    <a href="missing_insert.jsp">실종자 접수</a>
    <a href="목격자 제보 페이지 URL">제보하기(목격자 제보 URL 아직 연결 안함)</a>
</body>
</html>