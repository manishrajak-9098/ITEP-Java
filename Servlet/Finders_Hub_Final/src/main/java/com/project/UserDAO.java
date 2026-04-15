package com.project;
import java.sql.*;

public class UserDAO {

    // REGISTER METHOD (Aapke logic ke hisab se)
    public int register(User u) {
        try (Connection con = GetConnection.getConnect()) {
            PreparedStatement ps = con.prepareStatement(
              "INSERT INTO users(username,email,password,dob,address,gender) VALUES(?,?,?,?,?,?)");

            ps.setString(1, u.getUsername());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getPassword());
            ps.setString(4, u.getDob());
            ps.setString(5, u.getAddress());
            ps.setString(6, u.getGender());

            return ps.executeUpdate(); // Returns 1 on success
        } catch (SQLIntegrityConstraintViolationException e) {
            return 2; // Duplicate Email Code
        } catch (Exception e) {
            e.printStackTrace();
            return 0; // Generic Error
        }
    }

    // LOGIN METHOD (Isse same rehne dein taaki login functionality na tute)
    public String[] login(String email, String password) {
        try (Connection con = GetConnection.getConnect()) {
            PreparedStatement ps = con.prepareStatement("SELECT username, email FROM users WHERE email=? AND password=?");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new String[]{rs.getString("username"), rs.getString("email")};
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}