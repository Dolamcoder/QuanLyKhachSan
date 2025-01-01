package Connection;

import java.sql.*;

public class JDBC {

    // Method to establish a connection
    public static Connection getConnection() {
        Connection c = null;
        try {
            // Register the driver
            com.mysql.cj.jdbc.Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);

            // Database connection URL and credentials
            String url = "jdbc:mysql://localhost:3306/quanlykhachsan?useUnicode=true&characterEncoding=UTF-8";
            String user = "root";
            String password = "";

            // Establish the connection
            c = DriverManager.getConnection(url, user, password);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    // Method to close the connection
    public static void closeConnection(Connection c) {
        try{
            if(c!=null)
            {
                c.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
