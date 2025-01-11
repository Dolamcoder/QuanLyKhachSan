/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.KhachHangDao;
import Model.KhachHang;
import Model.QuanLy;
import View.DSKhachHangView;
import View.SuaKhachHang;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author Admin
 */
public class DSKHController implements ActionListener {
    DSKhachHangView dskh;
    KhachHangDao dao;
    QuanLy ql=new QuanLy();
    public DSKHController(DSKhachHangView dskh){
        this.dskh=dskh;
        dao=new KhachHangDao();
        ql.setDanhSachKH(dao.selectAll());
    }
   @Override
public void actionPerformed(ActionEvent e) {
    String src = e.getActionCommand();
    if (src.equals("ds")) {
        String select = (String) dskh.getDs().getSelectedItem();
        if (select.equals("Danh Sách")) {
            dskh.updateTable(dao.selectAll()); 
        } else if (select.equals("Khách Đã Trả Phòng")) {
            ArrayList<KhachHang> traPhongList = danhSachTraPhong();
            if (traPhongList.isEmpty()) {
                JOptionPane.showMessageDialog(dskh, "Chưa có khách hàng nào trả phòng");
            }
            dskh.updateTable(traPhongList); 
        }
        else {
            if(danhSachChuaTraPhong().isEmpty()){
                JOptionPane.showMessageDialog(dskh, "Tất cả các khách hàng đều đã trả phòng");
            }
            else dskh.updateTable(danhSachChuaTraPhong());
        }
    }
    else if(src.equals("Sắp Xếp Theo Tên")){
        sapXep();
    }
    else if(src.equals("Tìm kiếm Khách Hàng")){
       showSearchField();
    }
    else if(src.equals("Xoá Khách Hàng")){
        xoa();
    }
    else if(src.equals("Sửa Thông Tin")){
       SuaKhachHang skh = new SuaKhachHang();
        skh.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosed(java.awt.event.WindowEvent e) {
            dskh.updateTable(dao.selectAll()); 
        }
    });
       skh.setVisible(true);
    }
}

public ArrayList<KhachHang> danhSachTraPhong() {
    ArrayList<KhachHang> dstraPhong = new ArrayList<>();
    for (KhachHang kh : dao.selectAll()) {
        if (kh.isStatus()) { 
            dstraPhong.add(kh);
        }
    }
    return dstraPhong;
}
public ArrayList<KhachHang> danhSachChuaTraPhong() {
    ArrayList<KhachHang> dschuatraPhong = new ArrayList<>();
    for (KhachHang kh : dao.selectAll()) {
        if (!kh.isStatus()) { 
            dschuatraPhong.add(kh);
        }
    }
    return dschuatraPhong;
}
public void sapXep(){
    dskh.updateTable(ql.sapXepTheoTen());
}
public void xoa() {
    JTable table = dskh.getTable();
    int selectedRow = table.getSelectedRow(); 

    if (selectedRow == -1) { 
        JOptionPane.showMessageDialog(dskh, "Vui lòng chọn khách hàng để xoá!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        return;
    }
    String idKhachHang = table.getValueAt(selectedRow, 1).toString(); 
    KhachHang khachHang =ql.timKiem(idKhachHang) ;

    if (khachHang == null) {
        JOptionPane.showMessageDialog(dskh, "Không tìm thấy thông tin khách hàng!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        return;
    }
    if (!khachHang.isStatus()) { 
        JOptionPane.showMessageDialog(dskh, "Không được phép xoá khách hàng chưa trả phòng!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        return;
    }
    int confirm = JOptionPane.showConfirmDialog(dskh, "Bạn có chắc chắn muốn xoá khách hàng "+ idKhachHang+" Không ?", "Xác nhận", JOptionPane.YES_NO_OPTION);
    if (confirm == JOptionPane.YES_OPTION) {
        int  Deleted = dao.delete(idKhachHang); 
        if (Deleted>0) {
            JOptionPane.showMessageDialog(dskh, "Xoá khách hàng thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            dskh.updateTable(dao.selectAll()); 
        } else {
            JOptionPane.showMessageDialog(dskh, "Xoá khách hàng thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }
}
public void showSearchField() {
    JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(dskh);
    JDialog searchDialog = new JDialog(parentFrame, "Tìm kiếm Khách Hàng", true);  // 'parentFrame' là JFrame cha của JPanel
    searchDialog.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
    JTextField searchField = new JTextField(20);  
    searchField.setEditable(true);  

    JButton searchButton = new JButton("Tìm kiếm");  // Nút tìm kiếm
    searchButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String keyword = searchField.getText().toLowerCase();  // Lấy từ khóa tìm kiếm
            searchCustomer(keyword);  // Tìm kiếm và hiển thị kết quả
        }
    });
    searchDialog.add(new JLabel("Tìm kiếm:"));
    searchDialog.add(searchField);
    searchDialog.add(searchButton);
    searchDialog.setSize(300, 140);  
    searchDialog.setLocationRelativeTo(parentFrame); 
    searchDialog.setVisible(true);  
     searchDialog.pack();
    
}
public void searchCustomer(String keyword) {
    ArrayList<KhachHang> result = new ArrayList<>();
    keyword = keyword.toLowerCase();
    for (KhachHang kh : dao.selectAll()) {
        if (kh.getName().toLowerCase().contains(keyword) || 
            kh.getCccd().toLowerCase().contains(keyword) || 
            kh.getDiaChi().toLowerCase().contains(keyword) || 
            kh.getSdt().toLowerCase().contains(keyword)) {
            result.add(kh); 
        }
    }

    if (result.isEmpty()) {
        JOptionPane.showMessageDialog(dskh, "Không tìm thấy khách hàng với từ khóa: " + keyword, "Kết quả tìm kiếm", JOptionPane.INFORMATION_MESSAGE);
    } else {
        dskh.updateTable(result); 
    }
}



}