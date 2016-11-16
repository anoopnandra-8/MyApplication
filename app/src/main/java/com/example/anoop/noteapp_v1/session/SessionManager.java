package com.example.anoop.noteapp_v1.session;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.EditText;

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
    private static final String KEY_NAME="name";
    private static final String KEY_EMAIL="email";


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

    public void checkLogin(){
        //if(!this.isLoggedIN(){do something herer}))}
    }

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_EMAIL, preferences.getString(KEY_NAME, null));
        user.put(KEY_NAME, preferences.getString(KEY_EMAIL, null));
        return user;
    }

    public boolean isLoggedIn(){
        return preferences.getBoolean(IS_LOGIN, false);
    }


}
