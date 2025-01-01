/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.RoomDao;
import Model.Room;
import View.DSPhongView;
import View.SuaPhongView;
import View.ThemPhongView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class DSPController implements ActionListener {
    DSPhongView dsp;
    RoomDao dao;
    public DSPController(DSPhongView dsp){
        this.dsp=dsp;
        dao=new RoomDao();
    }

 @Override
public void actionPerformed(ActionEvent e) {
    String src = e.getActionCommand();
    if (src.equals("Thêm phòng")) {
        new ThemPhongView();
    } else if (src.equals("Sửa Thông tin")) {
        new SuaPhongView();
    } else if (src.equals("ds")) {
        String select = (String) dsp.getDs().getSelectedItem();
       
        if (select.equals("Danh Sách")) {
   
            dsp.updateTable(dao.selectAll()); 
        } else if (select.equals("Phòng Chưa Đặt")) {
            ArrayList<Room> rooms = dsChuaDat();
            if (rooms == null || rooms.isEmpty()) {
                JOptionPane.showMessageDialog(dsp, "Danh sách phòng trống");
            } else {
               
                dsp.updateTable(rooms);  // Cập nhật bảng với phòng chưa đặt
            }
        } else if (select.equals("Phòng Đã Đặt")) {
            ArrayList<Room> rooms = dsDaDat();
            if (rooms == null || rooms.isEmpty()) {
                JOptionPane.showMessageDialog(dsp, "Danh sách phòng trống");
            } else {
                
                dsp.updateTable(rooms);  // Cập nhật bảng với phòng đã đặt
            }
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
    
}
