/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Connection.JDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
 
public class DangKyDao {
   private Connection conn;

    public DangKyDao() {
        conn = JDBC.getConnection();
    }
    public boolean isUserExist(String user, String email) {
        String sql = "SELECT * FROM users WHERE username = ? OR email = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user);
            stmt.setString(2, email);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;  // Tồn tại tài khoản hoặc email
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
   public boolean taoTaiKhoan(String user, String pass, String email) {
    String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        // Gán giá trị vào các tham số của câu lệnh SQL
        stmt.setString(1, user);  // Gán tên người dùng
        stmt.setString(2, pass);  // Gán mật khẩu
        stmt.setString(3, email); // Gán email

        // Thực thi câu lệnh SQL
        int rowsInserted = stmt.executeUpdate();

        if(rowsInserted>0) return true;
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}

}
