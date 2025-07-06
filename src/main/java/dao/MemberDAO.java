package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.MemberDTO;
import util.DBUtil;

public class MemberDAO {

    // 회원가입
    public int insertMember(MemberDTO member) {
        String sql = "INSERT INTO member_info (member_serialNum, member_id, member_pw, member_name, member_email, member_phone) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, member.getMember_serialNum());
            pstmt.setString(2, member.getMember_id());
            pstmt.setString(3, member.getMember_pw());
            pstmt.setString(4, member.getMember_name());
            pstmt.setString(5, member.getMember_email());
            pstmt.setString(6, member.getMember_phone());

            return pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    // 로그인
    public MemberDTO login(String member_id, String member_pw) {
        String sql = "SELECT * FROM member_info WHERE member_id = ? AND member_pw = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, member_id);
            pstmt.setString(2, member_pw);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new MemberDTO(
                    rs.getString("member_serialNum"),
                    rs.getString("member_id"),
                    rs.getString("member_pw"),
                    rs.getString("member_name"),
                    rs.getString("member_email"),
                    rs.getString("member_phone"),
                    null // admin_serialNum은 현재 테이블에 없음
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // 로그인 실패
    }
}

