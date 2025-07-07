<%@ page contentType="text/html; charset=UTF-8" %>
<%
    // 세션에서 개별 값 꺼내기
    String admin_name = (String) session.getAttribute("admin_name");
    String admin_id = (String) session.getAttribute("admin_id");
    String admin_serialNum = (String) session.getAttribute("admin_serialNum");

    // 로그인 안 되어 있으면 로그인 페이지로 이동
    if (admin_id == null || admin_name == null) {
        response.sendRedirect("admin_login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>관리자 메인</title>
  <style>
    body {
      font-family: 'Noto Sans KR', sans-serif;
      background-color: #f0f0f0;
      padding: 100px;
      text-align: center;
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
      background-color: #333;
      color: white;
      border: none;
      border-radius: 6px;
      cursor: pointer;
    }
    .logout-btn:hover {
      background-color: #555;
    }
  </style>
</head>
<body>

<h2><%= admin_name %> 관리자님, 다시 봄 관리자 페이지에 오신 것을 환영합니다 🌸</h2>

<div class="info">
    <p><strong>관리자 ID:</strong> <%= admin_id %></p>
    <% if (admin_serialNum != null) { %>
        <p><strong>관리자 고유번호:</strong> <%= admin_serialNum %></p>
    <% } %>
</div>

<button class="logout-btn" onclick="location.href='logout.jsp'">로그아웃</button>

</body>
</html>
