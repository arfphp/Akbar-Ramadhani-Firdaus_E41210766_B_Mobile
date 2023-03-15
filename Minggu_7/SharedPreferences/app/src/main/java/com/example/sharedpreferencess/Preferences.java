package com.example.sharedpreferencess;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preferences {
//    Pendeklarasian key-data berupa String, sebagai wadah penyimpanan data.
//    Jadi setiap data mempunyai key yang berbeda satu sama lain
    static final String KEY_USER_TEREGISTER = "user", KEY_PASS_TEREGISTER="pass";
    static final String KEY_USERNAME_SEDANG_LOGIN = "Username_logged_in";
    static final String KEY_STATUS_SEDANG_LOGIN = "Status_logged_in";

//    Pendeklarasian Shared Preferences yang berdasarkan parameter context
    private static SharedPreferences getSharedPreferences(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

//    Pendeklarasian Edit Preferences dan mengubah data
//    yang memiliki key isi KEY_USER_TEREGISTER dengan parameter username
    public static void setRegisteredUser(Context context, String username){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(KEY_USER_TEREGISTER, username);
        editor.apply();
    }

//    Mengembalikan nilai dari key KEY_USER_TEREGISTER berupa String
    public static String getRegisteredUser(Context context){
        return getSharedPreferences(context).getString(KEY_USER_TEREGISTER,"");
    }
//    Pendeklarasian Edit Preferences dan mengubah data
//    yang memiliki key isi KEY_PASS_TEREGISTER dengan parameter password
    public static void setRegisteredPass(Context context, String password){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(KEY_PASS_TEREGISTER, password);
        editor.apply();
    }

//    Mengembalikan nilai dari key KEY_PASS_TEREGISTER berupa String
    public static String getRegisteredPass(Context context){
        return getSharedPreferences(context).getString(KEY_PASS_TEREGISTER,"");
    }
//    Pendeklarasian Edit Preferences dan mengubah data
//    yang memiliki key isi KEY_USERNAME_SEDANG_LOGIN dengan parameter username
    public static void setLoggedInUser(Context context,String username){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(KEY_USERNAME_SEDANG_LOGIN, username);
        editor.apply();
    }

//    Mengembalikan nilai dari key KEY_USERNAME_SEDANG_LOGIN berupa String
    public static String getLoggedInUser(Context context){
        return getSharedPreferences(context).getString(KEY_USERNAME_SEDANG_LOGIN,"");
    }

//    Pendeklarasian Edit Preferences dan mengubah data
//    yang memiliki key isi KEY_STATIS_SEDANG_LOGIN dengan parameter status
    public static void setLoggedInStatus(Context context, Boolean status){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putBoolean(KEY_STATUS_SEDANG_LOGIN, status);
        editor.apply();
    }

//    Mengembalikan nilai dari key KEY_STATUS_SEDANG_LOGIN berupa boolean
    public static boolean getLoggedInStatus(Context context){
        return getSharedPreferences(context).getBoolean(KEY_STATUS_SEDANG_LOGIN, false);
    }

//    Pendeklarasian Edit Preferences dan menghapus data, sehingga menjadikannya bernilai default
//    khusus data yang memiliki key KEY_USERNAME_SEDANG_LOGIN dan KEY_STATUS_SEDANG_LOGIN
    public static void clearLoggedInUser(Context context){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.remove(KEY_USERNAME_SEDANG_LOGIN);
        editor.remove(KEY_STATUS_SEDANG_LOGIN);
        editor.apply();
    }

}
