
package View;

import Controller.DSKHController;
import Controller.DSPController;
import Dao.KhachHangDao;
import Dao.RoomDao;
import Model.KhachHang;
import Model.Room;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class DSKhachHangView extends JPanel{
        JPanel danhMuc = new JPanel(); 
         
        JButton tk, clear, update, sort;
        JPanel bangPanel = new JPanel(); 
        JComboBox<String>  ds;
        ActionListener ac;
        KhachHangDao dao=new KhachHangDao();
        JScrollPane table;
        ArrayList<KhachHang> dsKhachHang=new ArrayList<>();
        public DSKhachHangView() {
        init();
        dsKhachHang=dao.selectAll();
        // Repaint lại panel
        bangPanel.setLayout(new BorderLayout());
        table = displayTable();

        bangPanel.add(table, BorderLayout.CENTER);

        ac = new DSKHController(this);  
        tk.addActionListener(ac);
        ds.setActionCommand("ds");
        ds.addActionListener(ac);
        clear.addActionListener(ac);
        update.addActionListener(ac);
        sort.addActionListener(ac);
        this.setLayout(new BorderLayout());
        this.setSize(742, 452);
        this.setVisible(true);
        this.add(danhMuc, BorderLayout.NORTH);
        this.add(bangPanel, BorderLayout.CENTER);
        Border blueBorder = BorderFactory.createLineBorder(Color.BLUE, 4);
        this.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 4));
    }
            public void init() {
           String danhSach[] = {"Danh Sách", "Khách Đã Trả Phòng", "Khách Chưa Trả Phòng"};
            ds = new JComboBox<>(danhSach);
            sort=new JButton("Sắp Xếp Theo Tên");
            // Tạo các nút
            tk = new JButton("Tìm kiếm Khách Hàng");
            
            clear = new JButton("Xoá Khách Hàng");
            update = new JButton("Sửa Thông Tin");

            Color lightBlue = new Color(173, 216, 230);
        Color darkBlue = new Color(34, 45, 65); 
        Color darkText = new Color(50, 50, 50); 

        tk.setBackground(lightBlue); 
        tk.setForeground(darkBlue); 
        tk.setFocusPainted(false);

        ds.setBackground(lightBlue); 
        ds.setForeground(darkBlue); 
     

        clear.setBackground(lightBlue);
        clear.setForeground(darkBlue);
        clear.setFocusPainted(false);

        update.setBackground(lightBlue);
        update.setForeground(darkBlue);
        update.setFocusPainted(false);
        
        
        sort.setBackground(lightBlue);
        sort.setForeground(darkBlue);
        sort.setFocusPainted(false);
        
        
            danhMuc.add(ds);
            danhMuc.add(sort);
            danhMuc.add(tk);
           
            danhMuc.add(clear);
            danhMuc.add(update);
        }

        public  JScrollPane displayTable() {

        String[] columnNames = {"Name", "ID_KH", "ID_Phong", "CCCD", "Dia_Chi", "SDT", "Trạng Thái"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

     for (KhachHang kh : this.dsKhachHang) {
            Object[] rowData = {
                kh.getName(), kh.getId(), kh.getIdPhong(), kh.getCccd(), kh.getDiaChi(), kh.getSdt(), kh.trangThai()
            };
            tableModel.addRow(rowData);
        }

         JTable table = new JTable(tableModel);

         JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Verdana", Font.BOLD, 16)); 
        header.setBackground(new Color(34, 45, 65)); 
        header.setForeground(new Color(220, 220, 220)); 
        header.setBorder(BorderFactory.createMatteBorder(1, 1, 2, 1, new Color(255, 165, 0))); // Đường viền dưới màu cam nhạt
        header.setOpaque(true); 
        
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.setRowHeight(25); 
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                            boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (row % 2 == 0) {
                    c.setBackground(new Color(230, 240, 255)); 
                } else {
                    c.setBackground(Color.WHITE);
                }
                if (isSelected) {
                    c.setBackground(new Color(255, 220, 220)); 
                    c.setFont(new Font("Arial", Font.BOLD, 14)); 
                }

                return c;
            }
        });

        
        table.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        table.setGridColor(Color.GRAY);
        JScrollPane scrollPane = new JScrollPane(table);
        return scrollPane;
    }

        public void setDsPhong(ArrayList<KhachHang> dsKhachHang) {
            this.dsKhachHang = dsKhachHang;
        }

    public ArrayList<KhachHang> getDsPhong() {
        return dsKhachHang;
    }

public void updateTable(ArrayList<KhachHang> khs) {
    this.dsKhachHang = khs; 
     bangPanel.removeAll();
    bangPanel.revalidate();
    bangPanel.repaint();

    JScrollPane newTable = displayTable();
    bangPanel.add(newTable, BorderLayout.CENTER);
}

    public JComboBox<String> getDs() {
        return ds;
    }

    public JTable getTable() {
         JScrollPane scrollPane = (JScrollPane) bangPanel.getComponent(0); 
    return (JTable) scrollPane.getViewport().getView(); 
    }
}
