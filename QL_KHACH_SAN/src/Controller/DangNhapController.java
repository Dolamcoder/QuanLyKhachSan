package Controller;

//import Model.DatabaseConnection;
import Dao.DangNhapDao;
import View.DangKyView;
import View.DangNhapView;
import View.GiaoDienView;
import View.QuenMatKhauView;

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
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(dn, "Vui lòng nhập đầy đủ thông tin!");
            return;  
        }
        if (dnDao.validateUser(username, password)) {
            JOptionPane.showMessageDialog(dn, "Đăng nhập thành công!");
            new GiaoDienView(); 
            dn.close(); 
        } else {
            JOptionPane.showMessageDialog(dn, "Sai tài khoản hoặc mật khẩu!");
        }
    } else if (src.equals("Đang Ky")) {
        new DangKyView();  
      
    }
    else if(src.equals("Quen mat khau")){
        new QuenMatKhauView();
        dn.close();
    }
}
}
