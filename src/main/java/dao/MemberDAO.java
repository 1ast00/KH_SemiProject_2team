package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dto.MemberDTO;
import util.DBUtil;

public class MemberDAO {

    // 회원 등록
    public int registerMember(MemberDTO dto) {
        int result = 0;
        String sql = "INSERT INTO member_info "
                   + "(member_serialNum, member_id, member_pw, member_name, member_email, member_phone, admin_serialNum) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, dto.getMember_serialNum());
            pstmt.setString(2, dto.getMember_id());
            pstmt.setString(3, dto.getMember_pw());
            pstmt.setString(4, dto.getMember_name());
            pstmt.setString(5, dto.getMember_email());
            pstmt.setString(6, dto.getMember_phone());
            pstmt.setString(7, dto.getAdmin_serialNum());

            result = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    // ID 중복 확인
    public boolean isMemberIdDuplicate(String member_id) {
        boolean isDuplicate = false;
        String sql = "SELECT member_id FROM member_info WHERE member_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, member_id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    isDuplicate = true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return isDuplicate;
    }

    // 로그인
    public MemberDTO loginMember(String member_id, String member_pw) {
        MemberDTO dto = null;
        String sql = "SELECT * FROM member_info WHERE member_id = ? AND member_pw = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, member_id);
            pstmt.setString(2, member_pw);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    dto = new MemberDTO(
                        rs.getString("member_serialNum"),
                        rs.getString("member_id"),
                        rs.getString("member_pw"),
                        rs.getString("member_name"),
                        rs.getString("member_email"),
                        rs.getString("member_phone"),
                        rs.getString("admin_serialNum")
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dto;
    }
}
