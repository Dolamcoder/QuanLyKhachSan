/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Connection.JDBC;
import Model.Bill;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.*;
import java.time.LocalDate;
/**
 *
 * @author Admin
 */
public class BillDao implements DaoInterface<Bill> {
    Connection con;
   @Override
public int insert(Bill t) {
    int result = 0;
    con = JDBC.getConnection();
    String query = "INSERT INTO bill (ID_Bill, ID_DatPhong, ID_KH, ID_Phong, Price, Ngay_Dat, Ngay_Tra, ThoiGianThue, ChiPhi) "
                 + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    
    try {
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, t.getId());        
        ps.setInt(2, t.getIdDP());         
        ps.setString(3, t.getIdKH());       
        ps.setString(4, t.getIdPhong());   
        ps.setDouble(5, t.getGiaThue());   
        
        ps.setDate(6, new java.sql.Date(t.getNgayDat().getTime())); 
        ps.setDate(7, new java.sql.Date(System.currentTimeMillis())); 
        
        ps.setInt(8, t.getThoiGianThue()); 
        ps.setDouble(9, t.getChiPhi());    

        result = ps.executeUpdate();
        JDBC.closeConnection(con);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return result;
}


    @Override
    public int update(Bill t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   @Override
public ArrayList<Bill> selectAll() {
    ArrayList<Bill> list = new ArrayList<>(); 
    con = JDBC.getConnection();
    String query = "SELECT * FROM bill"; 

    try {
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String idBill = rs.getString("ID_Bill");
            int idDP = rs.getInt("ID_DatPhong");
            String idKH = rs.getString("ID_KH");
            String idPhong = rs.getString("ID_Phong");
            double price = rs.getDouble("Price");
            Date ngayDat = rs.getDate("Ngay_Dat");  
            Date ngayTra = rs.getDate("Ngay_Tra"); 
            int thoiGianThue = rs.getInt("ThoiGianThue");
            double chiPhi = rs.getDouble("ChiPhi");
            Bill bill = new Bill(idBill, idDP, chiPhi);
            bill.setIdKH(idKH);
            bill.setIdPhong(idPhong);
            bill.setGiaThue(price);
            bill.setNgayDat(ngayDat);
            bill.setNgayTra(ngayTra);
            bill.setThoiGianThue(thoiGianThue);

            list.add(bill);  
        }
        JDBC.closeConnection(con);
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return list;  
}


    @Override
    public Bill slectById(Bill t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Bill> selectBycondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<String> getAllID() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public String getNextId() {
    String query = "SELECT MAX(ID_Bill) AS max_id FROM bill";  
    String maxId = null;

    try (Connection con = JDBC.getConnection();
         PreparedStatement pstmt = con.prepareStatement(query);
         ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
            maxId = rs.getString("max_id"); 
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    if (maxId != null) {
        return generateNextId(maxId);  
    }
    JDBC.closeConnection(con);
   
    return "BILL001"; 
}

public String generateNextId(String currentMaxId) {
    
    if (currentMaxId == null || currentMaxId.isEmpty()) {
        return "BILL001"; 
    }

   
    String numberPart = currentMaxId.replaceAll("[^\\d]", "");
    int nextNumber = Integer.parseInt(numberPart) + 1; 

    
    String prefix = currentMaxId.replaceAll("\\d", "");

     return prefix + String.format("%03d", nextNumber);  
}

}
