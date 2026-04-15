import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

public class UserDAO {

    // REGISTER
    public int register(User u) {
        int result = 0;
        try {
            Connection con = GetConnection.getConnect();
            String sql =
              "insert into servlet_user(username,email,password,dob,address,gender) values(?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getPassword());
            ps.setString(4, u.getDob());
            ps.setString(5, u.getAddress());
            ps.setString(6, u.getGender());

            result = ps.executeUpdate(); 
        }
        catch (SQLIntegrityConstraintViolationException e) {
            result = 2; 
        }
        catch (Exception e) {
            System.out.println("Exception : " + e);
        }
        return result;
    }

    // Login
    public boolean login(String email, String password) {
        try {
            Connection con = GetConnection.getConnect();
            String sql =
              "select * from servlet_user where email=? and password=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
}
