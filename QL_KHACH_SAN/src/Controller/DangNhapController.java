package Controller;

//import Model.DatabaseConnection;
import Dao.DangNhapDao;
import View.DangKyView;
import View.DangNhapView;
import View.GiaoDienView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DangNhapController implements ActionListener {
     DangNhapView dn;
    DangNhapDao dnDao;
    public DangNhapController(DangNhapView dn) {
        this.dn = dn;
        dnDao=new DangNhapDao();
    }

  @Override
public void actionPerformed(ActionEvent e) {
    String src = e.getActionCommand();

    if (src.equals("Xac Nhan")) {
        String username = dn.getUsername();
        String password = dn.getPassword();

        // Kiểm tra xem người dùng nhập liệu hợp lệ
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(dn, "Vui lòng nhập đầy đủ thông tin!");
            return;  // Dừng hàm nếu chưa nhập đầy đủ thông tin
        }

        // Kiểm tra thông tin người dùng
        if (dnDao.validateUser(username, password)) {
            JOptionPane.showMessageDialog(dn, "Đăng nhập thành công!");
            new GiaoDienView();  // Mở giao diện chính
            dn.close();  // Đóng cửa sổ đăng nhập một cách an toàn
        } else {
            JOptionPane.showMessageDialog(dn, "Sai tài khoản hoặc mật khẩu!");
        }
    } else if (src.equals("Đang Ky")) {
        new DangKyView();  // Mở giao diện đăng ký
    }
}




  
}
