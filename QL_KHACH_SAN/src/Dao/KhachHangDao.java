/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Connection.JDBC;
import Model.KhachHang;
import com.mysql.cj.protocol.Resultset;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author Admin
 */
public class KhachHangDao implements DaoInterface<KhachHang> {
    Connection con;
    @Override
    public int insert(KhachHang t) {
       con = JDBC.getConnection(); 
        String query = "INSERT INTO khach_hang (ID_KH, Name, ID_Phong, CCCD, Dia_Chi, SDT, Status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        int result = 0;

        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            // Gán giá trị cho các tham số
            pstmt.setString(1, t.getId());
            pstmt.setString(2, t.getName());
            pstmt.setString(3, t.getIdPhong());
            pstmt.setString(4, t.getCccd());
            pstmt.setString(5, t.getDiaChi());
            pstmt.setString(6, t.getSdt());
            pstmt.setString(7, "Chưa Trả Phòng");
           
            result = pstmt.executeUpdate();
            JDBC.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return result;
    }

    @Override
    public int update(KhachHang t) {
       con = JDBC.getConnection();
    KhachHang kh = (KhachHang) t; 
    int result = 0;

    String sql = "UPDATE khach_hang SET Name = ?, CCCD = ?, Dia_Chi = ?, SDT = ? WHERE ID_KH = ?";

    try {
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, kh.getName());    
        pstmt.setString(2, kh.getCccd());    
        pstmt.setString(3, kh.getDiaChi());   
        pstmt.setString(4, kh.getSdt());      
        pstmt.setString(5, kh.getId());    

        result = pstmt.executeUpdate();
        JDBC.closeConnection(con); 
    } catch (SQLException e) {
        e.printStackTrace(); 
    }

    return result;
    }

  @Override
public int delete(String id) {
    con=JDBC.getConnection();
    int result = 0;
    String sql = "DELETE FROM khach_hang WHERE ID_KH = ?"; 
    try{
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, id); 
        result = pstmt.executeUpdate(); 
        JDBC.closeConnection(con);
    } catch (SQLException e) {
        e.printStackTrace(); 
    }

    return result; 
}

    @Override
    public ArrayList<KhachHang> selectAll() {
         con=JDBC.getConnection();
         ArrayList<KhachHang> dsKH=new ArrayList<>();
        String query="Select * from khach_hang";
        try{
         PreparedStatement ps=con.prepareStatement(query);
         ResultSet rs=ps.executeQuery();
         while(rs.next()){
             String name=rs.getString("Name");
             String idkh=rs.getString("ID_KH");
             String idPhong=rs.getString("ID_Phong");
             String cccd=rs.getString("CCCD");
             String diaChi=rs.getString("Dia_Chi");
             String sdt=rs.getString("SDT");
             boolean check=false;
             if(rs.getString("Status").equals("Đã Trả Phòng")){
                 check=true;
             }
             KhachHang kh=new KhachHang(name, idkh, idPhong, cccd, diaChi, sdt);
             kh.setStatus(check);
             if(kh!=null){
                 dsKH.add(kh);
             } 
         }
        JDBC.closeConnection(con);
        }catch(SQLException e){
           e.getStackTrace();
        }
        return dsKH;
    }

    @Override
    public KhachHang slectById(KhachHang t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList selectBycondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<String> getAllID() {
          con=JDBC.getConnection();
          ArrayList<String> ids=new ArrayList<>();
          String query="select ID_KH from khach_hang";
          try{
              PreparedStatement ps=con.prepareStatement(query);
              ResultSet rs=ps.executeQuery();
              while(rs.next()){
                  ids.add(rs.getString("ID_KH"));
              }
              JDBC.closeConnection(con);
            
          }catch(SQLException e){
              e.getStackTrace();
          }
          return ids;
    }
   public int  upaDateTrangThai(String idKH) {
       int result=0;
    String query = "UPDATE khach_hang SET Status= 'Đã Trả Phòng' WHERE ID_KH = ?";
    
    try (Connection con = JDBC.getConnection();
         PreparedStatement ps = con.prepareStatement(query)) {
         ps.setString(1, idKH);
        result=ps.executeUpdate();
       JDBC.closeConnection(con);
    } catch (SQLException e) {
       e.printStackTrace();
    }
    return result;
}
public int  upaDateTrangThaiDatPhong(String idKH, String idPhong) {
       int result=0;
    String query = "UPDATE khach_hang SET Status = 'Chưa Trả Phòng', ID_Phong = ? WHERE ID_KH = ?";

    
    try (Connection con = JDBC.getConnection();
         PreparedStatement ps = con.prepareStatement(query)) {
         ps.setString(2, idKH);
        ps.setString(1, idPhong);
       result=ps.executeUpdate();
       JDBC.closeConnection(con);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return result;
}
public String getNextId() {
    String query = "SELECT MAX(ID_KH) AS max_id FROM khach_hang";
    String maxId = null;

    try (Connection con = JDBC.getConnection();
         PreparedStatement pstmt = con.prepareStatement(query);
         ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
            maxId = rs.getString("max_id"); // Lấy ID lớn nhất
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    if (maxId != null) {
        return generateNextId(maxId);
    }
    JDBC.closeConnection(con);
    return "KH001";
}

public String generateNextId(String currentMaxId) {
    String numberPart = currentMaxId.replaceAll("[^\\d]", "");
    int nextNumber = Integer.parseInt(numberPart) + 1; // Tăng giá trị số lên 1
    String prefix = currentMaxId.replaceAll("\\d", "");
    return prefix + String.format("%03d", nextNumber);
}
}
