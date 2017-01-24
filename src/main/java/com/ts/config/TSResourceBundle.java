package com.ts.config;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by i.nasim on 18-Jan-17.
 */
public class TSResourceBundle {

    public static final ResourceBundle DB_BUNDLE = ResourceBundle.getBundle("DBProperties", Locale.ENGLISH);
    public static final ResourceBundle SYSTEM_BUNDLE = ResourceBundle.getBundle("system", Locale.ENGLISH);

}
