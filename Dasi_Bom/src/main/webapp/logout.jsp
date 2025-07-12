<%@ page contentType="text/html; charset=UTF-8" %>
<%
    session.invalidate(); // 세션 초기화
%>
<script>
  alert("정상적으로 로그아웃되었습니다.");
  location.href = "admin_login.jsp"; 
</script>