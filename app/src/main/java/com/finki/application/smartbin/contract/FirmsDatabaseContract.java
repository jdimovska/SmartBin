package com.finki.application.smartbin.contract;

import android.provider.BaseColumns;

/**
 * Created by Jona Dimovska on 28.8.2017.
 */

public class FirmsDatabaseContract {
    public static class FirmsEntry implements BaseColumns {
        public static final String TABLE_NAME = "firms";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_LOCATION = "location";
        public static final String COLUMN_NAME_PHONE = "phone";
        public static final String COLUMN_NAME_URL = "url";

    }
}
