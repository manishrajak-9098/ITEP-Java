package com.jsp.dao;
import java.sql.*;
import java.util.*;
import com.jsp.model.User;

public class UserDao {
    public static int register(User u) {
        int status = 0;
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO users(name, email, password, phone, address, dob, gender) VALUES(?,?,?,?,?,?,?)");
            ps.setString(1, u.getName()); ps.setString(2, u.getEmail()); ps.setString(3, u.getPassword());
            ps.setString(4, u.getPhone()); ps.setString(5, u.getAddress()); ps.setString(6, u.getDob()); ps.setString(7, u.getGender());
            status = ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
        return status;
    }

    public static User login(String email, String password) {
        User u = null;
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE email=? AND password=?");
            ps.setString(1, email); ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                u = new User();
                u.setUserId(rs.getInt("user_id"));
                u.setName(rs.getString("name"));
                u.setEmail(rs.getString("email"));
                u.setRole(rs.getString("role"));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return u;
    }

    public static void updateRole(int id, String role) {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE users SET role=? WHERE user_id=?");
            ps.setString(1, role); 
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
    
    public static boolean isEmailExist(String email) {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT user_id FROM users WHERE email=?");
            ps.setString(1, email);
            return ps.executeQuery().next();
        } catch (Exception e) { return false; }
    }

    public static List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM users ORDER BY user_id DESC");
            while(rs.next()){
                User u = new User();
                u.setUserId(rs.getInt("user_id"));
                u.setName(rs.getString("name"));
                u.setEmail(rs.getString("email")); 
                u.setRole(rs.getString("role"));
                u.setPhone(rs.getString("phone"));
                list.add(u);
            }
        } catch(Exception e){ e.printStackTrace(); }
        return list;
    }
}