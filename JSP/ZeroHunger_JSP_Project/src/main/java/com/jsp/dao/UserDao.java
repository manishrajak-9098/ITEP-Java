package com.jsp.dao;

import java.sql.*;
import com.jsp.model.User;

public class UserDao {

  
    public static int register(User u) {
        int status = 0;
        try {
            Connection con = DBConnection.getConnection();
          
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO users(name,email,password,role) VALUES(?,?,?,?)"
            );

            ps.setString(1, u.getName());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getPassword());
            ps.setString(4, u.getRole());

            status = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }


    public static boolean isEmailExist(String email) {
        boolean exists = false;
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT email FROM users WHERE email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                exists = true; 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exists;
    }
    
   
    public static User login(String email, String password) {
        User u = null;
        try {
            Connection con = DBConnection.getConnection();
         
            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM users WHERE email=? AND password=?"
            );

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                u = new User();
                u.setUserId(rs.getInt("user_id"));
                u.setName(rs.getString("name"));
                u.setRole(rs.getString("role"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }
}