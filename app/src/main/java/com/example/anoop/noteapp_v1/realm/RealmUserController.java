package com.example.anoop.noteapp_v1.realm;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.anoop.noteapp_v1.models.Users;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by anoop on 19/11/2016.
 */

public class RealmUserController {
    private static RealmUserController instance;
    private final Realm realm;

    public RealmUserController(Application application){
        realm=Realm.getDefaultInstance();
    }

    public static RealmUserController with(Fragment fragment){
        if(instance == null){
            instance = new RealmUserController(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static RealmUserController with(Activity activity){
        if(instance == null){
            instance = new RealmUserController(activity.getApplication());
        }
        return instance;
    }

    public static RealmUserController with(Application application){
        if(instance == null){
            instance = new RealmUserController(application);
        }
        return instance;
    }

    public static RealmUserController getInstance(){
        return instance;
    }

    public Realm getRealm(){
        return realm;
    }

    public void refresh(){
        realm.waitForChange();
    }

    public RealmResults<Users> getUsers() {

        return realm.where(Users.class).findAll();
    }

    public void clearAllUserData(){
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
    }

    public boolean checkEmailPrimaryKey(String email){
        RealmResults<Users> query = realm.where(Users.class).equalTo("email", email).findAll();
        if(query.size() == 0){
            return true;
        }
        return false;
    }

    public int getDBSize(){
        RealmResults<Users>query = realm.where(Users.class).findAll();
        return query.size();
    }




}
