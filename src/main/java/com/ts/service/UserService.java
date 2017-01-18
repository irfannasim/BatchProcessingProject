package com.ts.service;

import com.ts.com.ts.DAO.UserDAO;
import com.ts.com.ts.DAO.UserDAOImpl;
import com.ts.com.ts.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by i.nasim on 18-Jan-17.
 */
public class UserService {
    UserDAO userDAO = new UserDAOImpl();

    public User getUserById(int userId) {
        ResultSet rs = userDAO.getUserById(userId);
        return buildUserWrapper(rs);
    }

    private User buildUserWrapper(ResultSet userRS) {
        User user = new User();

        try {
            while (userRS.next()) {
                user.setUserId(userRS.getInt("user_id"));
                user.setUserName(userRS.getString("username"));
                user.setFirstName(userRS.getString("first_name"));
                user.setLastName(userRS.getString("last_name"));
                user.setGender(userRS.getString("gender"));
                user.setPassword(userRS.getString("password"));
                user.setStatus(userRS.getBoolean("status"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }
}
