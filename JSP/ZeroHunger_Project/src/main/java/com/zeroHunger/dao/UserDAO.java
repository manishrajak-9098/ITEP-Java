package com.zeroHunger.dao;
import java.sql.*;
import com.zeroHunger.model.User;
public class UserDAO {
    public static User login(String email,String pass){
        try{
            Connection con=DBConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(
                "select * from users where email=? and password=?");
            ps.setString(1,email);ps.setString(2,pass);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                User u=new User();
                u.setUserId(rs.getInt("user_id"));
                u.setName(rs.getString("name"));
                u.setRole(rs.getString("role"));
                return u;
            }
        }catch(Exception e){e.printStackTrace();}
        return null;
    }
}