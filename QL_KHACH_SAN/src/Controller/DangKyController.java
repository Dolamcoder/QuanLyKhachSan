package Controller;

import Dao.DangKyDao;
import View.DangKyView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DangKyController implements ActionListener {
    DangKyView dk;  // Giao diện đăng ký
    DangKyDao dkDao;  // Lớp truy cập cơ sở dữ liệu

    public DangKyController(DangKyView dk) {
        this.dk = dk;
        this.dkDao = new DangKyDao();  // Khởi tạo đối tượng dao để thao tác với cơ sở dữ liệu
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        // Nếu người dùng nhấn nút "Đăng Ký"
        if (command.equals("Đăng Ký")) {
            // Lấy thông tin từ các trường nhập liệu
            String username = dk.getUsername();  // Giả sử bạn có phương thức getUsername() trong DangKyView
            String password = dk.getPassword();  // Giả sử bạn có phương thức getPassword() trong DangKyView
            String confirmPassword = dk.getConfirmPassword();  // Giả sử bạn có phương thức getConfirmPassword() trong DangKyView
            String email = dk.getEmail();  // Giả sử bạn có phương thức getEmail() trong DangKyView
            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(dk, "Vui lòng điền đầy đủ thông tin!");
                return;
              }

            // Kiểm tra xem mật khẩu có trùng khớp không
            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(dk, "Mật khẩu không khớp!");
               return;
            }
            if(dkDao.isUserExist(username, email)){
                JOptionPane.showMessageDialog(dk, "Tai khoan da ton tai");
                return;
            }
            // Gọi phương thức từ DangKyDao để tạo tài khoản
            boolean isRegistered = dkDao.taoTaiKhoan(username, password, email);

            if (isRegistered) {
                JOptionPane.showMessageDialog(dk, "Đăng ký thành công!");
                dk.dispose();  // Đóng cửa sổ đăng ký
            } else {
                JOptionPane.showMessageDialog(dk, "Đã xảy ra lỗi trong quá trình đăng ký!");
            }
        } 
        // Nếu người dùng nhấn nút "Hủy"
        else if (command.equals("Hủy")) {
            dk.close();  // Đóng cửa sổ đăng ký
        }
    }
}
