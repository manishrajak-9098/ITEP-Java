package com.app.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName(DBConfig.DRIVER);
            con = DriverManager.getConnection(
                    DBConfig.URL,
                    DBConfig.USERNAME,
                    DBConfig.PASSWORD
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
