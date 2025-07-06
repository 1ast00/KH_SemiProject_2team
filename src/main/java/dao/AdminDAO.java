package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.AdminDTO;
import util.DBUtil;

public class AdminDAO {

    // 관리자 회원가입
    public int insertAdmin(AdminDTO admin) {
        String sql = "INSERT INTO admin_info (admin_id, admin_pw, admin_name, admin_email, admin_phone) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, admin.getAdmin_id());
            pstmt.setString(2, admin.getAdmin_pw());
            pstmt.setString(3, admin.getAdmin_name());
            pstmt.setString(4, admin.getAdmin_email());
            pstmt.setString(5, admin.getAdmin_phone());

            return pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    // ✅ 관리자 로그인
    public AdminDTO login(String admin_id, String admin_pw) {
        String sql = "SELECT * FROM admin_info WHERE admin_id = ? AND admin_pw = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, admin_id);
            pstmt.setString(2, admin_pw);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                // 로그인 성공 시 관리자 정보 반환
                return new AdminDTO(
                    rs.getString("admin_id"),
                    rs.getString("admin_pw"),
                    rs.getString("admin_name"),
                    rs.getString("admin_email"),
                    rs.getString("admin_phone")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // 로그인 실패
    }
}
