package com.jsp.dao;
import java.sql.*;
import java.util.*;
import com.jsp.model.Feedback;

public class FeedbackDao {

    // 1. Save Feedback
    public static void saveFeedback(Feedback f) {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO feedbacks(name, email, message) VALUES(?,?,?)");
            ps.setString(1, f.getName());
            ps.setString(2, f.getEmail());
            ps.setString(3, f.getMessage());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    // 2. Get All Feedbacks (For Admin)
    public static List<Feedback> getAllFeedbacks() {
        List<Feedback> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM feedbacks ORDER BY id DESC");
            while(rs.next()) {
                Feedback f = new Feedback();
                f.setId(rs.getInt("id"));
                f.setName(rs.getString("name"));
                f.setEmail(rs.getString("email"));
                f.setMessage(rs.getString("message"));
                f.setSentDate(rs.getTimestamp("sent_date"));
                list.add(f);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
}