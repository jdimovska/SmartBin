package com.finki.application.smartbin.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.finki.application.smartbin.contract.FirmsDatabaseContract;
import com.finki.application.smartbin.contract.UsersDatabaseContract;

/**
 * Created by Jona Dimovska on 25.9.2017.
 */

public class AppDatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "smartbin.db";
    public static final String SQL_CREATE_USERS =
            "CREATE TABLE " + UsersDatabaseContract.UsersEntry.TABLE_NAME + " (" +
                    UsersDatabaseContract.UsersEntry._ID + " INTEGER PRIMARY KEY," +
                    UsersDatabaseContract.UsersEntry.COLUMN_NAME_NAME + " TEXT," +
                    UsersDatabaseContract.UsersEntry.COLUMN_NAME_EMAIL + " TEXT," +
                    UsersDatabaseContract.UsersEntry.COLUMN_NAME_POINTS + " DOUBLE," +
                    UsersDatabaseContract.UsersEntry.COLUMN_NAME_USERNAME + " TEXT);";

    public static final String SQL_CREATE_FIRMS =
            "CREATE TABLE " + FirmsDatabaseContract.FirmsEntry.TABLE_NAME + " (" +
                    FirmsDatabaseContract.FirmsEntry._ID + " INTEGER PRIMARY KEY," +
                    FirmsDatabaseContract.FirmsEntry.COLUMN_NAME_NAME + " TEXT," +
                    FirmsDatabaseContract.FirmsEntry.COLUMN_NAME_EMAIL + " TEXT," +
                    FirmsDatabaseContract.FirmsEntry.COLUMN_NAME_LOCATION + " TEXT," +
                    FirmsDatabaseContract.FirmsEntry.COLUMN_NAME_PHONE + " TEXT," +
                    FirmsDatabaseContract.FirmsEntry.COLUMN_NAME_URL + " TEXT);";
    public AppDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_FIRMS);
        db.execSQL(SQL_CREATE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE firms");
        db.execSQL("DROP TABLE firms");
        db.execSQL(SQL_CREATE_FIRMS);
        db.execSQL(SQL_CREATE_USERS);
    }
}
