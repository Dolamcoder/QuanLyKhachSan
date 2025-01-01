/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.DSPhongView;
import View.DangNhapView;
import View.GiaoDienView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class GiaoDienController implements ActionListener {
    GiaoDienView gd;
    public GiaoDienController(GiaoDienView gd){
        this.gd=gd;
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
      
    }
}
