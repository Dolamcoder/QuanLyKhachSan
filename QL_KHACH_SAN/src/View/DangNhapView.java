package View;

import Controller.DangNhapController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DangNhapView extends JFrame {
   
    private JTextField userTextField;
    private JPasswordField passwordField;
    private ActionListener ac;

    public DangNhapView() {
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel panelDangNhap = new JPanel();
        JLabel dangNhap = new JLabel("Đăng Nhập");
        dangNhap.setFont(new Font("Arial", Font.BOLD, 22));
        dangNhap.setForeground(Color.BLUE);
        panelDangNhap.add(dangNhap);

        JPanel userPanel = new JPanel();
        userPanel.add(new JLabel("User:           "));
        userTextField = new JTextField(15);
        userPanel.add(userTextField);

        JPanel passwordPanel = new JPanel();
        passwordPanel.add(new JLabel("Password: "));
        passwordField = new JPasswordField(15);
        passwordPanel.add(passwordField);

        JPanel buttonPanel = new JPanel();
        JButton xacNhanButton = new JButton("Xac Nhan");
        JButton quenMatKhau = new JButton("Quen mat khau");
        JButton dangKyButton = new JButton("Đang Ky");

        buttonPanel.add(xacNhanButton);
        buttonPanel.add(quenMatKhau);
        buttonPanel.add(dangKyButton);

        ac = new DangNhapController(this);
        xacNhanButton.addActionListener(ac);
        dangKyButton.addActionListener(ac);
       quenMatKhau.addActionListener(ac);
        mainPanel.add(panelDangNhap);
        mainPanel.add(userPanel);
        mainPanel.add(passwordPanel);
        mainPanel.add(buttonPanel);

        this.add(mainPanel);
        this.setVisible(true);
        this.pack();
    }

    public String getUsername() {
        return userTextField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public void close() {
        this.dispose();
    }
}
