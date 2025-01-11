package View;
import Controller.BillController;
import Dao.BillDao;
import Dao.DatPhongDao;
import Model.Bill;
import Model.DatPhong;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class BillView extends JPanel{
    JButton dsHoaDon, truyVan;
    JPanel northPanel, centerPanel;
    ArrayList<DatPhong> dps = new ArrayList<>();
    ArrayList<Bill> dsB=new ArrayList<>();
    DatPhongDao daoDP = new DatPhongDao();
    BillDao daoB=new BillDao();
    JScrollPane  tableBill;
    JLabel tenBang;
    ActionListener ac;
    public BillView(){
        northPanel = new JPanel();
        centerPanel = new JPanel(new BorderLayout());
        dsB=daoB.selectAll();
        dps = daoDP.selectAll();
        tableBill=this.bangBill();
        centerPanel.add(tableBill, BorderLayout.CENTER);  
        this.setLayout(new BorderLayout());
        init();
        this.add(northPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        Border blueBorder = BorderFactory.createLineBorder(Color.BLUE, 4);
        this.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 4));
        ac=new BillController(this);
        dsHoaDon.addActionListener(ac);
        truyVan.addActionListener(ac);
        this.setVisible(true);
    }

    public void init(){
        tenBang=new JLabel("Bảng Hoá Đơn:");
       
        dsHoaDon = new JButton("Danh Sách Hoá Đơn");
        truyVan = new JButton("Tra Hoá Đơn");
        // Thêm các nút vào northPanel
        northPanel.add(tenBang);
        northPanel.add(dsHoaDon);
       
        northPanel.add(truyVan);
    }
    public JScrollPane bangBill(){
        String[] columnNames = {"ID_Bill", "ID_DatPhong", "Chi Phí"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        for (Bill x : this.dsB) {
            Object[] rowData = {
                x.getId(), x.getIdDP(), x.getChiPhi()
            };
            tableModel.addRow(rowData); 
        }

        JTable table = new JTable(tableModel);
        JTableHeader header = table.getTableHeader();

        // Thiết kế tiêu đề bảng
        header.setFont(new Font("Verdana", Font.BOLD, 14));
        header.setBackground(new Color(34, 45, 65));
        header.setForeground(new Color(220, 220, 220));
        header.setBorder(BorderFactory.createMatteBorder(1, 1, 2, 1, new Color(255, 165, 0)));  // Viền màu cam
        header.setOpaque(true);

        // Thiết kế bảng
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.setRowHeight(30);  
        table.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        table.setGridColor(new Color(200, 200, 200));  
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(750, 300)); 
        return scrollPane;
    }
    public void updateTableBill(ArrayList<Bill> dsB){
         this.dsB=dsB;
         centerPanel.removeAll();
         centerPanel.revalidate();
         centerPanel.repaint();

    // Tạo bảng mới
    JScrollPane newTable = bangBill();
    centerPanel.add(newTable, BorderLayout.CENTER);
    }
    public JLabel getTenBang() {
        return tenBang;
    }
    public JTable getTableHoaDon() {
         JScrollPane scrollPane = (JScrollPane) centerPanel.getComponent(0);
    return (JTable) scrollPane.getViewport().getView(); 
    }

}
