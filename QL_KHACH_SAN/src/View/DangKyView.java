package View;

import Controller.DangKyController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DangKyView extends JFrame {

    
    private JTextField usernameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    ActionListener ac;

    public DangKyView() {
        ac = new DangKyController(this);
         this.setTitle("Đăng Ký");
    this.setSize(400, 300);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

   
    JLabel title = new JLabel("Đăng Ký Tài Khoản", SwingConstants.CENTER);
    title.setFont(new Font("Arial", Font.BOLD, 20));
    title.setForeground(Color.BLUE);
    this.add(title, BorderLayout.NORTH);
    JPanel formPanel = new JPanel();
    formPanel.setLayout(new GridLayout(4, 2, 10, 10));
    formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    formPanel.add(new JLabel("Tên người dùng:"));
    usernameField = new JTextField();
    formPanel.add(usernameField);

    formPanel.add(new JLabel("Email:"));
    emailField = new JTextField();
    formPanel.add(emailField);

    formPanel.add(new JLabel("Mật khẩu:"));
    passwordField = new JPasswordField();
    formPanel.add(passwordField);

    formPanel.add(new JLabel("Xác nhận MK:"));
    confirmPasswordField = new JPasswordField();
    formPanel.add(confirmPasswordField);

    this.add(formPanel, BorderLayout.CENTER);

    // Tạo panel chứa các nút
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

    JButton registerButton = new JButton("Đăng Ký");
    JButton cancelButton = new JButton("Hủy");

    // Đặt ActionCommand cho các nút
    registerButton.setActionCommand("Đăng Ký");
    cancelButton.setActionCommand("Hủy");

    // Thêm ActionListener vào các nút
    registerButton.addActionListener(ac);
    cancelButton.addActionListener(ac);
    
    buttonPanel.add(registerButton);
    buttonPanel.add(cancelButton);

    this.add(buttonPanel, BorderLayout.SOUTH);

    // Hiển thị giao diện
    this.setVisible(true);
    }

   
    // Getter cho các trường nhập liệu
    public String getUsername() {
        return usernameField.getText();
    }

    public String getEmail() {
        return emailField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public String getConfirmPassword() {
        return new String(confirmPasswordField.getPassword());
    }

    
    public void close() {
        this.dispose();
    }
}
