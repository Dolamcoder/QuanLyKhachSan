package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TruyVanHoaDonView extends JFrame {
        JLabel lblMaDatPhong ;
        JLabel lblMaHoaDon;
        JLabel lblMaKhachHang;
        JLabel lblMaPhong;
        JLabel lblNgayDat;
        JLabel lblNgayTra;
        JLabel lblGiaThue;
        JLabel lblTongChiPhi;
        JButton huy;
    public TruyVanHoaDonView() { 
        // Tạo khung chính
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(350, 350);
        this.setLocationRelativeTo(null);
        
        // Tạo panel chính
        JPanel panel = new JPanel();
        panel.setBackground(new Color(240, 248, 255));  // Màu nền nhạt
        panel.setBorder(BorderFactory.createLineBorder(new Color(173, 216, 230), 2));
        panel.setLayout(new BorderLayout());

        // Tên khách hàng (in đậm, phóng to)
        JLabel lblTenKhachHang = new JLabel("Tên Khách Hàng", SwingConstants.CENTER);
        lblTenKhachHang.setFont(new Font("Arial", Font.BOLD, 20));
        lblTenKhachHang.setForeground(new Color(0, 102, 204));  // Màu xanh dương đậm
        lblTenKhachHang.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        panel.add(lblTenKhachHang, BorderLayout.NORTH);

        // Tạo panel chứa thông tin
        JPanel infoPanel = new JPanel(new GridLayout(5, 2, 20, 10));
        infoPanel.setBackground(new Color(240, 248, 255));

        // Các label thông tin
        lblMaDatPhong = new JLabel("Mã Đặt Phòng:");
        lblMaHoaDon = new JLabel("Mã Hoá Đơn:");
        lblMaKhachHang = new JLabel("Mã Khách Hàng:");
        lblMaPhong = new JLabel("Mã Phòng:");
        lblNgayDat = new JLabel("Ngày Đặt:");
        lblNgayTra = new JLabel("Ngày Trả:");
        lblGiaThue = new JLabel("Giá Thuê:");
        lblTongChiPhi = new JLabel("Tổng Chi Phí:");

        // Cài đặt font và màu sắc cho label
        JLabel[] labels = {lblMaDatPhong, lblMaHoaDon, lblMaKhachHang, lblMaPhong,
                           lblNgayDat, lblNgayTra, lblGiaThue, lblTongChiPhi};

        for (JLabel label : labels) {
            label.setFont(new Font("Arial", Font.PLAIN, 14));
            label.setForeground(new Color(0, 0, 128));  // Màu xanh navy
        }
        huy=new JButton("Huỷ");
        // Thêm label vào panel thông tin
        infoPanel.add(lblMaDatPhong);
        infoPanel.add(lblMaHoaDon);
        infoPanel.add(lblMaKhachHang);
        infoPanel.add(lblMaPhong);
        infoPanel.add(lblNgayDat);
        infoPanel.add(lblNgayTra);
        infoPanel.add(lblGiaThue);
        infoPanel.add(lblTongChiPhi);
        infoPanel.add(huy);
        panel.add(infoPanel, BorderLayout.CENTER);

        // Thêm panel vào frame
        this.add(panel);
        this.setVisible(true);
         huy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               dispose();
            }
        });
    }

    public JLabel getLblMaDatPhong() {
        return lblMaDatPhong;
    }

    public JLabel getLblMaHoaDon() {
        return lblMaHoaDon;
    }

    public JLabel getLblMaKhachHang() {
        return lblMaKhachHang;
    }

    public JLabel getLblMaPhong() {
        return lblMaPhong;
    }

    public JLabel getLblNgayDat() {
        return lblNgayDat;
    }

    public JLabel getLblNgayTra() {
        return lblNgayTra;
    }

    public JLabel getLblGiaThue() {
        return lblGiaThue;
    }

    public JLabel getLblTongChiPhi() {
        return lblTongChiPhi;
    }
    
}
