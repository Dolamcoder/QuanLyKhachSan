package View;

import Controller.GiaoDienController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GiaoDienView extends JFrame {
    JPanel northPanel;
    JPanel westPanel;
    JPanel centerPanel;
    JButton btnLogout;
    JButton btnExit;
    JButton btnDanhSachPhong;
    JButton btnDanhSachKhachHang;
    JButton btnDatPhong;
    JButton btnHoaDon;
    JButton btnDoanhThu;
    ActionListener ac;

    public GiaoDienView() {
        initUI();
        ac = new GiaoDienController(this);
        btnLogout.addActionListener(ac);
        btnExit.addActionListener(ac);
        btnDanhSachPhong.addActionListener(ac);
        btnDanhSachKhachHang.addActionListener(ac);
        btnDatPhong.addActionListener(ac);
        btnHoaDon.addActionListener(ac);
        btnDoanhThu.addActionListener(ac);
         this.add(northPanel, BorderLayout.NORTH);
        this.add(westPanel, BorderLayout.WEST);
        this.add(centerPanel, BorderLayout.CENTER);

    }

    public void initUI() {
        // Thiết lập JFrame
        this.setTitle("Quản Lý Khách Sạn");
        this.setSize(1000, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // Tạo các panel chính
        northPanel = createNorthPanel();
        westPanel = createWestPanel();
        centerPanel = createCenterPanel();

        // Thêm các panel vào JFrame
       
        this.setVisible(true);
    }

    public JPanel createNorthPanel() {
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.setPreferredSize(new Dimension(1000, 100));
        northPanel.setBackground(new Color(0, 51, 102)); // Màu xanh đậm hiện đại

        JLabel title = new JLabel("QUẢN LÝ KHÁCH SẠN", JLabel.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 36));
        title.setForeground(Color.WHITE);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(0, 51, 102));

        btnLogout = createStyledButton("Đăng Xuất", new Color(255, 140, 0));
        btnExit = createStyledButton("Thoát", new Color(220, 20, 60));
        btnLogout.setPreferredSize(new Dimension(150, 40));
        btnExit.setPreferredSize(new Dimension(100, 40));
        buttonPanel.add(btnLogout);
        buttonPanel.add(btnExit);
       
        northPanel.add(title, BorderLayout.CENTER);
        northPanel.add(buttonPanel, BorderLayout.EAST);

        return northPanel;
    }

    public JPanel createWestPanel() {
        JPanel westPanel = new JPanel();
        westPanel.setPreferredSize(new Dimension(250, 600));
        westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
        westPanel.setBackground(new Color(245, 245, 245));
        westPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 4));

        // Tạo các nút và thêm sự kiện
        btnDanhSachPhong = createStyledButton("Danh Sách Phòng", new Color(255, 160, 122));
        btnDanhSachKhachHang = createStyledButton("Danh Sách Khách Hàng", new Color(255, 127, 80));
        btnDatPhong = createStyledButton("Đặt Phòng", new Color(255, 69, 0));
        btnHoaDon = createStyledButton("Hoá Đơn", new Color(138, 43, 226));
        btnDoanhThu = createStyledButton("Doanh Thu", new Color(70, 130, 180));

        // Thêm các nút vào westPanel
        westPanel.add(createButtonPanel(btnDanhSachPhong));
        westPanel.add(createButtonPanel(btnDanhSachKhachHang));
        westPanel.add(createButtonPanel(btnDatPhong));
        westPanel.add(createButtonPanel(btnHoaDon));
        westPanel.add(createButtonPanel(btnDoanhThu));

        // Gán sự kiện cho các nút
       

        return westPanel;
    }

    private JPanel createButtonPanel(JButton button) {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(245, 245, 245));
        panel.add(button);
        return panel;
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Serif", Font.BOLD, 16));
        button.setPreferredSize(new Dimension(200, 50));
        return button;
    }

    public JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(new Color(240, 248, 255));
        centerPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 4));

        JLabel imageLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon("src/icon/hotel.png"); // Đường dẫn ảnh nền mới
        Image img = imageIcon.getImage().getScaledInstance(742, 452, Image.SCALE_SMOOTH); // Đặt kích thước phù hợp
        imageLabel.setIcon(new ImageIcon(img));
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);

        centerPanel.add(imageLabel, BorderLayout.CENTER);
        return centerPanel;
    }

    public void setCenterPanel(JPanel panel) {
        if (centerPanel != null) {
            this.remove(centerPanel);  // Loại bỏ panel cũ
        }
        centerPanel = panel;
        this.add(centerPanel, BorderLayout.CENTER);  // Thêm panel mới vào trung tâm
        this.revalidate();  // Cập nhật lại giao diện
        this.repaint();  // Vẽ lại giao diện
    }

    public void setNorthPanel(JPanel northPanel) {
        this.northPanel = northPanel;
    }

    public void setWestPanel(JPanel westPanel) {
        this.westPanel = westPanel;
    }

    public JPanel getNorthPanel() {
        return northPanel;
    }

    public JPanel getWestPanel() {
        return westPanel;
    }

    public JPanel getCenterPanel() {
        return centerPanel;
    }

    public void close() {
        this.dispose();
    }
    public static void main(String[] args) {
        new GiaoDienView();
    }
}
