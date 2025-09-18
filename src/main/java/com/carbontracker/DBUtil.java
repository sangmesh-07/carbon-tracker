package com.carbontracker;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/carbon_tracker";
    private static final String USER = "root";       // your MySQL username
    private static final String PASSWORD = "Sangu@78922";   // your MySQL password

    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
