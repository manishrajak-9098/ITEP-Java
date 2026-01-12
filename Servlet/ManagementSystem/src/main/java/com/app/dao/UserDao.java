package com.app.dao;

import java.sql.*;
import java.util.*;
import com.app.connection.DBConnection;
import com.app.dto.User;

public class UserDao {

    public boolean register(User u) {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "insert into users(name,email,password,address) values(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, u.getName());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getPassword());
            ps.setString(4, u.getAddress());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean login(String email, String pass) {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "select * from users where email=? and password=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            return false;
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            ResultSet rs = con.prepareStatement("select * from users").executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt(1));
                u.setName(rs.getString(2));
                u.setEmail(rs.getString(3));
                u.setPassword(rs.getString(4));
                u.setAddress(rs.getString(5));
                list.add(u);
            }
        } catch (Exception e) {}
        return list;
    }
}
