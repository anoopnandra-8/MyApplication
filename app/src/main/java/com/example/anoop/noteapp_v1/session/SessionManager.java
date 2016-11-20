package com.example.anoop.noteapp_v1.session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.EditText;

import com.example.anoop.noteapp_v1.activities.SignUpActivity;

import java.util.HashMap;

/**
 * Created by anoop on 16/11/2016.
 */

public class SessionManager {

    private SharedPreferences preferences;
    private SharedPreferences.Editor edit;
    private Context _context;
    private int PRIVATE_MODE=0;
    private static final String PREF_NAME="UserPref";
    private static final String IS_LOGIN="isLoggedIn";
    public static final String KEY_NAME="name";
    public static final String KEY_EMAIL="email";


    public SessionManager(Context context){
        this._context=context;
        preferences=context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        edit=preferences.edit();
    }

    public void createUserSession(String name, String email){
        edit.putBoolean(IS_LOGIN, true);
        edit.putString(KEY_NAME, name);
        edit.putString(KEY_EMAIL, email);
        edit.commit();
    }

     public boolean checkLogin(){
        if(!this.isLoggedIn()){
            Intent intent = new Intent(_context, SignUpActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(intent);
            return false;
        }
        return true;
    }

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_EMAIL, preferences.getString(KEY_NAME, null));
        user.put(KEY_NAME, preferences.getString(KEY_EMAIL, null));
        return user;
    }

    public void clearSession(){
        edit.clear();
        edit.commit();
    }

    public boolean isLoggedIn(){
        return preferences.getBoolean(IS_LOGIN, false);
    }


}
