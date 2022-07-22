package com.example.nmmovie.Session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.nmmovie.LoginActivity;

import java.util.HashMap;

public class SessionManager {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;
    int Private_mode = 0;
    private static final String PREF_NAME = "AndroidHivePref";
    private static final String IS_LOGIN = "IsLoggedIn";
    private static final String KEY_NAMA = "namalengkap";
    private static final String KEY_TELEPON = "nomortelepon";

    public SessionManager (Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Private_mode);
        editor = sharedPreferences.edit();
    }

    public void createLoginSession(String nomortelepon, String namalengkap) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_NAMA, namalengkap);
        editor.putString(KEY_TELEPON, nomortelepon);
        editor.commit();
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(IS_LOGIN, false);
    }

    public void checkLogin() {
        if (!this.isLoggedIn()) {
            Intent intent = new Intent(context, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_NAMA, sharedPreferences.getString(KEY_NAMA, null));
        user.put(KEY_TELEPON, sharedPreferences.getString(KEY_TELEPON, null));
        return user;
    }

    public void logoutUser() {
        editor.clear();
        editor.commit();

        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
