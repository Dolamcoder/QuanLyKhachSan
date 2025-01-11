/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.BillDao;
import Dao.DaoInterface;
import Dao.DatPhongDao;
import Dao.KhachHangDao;
import Dao.RoomDao;
import Model.Bill;
import Model.DatPhong;
import Model.QuanLy;
import View.DatPhongView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import javax.swing.*;
import view.ThemDatPhongView;

/**
 *
 * @author Admin
 */
public class DatPhongController implements ActionListener{
    DatPhongView dp;
    DatPhongDao dao;
    QuanLy ql=new QuanLy();
    RoomDao daoRoom=new RoomDao();
    KhachHangDao daoKH=new KhachHangDao();
    BillDao daoB=new BillDao();
    Bill bill;
    public DatPhongController(DatPhongView dp){
        this.dp=dp;
        dao=new DatPhongDao();
        ql.setDanhSachDatPhong(dao.selectAll());
    }
    @Override
public void actionPerformed(ActionEvent e) {
    String comand = e.getActionCommand();
    
       if (comand.equals("Danh Sách")) {
        dp.updateTable(dao.selectAll());
        System.out.println(comand);
    } 
    else if(comand.equals("Sort")){
        System.out.println(comand);
        String src=(String)dp.getSort().getSelectedItem();
     if (src.equals("Tên khách hàng")) {
        System.out.println(src);
        dp.updateTable(dao.sapXepTen());
    } 
    else if (src.equals("Thời gian thuê")) {
        System.out.println(src);
        dp.updateTable(dao.sapXepThoiGianThue());
    } 
    else if (src.equals("Giá Thuê")) {
        System.out.println(src);
        dp.updateTable(dao.sapXepGiaThue());
    } 
    else if (src.equals("Ngày đặt")) {
        System.out.println(src);
        dp.updateTable(dao.sapXepNgayDat());
    }
    }
    else if(comand.equals("Trả Phòng")){
        traPhong();
        dp.updateTable(dao.selectAll());
    }
    else if(comand.equals("Thêm Đặt Phòng")){
         ThemDatPhongView tdp = new ThemDatPhongView();
        tdp.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosed(java.awt.event.WindowEvent e) {
            dp.updateTable(dao.selectAll()); 
        }
    });
      tdp.setVisible(true);
    }
    
}
   public void traPhong() {
    JTable table = dp.getTable();
    int select = table.getSelectedRow();
    if (select == -1) { 
        JOptionPane.showMessageDialog(dp, "Vui lòng chọn khách hàng trả phòng!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        return;
    }

    String idKH = table.getValueAt(select, 2).toString();
    String idP = table.getValueAt(select, 1).toString();
    String idDPString = table.getValueAt(select, 0).toString();

    int idDP = Integer.parseInt(idDPString);  
    
   
    DatPhong datPhong = ql.timKiemDatPhong(idDP);
    if (datPhong == null) {
        JOptionPane.showMessageDialog(dp, "Không tìm thấy thông tin đặt phòng", "Thông báo", JOptionPane.ERROR_MESSAGE);
        return;
    }
     hoaDon(datPhong);
   
    int ans = dao.xoa(idDP);
    if (ans > 0 && xoa(idKH, idP)) {
        JOptionPane.showMessageDialog(dp, "Trả phòng thành công");
    } else {
        JOptionPane.showMessageDialog(dp, "Xoá phòng thất bại");
    }

       dp.updateTable(dao.selectAll());
}
    public boolean xoa(String idKH, String idPhong){
        if(daoRoom.upDateTrangThai(idPhong)>0 && daoKH.upaDateTrangThai(idKH)>0) return true;
        return false;
    }
    public void hoaDon(DatPhong datPhong){
        double chiPhi=datPhong.getThoiGianThue()*datPhong.getGiaThue();
        bill=new Bill(daoB.getNextId(), datPhong.getIdDP(), chiPhi);
        bill.setIdKH(datPhong.getIdKH());
        bill.setIdPhong(datPhong.getIdPhong());
        bill.setGiaThue(datPhong.getGiaThue());
        bill.setNgayDat(datPhong.getNgayDat());
        java.sql.Date ngayTraDate = java.sql.Date.valueOf(LocalDate.now());
        bill.setNgayTra(ngayTraDate);
        bill.setThoiGianThue(datPhong.getThoiGianThue());
        daoB.insert(bill);
    }
}
