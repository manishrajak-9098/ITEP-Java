package com.project;
import java.sql.*;

public class GetConnection {
    public static Connection getConnect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // CHANGE PASSWORD if needed
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/finders_hub", "root", "root");
        } catch (Exception e) {
        	e.printStackTrace(); 
        return null; 
        }
    }
}