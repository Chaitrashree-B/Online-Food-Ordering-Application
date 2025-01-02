package com.foodapp.DBUtil;

import java.sql.Connection;
import java.sql.DriverManager;
public class DBConnection {
	private static final String URL = "jdbc:mysql://localhost:3306/foodapp";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";
    private static Connection connection;
    
    public static Connection connect() {
    	
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

}
