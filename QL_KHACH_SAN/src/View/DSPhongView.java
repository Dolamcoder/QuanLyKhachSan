    package View;

    import Controller.DSPController;
    import Dao.RoomDao;
    import Model.Room;
    import java.awt.*;
    import java.awt.event.ActionListener;
    import java.util.ArrayList;
    import javax.swing.*;
    import javax.swing.border.Border;
    import javax.swing.table.DefaultTableCellRenderer;
    import javax.swing.table.DefaultTableModel;
    import javax.swing.table.JTableHeader;

    public class DSPhongView extends JPanel {
        
        JPanel danhMuc = new JPanel(); 
        JComboBox<String> ds, dsSort; 
        JButton tk, add, clear, update; 
        JPanel bangPanel = new JPanel(); 
        ActionListener ac;
        RoomDao dao=new RoomDao();
        JScrollPane table;
        ArrayList<Room> dsPhong=new ArrayList<>();
        public DSPhongView() {
        init();
        dsPhong=dao.selectAll();
        // Repaint lại panel
        bangPanel.setLayout(new BorderLayout());
        table = displayTable();

        bangPanel.add(table, BorderLayout.CENTER);

        ac = new DSPController(this); 

       
        tk.addActionListener(ac);
        add.addActionListener(ac);
        clear.addActionListener(ac);
        update.addActionListener(ac);
        ds.setActionCommand("ds");
        dsSort.setActionCommand("sort");
        ds.addActionListener(ac);
        dsSort.addActionListener(ac);
        this.setLayout(new BorderLayout());
        this.setSize(742, 452);
        this.setVisible(true);
        this.add(danhMuc, BorderLayout.NORTH);
        this.add(bangPanel, BorderLayout.CENTER);
        Border blueBorder = BorderFactory.createLineBorder(Color.BLUE, 4);
        this.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 4));
    }
        public void init() {
            String danhSach[] = {"Danh Sách", "Phòng Chưa Đặt", "Phòng Đã Đặt"};
            ds = new JComboBox<>(danhSach);
            String sort[] = {"Sort theo giá", "Sort theo giường", "Sort theo tầng"};
            dsSort = new JComboBox<>(sort);
            tk = new JButton("Tìm kiếm phòng");
            add = new JButton("Thêm phòng");
            clear = new JButton("Xoá phòng");
            update = new JButton("Sửa Thông tin");

            Color lightBlue = new Color(173, 216, 230); 
        Color darkBlue = new Color(34, 45, 65); 
        Color darkText = new Color(50, 50, 50); 

        tk.setBackground(lightBlue); 
        tk.setForeground(darkBlue); 
        tk.setFocusPainted(false);

        add.setBackground(lightBlue);
        add.setForeground(darkBlue);
        add.setFocusPainted(false);

        clear.setBackground(lightBlue);
        clear.setForeground(darkBlue);
        clear.setFocusPainted(false);

        update.setBackground(lightBlue);
        update.setForeground(darkBlue);
        update.setFocusPainted(false);
        dsSort.setBackground(lightBlue);
        dsSort.setForeground(darkText);
        ds.setBackground(lightBlue);
        ds.setForeground(darkText);

           
            danhMuc.add(ds);
            danhMuc.add(dsSort);
            danhMuc.add(tk);
            danhMuc.add(add);
            danhMuc.add(clear);
            danhMuc.add(update);
        }
    public  JScrollPane displayTable() {

        String[] columnNames = {"ID_Phong", "Bed", "Floor", "Status", "Price"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

       
        for (Room room : this.dsPhong) {
            Object[] rowData = {
                room.getIdPhong(),
                room.getBed(),
                room.getFloor(),
                room.isStatus() ? "Sẵn Sàng" : "Chưa Sẵn Sàng",
                room.getPrice()
            };
            tableModel.addRow(rowData);
        }

        JTable table = new JTable(tableModel);

       
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Verdana", Font.BOLD, 18)); // Phông chữ Verdana, in đậm, kích thước lớn hơn
        header.setBackground(new Color(34, 45, 65)); // Màu nền xanh đậm hiện đại
        header.setForeground(new Color(220, 220, 220)); // Màu chữ xám nhạt, tạo độ tương phản tốt
        header.setBorder(BorderFactory.createMatteBorder(1, 1, 2, 1, new Color(255, 165, 0))); // Đường viền dưới màu cam nhạt
        header.setOpaque(true); // Bật chế độ vẽ màu nền tùy chỉnh
        // Thay đổi phông chữ và chiều cao dòng
        table.setFont(new Font("Arial", Font.PLAIN, 14)); // Phông chữ nội dung
        table.setRowHeight(25); // Tăng chiều cao dòng

        // Căn giữa nội dung trong bảng
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Thêm màu nền xen kẽ các hàng
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                            boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Màu nền xen kẽ
                if (row % 2 == 0) {
                    c.setBackground(new Color(230, 240, 255)); // Màu xanh nhạt
                } else {
                    c.setBackground(Color.WHITE);
                }

                // Màu khi hàng được chọn
                if (isSelected) {
                    c.setBackground(new Color(255, 220, 220)); // Màu đỏ nhạt khi chọn
                    c.setFont(new Font("Arial", Font.BOLD, 14)); // Phông chữ đậm khi chọn
                }

                return c;
            }
        });

        // Thêm đường viền cho bảng
        table.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        table.setGridColor(Color.GRAY);

        // Đặt bảng vào JScrollPane để thêm tính năng cuộn
        JScrollPane scrollPane = new JScrollPane(table);
        return scrollPane;
    }

        public void setDsPhong(ArrayList<Room> dsPhong) {
            this.dsPhong = dsPhong;
        }

        public JComboBox<String> getDs() {
            return ds;
        }

        public JComboBox<String> getDsSort() {
            return dsSort;
        }

    public ArrayList<Room> getDsPhong() {
        return dsPhong;
    }

public void updateTable(ArrayList<Room> rooms) {
    this.dsPhong = rooms; // Cập nhật danh sách phòng mới

    // Xóa bảng cũ trước khi thêm bảng mới
    bangPanel.removeAll();
    bangPanel.revalidate();
    bangPanel.repaint();

    // Tạo bảng mới
    JScrollPane newTable = displayTable();
    bangPanel.add(newTable, BorderLayout.CENTER);
}

    public JTable getTable() {
         JScrollPane scrollPane = (JScrollPane) bangPanel.getComponent(0); 
    return (JTable) scrollPane.getViewport().getView(); 
    }
    
    }
