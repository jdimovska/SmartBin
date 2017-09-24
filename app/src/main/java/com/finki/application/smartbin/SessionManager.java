package com.finki.application.smartbin;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class SessionManager {

    private static String TAG = SessionManager.class.getSimpleName();
    SharedPreferences pref;
    Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "SmartBinLogin";

    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";
    private static final String KEY_FULLNAME = "fullName";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_POINTS = "points";


    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLogin(boolean isLoggedIn) {
        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        editor.commit();
        Log.d(TAG, "User login session modified!");
    }
    public void setFullName(String fullName) {
        editor.putString(KEY_FULLNAME, fullName);
        editor.commit();
        Log.d(TAG, "User fullname stored!");
    }
    public void setUsername(String username) {
        editor.putString(KEY_USERNAME, username);
        editor.commit();
        Log.d(TAG, "User username stored!");
    }
    public void setEmail(String email) {
        editor.putString(KEY_EMAIL, email);
        editor.commit();
        Log.d(TAG, "User email stored!");
    }
    public void setPoints(String points) {
        editor.putString(KEY_POINTS, points);
        editor.commit();
        Log.d(TAG, "User points stored!");
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }

    public String getEmail() {
       return pref.getString(KEY_EMAIL,"");
    }
    public String getUsername() {
        return pref.getString(KEY_USERNAME,"");
    }
    public String getFullname() {
        return pref.getString(KEY_FULLNAME,"");
    }

    public String getPoints() {
        return pref.getString(KEY_POINTS,"");
    }


}