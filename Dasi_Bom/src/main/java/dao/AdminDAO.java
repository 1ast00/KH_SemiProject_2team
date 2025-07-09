package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dto.AdminDTO;
import util.DBUtil;

public class AdminDAO {

    // 관리자 등록
    public int insertAdmin(AdminDTO dto) {
        String sql = "INSERT INTO admin_info VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dto.getAdmin_serialNum()); // 직접 생성 또는 시퀀스 필요
            ps.setString(2, dto.getAdmin_id());
            ps.setString(3, dto.getAdmin_pw());
            ps.setString(4, dto.getAdmin_name());
            ps.setString(5, dto.getAdmin_phone());
            ps.setString(6, dto.getAdmin_email());

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // 중복 아이디 확인
    public boolean isIdDuplicate(String admin_id) {
        String sql = "SELECT admin_id FROM admin_info WHERE admin_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, admin_id);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // 있으면 중복
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 로그인 유효성 확인
    public boolean isValidLogin(String admin_id, String admin_pw) {
        String sql = "SELECT * FROM admin_info WHERE admin_id = ? AND admin_pw = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, admin_id);
            ps.setString(2, admin_pw);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // true = 로그인 성공
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
