package View;

import Controller.DatPhongController;
import Dao.DatPhongDao;
import Model.DatPhong;
import Model.KhachHang;
import java.awt.*;
import java.awt.event.*;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class DatPhongView extends JPanel {
    JComboBox<String> sort;
    JButton tp, add, ds;
    JTextField tk;
    JPanel danhMucPanel, bangPanel;
    JScrollPane tableScrollPane;
    DatPhongDao dao = new DatPhongDao();
    ArrayList<DatPhong> dps=new ArrayList<>();
    ActionListener ac;
    ArrayList<DatPhong> originalDps;
    public DatPhongView() {
        this.setLayout(new BorderLayout());
        ac=new DatPhongController(this);
        danhMuc();
        dps=dao.selectAll();
        tableScrollPane = displayTable();
        bangPanel = new JPanel(new BorderLayout());
        bangPanel.add(tableScrollPane, BorderLayout.CENTER);
        this.add(danhMucPanel, BorderLayout.NORTH);
        this.add(bangPanel, BorderLayout.CENTER);
        Border blueBorder = BorderFactory.createLineBorder(Color.BLUE, 4);
        this.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 4));
        sort.setActionCommand("Sort");
        sort.addActionListener(ac);
        tp.addActionListener(ac);
        add.addActionListener(ac);
        ds.addActionListener(ac);
        originalDps = new ArrayList<>(dps);
        tk.getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
           
            searchTable();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
         
            searchTable();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            
            searchTable();
        }
    });
    }
    public void danhMuc() {
        danhMucPanel = new JPanel();
        danhMucPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 20)); 
        danhMucPanel.setBackground(new Color(173, 216, 230)); 

        ds = new JButton("Danh Sách");
        tp = new JButton("Trả Phòng");
        add = new JButton("Thêm Đặt Phòng");
        tk = new JTextField(10);
        String sapXep[] = {"Tên khách hàng", "Thời gian thuê", "Giá Thuê", "Ngày đặt"};
        sort = new JComboBox<>(sapXep);

       
        styleButton(ds, new Color(220, 20, 60)); 
        styleButton(tp, new Color(220, 20, 60));
        styleButton(add, new Color(220, 20, 60)); 

      
        tk.setPreferredSize(new Dimension(200, 30));
        tk.setFont(new Font("Arial", Font.PLAIN, 14));
        tk.setBackground(Color.WHITE); 
        tk.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1)); 
        sort.setFont(new Font("Arial", Font.PLAIN, 14));
        sort.setBackground(new Color(240, 240, 240));
        danhMucPanel.add(ds);
        danhMucPanel.add(sort);
        danhMucPanel.add(add);
        danhMucPanel.add(tp);
        danhMucPanel.add(tk);
    }
    public void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(120, 40));
        button.setBorder(BorderFactory.createLineBorder(new Color(40, 60, 90), 2));
        button.setOpaque(true);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(color.darker()); 
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(color); 
            }
        });
    }

    public JScrollPane displayTable() {
        String[] columnNames = {"ID","ID_Phong", "ID_KH", "Name", "Price", "Ngày đặt", "Ngày Trả", "Thời gian thuê"};

        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        for (DatPhong dp : this.dps) {
            Object[] rowData = {
                dp.getIdDP(),
                dp.getIdPhong(),
                dp.getIdKH(),
                dp.getName(),
                dp.getGiaThue(),
                sdf.format(dp.getNgayDat()),
                sdf.format(dp.getNgayTra()),
                dp.getThoiGianThue()
            };
            tableModel.addRow(rowData);
        }

        JTable table = new JTable(tableModel);
        JTableHeader header = table.getTableHeader();

        // Header styling
        header.setFont(new Font("Verdana", Font.BOLD, 14));
        header.setBackground(new Color(34, 45, 65));
        header.setForeground(new Color(220, 220, 220));
        header.setBorder(BorderFactory.createMatteBorder(1, 1, 2, 1, new Color(255, 165, 0))); 
        header.setOpaque(true);

        // Table styling
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.setRowHeight(30); 
        table.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        table.setGridColor(new Color(200, 200, 200));

        // Center cell content
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Alternate row colors
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (row % 2 == 0) {
                    c.setBackground(new Color(240, 240, 250)); 
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

        return new JScrollPane(table);
    }
    public void updateTable(ArrayList<DatPhong> dps) {
    this.dps= dps; 

    bangPanel.removeAll();
    bangPanel.revalidate();
    bangPanel.repaint();

    // Tạo bảng mới
    JScrollPane newTable = displayTable();
    bangPanel.add(newTable, BorderLayout.CENTER);
}

    public JComboBox<String> getSort() {
        return sort;
    }
     public JTable getTable() {
         JScrollPane scrollPane = (JScrollPane) bangPanel.getComponent(0); 
    return (JTable) scrollPane.getViewport().getView();
    }
  private void searchTable() {
    String keyword = tk.getText().trim().toLowerCase();
    if (keyword.isEmpty()) {
        updateTable(originalDps);
        return;
    }
    ArrayList<DatPhong> filteredList = new ArrayList<>();
    for (DatPhong dp : originalDps) { 
        if (dp.getName().toLowerCase().contains(keyword) || 
            dp.getIdKH().toLowerCase().contains(keyword) || 
            dp.getIdPhong().toLowerCase().contains(keyword)) {
            filteredList.add(dp);
        }
    }
    updateTable(filteredList);
}

}
