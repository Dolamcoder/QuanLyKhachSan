package Dao;

import Connection.JDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DangNhapDao {
    private Connection conn;

    public DangNhapDao() {
        conn = JDBC.getConnection();
    }

    public boolean validateUser(String username, String password) {
        String query = "SELECT * FROM Users WHERE username = ? AND password = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Trả về true nếu tìm thấy bản ghi
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
