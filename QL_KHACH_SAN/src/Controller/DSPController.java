/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.RoomDao;
import Model.QuanLy;
import Model.Room;
import View.DSPhongView;
import View.SuaPhongView;
import View.ThemPhongView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.sql.SQLException;
public class DSPController implements ActionListener {
    DSPhongView dsp;
    RoomDao dao;
    QuanLy ql=new QuanLy();
    public DSPController(DSPhongView dsp){
        this.dsp=dsp;
        dao=new RoomDao();
         ql.setDanhSachRoom(dao.selectAll());
    }

 @Override
public void actionPerformed(ActionEvent e) {
    String src = e.getActionCommand();
    if (src.equals("Thêm phòng")) {
        ThemPhongView themPhongView = new ThemPhongView();
        themPhongView.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosed(java.awt.event.WindowEvent e) {
            dsp.updateTable(dao.selectAll()); // Cập nhật danh sách sau khi cửa sổ đóng
        }
    });
        themPhongView.setVisible(true);
       
    }else if(src.equals("Tìm kiếm phòng")){
        addPhong();
    }
    else if (src.equals("Sửa Thông tin")) {
         SuaPhongView suaPhong = new SuaPhongView();
        suaPhong.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosed(java.awt.event.WindowEvent e) {
            dsp.updateTable(dao.selectAll()); // Cập nhật danh sách sau khi cửa sổ đóng
        }
    });
       suaPhong.setVisible(true);
        
    }else if(src.equals("Xoá phòng")){
        xoaPhong();
    }
    else if (src.equals("ds")) {
        String select = (String) dsp.getDs().getSelectedItem();
        if (select.equals("Danh Sách")) {
   
            dsp.updateTable(dao.selectAll()); 
        } else if (select.equals("Phòng Chưa Đặt")) {
            ArrayList<Room> rooms = dsChuaDat();
            if (rooms == null || rooms.isEmpty()) {
                JOptionPane.showMessageDialog(dsp, "Danh sách phòng trống");
            } else {
               
                dsp.updateTable(rooms);  
            }
        } else if (select.equals("Phòng Đã Đặt")) {
            ArrayList<Room> rooms = dsDaDat();
            if (rooms == null || rooms.isEmpty()) {
                JOptionPane.showMessageDialog(dsp, "Danh sách phòng trống");
            } else {
                
                dsp.updateTable(rooms); 
            }
        }
    }
    else if(src.equals("sort")){
        String select= (String)dsp.getDsSort().getSelectedItem();
        if(select.equals("Sort theo giường")){
            sapXepBed();
        }
        if(select.equals("Sort theo tầng")){
            sapXepFloor();
        }
        if(select.equals("Sort theo giá")){
            sapXepGia();
        }
    }
}


    public ArrayList<Room> dsChuaDat(){
        ArrayList<Room> dsChuaDat=new ArrayList<>();
          for(Room x:dao.selectAll()){
              if(!x.isStatus()){
                  dsChuaDat.add(x);
              }
          }
          return dsChuaDat;
    }
    public ArrayList<Room> dsDaDat(){
        ArrayList<Room> dsDaDat=new ArrayList<>();
            for(Room x: dao.selectAll()){
                if(x.isStatus()){
                    dsDaDat.add(x);
                }
            }
            return dsDaDat;
    }
    public void xoaPhong() {
    JTable table = dsp.getTable(); 
    int selectedRow = table.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(dsp, "Vui lòng chọn phòng để xoá!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        return;
    }
    String roomId = table.getValueAt(selectedRow, 0).toString();
    int confirm = JOptionPane.showConfirmDialog(dsp, "Bạn có chắc chắn muốn xoá phòng có mã \"" + roomId + "\"?", "Xác nhận", JOptionPane.YES_NO_OPTION);
    if (confirm == JOptionPane.YES_OPTION) {
        Room room=ql.timKiemPhong(roomId);
        if(room.isStatus()){
            JOptionPane.showMessageDialog(dsp, "Không được xoá phòng đang có khách đặt");
            return;
        }
        int result = dao.delete(roomId); 
        if (result > 0) {
            JOptionPane.showMessageDialog(dsp, "Xoá phòng thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            dsp.updateTable(dao.selectAll()); // Cập nhật lại bảng danh sách
        } else {
            JOptionPane.showMessageDialog(dsp, "Xoá phòng thất bại hoặc không tìm thấy mã phòng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}
    public void sapXepBed(){
        dsp.updateTable(ql.sapXepTheoBed());
    }
    public void sapXepFloor(){
        dsp.updateTable(ql.sapXepTheoFloor());
    }
    public void  sapXepGia(){
       dsp.updateTable(ql.sapXepTheoGia());
    }
  public void addPhong() {
    try {
        String condition = JOptionPane.showInputDialog(dsp, "Nhập điều kiện tìm kiếm:", "Tìm kiếm", JOptionPane.QUESTION_MESSAGE);
        ArrayList<Room> dsR = dao.selectBycondition(condition);
        if (dsR.isEmpty()) {
            JOptionPane.showMessageDialog(dsp, "Không tìm thấy phòng nào khớp với điều kiện.");
        } else {
            dsp.updateTable(dsR);
        }
    }catch (Exception e) {
        JOptionPane.showMessageDialog(dsp, "Đã xảy ra lỗi: " + e.getMessage());
        e.printStackTrace();
    }
        
}

}
