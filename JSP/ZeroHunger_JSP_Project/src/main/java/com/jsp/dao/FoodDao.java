package com.jsp.dao;

import java.sql.*;
import java.util.*;
import com.jsp.model.Food;

public class FoodDao {

    // 1. ADD FOOD
    public static int addFood(Food f) {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO food_items(donor_id, food_name, quantity, place_name, full_address, contact_no, pickup_time, note) VALUES(?,?,?,?,?,?,?,?)");
            ps.setInt(1, f.getDonorId());
            ps.setString(2, f.getFoodName());
            ps.setString(3, f.getQuantity());
            ps.setString(4, f.getPlaceName());
            ps.setString(5, f.getFullAddress());
            ps.setString(6, f.getContactNo());
            ps.setString(7, f.getPickupTime());
            ps.setString(8, f.getNote());
            return ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); return 0; }
    }

    // 2. CLAIM FOOD (NGO Claim Logic)
    public static void claimFood(int foodId, int ngoId) {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE food_items SET status='CLAIMED', ngo_id=? WHERE food_id=?");
            ps.setInt(1, ngoId);
            ps.setInt(2, foodId);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    // 3. CHECK IF DONOR IS SAME AS CLAIMER (To prevent error)
    public static boolean isDonorSameAsClaimer(int foodId, int userId) {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT donor_id FROM food_items WHERE food_id=?");
            ps.setInt(1, foodId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getInt("donor_id") == userId;
            }
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    // 4. UPDATE STATUS (COLLECTED etc.)
    public static void updateStatus(int id, String status) {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE food_items SET status=? WHERE food_id=?");
            ps.setString(1, status);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    // 5. GET AVAILABLE FOOD
    public static List<Food> getAvailableFood() {
        return getList("SELECT * FROM food_items WHERE status='AVAILABLE' ORDER BY food_id DESC");
    }

    // 6. GET FOOD BY DONOR (Join for NGO Details)
    public static List<Food> getFoodByDonor(int donorId) {
        List<Food> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT f.*, u.name AS n_name, u.email AS n_email FROM food_items f LEFT JOIN users u ON f.ngo_id = u.user_id WHERE f.donor_id = ? ORDER BY f.food_id DESC";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, donorId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Food f = mapRow(rs);
                f.setNgoName(rs.getString("n_name") != null ? rs.getString("n_name") + " (" + rs.getString("n_email") + ")" : "-");
                list.add(f);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    // 7. GET FOOD BY NGO
    public static List<Food> getFoodByNGO(int ngoId) {
        return getList("SELECT * FROM food_items WHERE ngo_id=" + ngoId + " ORDER BY food_id DESC");
    }

    // 8. ADMIN LOGS
    public static List<Food> getAllFoodAdmin() {
        List<Food> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT f.*, d.name AS d_name, n.name AS n_name FROM food_items f LEFT JOIN users d ON f.donor_id = d.user_id LEFT JOIN users n ON f.ngo_id = n.user_id ORDER BY food_id DESC";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while(rs.next()){
                Food f = mapRow(rs);
                f.setDonorName(rs.getString("d_name"));
                f.setNgoName(rs.getString("n_name") != null ? rs.getString("n_name") : "-");
                list.add(f);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    // Helper: Map Rows
    private static Food mapRow(ResultSet rs) throws SQLException {
        Food f = new Food();
        f.setFoodId(rs.getInt("food_id"));
        f.setFoodName(rs.getString("food_name"));
        f.setQuantity(rs.getString("quantity"));
        f.setFullAddress(rs.getString("full_address"));
        f.setContactNo(rs.getString("contact_no"));
        f.setPickupTime(rs.getString("pickup_time"));
        f.setStatus(rs.getString("status"));
        f.setPostedTime(rs.getTimestamp("posted_time"));
        return f;
    }

    // Helper: Execute Query
    private static List<Food> getList(String sql) {
        List<Food> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection(); ResultSet rs = con.createStatement().executeQuery(sql)) {
            while(rs.next()) list.add(mapRow(rs));
        } catch(Exception e) { e.printStackTrace(); }
        return list;
    }
}