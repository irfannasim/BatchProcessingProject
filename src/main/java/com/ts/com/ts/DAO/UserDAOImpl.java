package com.ts.com.ts.DAO;

import com.ts.config.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by i.nasim on 18-Jan-17.
 */
public class UserDAOImpl implements UserDAO {
    Connection con;

    public List<Object> getAllUsers() {
        return null;
    }

    public ResultSet getUserById(int userId) {
        ResultSet rs = null;
        PreparedStatement statement;
        con = DBConnection.getJDBCConnection();

        StringBuilder query = new StringBuilder();
        query.append("SELECT * ")
                .append("FROM user_details ud ")
                .append("WHERE ud.user_id = ? ");

        try {
            statement = con.prepareStatement(query.toString());
            statement.setInt(1, userId);
            rs = statement.executeQuery();

            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
