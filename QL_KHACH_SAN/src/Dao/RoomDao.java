package Dao;

import Connection.JDBC;
import Model.Room;
import View.ThemPhongView;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * RoomDao: DAO class for managing KhachHang operations
 */
public class RoomDao implements DaoInterface<Room> {
   Connection con;
    @Override
    public int insert(Room t) {
        con=JDBC.getConnection();
        int result=0;
        try{
            String query = "INSERT INTO room (ID_Phong, Bed, Floor, Status, Price) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, t.getIdPhong());
            ps.setInt(2, t.getBed());
            ps.setInt(3, t.getFloor());
            ps.setString(4, "Chưa Sẵn Sàng");
            ps.setDouble(5, t.getPrice());
            result=ps.executeUpdate();
            JDBC.closeConnection(con);
        }catch(SQLException ex){
            ex.getStackTrace();
        }
        return result;
    }
    
    @Override
    public int update(Room t) {
        con=JDBC.getConnection();
    int result = 0;
    String query = "UPDATE room SET Bed = ?, Floor = ?, Status = ?, Price = ? WHERE ID_Phong = ?";
    try (PreparedStatement ps = con.prepareStatement(query)) {
        ps.setInt(1, t.getBed());
        ps.setInt(2, t.getFloor());
        ps.setString(3, t.trangThai());
        ps.setDouble(4, t.getPrice());
        ps.setString(5, t.getIdPhong()); result = ps.executeUpdate(); 
        JDBC.closeConnection(con);
    } catch (SQLException e) {
        e.printStackTrace(); 
    }
    return result;
}
@Override
public int delete(String id) {
    con=JDBC.getConnection();
    int result=0;
    String sql = "DELETE FROM Room WHERE ID_Phong = ?"; 
    try (PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, id); 
        result =ps.executeUpdate();
       JDBC.closeConnection(con);
       return result;
    } catch (SQLException e) {
        e.printStackTrace();
        return 0; 
    }
   
}

    @Override

    public ArrayList<Room> selectAll() {
        con=JDBC.getConnection();
        ArrayList<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM room";
        try (Connection con = JDBC.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
             String id=rs.getString("ID_Phong");
             int bed=rs.getInt("Bed");
             int floor=rs.getInt("Floor");
             double price=rs.getDouble("Price");
             boolean status=true;
             if(rs.getString("Status").equals("Chưa Sẵn Sàng")){
                status=false;
             }
             Room room=new Room(id, bed, floor, status, price);
             rooms.add(room);
            }
            JDBC.closeConnection(con);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rooms;
    }

    @Override
    public Room slectById(Room t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
@Override
public ArrayList<Room> selectBycondition(String condition) {
    ArrayList<Room> rooms = new ArrayList<>();
    // Tạo câu lệnh truy vấn với điều kiện
    String query = "SELECT * FROM room WHERE " + condition;

    try (Connection con = JDBC.getConnection();
         PreparedStatement ps = con.prepareStatement(query);
         ResultSet rs = ps.executeQuery()) {
         while (rs.next()) {
             String id=rs.getString("ID_Phong");
             int bed=rs.getInt("Bed");
             int floor=rs.getInt("Floor");
             double price=rs.getDouble("Price");
             boolean status=true;
             if(rs.getString("Status").equals("Chưa Sẵn Sàng")){
                status=false;
             }
             Room room=new Room(id, bed, floor, status, price);
             rooms.add(room);
            }
        JDBC.closeConnection(con); 
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    
    return rooms;
}
    @Override
    public ArrayList<String> getAllID() {
        con=JDBC.getConnection();
        ArrayList<String> roomIds = new ArrayList<>();
    String query = "SELECT ID_Phong FROM room";
    try {
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            roomIds.add(rs.getString("ID_Phong"));
        }
        JDBC.closeConnection(con);
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return roomIds;
    }
   public int  upDateTrangThai(String idPhong) {
       int result=0;
    String query = "UPDATE room SET Status = 'Chưa Sẵn Sàng' WHERE ID_Phong = ?";
    
    try (Connection con = JDBC.getConnection();
            PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, idPhong);
            result=ps.executeUpdate();
        JDBC.closeConnection(con);
        
    } catch (SQLException e) {
       e.printStackTrace();
    }
    return result;
}
public int  upDateTrangThaiDatPhong(String idPhong) {
       int result=0;
       String query = "UPDATE room SET Status = 'Sẵn Sàng' WHERE ID_Phong = ?";
    
    try (Connection con = JDBC.getConnection();
         PreparedStatement ps = con.prepareStatement(query)) {
         ps.setString(1, idPhong);
         result=ps.executeUpdate();
        JDBC.closeConnection(con);
    } catch (SQLException e) {
     e.printStackTrace();
    }
    return result;
}
}
   

