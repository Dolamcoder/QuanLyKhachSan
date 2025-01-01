/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.RoomDao;
import Model.Room;
import View.ThemPhongView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class ThemPhongController implements ActionListener{
    ThemPhongView tp;
    RoomDao rDao;
    public ThemPhongController (ThemPhongView tp){
        this.tp=tp;
        rDao=new RoomDao();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
       String src=e.getActionCommand();
       if(src.equals("Xác nhận")){
           themPhong();
       }
       else if(src.equals("Hủy")){
           tp.close();
       }
    }
    public void themPhong() {
    Room room = tp.themPhong();
    if (room != null) { // Kiểm tra nếu room không null
        if (rDao.insert(room) > 0) {
            JOptionPane.showMessageDialog(tp, "Thêm phòng thành công");
        } else {
            JOptionPane.showMessageDialog(tp, "Thêm phòng không thành công");
        }
    } 
}

}
