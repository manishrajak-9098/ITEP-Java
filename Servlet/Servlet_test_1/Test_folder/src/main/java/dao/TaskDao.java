package dao;

import java.sql.*;
import java.util.*;
import com.servlet.connection.GetConnection;
import dto.Task;

public class TaskDao {

    public void addData(Task t) {
        try {
            Connection con = GetConnection.getConnect();
            PreparedStatement ps =
                con.prepareStatement(
                "insert into task(category,task,priority,status) values(?,?,?,?)");

            ps.setString(1, t.getCategory());
            ps.setString(2, t.getTask());
            ps.setString(3, t.getPriority());
            ps.setString(4, "Pending");

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Task> getAllTask() {
        List<Task> list = new ArrayList<>();

        try {
            Connection con = GetConnection.getConnect();
            ResultSet rs =
                con.createStatement().executeQuery("select * from task");

            while (rs.next()) {
                Task t = new Task();
                t.setId(rs.getInt("id"));
                t.setCategory(rs.getString("category"));
                t.setTask(rs.getString("task"));
                t.setPriority(rs.getString("priority"));
                t.setStatus(rs.getString("status"));
                list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
