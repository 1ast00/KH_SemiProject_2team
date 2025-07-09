package dao;

import java.sql.*;

import java.util.*;
import dto.MissingPersonDTO;

public class MissingPersonDAO {
    private Connection conn;

    public MissingPersonDAO(Connection conn) { // DB 커넥션(연결)하는 객체 
        this.conn = conn; 
    }

    public List<MissingPersonDTO> getAllMissingPersons() {
        List<MissingPersonDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM missing_person ORDER BY id DESC";

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                MissingPersonDTO person = new MissingPersonDTO();
                person.setId(rs.getInt("id"));
                person.setName(rs.getString("name"));
                person.setGender(rs.getString("gender"));
                person.setBirth(rs.getString("birth"));
                person.setMissingDate(rs.getString("missing_date"));
                person.setPlace(rs.getString("place"));
                person.setEtc(rs.getString("etc"));
                person.setImage(rs.getString("image"));
                list.add(person);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}