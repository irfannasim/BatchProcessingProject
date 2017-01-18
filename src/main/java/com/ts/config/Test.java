package com.ts.config;

import com.ts.service.UserService;

import javax.jws.soap.SOAPBinding;

/**
 * Created by i.nasim on 18-Jan-17.
 */
public class Test {

    public static void main(String... args) {
        UserService userService = new UserService();
        System.out.println(userService.getUserById(14642).toString());
    }
}
