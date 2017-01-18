package com.ts.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by i.nasim on 18-Jan-17.
 */
public class DBConnection {

    public static Connection getJDBCConnection() {
        Connection con = null;

        try {
            Class.forName(TSResourceBundle.DB_BUNDLE.getString("driverName"));
            System.out.println("Congrats - Seems your MySQL JDBC Driver Registered!");
        } catch (ClassNotFoundException e) {
            System.out.println("Sorry, couldn't found JDBC driver. Make sure you have added JDBC Maven Dependency Correctly");
            e.printStackTrace();
            return con;
        }

        try {
            // DriverManager: The basic UserService for managing a set of JDBC drivers.
            String url = TSResourceBundle.DB_BUNDLE.getString("driverType")
                    + TSResourceBundle.DB_BUNDLE.getString("serverName") + ":"
                    + TSResourceBundle.DB_BUNDLE.getString("portNo")
                    + TSResourceBundle.DB_BUNDLE.getString("urlSeperator")
                    + TSResourceBundle.DB_BUNDLE.getString("databaseName");

            con = DriverManager.getConnection(url, TSResourceBundle.DB_BUNDLE.getString("userName"), TSResourceBundle.DB_BUNDLE.getString("password"));
            if (con != null) {
                System.out.println("Connection Successful! Enjoy. Now it's time to play with data");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.out.println("MySQL Connection Failed!");
            e.printStackTrace();
            return con;
        }
        return con;
    }
}
