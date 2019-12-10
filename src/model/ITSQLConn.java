package model;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//SQL connection used in every class that uses DB
public class ITSQLConn {
    //change the "test" here to the name of your own database
    private static final String URL="jdbc:mysql://localhost:3306/information_schema?useUnicode=true&characterEncoding=utf8";

    private static final String NAME="root";//username
    private static final String PASSWORD="hd3d1337";//password
    public java.sql.Connection conn = null;

    public ITSQLConn(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to load the driver");
            e.printStackTrace();
        }try {
            conn = DriverManager.getConnection(URL, NAME, PASSWORD);
            System.out.println("Successfully connected!");

        }catch (SQLException e){
            System.out.println("Failed to connect to mysql!");
            //check your username and password
            e.printStackTrace();
        }
    }

    public java.sql.Connection getConn(){
        return conn;
    }

    public void close(){
        //shut down the connection
        if(conn!=null){
            try {
                conn.close();
            }catch (SQLException e){
                e.printStackTrace();
                conn = null;
            }
        }
    }

    public static int getCourseIDStart(String tableName){
        String sql = "SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA = \"gradingsystem\" AND TABLE_NAME = \"" + tableName + "\"";
        PreparedStatement pst = null;
        ITSQLConn sc = new ITSQLConn();
        int count = 0;
        try {
            pst = (PreparedStatement) sc.getConn().prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                count = rs.getInt("AUTO_INCREMENT");
            }
            sc.close();
        }catch (Exception e) {
            System.out.println("don't get any");
        }
        return count;
    }
}