/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Connection.JDBC;
import Model.DatPhong;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.sql.*;
/**
 *
 * @author Admin
 */
public class DatPhongDao implements DaoInterface<DatPhong>{
    Connection con;
    @Override
    public int insert(DatPhong t) {
        int ans=0;
       con=JDBC.getConnection();
       String query="INSERT INTO roombooking (ID_Phong, ID_KH, Name, Price, ThoiGianThue) values (?, ?, ?, ?,?)";
       try{
           PreparedStatement ps=con.prepareStatement(query);
           ps.setString(1, t.getIdPhong());
           ps.setString(2, t.getIdKH());
           ps.setString(3, t.getName());
           ps.setDouble(4, t.getGiaThue());
           ps.setInt(5, t.getThoiGianThue());
           ans=ps.executeUpdate();
           JDBC.closeConnection(con);
       }catch(SQLException e){
           e.printStackTrace();
       }
       return ans;
    }

    @Override
    public int update(DatPhong t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
   public int xoa(int key) {
    int result = 0;
    String query = "DELETE FROM roombooking WHERE ID_DatPhong = ?";
    
    try (Connection con = JDBC.getConnection();
         PreparedStatement ps = con.prepareStatement(query)) {
        ps.setInt(1, key);
        result = ps.executeUpdate();
        JDBC.closeConnection(con);
    } catch (SQLException e) { 
        e.printStackTrace();
    }  
    return result; 
}

  public ArrayList<DatPhong> selectAll() {
    ArrayList<DatPhong> list = new ArrayList<>();
    String sql = "SELECT * FROM roombooking";

    try (Connection con = JDBC.getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            int idDP=rs.getInt("ID_DatPhong");
            String idPhong = rs.getString("ID_Phong");
            String idKh = rs.getString("ID_KH");
            String name = rs.getString("Name");
            double price = rs.getDouble("Price");
            Date ngayDat = rs.getDate("Ngay_Dat");
            Date ngayTra = rs.getDate("Ngay_Tra");
            int thoiGianThue = rs.getInt("ThoiGianThue");
            

            DatPhong datPhong = new DatPhong(idDP,idPhong, idKh, name, price, ngayDat, ngayTra, thoiGianThue);
            list.add(datPhong);
           
        }
         JDBC.closeConnection(con);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}


    @Override
    public DatPhong slectById(DatPhong t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<DatPhong> selectBycondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<String> getAllID() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public ArrayList<DatPhong> sapXepTen() {
    ArrayList<DatPhong> list = new ArrayList<>();
    String query = "SELECT * FROM roombooking ORDER BY Name";  // Sắp xếp theo tên (Name)
    con=JDBC.getConnection();
    try {
            PreparedStatement ps=con.prepareStatement(query);
            ResultSet rs=ps.executeQuery();
        while (rs.next()) {
            DatPhong datPhong = new DatPhong();
            datPhong.setIdPhong(rs.getString("ID_Phong"));
            datPhong.setIdKH(rs.getString("ID_KH"));
            datPhong.setName(rs.getString("Name"));
            datPhong.setGiaThue(rs.getDouble("Price"));
            datPhong.setNgayDat(rs.getDate("Ngay_Dat"));
            datPhong.setNgayTra(rs.getDate("Ngay_Tra"));
            datPhong.setThoiGianThue(rs.getInt("ThoiGianThue"));
            datPhong.setIdDP(rs.getInt("ID_DatPhong"));
            list.add(datPhong);
           
        }
         JDBC.closeConnection(con);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}

    public ArrayList<DatPhong> sapXepThoiGianThue(){
        ArrayList<DatPhong> list = new ArrayList<>();
   String query = "SELECT * FROM roombooking ORDER BY ThoiGianThue";
    con=JDBC.getConnection();
    try {
            PreparedStatement ps=con.prepareStatement(query);
            ResultSet rs=ps.executeQuery();
        while (rs.next()) {
             DatPhong datPhong = new DatPhong();
            datPhong.setIdPhong(rs.getString("ID_Phong"));
            datPhong.setIdKH(rs.getString("ID_KH"));
            datPhong.setName(rs.getString("Name"));
            datPhong.setGiaThue(rs.getDouble("Price"));
            datPhong.setNgayDat(rs.getDate("Ngay_Dat"));
            datPhong.setNgayTra(rs.getDate("Ngay_Tra"));
            datPhong.setThoiGianThue(rs.getInt("ThoiGianThue"));
            datPhong.setIdDP(rs.getInt("ID_DatPhong"));
            list.add(datPhong);
           
        }
         JDBC.closeConnection(con);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
    }
    public ArrayList<DatPhong> sapXepGiaThue(){
        ArrayList<DatPhong> list = new ArrayList<>();
    String query = "SELECT * FROM roombooking ORDER BY Price";
    con=JDBC.getConnection();
    try {
            PreparedStatement ps=con.prepareStatement(query);
            ResultSet rs=ps.executeQuery();
        while (rs.next()) {
             DatPhong datPhong = new DatPhong();
            datPhong.setIdPhong(rs.getString("ID_Phong"));
            datPhong.setIdKH(rs.getString("ID_KH"));
            datPhong.setName(rs.getString("Name"));
            datPhong.setGiaThue(rs.getDouble("Price"));
            datPhong.setNgayDat(rs.getDate("Ngay_Dat"));
            datPhong.setNgayTra(rs.getDate("Ngay_Tra"));
            datPhong.setThoiGianThue(rs.getInt("ThoiGianThue"));
            datPhong.setIdDP(rs.getInt("ID_DatPhong"));
            list.add(datPhong);
        }
           JDBC.closeConnection(con);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
    }
    public ArrayList<DatPhong> sapXepNgayDat(){
        ArrayList<DatPhong> list = new ArrayList<>();
    String query = "SELECT * FROM roombooking ORDER BY Ngay_Dat";
    con=JDBC.getConnection();
    try {
            PreparedStatement ps=con.prepareStatement(query);
            ResultSet rs=ps.executeQuery();
        while (rs.next()) {
             DatPhong datPhong = new DatPhong();
            datPhong.setIdPhong(rs.getString("ID_Phong"));
            datPhong.setIdKH(rs.getString("ID_KH"));
            datPhong.setName(rs.getString("Name"));
            datPhong.setGiaThue(rs.getDouble("Price"));
            datPhong.setNgayDat(rs.getDate("Ngay_Dat"));
            datPhong.setNgayTra(rs.getDate("Ngay_Tra"));
            datPhong.setThoiGianThue(rs.getInt("ThoiGianThue"));
            datPhong.setIdDP(rs.getInt("ID_DatPhong"));
            list.add(datPhong);
            
        }
        JDBC.closeConnection(con);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
    }

    @Override
    public int delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
