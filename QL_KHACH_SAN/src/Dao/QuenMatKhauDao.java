
package Dao;
import Connection.JDBC;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class QuenMatKhauDao {
    Connection con;
    public String getUser(String email){
        String query="SELECT username FROM users WHERE email = ?";
        String user="";
        con=JDBC.getConnection();
        try{
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
                user=rs.getString("username");
            }
            JDBC.closeConnection(con);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return user;
    }
}
