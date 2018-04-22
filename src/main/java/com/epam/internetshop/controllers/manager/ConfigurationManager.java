package com.epam.internetshop.controllers.manager;

import java.util.ResourceBundle;

public class ConfigurationManager {

    private static ConfigurationManager instance;
    private ResourceBundle resourceBundle;
    private static final String BUNDLE_NAME = "config";

    public static final String INDEX_PAGE_PATH = "INDEX_PAGE_PATH";
    public static final String MAIN_PAGE_PATH = "MAIN_PAGE_PATH";
    public static final String BUCKET_PAGE_PATH = "BUCKET_PAGE_PATH";
    public static final String PROFILE_PAGE_PATH = "PROFILE_PAGE_PATH";

    public static final String FONTS_PATH = "FONTS_PATH";
    public static final String IMAGES_PATH = "IMAGES_PATH";
    public static final String JS_PATH = "JS_PATH";
    public static final String CSS_PATH = "CSS_PATH";
    public static final String DB_PATH = "DB_PATH";

    public static final String LOGIN_ACTION = "LOGIN_ACTION";
    public static final String REGISTER_ACTION = "REGISTER_ACTION";
    public static final String PROCESS_ACTION = "PROCESS_ACTION";

    private ConfigurationManager() {
        resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
    }

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
        }
        return instance;
    }

    public String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
