package com.jsp.dao;

import java.sql.*;
import java.util.*;
import com.jsp.model.Food;

public class FoodDao {

    // ADD FOOD
    public static int addFood(Food f) {
        int status = 0;
        try {
            Connection con = DBConnection.getConnection();
          
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO food_items(donor_id, food_name, quantity, location) VALUES(?,?,?,?)"
            );
            ps.setInt(1, f.getDonorId());
            ps.setString(2, f.getFoodName());
            ps.setString(3, f.getQuantity());
            ps.setString(4, f.getLocation());

            status = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

   
    public static List<Food> getAvailableFood() {
        List<Food> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
           
            ResultSet rs = con.createStatement().executeQuery(
                "SELECT * FROM food_items WHERE status='AVAILABLE' ORDER BY food_id DESC"
            );

            while (rs.next()) {
                Food f = new Food();
                f.setFoodId(rs.getInt("food_id"));
                f.setFoodName(rs.getString("food_name"));
                f.setQuantity(rs.getString("quantity"));
                f.setLocation(rs.getString("location"));
                f.setStatus(rs.getString("status"));
                list.add(f);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    
    public static void updateStatus(int foodId, String status) {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "UPDATE food_items SET status=? WHERE food_id=?"
            );
            ps.setString(1, status);
            ps.setInt(2, foodId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}