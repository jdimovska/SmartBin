package com.finki.application.smartbin.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.finki.application.smartbin.contract.FirmsDatabaseContract;

/**
 * Created by Jona Dimovska on 28.8.2017.
 */

public class FirmsDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "smartbin.db";
    public static final String SQL_CREATE_FIRMS =
            "CREATE TABLE " + FirmsDatabaseContract.FirmsEntry.TABLE_NAME + " (" +
                    FirmsDatabaseContract.FirmsEntry._ID + " INTEGER PRIMARY KEY," +
                    FirmsDatabaseContract.FirmsEntry.COLUMN_NAME_NAME + " TEXT," +
                    FirmsDatabaseContract.FirmsEntry.COLUMN_NAME_EMAIL + " TEXT," +
                    FirmsDatabaseContract.FirmsEntry.COLUMN_NAME_LOCATION + " TEXT," +
                    FirmsDatabaseContract.FirmsEntry.COLUMN_NAME_PHONE + " TEXT," +
                    FirmsDatabaseContract.FirmsEntry.COLUMN_NAME_URL + " TEXT);";
    public FirmsDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_FIRMS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
