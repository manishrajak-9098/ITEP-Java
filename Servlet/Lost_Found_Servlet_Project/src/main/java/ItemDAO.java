import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ItemDAO {

    // Add Lost/Found item
	public boolean addItem(Item i) {
	    try (Connection con = GetConnection.getConnect()) {
	        String sql = "INSERT INTO lost_found_items (username,email,item_name,location,item_date,contact,status,description) VALUES (?,?,?,?,?,?,?,?)";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, i.getUsername());
	        ps.setString(2, i.getEmail());
	        ps.setString(3, i.getItemName());
	        ps.setString(4, i.getLocation());
	        ps.setDate(5, java.sql.Date.valueOf(i.getItemDate()));
	        ps.setString(6, i.getContact());
	        ps.setString(7, i.getStatus());
	        ps.setString(8, i.getDescription());
	        return ps.executeUpdate() > 0;
	    } catch (Exception e) { e.printStackTrace(); return false; }
	}
	
	
    // View only Lost & Found items
    public ResultSet viewItems() {
        try {
            Connection con = GetConnection.getConnect();
            return con.createStatement().executeQuery(
                "SELECT * FROM lost_found_items "
              + "WHERE TRIM(LOWER(status)) IN ('lost','found')"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Resolve item
    public void resolveItem(int id, String name, String email) {
        try (Connection con = GetConnection.getConnect()) {

            String sql = "UPDATE lost_found_items SET "
                       + "status='Resolved', finder_name=?, finder_email=? "
                       + "WHERE id=?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setInt(3, id);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
