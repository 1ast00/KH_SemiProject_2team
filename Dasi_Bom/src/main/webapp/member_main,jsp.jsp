<!--  임시 main 추후 삭제 or 수정 -->

<%@ page contentType="text/html; charset=UTF-8" %>
<%
    String member_id = (String) session.getAttribute("member_id");
    String member_name = (String) session.getAttribute("member_name");
    String member_serialNum = (String) session.getAttribute("member_serialNum");

    if (member_id == null || member_name == null) {
        response.sendRedirect("member_login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>메인 페이지</title>
  <style>
    body {
      font-family: 'Noto Sans KR', sans-serif;
      background-color: #f9f9f9;
      text-align: center;
      padding: 100px;
    }
    h2 {
      font-size: 28px;
      color: #333;
    }
    .info {
      margin-top: 20px;
      font-size: 18px;
    }
    .logout-btn {
      margin-top: 30px;
      padding: 10px 20px;
      font-size: 16px;
      background-color: #f99db5;
      color: white;
      border: none;
      border-radius: 6px;
      cursor: pointer;
    }
    .logout-btn:hover {
      background-color: #f07fa1;
    }
  </style>
</head>
<body>

  <h2><%= member_name %>님, 다시 봄에 오신 것을 환영합니다 🌸</h2>

  <div class="info">
    <p><strong>회원 ID:</strong> <%= member_id %></p>
    <% if (member_serialNum != null) { %>
      <p><strong>회원 고유번호:</strong> <%= member_serialNum %></p>
    <% } %>
  </div>

  <button class="logout-btn" onclick="location.href='logout.jsp'">로그아웃</button>

</body>
</html>