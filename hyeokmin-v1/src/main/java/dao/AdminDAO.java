package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dto.AdminDTO;
import util.DBUtil;

public class AdminDAO {
    public int registerAdmin(AdminDTO dto) {
        String sql = "INSERT INTO admin_info VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dto.getAdmin_serialNum());
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

    public AdminDTO loginAdmin(String id, String pw) {
        String sql = "SELECT * FROM admin_info WHERE admin_id=? AND admin_pw=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id); ps.setString(2, pw);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new AdminDTO(
                    rs.getString("admin_serialNum"),
                    rs.getString("admin_id"),
                    rs.getString("admin_pw"),
                    rs.getString("admin_name"),
                    rs.getString("admin_phone"),
                    rs.getString("admin_email")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

