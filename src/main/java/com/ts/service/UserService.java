package com.ts.service;

import com.ts.DAO.UserDAO;
import com.ts.DAO.UserDAOImpl;
import com.ts.model.User;
import com.ts.util.LogUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * Created by i.nasim on 18-Jan-17.
 */
public class UserService {
    UserDAO userDAO = new UserDAOImpl();

    public User getUserById(int userId) {
        ResultSet rs = userDAO.getUserById(userId);
        return buildUserWrapper(rs);
    }

    public String getUserIdsInStringByLimit(int limit) {
        LogUtil.log("getUserIdsByLimit starts for " + limit + "records " + "at: " + System.currentTimeMillis(), Level.INFO, null);

        ResultSet rs = userDAO.getUserIdsByLimit(limit);

        LogUtil.log("getUserIdsByLimit ends for " + limit + "records " + "at: " + System.currentTimeMillis(), Level.INFO, null);
        return buildStringOfIds(rs);
    }

    public List<Integer> getUserIdsInIntegerByLimit(int limit) {
        LogUtil.log("getUserIdsByLimit starts for " + limit + "records " + "at: " + System.currentTimeMillis(), Level.INFO, null);

        ResultSet rs = userDAO.getUserIdsByLimit(limit);

        LogUtil.log("getUserIdsByLimit ends for " + limit + "records " + "at: " + System.currentTimeMillis(), Level.INFO, null);
        return buildIntegerOfIds(rs);
    }

    public void archiveUsersBatch(List<Integer> userIds) {
        LogUtil.log("archiveUsersBatch starts for " + userIds.size() + "records " + "at: " + System.currentTimeMillis(), Level.INFO, null);

        try {
            userDAO.archiveUsersBatch(userIds);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        LogUtil.log("archiveUsersBatch ends for " + userIds.size() + "records " + "at: " + System.currentTimeMillis(), Level.INFO, null);
    }

    public void deleteUsersBatch(List<Integer> userIds, Boolean isInClause) {
        LogUtil.log("deleteUsersBatch starts for " + userIds.size() + "records " + "at: " + System.currentTimeMillis(), Level.INFO, null);

        try {
            userDAO.deleteUsersBatch(userIds, isInClause);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        LogUtil.log("deleteUsersBatch ends for " + userIds.size() + "records " + "at: " + System.currentTimeMillis(), Level.INFO, null);
    }

    public void addUsersBatch(List<User> users) {
        LogUtil.log("addUsersBatch starts for " + users.size() + "records " + "at: " + System.currentTimeMillis(), Level.INFO, null);

        try {
            userDAO.addUsersBatch(users);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        LogUtil.log("addUsersBatch ends for " + users.size() + "records " + "at: " + System.currentTimeMillis(), Level.INFO, null);
    }

    private User buildUserWrapper(ResultSet userRS) {
        User user = new User();
        LogUtil.log("buildUserWrapper starts at: " + System.currentTimeMillis(), Level.INFO, null);

        try {
            while (userRS.next()) {
                user.setUserId(userRS.getInt("user_id"));
                user.setUserName(userRS.getString("username"));
                user.setFirstName(userRS.getString("first_name"));
                user.setLastName(userRS.getString("last_name"));
                user.setGender(userRS.getString("gender"));
                user.setPassword(userRS.getString("password"));
                user.setStatus(userRS.getBoolean("status"));
                user.setDeleted(userRS.getBoolean("is_deleted"));
            }
        } catch (SQLException ex) {
            LogUtil.log("buildUserWrapper starts at: " + System.currentTimeMillis(), Level.INFO, null);
            ex.printStackTrace();
        }
        LogUtil.log("buildUserWrapper ends at: " + System.currentTimeMillis(), Level.INFO, null);
        return user;
    }

    private String buildStringOfIds(ResultSet idsRS) {

        LogUtil.log("buildStringOfIds starts at: " + System.currentTimeMillis(), Level.INFO, null);
        StringBuilder ids = new StringBuilder();
        try {
            while (idsRS.next()) {
                ids.append(idsRS.getString(1));
                ids.append(",");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        LogUtil.log("buildStringOfIds ends at: " + System.currentTimeMillis(), Level.INFO, null);
        return ids.deleteCharAt(ids.length() - 1).toString();
    }

    private List<Integer> buildIntegerOfIds(ResultSet idsRS) {

        LogUtil.log("buildIntegersOfIds starts at: " + System.currentTimeMillis(), Level.INFO, null);
        List<Integer> ids = new ArrayList<Integer>();
        try {
            while (idsRS.next()) {
                ids.add(Integer.parseInt(idsRS.getString(1)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        LogUtil.log("buildIntegersOfIds ends at: " + System.currentTimeMillis(), Level.INFO, null);
        return ids;
    }
}
