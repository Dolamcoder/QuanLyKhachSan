/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.DatPhongDao;
import Dao.KhachHangDao;
import Dao.RoomDao;
import Model.DatPhong;
import Model.KhachHang;
import Model.QuanLy;
import Model.Room;
import View.SuaKhachHang;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import view.ThemDatPhongView;

/**
 *
 * @author Admin
 */
public class ThemDatPhongController implements ActionListener{
    ThemDatPhongView tdp;
    QuanLy ql=new QuanLy();
    RoomDao daoR=new RoomDao();
    KhachHangDao daoKH=new KhachHangDao();
    Room room;
    DatPhongDao dao=new DatPhongDao();
    KhachHang khachHang;
    public ThemDatPhongController(ThemDatPhongView tdp){
        this.tdp=tdp;
        ql.setDanhSachKH(daoKH.selectAll());
        ql.setDanhSachRoom(daoR.selectAll());
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String src=e.getActionCommand();
        if(src.equals("Hủy")){
            tdp.close();
        }
        else if(src.equals("phong")){
            themPhong();
        }
        else if(src.equals("KH")){
            themKhachHang();
        }
        else if(src.equals("Thêm")){
           themDatPhong();
    }
    }

    public void themPhong(){
        if (tdp.getPhongComboBox() != null && tdp.getPhongComboBox().getItemCount() > 0) {
            String idPhong = (String) tdp.getPhongComboBox().getSelectedItem();
            room = ql.timKiemPhong(idPhong);
            if (room != null) {
                tdp.themPhong(room);
            } else {
                JOptionPane.showMessageDialog(tdp, "Không tìm thấy phòng.");
            }
        } else {
            JOptionPane.showMessageDialog(tdp, "Danh sách phòng trống.");
        }
    }

    public void themKhachHang() {
        if (tdp.getKhachHangComboBox() != null && tdp.getKhachHangComboBox().getItemCount() > 0) {
            String idKH = (String) tdp.getKhachHangComboBox().getSelectedItem();

            if (idKH.equals("New")) {
                String name = JOptionPane.showInputDialog("Nhập vào tên khách hàng");
                if (isNullOrEmpty(name)) {
                    JOptionPane.showMessageDialog(tdp, "Tên không được để trống hoặc bạn đã hủy.");
                    return;
                }

                String cccd = JOptionPane.showInputDialog("Nhập vào mã định danh");
                if (isNullOrEmpty(cccd)) {
                    JOptionPane.showMessageDialog(tdp, "Mã định danh không được để trống hoặc bạn đã hủy.");
                    return;
                }
                
                String diaChi = JOptionPane.showInputDialog("Nhập vào địa chỉ");
                if (isNullOrEmpty(diaChi)) {
                    JOptionPane.showMessageDialog(tdp, "Địa chỉ không được bỏ trống hoặc bạn đã hủy.");
                    return;
                }

                String sdt = JOptionPane.showInputDialog("Nhập vào số điện thoại");
                if (isNullOrEmpty(sdt)) {
                    JOptionPane.showMessageDialog(tdp, "Số điện thoại không được để trống hoặc bạn đã hủy.");
                    return;
                }

                try {
                    khachHang = new KhachHang(name, daoKH.getNextId(), room != null ? room.getIdPhong() : null, cccd, diaChi, sdt, false);
                    tdp.themKhachHang(khachHang);
                    daoKH.insert(khachHang);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(tdp, "Đã xảy ra lỗi khi thêm khách hàng mới!");
                }
            } else {
                khachHang = ql.timKiemKhachHang(idKH);
                if (khachHang != null && room != null) {
                    khachHang.setIdPhong(room.getIdPhong());
                    tdp.themKhachHang(khachHang);
                } else {
                    JOptionPane.showMessageDialog(tdp, "Vui lòng chọn phòng trước khi thêm khách hàng.");
                }
            }
        }
    }

    public void themDatPhong() {
        if (room != null && khachHang != null) {
            if (tdp.getThoiGian() <= 0) {
                JOptionPane.showMessageDialog(tdp, "Vui lòng nhập thời gian hợp lệ.");
                return;
            }

            DatPhong datPhong = new DatPhong(room.getIdPhong(), khachHang.getId(), khachHang.getName(), tdp.getThoiGian(), room.getPrice());
            if (dao.insert(datPhong) > 0 && daoR.upDateTrangThaiDatPhong(room.getIdPhong()) > 0 && daoKH.upaDateTrangThaiDatPhong(khachHang.getId(), room.getIdPhong()) > 0) {
                JOptionPane.showMessageDialog(tdp, "Đặt phòng thành công");
                tdp.close();
            } else {
                JOptionPane.showMessageDialog(tdp, "Đặt phòng thất bại. Vui lòng thử lại.");
            }
        } else {
            JOptionPane.showMessageDialog(tdp, "Vui lòng chọn phòng và khách hàng");
        }
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
