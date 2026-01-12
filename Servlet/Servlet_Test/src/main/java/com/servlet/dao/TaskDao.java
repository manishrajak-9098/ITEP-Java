package com.servlet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.servlet.connection.GetConnection;
import com.servlet.dto.Task;


public class TaskDao{
	
	//add task
	public int addTask(Task tx) {
		int i=0;
		try {
			Connection con = GetConnection.getConnect();
			String insertQuery = "insert into servlet_test(category, task, priority) values(?,?,?)";
			PreparedStatement ps = con.prepareStatement(insertQuery);
			ps.setString(1, tx.getCategory());
			ps.setString(2, tx.getTask());
			ps.setString(3, tx.getPriority());
		
			
			i = ps.executeUpdate();
			System.out.println("User added successfully in dao");
		}catch(Exception e) {
			i=2;
			System.out.println("Exception : "+e);
		}
		return i;
	}
	
	
	  //view task
    public List<Task> viewTasks() {
        List<Task> list = new ArrayList<>();

        try {
            Connection con = GetConnection.getConnect();
            String selectQuery = "select * from servlet_test";
            PreparedStatement ps = con.prepareStatement(selectQuery);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Task t = new Task();
//                t.setId(rs.getInt("id"));           // agar id column hai
                t.setCategory(rs.getString("category"));
                t.setTask(rs.getString("task"));

                list.add(t);
            }

        } catch (Exception e) {
            System.out.println("Exception in viewTasks : " + e);
        }
        return list;
	
} 
}