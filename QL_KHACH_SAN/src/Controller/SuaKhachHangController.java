/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.KhachHangDao;
import Model.KhachHang;
import Model.QuanLy;
import View.SuaKhachHang;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class SuaKhachHangController implements ActionListener{
    SuaKhachHang skh;
    KhachHangDao dao;
    public SuaKhachHangController(SuaKhachHang skh){
        this.skh=skh;
        dao=new KhachHangDao();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
       String src=e.getActionCommand();
       if(src.equals("Huỷ")){
           skh.close();
       }
       else if(src.equals("Xác nhận")){
           upDate();
       }
    }

    public void upDate(){
         String id = skh.getSelectedID();
        String name=skh.getNameField().getText().trim();
        String cccd=skh.getCccdField().getText().trim();
        String diaChi=skh.getAddressField().getText().trim();
        String sdt=skh.getPhoneField().getText().trim();
        if(name.isEmpty() || cccd.isEmpty() || diaChi.isEmpty() || sdt.isEmpty()){
            JOptionPane.showMessageDialog(skh, "Vui lòng không để trống bất kỳ ô nào ");
            return;
        }
        KhachHang kh=new KhachHang(id, name, cccd, diaChi, sdt);
        if(dao.update(kh)>0){
            JOptionPane.showMessageDialog(skh, "Sửa Khách Hàng Thành Công");
            skh.close();
        }
        else {
            JOptionPane.showMessageDialog(skh, "Sửa Khách Hàng Thất Bại");
            return;
        }     
             
    }
}
