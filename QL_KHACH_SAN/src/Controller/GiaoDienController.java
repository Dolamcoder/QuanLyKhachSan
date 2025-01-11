/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.BillDao;
import Model.Bill;
import Model.QuanLy;
import View.DatPhongView;
import View.DSKhachHangView;
import View.DSPhongView;
import View.DangNhapView;
import View.GiaoDienView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import View.BillView;
import View.DoanhThuView;
/**
 *
 * @author Admin
 */
public class GiaoDienController implements ActionListener {
    GiaoDienView gd;
    QuanLy ql=new QuanLy();
    BillDao dao=new BillDao();
    private int thangInt=0, namInt;
    DoanhThuView view=new DoanhThuView();
    public GiaoDienController(GiaoDienView gd){
        this.gd=gd;
        view.setVisible(false);
    }
    public void actionPerformed(ActionEvent e){
        String src=e.getActionCommand();
        if(src.equals("Đăng Xuất")){
           int response = JOptionPane.showConfirmDialog(gd, "Bạn có chắc muốn Đăng xuất chứ?", "Xác nhận thoát", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            new DangNhapView();
            gd.close();
        }
        }
        else if(src.equals("Thoát")){
            int response = JOptionPane.showConfirmDialog(gd, "Bạn có chắc muốn thoát?", "Xác nhận thoát", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            gd.close();
        }
        }
        else if(src.equals("Danh Sách Phòng")){
              gd.setCenterPanel(new DSPhongView());
        }
        else if(src.equals("Danh Sách Khách Hàng")){
            gd.setCenterPanel(new DSKhachHangView());
        }
        else if(src.equals("Đặt Phòng")){
            gd.setCenterPanel(new DatPhongView());
        }
        else if(src.equals("Hoá Đơn"))  {
            gd.setCenterPanel(new BillView());
        }
        else if(src.equals("Doanh Thu")){
            ql.setDanhSachHoaDon(dao.selectAll());
            doanhThu();
        }
    }
    public void doanhThu() {
        int doanhThu;
    String thang = JOptionPane.showInputDialog(gd, "Nhập tháng:", "Tháng", JOptionPane.PLAIN_MESSAGE);
    if (thang != null && !thang.isEmpty()) {
        try {
            thangInt= Integer.parseInt(thang);
            if (thangInt < 1 || thangInt > 12) {
                JOptionPane.showMessageDialog(gd, "Tháng phải trong khoảng từ 1 đến 12!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(gd, "Tháng phải là số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    else return;
    String nam = JOptionPane.showInputDialog(gd, "Nhập năm:", "Năm", JOptionPane.PLAIN_MESSAGE);
    if (nam != null && !nam.isEmpty()) {
        try {
            namInt = Integer.parseInt(nam);
            if (namInt < 1) {
                JOptionPane.showMessageDialog(gd, "Năm không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(gd, "Năm phải là số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    else return;
    suLyTieuDe(thangInt, namInt);
    int tien=0;
    int dem=0;
    String ngayThang="";
    if(thangInt<10){
        ngayThang+="0"+thangInt+"-"+nam;
    }
    else {
        ngayThang+=thangInt+"-"+nam;
    }
    for(Bill x:ql.getDanhSachHoaDon()){
        if(x.getMonthAndYearOfNgayDat().equals(ngayThang)){
            dem++;
            tien+=x.getChiPhi();
        }
    }
    view.getDoanhThu().setText("Doanh Thu: "+ tien +".000VND");
    view.getSoLuong().setText("Số Lượng Đặt Phòng: "+dem);
    view.setVisible(true);
    }
    public void suLyTieuDe(int thang, int nam){
        view.getTieuDe().setText("Thông kê tháng "+thang +" Năm "+ nam);
    }
}
