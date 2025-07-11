package dao;

import dto.MissingPersonDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MissingPersonDAO {
    private Connection conn;

    public MissingPersonDAO(Connection conn) {
        this.conn = conn;
    }

    public String addMissingPerson(MissingPersonDTO person) {
        String sql = "INSERT INTO missing_info (missing_serialNum, member_serialNum, admin_serialNum, missing_name, missing_gender, missing_birth, missing_etc, missing_place, missing_date, missing_img) " +
                     "VALUES ('M' || LPAD(missing_seq.NEXTVAL, 9, '0'), ?, ?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?)";

        String generatedKeys[] = {"missing_serialNum"};

        try (PreparedStatement pstmt = conn.prepareStatement(sql, generatedKeys)) {

            pstmt.setString(1, "MEMB000001");
            pstmt.setString(2, "ADMIN00001");

            pstmt.setString(3, person.getName());
            pstmt.setString(4, person.getGender());
            // 변경된 부분: setInt -> setString으로 변경하고, YYYY-MM-DD 형식의 문자열을 그대로 전달
            pstmt.setString(5, person.getBirth());
            pstmt.setString(6, person.getEtc());
            pstmt.setString(7, person.getPlace());
            pstmt.setString(8, person.getDate());

            if (person.getImg() != null && person.getImg().length > 0) {
                pstmt.setBytes(9, person.getImg());
            } else {
                pstmt.setNull(9, Types.BLOB);
            }

            int result = pstmt.executeUpdate();

            if (result > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getString(1);
                    }
                }
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<MissingPersonDTO> getAllMissingPersons() {
        List<MissingPersonDTO> list = new ArrayList<>();

        String sql = "SELECT missing_serialNum, missing_name, missing_gender, missing_birth, missing_date, missing_place, missing_etc FROM missing_info ORDER BY missing_serialNum DESC";

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {

                MissingPersonDTO person = new MissingPersonDTO();

                person.setSerialNum(rs.getString("missing_serialNum"));
                person.setName(rs.getString("missing_name"));
                person.setGender(rs.getString("missing_gender"));
                
                // 변경된 부분: Date 타입으로 조회 후 YYYY-MM-DD 형식의 문자열로 변환
                Date dbBirth = rs.getDate("missing_birth");
                person.setBirth(dbBirth != null ? dbBirth.toString() : "");

                Date dbDate = rs.getDate("missing_date");
                person.setDate(dbDate != null ? dbDate.toString() : "");
                
                person.setPlace(rs.getString("missing_place"));
                person.setEtc(rs.getString("missing_etc"));
                list.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public byte[] getMissingImageById(String serialNum) {
        String sql = "SELECT missing_img FROM missing_info WHERE missing_serialNum = ?";
        byte[] imageBytes = null;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, serialNum);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Blob blob = rs.getBlob("missing_img");
                    if (blob != null) {
                        imageBytes = blob.getBytes(1, (int) blob.length());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return imageBytes;
    }
}