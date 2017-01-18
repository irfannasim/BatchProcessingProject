package com.ts.com.ts.DAO;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by i.nasim on 18-Jan-17.
 */
public interface UserDAO {
    public List<Object> getAllUsers();
    public ResultSet getUserById(int userId);
}
