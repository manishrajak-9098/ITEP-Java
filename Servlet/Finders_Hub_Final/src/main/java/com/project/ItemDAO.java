package com.project;
import java.sql.*;
import java.util.*;

public class ItemDAO {

    // Helper method to map ResultSet to Object
	private Item mapRow(ResultSet rs) throws SQLException {
	    Item i = new Item();
	    i.setId(rs.getInt("id"));
	    i.setUsername(rs.getString("username"));
	    i.setEmail(rs.getString("email"));
	    i.setItemName(rs.getString("item_name"));
	    i.setLocation(rs.getString("location"));
	    i.setContact(rs.getString("contact"));
	    i.setDescription(rs.getString("description"));
	    i.setStatus(rs.getString("status"));
	    i.setItemDate(rs.getString("item_date"));
	    // YE DO LINE JAROOR ADD KAREIN
	    i.setFinderName(rs.getString("finder_name")); 
	    i.setFinderEmail(rs.getString("finder_email"));
	    return i;
	}

    public boolean addItem(Item item) {
        try (Connection con = GetConnection.getConnect()) {
            String sql = "INSERT INTO items(username,email,item_name,location,contact,description,status,item_date) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, item.getUsername()); ps.setString(2, item.getEmail());
            ps.setString(3, item.getItemName()); ps.setString(4, item.getLocation());
            ps.setString(5, item.getContact()); ps.setString(6, item.getDescription());
            ps.setString(7, item.getStatus()); ps.setString(8, item.getItemDate());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }
 // ItemDAO.java

    public List<Item> getAllItems(String emailFilter) {
        List<Item> list = new ArrayList<>();
        try (Connection con = GetConnection.getConnect()) {
            String sql;
            
            // Agar emailFilter null hai (Admin ke liye), to sab dikhao
            if (emailFilter == null) {
                sql = "SELECT * FROM items ORDER BY id DESC";
            } 
            // Agar user logged in hai, to USKA posted item YA USKA resolved item dikhao
            else {
                sql = "SELECT * FROM items WHERE email=? OR finder_email=? ORDER BY id DESC";
            }
            
            PreparedStatement ps = con.prepareStatement(sql);
            
            if(emailFilter != null) {
                ps.setString(1, emailFilter); // Check creator email
                ps.setString(2, emailFilter); // Check finder email (Ye line add karni padegi)
            }
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (Exception e) { 
            e.printStackTrace(); 
        }
        return list;
    }

    public boolean resolveItem(int id, String name, String email) {
        try (Connection con = GetConnection.getConnect()) {
            // Status update karo aur kisne dhoonda wo info daalo
            String sql = "UPDATE items SET status='Resolved', finder_name=?, finder_email=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name); 
            ps.setString(2, email); 
            ps.setInt(3, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { 
            e.printStackTrace(); 
            return false; 
        }
    }
    public Map<String, Integer> getStats() {
        Map<String, Integer> stats = new HashMap<>();
        stats.put("total", 0); stats.put("resolved", 0); stats.put("lost", 0); stats.put("found", 0);
        try (Connection con = GetConnection.getConnect()) {
            ResultSet rs = con.createStatement().executeQuery("SELECT status, COUNT(*) FROM items GROUP BY status");
            int total = 0;
            while(rs.next()){
                String s = rs.getString(1);
                int c = rs.getInt(2);
                if("Resolved".equalsIgnoreCase(s)) stats.put("resolved", c);
                else if("Lost".equalsIgnoreCase(s)) stats.put("lost", c);
                else if("Found".equalsIgnoreCase(s)) stats.put("found", c);
                total += c;
            }
            stats.put("total", total);
        } catch(Exception e){ e.printStackTrace(); }
        return stats;
    }
}