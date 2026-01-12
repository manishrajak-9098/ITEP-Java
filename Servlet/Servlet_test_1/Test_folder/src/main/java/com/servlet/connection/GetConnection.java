package com.servlet.connection;

import java.sql.*;

public class GetConnection {

    public static Connection getConnect() {

        Connection con = null;

        try {
            Class.forName(GetDetails.DRIVER);
            con = DriverManager.getConnection(
                    GetDetails.URL,
                    GetDetails.USERNAME,
                    GetDetails.PASSWORD);

            Statement st = con.createStatement();
            st.execute(
              "CREATE TABLE IF NOT EXISTS task (" +
              "id INT AUTO_INCREMENT PRIMARY KEY," +
              "category VARCHAR(50)," +
              "task VARCHAR(100)," +
              "priority VARCHAR(20)," +
              "status VARCHAR(20) DEFAULT 'Pending')" );

        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
