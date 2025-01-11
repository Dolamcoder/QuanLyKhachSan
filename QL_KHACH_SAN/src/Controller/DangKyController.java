package Controller;

import Dao.DangKyDao;
import View.DangKyView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DangKyController implements ActionListener {
    DangKyView dk; 
    DangKyDao dkDao;  

    public DangKyController(DangKyView dk) {
        this.dk = dk;
        this.dkDao = new DangKyDao();  
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

       
        if (command.equals("Đăng Ký")) {
            
            String username = dk.getUsername();  
            String password = dk.getPassword();  
            String confirmPassword = dk.getConfirmPassword();  
            String email = dk.getEmail();  
            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(dk, "Vui lòng điền đầy đủ thông tin!");
                return;
              }

           
            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(dk, "Mật khẩu không khớp!");
               return;
            }
            if(dkDao.isUserExist(username, email)){
                JOptionPane.showMessageDialog(dk, "Tai khoan da ton tai");
                return;
            }
            boolean isRegistered = dkDao.taoTaiKhoan(username, password, email);

            if (isRegistered) {
                JOptionPane.showMessageDialog(dk, "Đăng ký thành công!");
                dk.dispose(); 
            } else {
                JOptionPane.showMessageDialog(dk, "Đã xảy ra lỗi trong quá trình đăng ký!");
            }
        } 
     
        else if (command.equals("Hủy")) {
            dk.close(); 
        }
    }
}
