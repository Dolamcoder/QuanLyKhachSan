package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class DoanhThuView extends JFrame {
    private JLabel title, doanhThu, soLuong;
    private JButton huy;

    public DoanhThuView() {
        // Cấu hình JFrame
        this.setTitle("Thống Kê Doanh Thu");
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

      
        this.getContentPane().setBackground(new Color(173, 216, 230));

     
        title = new JLabel("Thống Kê:");
        title.setFont(new Font("Arial", Font.BOLD, 25)); 
        title.setForeground(Color.RED); 

        doanhThu = new JLabel("Doanh Thu:");
        doanhThu.setFont(new Font("Arial", Font.BOLD, 16)); 
        doanhThu.setForeground(Color.BLACK); // Màu đen

        soLuong = new JLabel("Số Lượng Đặt Phòng:");
        soLuong.setFont(new Font("Arial", Font.BOLD, 16)); 
        soLuong.setForeground(Color.BLACK); // Màu đen

        huy = new JButton("Hủy");

       
        this.add(title);
        this.add(doanhThu);
        this.add(soLuong);
        this.add(huy);

     
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 0, SpringLayout.HORIZONTAL_CENTER, this.getContentPane());
        layout.putConstraint(SpringLayout.NORTH, title, 20, SpringLayout.NORTH, this.getContentPane());

        layout.putConstraint(SpringLayout.WEST, doanhThu, 20, SpringLayout.WEST, this.getContentPane());
        layout.putConstraint(SpringLayout.NORTH, doanhThu, 20, SpringLayout.SOUTH, title);

        layout.putConstraint(SpringLayout.WEST, soLuong, 20, SpringLayout.WEST, this.getContentPane());
        layout.putConstraint(SpringLayout.NORTH, soLuong, 20, SpringLayout.SOUTH, doanhThu);

        
        layout.putConstraint(SpringLayout.WEST, huy, 10, SpringLayout.WEST, this.getContentPane());
        layout.putConstraint(SpringLayout.SOUTH, huy, -10, SpringLayout.SOUTH, this.getContentPane());
        huy.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dispose();
            }
        });
       
        this.setVisible(true);
    }

    public JLabel getTieuDe() {
        return title;
    }

    public JLabel getDoanhThu() {
        return doanhThu;
    }

    public JLabel getSoLuong() {
        return soLuong;
    }

    public JButton getHuy() {
        return huy;
    }
    
    
}
