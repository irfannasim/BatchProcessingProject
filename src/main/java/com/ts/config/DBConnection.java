package com.ts.config;

import com.ts.util.LogUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 * Created by i.nasim on 18-Jan-17.
 */
public class DBConnection {

    public static Connection getJDBCConnection() {
        Connection con = null;

        try {
            Class.forName(TSResourceBundle.DB_BUNDLE.getString("driverName"));
            LogUtil.log("Congrats - Seems your MySQL JDBC Driver Registered!", Level.INFO, null);
        } catch (ClassNotFoundException e) {
            LogUtil.log("Sorry, couldn't found JDBC driver. Make sure you have added JDBC Maven Dependency Correctly", Level.INFO, null);
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
                LogUtil.log("Connection Successful! Enjoy. Now it's time to play with data", Level.INFO, null);
            } else {
                LogUtil.log("Failed to make connection!", Level.INFO, null);
            }
        } catch (SQLException e) {
            LogUtil.log("MySQL Connection Failed!", Level.INFO, null);
            e.printStackTrace();
            return con;
        }
        return con;
    }
}
