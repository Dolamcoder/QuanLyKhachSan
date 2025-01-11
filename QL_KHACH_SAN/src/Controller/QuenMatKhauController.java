/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.QuenMatKhauDao;
import Model.Mail;
import View.GiaoDienView;
import View.QuenMatKhauView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class QuenMatKhauController implements ActionListener{
    QuenMatKhauView qmk;
    QuenMatKhauDao dao;
    Mail mail;
    public QuenMatKhauController(QuenMatKhauView qmk){
        this.qmk=qmk;
        dao=new QuenMatKhauDao();
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String src=e.getActionCommand();
        if(src.equals("Huỷ")){
            qmk.close();
        }
        else if(src.equals("Tìm kiếm")){
           timKiemUser();
        }
        else if(src.equals("Xác Nhận")){
            xacNhan();
        }
    }
   public void timKiemUser(){
       String email=qmk.getEmail().getText().trim();
            if(email.isEmpty()){
                JOptionPane.showMessageDialog(qmk, "Vui lòng nhập email để tìm kiếm");
            }
            else {
                String user=dao.getUser(email);
                if(user.isEmpty()){
                    qmk.getUser().setText("Không tìm thấy tài khoản");
                }
                else {
                    qmk.getUser().setText("Tên Tài khoản: " +  user);
                    mail=new Mail(email);
                }
          }
   }
   public boolean check(int ma){
       if(mail.getMa()==ma) return true;
       return false;
   }
  public void xacNhan(){
      try{
      int ma=Integer.parseInt(qmk.getMaXn().getText().trim());
      if(check(ma)){
          new GiaoDienView();
          qmk.close();
      }
      else {
          JOptionPane.showMessageDialog(qmk, "Mã xác nhận không đúng");
      }
      }catch(Exception e){
          e.printStackTrace();
          JOptionPane.showMessageDialog(qmk, "Nhập đúng định dạng số cho mã xác nhận");
      }
      
      
  }
}
