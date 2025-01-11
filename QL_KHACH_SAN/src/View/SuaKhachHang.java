package View;

//import Controller.SuaKhachHangController;
import Controller.SuaKhachHangController;
import Dao.KhachHangDao;
import Model.KhachHang;
import Model.QuanLy;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class SuaKhachHang extends JFrame {
    private JComboBox<String> idComboBox;
    private JTextField nameField, cccdField, addressField, phoneField;
    private JButton confirmButton, cancelButton;
    private KhachHangDao dao;
    private ArrayList<String> customerIds;
    QuanLy ql=new QuanLy();
    ActionListener ac;

    public SuaKhachHang() {
    // Khởi tạo KhachHangDao
    dao = new KhachHangDao();
    idComboBox = new JComboBox<>();
    ql.setDanhSachKH(dao.selectAll());
    customerIds = dao.getAllID();
    ac = new SuaKhachHangController(this);
    init();
    confirmButton.addActionListener(ac);
    cancelButton.addActionListener(ac);

  
    idComboBox.addActionListener(e -> suaKhachHang()); 
    suaKhachHang();  
}


    private void init() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 350);
        this.setLayout(new BorderLayout(10, 10)); 

        // Panel chính chứa các trường nhập liệu
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        inputPanel.setBackground(new Color(240, 248, 255)); 

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); 
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Các nhãn và trường nhập liệu
        JLabel idLabel = new JLabel("ID Khách Hàng:");
        if (customerIds != null && !customerIds.isEmpty()) {
            for (String id : customerIds) {
                idComboBox.addItem(id);
            }
        } else {
            idComboBox.addItem("Không có dữ liệu");
        }

        JLabel nameLabel = new JLabel("Họ Tên:");
        nameField = new JTextField(15);

        JLabel cccdLabel = new JLabel("CCCD:");
        cccdField = new JTextField(15);

        JLabel addressLabel = new JLabel("Địa Chỉ:");
        addressField = new JTextField(15);

        JLabel phoneLabel = new JLabel("Số Điện Thoại:");
        phoneField = new JTextField(15);

        // Thêm các thành phần vào inputPanel
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(idLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(idComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(cccdLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(cccdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(addressLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(addressField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        inputPanel.add(phoneLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(phoneField, gbc);

        // Panel chứa các nút
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(new Color(230, 230, 250)); // Màu nền nhạt

        confirmButton = new JButton("Xác nhận");
        confirmButton.setBackground(new Color(144, 238, 144));
        confirmButton.setFocusPainted(false);
        confirmButton.setActionCommand("Xác nhận"); // Set action command

        cancelButton = new JButton("Hủy");
        cancelButton.setBackground(new Color(255, 182, 193));
        cancelButton.setFocusPainted(false);
        cancelButton.setActionCommand("Huỷ"); // Set action command

        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        // Thêm viền và tiêu đề cho inputPanel
        inputPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(70, 130, 180)),
                "Sửa Thông Tin Khách Hàng",
                0, 0,
                new Font("Arial", Font.BOLD, 14),
                new Color(70, 130, 180)
        ));

        // Thêm các thành phần vào JFrame
        this.add(inputPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

        // Hiển thị JFrame
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

   
    public void setNameField(String name) {
        this.nameField .setText(name);
    }

    public void setCccdField(String cccd) {
        this.cccdField.setText(cccd);
    }

    public void setAddressField(String diaChi) {
        this.addressField.setText(diaChi);
    }

    public void setPhoneField(String sdt) {
        this.phoneField.setText(sdt);
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getCccdField() {
        return cccdField;
    }

    public JTextField getAddressField() {
        return addressField;
    }

    public JTextField getPhoneField() {
        return phoneField;
    }

    public void close() {
        this.dispose();
    }

    public String getSelectedID() {
        return (String) idComboBox.getSelectedItem();
    }
    public void suaKhachHang() {
      String id = this.getSelectedID();
    if (id == null) {
        JOptionPane.showMessageDialog(this, "Không có khách hàng nào được chọn.");
        return;
    }
    KhachHang a = ql.timKiemKhachHang(id);
    if (a == null) {
        JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng với ID: " + id);
        return;
    }
    this.setNameField(a.getName());
    this.setCccdField(a.getCccd());
    this.setAddressField(a.getDiaChi());
    this.setPhoneField(a.getSdt());
}
}
