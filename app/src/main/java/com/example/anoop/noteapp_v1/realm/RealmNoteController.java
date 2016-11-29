package com.example.anoop.noteapp_v1.realm;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import com.example.anoop.noteapp_v1.models.Note;
import com.example.anoop.noteapp_v1.models.Users;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by anoop on 22/11/2016.
 */

public class RealmNoteController {
    private static RealmNoteController instance;
    private final Realm realm;

    public RealmNoteController(Application application){
        realm=Realm.getDefaultInstance();
    }

    public static RealmNoteController with(Fragment fragment){
        if(instance == null){
            instance = new RealmNoteController(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static RealmNoteController with(Activity activity){
        if(instance == null){
            instance = new RealmNoteController(activity.getApplication());
        }
        return instance;
    }

    public static RealmNoteController with(Application application){
        if(instance == null){
            instance = new RealmNoteController(application);
        }
        return instance;
    }

    public static RealmNoteController getInstance(){
        return instance;
    }

    public Realm getRealm(){
        return realm;
    }

    public void refresh(){
        realm.waitForChange();
    }

    public RealmResults<Note> getNotes() {

        return realm.where(Note.class).findAll();
    }

    public void clearAllUserData(){
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
    }

    public boolean checkNoteID(long noteID){
        RealmResults<Note> query = realm.where(Note.class).equalTo("noteID", noteID).findAll();
        if(query.size() == 0){
            return false;
        }
        return true;
    }

    public int getDBSize(){
        RealmResults<Note>query = realm.where(Note.class).findAll();
        return query.size();
    }
}
