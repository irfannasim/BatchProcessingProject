package com.ts.config;

import com.ts.model.User;
import com.ts.service.UserService;
import com.ts.util.LogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * Created by i.nasim on 18-Jan-17.
 */
public class Test {


    public static void main(String... args) {
        //System.out.println(getUserById(14642));
        LogUtil.log("main starts at: " + System.currentTimeMillis(), Level.INFO, null);
        int queryLimit = Integer.parseInt(TSResourceBundle.SYSTEM_BUNDLE.getString("queryDataLimit"));
        //System.out.println(10 % 1000);
        //System.out.println(getUserIdsInStringByLimit(queryLimit);
        //List<Integer> ids = getUserIdsInIntegerByLimit(queryLimit);
        //deleteUsersBatch(ids, false);


        List<User> users = new ArrayList<User>();

        for (int i = 1; i <= queryLimit; i++) {
            User user = new User();
            user.setUserName("testUserName");
            user.setFirstName("testFirstName");
            user.setLastName("testLastName");
            user.setGender("Male");
            user.setPassword("password");
            user.setStatus(true);
            user.setDeleted(false);
            users.add(user);
        }
        addUsersBatch(users);

        LogUtil.log("main starts at: " + System.currentTimeMillis(), Level.INFO, null);
    }

    public static void archiveUsersBatch(List<Integer> userIds) {
        UserService userService = new UserService();
        userService.archiveUsersBatch(userIds);
    }

    public static void deleteUsersBatch(List<Integer> userIds, Boolean isInClause) {
        UserService userService = new UserService();
        userService.deleteUsersBatch(userIds, isInClause);
    }

    public static void addUsersBatch(List<User> users) {
        UserService userService = new UserService();
        userService.addUsersBatch(users);
    }

    private static String getUserById(int userId) {
        UserService userService = new UserService();
        return userService.getUserById(userId).toString();
    }

    private static String getUserIdsInStringByLimit(int limit) {
        UserService userService = new UserService();
        return userService.getUserIdsInStringByLimit(limit);
    }

    private static List<Integer> getUserIdsInIntegerByLimit(int limit) {
        UserService userService = new UserService();
        return userService.getUserIdsInIntegerByLimit(limit);
    }
}
