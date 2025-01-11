package view;

import Controller.ThemDatPhongController;
import Dao.KhachHangDao;
import Dao.RoomDao;
import Model.KhachHang;
import Model.QuanLy;
import Model.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ThemDatPhongView extends JFrame {
    JPanel mainPanel, topPanel, middlePanel, bottomPanel;
    public JTextArea textAreaPhong, textAreaKhachHang;
    JButton themButton, huyButton;
    JComboBox<String> phongComboBox, khachHangComboBox;
    RoomDao daoR = new RoomDao();
    KhachHangDao daoKH = new KhachHangDao();
    ActionListener ac;
    JTextField thoiGian=new JTextField(5);
    JLabel tLabel=new JLabel("Thời Gian Thuê: ");
    public ThemDatPhongView() {
        // Tạo JFrame
        ac = new ThemDatPhongController(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 400);
        this.setLocationRelativeTo(null); 
         textAreaPhong = new JTextArea();
        init(); 
        this.add(mainPanel);
        this.setVisible(true);
        themButton.addActionListener(ac);
        huyButton.addActionListener(ac);
        phongComboBox.setActionCommand("phong");
        phongComboBox.addActionListener(ac);
        this.khachHangComboBox.setActionCommand("KH");
        this.khachHangComboBox.addActionListener(ac);
    }

    public void init() {
        // Tạo panel chính
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(new Color(200, 220, 240)); // Màu nền đậm hơn

        // Panel trên chứa hai JComboBox
        topPanel = new JPanel(new GridLayout(1, 4, 10, 0));
        topPanel.setBackground(new Color(200, 220, 240)); // Màu nền đồng bộ
        JLabel phongLabel = new JLabel("Phòng:");
        phongComboBox = new JComboBox<>();
        for (Room x : daoR.selectAll()) {
            if (!x.isStatus()) {
                phongComboBox.addItem(x.getIdPhong());
            }
        }
        JLabel khachHangLabel = new JLabel("Khách hàng:");
        khachHangComboBox = new JComboBox<>();
        for (KhachHang kh : daoKH.selectAll()) {
            if (kh.isStatus()) {
                khachHangComboBox.addItem(kh.getId());
            }
        }
        khachHangComboBox.addItem("New");
        phongLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        khachHangLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        topPanel.add(phongLabel);
        topPanel.add(phongComboBox);
        topPanel.add(khachHangLabel);
        topPanel.add(khachHangComboBox);

        // Panel giữa chứa hai JTextArea (Thay JList bằng JTextArea)
        middlePanel = new JPanel(new GridLayout(1, 2, 10, 0));
        middlePanel.setBackground(new Color(200, 220, 240)); // Màu nền đồng bộ

        // Tạo JTextArea với định dạng màu sắc và kiểu chữ
        textAreaPhong.setFont(new Font("Arial", Font.PLAIN, 20)); // Kiểu chữ
        textAreaPhong.setForeground(Color.BLUE); // Màu chữ
        textAreaPhong.setBackground(Color.YELLOW); // Màu nền
        textAreaPhong.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        textAreaKhachHang = new JTextArea();
        textAreaKhachHang.setFont(new Font("Arial", Font.PLAIN, 20)); // Kiểu chữ
        textAreaKhachHang.setForeground(Color.BLUE); // Màu chữ
        textAreaKhachHang.setBackground(Color.YELLOW); // Màu nền
        textAreaKhachHang.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        middlePanel.add(new JScrollPane(textAreaPhong)); // Thêm JScrollPane nếu cần cuộn
        middlePanel.add(new JScrollPane(textAreaKhachHang));

        // Panel dưới chứa hai JButton
        bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bottomPanel.setBackground(new Color(200, 220, 240)); // Màu nền đồng bộ
        themButton = new JButton("Thêm");
        huyButton = new JButton("Hủy");

        // Trang trí nút bấm
        themButton.setBackground(new Color(0, 153, 76));
        themButton.setForeground(Color.WHITE);
        themButton.setFocusPainted(false);
        
        huyButton.setBackground(new Color(204, 0, 0));
        huyButton.setForeground(Color.WHITE);
        huyButton.setFocusPainted(false);

        bottomPanel.add(themButton);
        bottomPanel.add(huyButton);
        bottomPanel.add(this.tLabel);
        bottomPanel.add(this.thoiGian);
        // Thêm các panel vào frame
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(middlePanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
    }

    public void close() {
        this.dispose();
    }

    public JTextArea getTextAreaPhong() {
        return textAreaPhong;
    }

    public JTextArea getTextAreaKhachHang() {
        return textAreaKhachHang;
    }

    public JComboBox<String> getPhongComboBox() {
        return phongComboBox;
    }

    public JComboBox<String> getKhachHangComboBox() {
        return khachHangComboBox;
    }
    public void themPhong(Room room){
        this.textAreaPhong.setText(room.toString());
    }
    public void themKhachHang(KhachHang a){
        this.textAreaKhachHang.setText(a.toString());
    }
    public int getThoiGian(){
        String text=this.thoiGian.getText().trim();
        int t=0;
        try{
            t=Integer.parseInt(text);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, "Thời gian thuê phải là số");
        }
        return t;
    }
}
