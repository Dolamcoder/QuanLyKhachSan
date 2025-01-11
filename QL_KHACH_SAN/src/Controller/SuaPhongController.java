/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.RoomDao;
import Model.QuanLy;
import Model.Room;
import View.SuaPhongView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class SuaPhongController implements ActionListener {
    SuaPhongView sp;
    RoomDao dao;
    QuanLy ql;
    public SuaPhongController(SuaPhongView sp){
        this.sp=sp;
        dao=new RoomDao();
        ql=new QuanLy();
        ql.setDanhSachRoom(dao.selectAll());
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
       String src=e.getActionCommand();
       if(src.equals("Xác nhận")){
           thaoTacCSDL();
        
       }
       else if(src.equals("Huỷ")){
           sp.close();
       }
       
    }
    public Room suaPhong(String id, int bed, int floor, double price){
        Room room=ql.timKiemPhong(id);
        if(room!=null){
        room.setBed(bed);
        room.setFloor(floor);
        room.setPrice(price);
        return room;
        }
        return null;
    }
    public void thaoTacCSDL(){
       String id = sp.getID();
        int bed = sp.getBed();
        int floor = sp.getFloor();
        double price = sp.getPrice();
        if(bed==0){
            JOptionPane.showMessageDialog(sp, "Không được để giường trống hoặc nhập sai dữ liệu");
            return;
        }
        if(floor==0){
            JOptionPane.showMessageDialog(sp, "Không được để tầng trống hoặc nhập sai dữ liệu");
            return;
        }
        if(price==0){
            JOptionPane.showMessageDialog(sp, "Không được để giá phòng trống hoặc nhập sai dữ liệu");
            return;
        }
        Room updatedRoom = suaPhong(id, bed, floor, price);
        if(updatedRoom!=null){
            if(dao.update(updatedRoom)>0){
            JOptionPane.showMessageDialog(sp,"Sửa phòng thành công");
            sp.close();
            return;
            }
        }
       JOptionPane.showMessageDialog(sp, "Sửa phòng không thành công");
       return;
    }
}
