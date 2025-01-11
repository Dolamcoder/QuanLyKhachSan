/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.BillDao;
import Model.Bill;
import Model.QuanLy;
import View.BillView;
import View.TruyVanHoaDonView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class BillController implements ActionListener{
    BillView bill;
    BillDao daoB=new BillDao();
    QuanLy ql=new QuanLy();
    TruyVanHoaDonView view=new TruyVanHoaDonView();
    public BillController(BillView bill){
        ql.setDanhSachHoaDon(daoB.selectAll());
        this.bill=bill;
        view.setVisible(false);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
       String comand=e.getActionCommand();
       if(comand.equals("Danh Sách Hoá Đơn")){
           bill.updateTableBill(daoB.selectAll());
       }
       else if(comand.equals("Tra Hoá Đơn")){
           traHoaDon();
       }
    }
    public void traHoaDon(){
         int selectedRow = bill.getTableHoaDon().getSelectedRow(); 
     
    if (selectedRow != -1) {  
        String idBill = bill.getTableHoaDon().getValueAt(selectedRow, 0).toString();  
        Bill b=ql.timKiemBill(idBill);
        if(b==null){
            JOptionPane.showMessageDialog(bill, "Không tìm thấy hoá đơn");
            return;
        }
        view.getLblMaHoaDon().setText("Mã Đặt Phòng: "+b.getId());
        view.getLblMaDatPhong().setText("Mã Đặt Phòng: "+b.getIdDP());
        view.getLblGiaThue().setText("Giá Thuê: "+b.getGiaThue());
        view.getLblMaKhachHang().setText("Mã Khách Hàng: "+b.getIdKH());
        view.getLblMaPhong().setText("Mã Phòng: "+b.getIdPhong());
        view.getLblNgayDat().setText("Ngày Đặt: "+b.getNgayDat());
        view.getLblNgayTra().setText("Ngày Trả: "+b.getNgayTra());
        view.getLblTongChiPhi().setText("Tổng Chi Phí: "+b.getChiPhi());
        view.setVisible(true);
    } else {
        JOptionPane.showMessageDialog(null, "Vui lòng chọn một hóa đơn!");
    }
    }
    public void inHoaDon(){
        
    }
}
