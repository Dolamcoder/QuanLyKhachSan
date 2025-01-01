package View;

import Controller.SuaPhongController;
import Dao.RoomDao;
import Model.Room;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class SuaPhongView extends JFrame {
    private JComboBox<String> idComboBox;
    private JTextField bedsField, floorField, priceField;
    private JButton confirmButton, cancelButton;
    private RoomDao dao;
    private ArrayList<String> roomId;
    ActionListener ac;
    public SuaPhongView() {
        // Khởi tạo RoomDao
        dao = new RoomDao();
        roomId = dao.getAllID();
        ac=new SuaPhongController(this);
        init();
        confirmButton.addActionListener(ac);
        cancelButton.addActionListener(ac);
    }

   private void init() {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(400, 300);
    this.setLayout(new BorderLayout(10, 10)); // Khoảng cách giữa các vùng

    // Panel chính chứa các trường nhập liệu
    JPanel inputPanel = new JPanel();
    inputPanel.setLayout(new GridBagLayout());
    inputPanel.setBackground(new Color(240, 248, 255)); // Màu nền nhạt

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5); // Khoảng cách giữa các thành phần
    gbc.fill = GridBagConstraints.HORIZONTAL;

    // Các nhãn và trường nhập liệu
    JLabel idLabel = new JLabel("ID:");
    idComboBox = new JComboBox<>();
    if (roomId != null && !roomId.isEmpty()) {
        for (String id : roomId) {
            idComboBox.addItem(id);
        }
    } else {
        idComboBox.addItem("Không có dữ liệu");
    }

    JLabel bedsLabel = new JLabel("Beds:");
    bedsField = new JTextField(15);
    JLabel floorLabel = new JLabel("Floor:");
    floorField = new JTextField(15);
    JLabel priceLabel = new JLabel("Price:");
    priceField = new JTextField(15);

    // Thêm các thành phần vào inputPanel
    gbc.gridx = 0;
    gbc.gridy = 0;
    inputPanel.add(idLabel, gbc);
    gbc.gridx = 1;
    inputPanel.add(idComboBox, gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    inputPanel.add(bedsLabel, gbc);
    gbc.gridx = 1;
    inputPanel.add(bedsField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 2;
    inputPanel.add(floorLabel, gbc);
    gbc.gridx = 1;
    inputPanel.add(floorField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 3;
    inputPanel.add(priceLabel, gbc);
    gbc.gridx = 1;
    inputPanel.add(priceField, gbc);

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
            "Sửa Thông tin phòng",
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

    // Getter cho các thành phần giao diện
    public JComboBox<String> getIdComboBox() {
        return idComboBox;
    }

    public JTextField getBedsField() {
        return bedsField;
    }

    public JTextField getFloorField() {
        return floorField;
    }

    public JTextField getPriceField() {
        return priceField;
    }
    public int getBed(){
        return Integer.parseInt(bedsField.getText());
    }
    public int getFloor(){
        return Integer.parseInt(floorField.getText());
    }
    public double getPrice(){
        return Double.parseDouble(priceField.getText());
    }
    public void close() {
        this.dispose();
    }
    public String getID(){
        return (String)idComboBox.getSelectedItem();
    }
    
}
