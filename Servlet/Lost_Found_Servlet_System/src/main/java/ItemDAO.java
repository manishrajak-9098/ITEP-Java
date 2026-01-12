import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ItemDAO {

    // ADD ITEM (Lost ya Found dono)
    public void addItem(Item i) {
        try {
            Connection con = GetConnection.getConnect();

            String sql =
              "INSERT INTO lost_found_items " +
              "(username,email,item_name,location,item_date,contact,status,description) " +
              "VALUES (?,?,?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, i.getUsername());
            ps.setString(2, i.getEmail());
            ps.setString(3, i.getItemName());
            ps.setString(4, i.getLocation());
            ps.setString(5, i.getItemDate());
            ps.setString(6, i.getContact());

            // 🔥 USER JO SELECT KARE (Lost / Found)
            ps.setString(7, i.getStatus());

            ps.setString(8, i.getDescription());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔥 VIEW ONLY ACTIVE ITEMS
    public ResultSet viewItems() {
        try {
            Connection con = GetConnection.getConnect();
            return con.createStatement().executeQuery(
                "SELECT * FROM lost_found_items " +
                "WHERE status IN ('Lost','Found')"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 🔥 COMMON RESOLVE METHOD (Lost + Found dono ke liye)
    public void resolveItem(int id, String name, String email) {
        try {
            Connection con = GetConnection.getConnect();

            String sql =
              "UPDATE lost_found_items SET " +
              "status='Resolved', finder_name=?, finder_email=? " +
              "WHERE id=?";

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
