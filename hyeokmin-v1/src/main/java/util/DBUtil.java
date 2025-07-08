package util;

import java.sql.*;

public class DBUtil {

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver"); // 드라이버 명시 로딩
            String url = "jdbc:oracle:thin:@211.198.10.27:1521:xe"; // ← 여기 수정!!
            String user = "system";       // 너의 오라클 계정
            String password = "1234";     // 비밀번호

            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("✅ DB 연결 성공!");
            return conn;

        } catch (ClassNotFoundException e) {
            System.out.println("❌ 드라이버 클래스를 찾을 수 없습니다.");
            e.printStackTrace();
            throw new SQLException("드라이버 로딩 실패", e);

        } catch (SQLException e) {
            System.out.println("❌ DB 연결 실패!");
            e.printStackTrace();
            throw e;
        }
    }

    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        try { if (rs != null) rs.close(); } catch (Exception e) {}
        try { if (stmt != null) stmt.close(); } catch (Exception e) {}
        try { if (conn != null) conn.close(); } catch (Exception e) {}
    }
}
