package com.ts.DAO;

import com.ts.config.DBConnection;
import com.ts.config.TSResourceBundle;
import com.ts.model.User;
import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.util.List;

/**
 * Created by i.nasim on 18-Jan-17.
 */
public class UserDAOImpl implements UserDAO {
    private Connection con;
    private ResultSet rs = null;
    private PreparedStatement statement;

    public List<Object> getAllUsers() {
        return null;
    }

    public ResultSet getUserIdsByLimit(int limit) {

        con = DBConnection.getJDBCConnection();

        StringBuilder query = new StringBuilder();
        query.append("SELECT ud.user_id ")
                .append("FROM user_details ud ")
                .append("LIMIT 0, ")
                .append(limit);

        try {
            statement = con.prepareStatement(query.toString());
            rs = statement.executeQuery();

            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet getUserById(int userId) {

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

    public void archiveUsersBatch(List<Integer> userIds) throws SQLException {
        con = DBConnection.getJDBCConnection();
        int batchSize = Integer.parseInt(TSResourceBundle.SYSTEM_BUNDLE.getString("batchSize"));

        try {
            if (userIds.size() > 0) {
                con.setAutoCommit(false);
                int index = 0;
                String query = "UPDATE user_details SET is_deleted = ? WHERE user_id = ? ";
                statement = con.prepareStatement(query);

                for (Integer id : userIds) {
                    statement.setBoolean(1, true);
                    statement.setInt(1, id);
                    statement.addBatch();

                    if (index % batchSize == 0) {
                        statement.executeBatch();
                    }
                    index++;
                }
                statement.executeBatch();
                con.commit();
            }
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    public void deleteUsersBatch(List<Integer> userIds, boolean isInClause) throws SQLException {
        con = DBConnection.getJDBCConnection();
        int batchSize = Integer.parseInt(TSResourceBundle.SYSTEM_BUNDLE.getString("batchSize"));
        String userIdsStr = StringUtils.join(userIds, ",");

        try {
            if (userIds.size() > 0) {
                con.setAutoCommit(false);
                int index = 0;


                if (isInClause) {
                    String query = "DELETE FROM user_details WHERE user_id IN (:userIds) ";
                    query = query.replace(":userIds", userIdsStr);
                    statement = con.prepareStatement(query);
                    statement.addBatch();
                } else {
                    String query = "DELETE FROM user_details WHERE user_id = ? ";
                    statement = con.prepareStatement(query);

                    for (Integer id : userIds) {
                        statement.setInt(1, id);
                        statement.addBatch();

                        if (index % batchSize == 0) {
                            statement.executeBatch();
                        }
                        index++;
                    }
                }
                statement.executeBatch();
                con.commit();
            }
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    public void addUsersBatch(List<User> users) throws SQLException {
        con = DBConnection.getJDBCConnection();
        int batchSize = Integer.parseInt(TSResourceBundle.SYSTEM_BUNDLE.getString("batchSize"));

        try {
            if (users.size() > 0) {
                con.setAutoCommit(false);
                int index = 0;
                String query = "INSERT INTO user_details(username, first_name, last_name, gender, password, status, is_deleted) " +
                        "VALUES(?, ?, ?, ?, ?, ?, ?) ";
                statement = con.prepareStatement(query);

                for (User user : users) {
                    statement.setString(1, user.getUserName());
                    statement.setString(2, user.getFirstName());
                    statement.setString(3, user.getLastName());
                    statement.setString(4, user.getGender());
                    statement.setString(5, user.getPassword());
                    statement.setBoolean(6, user.isStatus());
                    statement.setBoolean(7, user.isDeleted());
                    statement.addBatch();

                    if (index % batchSize == 0) {
                        statement.executeBatch();
                    }
                    index++;
                }
                statement.executeBatch();
                con.commit();
            }
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

}
