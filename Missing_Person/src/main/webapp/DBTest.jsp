<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>DB 연결 테스트</title></head>
<body>
<%
    String url = "jdbc:oracle:thin:@nam3324.synology.me:32800:XE";
    String user = "c##dasibom22";
    String password = "dasibom22";

    try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conn = DriverManager.getConnection(url, user, password);
        out.println("<h2>✅ DB 연결 성공!</h2>");
        conn.close();
    } catch (Exception e) {
        out.println("<h2 style='color:red;'>❌ DB 연결 실패</h2>");
        out.println("<pre>" + e.getMessage() + "</pre>");
    }
%>
</body>
</html>
