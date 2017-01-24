package com.ts.DAO;

import com.ts.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by i.nasim on 18-Jan-17.
 */
public interface UserDAO {
    public List<Object> getAllUsers();
    public ResultSet getUserById(int userId);
    public ResultSet getUserIdsByLimit(int limit);
    public void archiveUsersBatch(List<Integer> userIds) throws SQLException;
    public void deleteUsersBatch(List<Integer> userIds, boolean isInClause) throws SQLException;
    public void addUsersBatch(List<User> users) throws SQLException;
}
