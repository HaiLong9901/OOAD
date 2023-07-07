package com.ooad.ooad.utils;
import java.sql.*;
public class DBUtil {
    public static Connection connectDB() {
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/ooad", "root", "root");
            if (connect != null) {
                System.out.println("Connect to DB");
                System.out.println(connect);
            }
            return connect;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
