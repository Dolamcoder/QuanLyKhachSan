/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;
import Controller.ThemPhongController;
import Dao.RoomDao;
import Model.Room;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
/**
 *
 * @author Admin
 */
public class ThemPhongView extends JFrame{
      JTextField idField , bedsField, floorField, priceField ;
      
       ActionListener ac;
       JButton confirmButton, cancelButton;
      RoomDao dao=new RoomDao();
     public ThemPhongView() {
         ac=new ThemPhongController(this);
        // Tạo JFrame chính
     
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 300);

        // Sử dụng BorderLayout
        this.setLayout(new BorderLayout(10, 10)); // Khoảng cách giữa các vùng

        // Panel chính chứa các trường nhập liệu
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout()); // Sử dụng GridBagLayout để bố trí linh hoạt
        inputPanel.setBackground(new Color(240, 248, 255)); // Màu nền nhạt

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Khoảng cách giữa các thành phần
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Các nhãn và trường nhập liệu
        JLabel idLabel = new JLabel("ID:");
        idField = new JTextField(15);
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
        inputPanel.add(idField, gbc);

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
        confirmButton.addActionListener(ac);
        confirmButton.setBackground(new Color(144, 238, 144)); // Màu xanh nhạt
        confirmButton.setFocusPainted(false);
        cancelButton = new JButton("Hủy");
        cancelButton.addActionListener(ac);
        cancelButton.setBackground(new Color(255, 182, 193)); // Màu hồng nhạt
        cancelButton.setFocusPainted(false);

        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        // Thêm viền và tiêu đề cho inputPanel
        inputPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(70, 130, 180)), "Thông tin phòng", 0, 0, new Font("Arial", Font.BOLD, 14), new Color(70, 130, 180)));

        // Thêm các thành phần vào JFrame
       this.add(inputPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

        // Hiển thị JFrame
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
    public boolean checkId(String id){
        for(String x : dao.getAllID()){
            if(x.equals(id)){
                return true;
            }
        }
        return false;
    }
    public Room themPhong() {
        String idPhong = this.idField.getText().trim(); // Xóa khoảng trắng thừa
        int bed = 0;
        int floor = 0;
        double price = 0.0;

    // Kiểm tra ID phòng
        if (idPhong.isEmpty()) {
                 JOptionPane.showMessageDialog(this, "Không được để trống ID phòng");
                return null;
             }
        if(checkId(idPhong)){
            JOptionPane.showMessageDialog(this, "Mã phòng đã tồn tại");
            return null;
        }
            try {
        // Chuyển đổi các giá trị từ chuỗi sang số
                bed = Integer.parseInt(this.bedsField.getText().trim());
                floor = Integer.parseInt(this.floorField.getText().trim());
                price = Double.parseDouble(this.priceField.getText().trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng số cho Bed, Floor, và Price");
                 return null;
            }

    // Tạo đối tượng Room khi không có lỗi
            Room room = new Room(idPhong, bed, floor, price);
            return room;
        }
   public void close() {
    this.dispose(); // Đóng frame hiện tại
    }
}


