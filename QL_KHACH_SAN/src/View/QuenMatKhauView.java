package View;
import Controller.QuenMatKhauController;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class QuenMatKhauView extends JFrame{
    JPanel  emailPanel, timKiemPanel, taiKhoanPanel, maXacNhanPanel, nutPanel;
    JButton tk, xn, huy;
    JTextField email, maXn;
    JLabel mailLabel, maLabel, user;
    ActionListener ac;
    public QuenMatKhauView() {
         this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
         this.setSize(400, 300);
         emailPanel=new JPanel();
         timKiemPanel=new JPanel();
         taiKhoanPanel=new JPanel();
         maXacNhanPanel=new JPanel();
         nutPanel=new JPanel();
         init();
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         this.setVisible(true);
         this.setLocationRelativeTo(null);
         ac=new QuenMatKhauController(this);
         tk.addActionListener(ac);
         xn.addActionListener(ac);
         huy.addActionListener(ac);
        
         
    }
    public void init(){
        tk=new JButton("Tìm kiếm");
        xn=new JButton("Xác Nhận");
        huy=new JButton("Huỷ");
        email=new JTextField(15);
        maXn=new JTextField(15);
        mailLabel=new JLabel("    Email      :");
        maLabel=new JLabel("Mã xác nhận:");
        user=new JLabel("Tên Tài khoản: ");
        emailPanel.add(mailLabel);
        emailPanel.add(email);
        timKiemPanel.add(tk);
        taiKhoanPanel.add(user);
        maXacNhanPanel.add(maLabel);
        maXacNhanPanel.add(maXn);
        nutPanel.add(xn);
        nutPanel.add(huy);
        this.add(emailPanel);
        this.add(timKiemPanel);
        this.add(taiKhoanPanel);
        this.add(maXacNhanPanel);
        this.add(nutPanel);
        this.pack();
    }

    public JPanel getEmailPanel() {
        return emailPanel;
    }

    public JPanel getTimKiemPanel() {
        return timKiemPanel;
    }

    public JPanel getTaiKhoanPanel() {
        return taiKhoanPanel;
    }

    public JPanel getMaXacNhanPanel() {
        return maXacNhanPanel;
    }

    public JPanel getNutPanel() {
        return nutPanel;
    }

    public JButton getTk() {
        return tk;
    }

    public JButton getXn() {
        return xn;
    }

    public JButton getHuy() {
        return huy;
    }

    public JTextField getEmail() {
        return email;
    }

    public JTextField getMaXn() {
        return maXn;
    }

    public JLabel getMailLabel() {
        return mailLabel;
    }

    public JLabel getMaLabel() {
        return maLabel;
    }

    public JLabel getUser() {
        return user;
    }
    public void close(){
        this.dispose();
    }
    
    }