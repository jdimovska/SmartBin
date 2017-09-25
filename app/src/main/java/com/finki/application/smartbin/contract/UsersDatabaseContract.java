package com.finki.application.smartbin.contract;

import android.provider.BaseColumns;

/**
 * Created by Jona Dimovska on 25.9.2017.
 */

public class UsersDatabaseContract {

    public static class UsersEntry implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_POINTS= "points";
        public static final String COLUMN_NAME_USERNAME= "username";

    }
}
