package com.example.anoop.noteapp_v1.adapters;

import android.content.Context;

import com.example.anoop.noteapp_v1.models.Note;

import io.realm.RealmResults;

/**
 * Created by anoop on 20/11/2016.
 */

public class RealmNoteAdapter extends RealmModelAdapter<Note> {
    public RealmNoteAdapter(Context context, RealmResults realmResults, boolean automaticUpdate){
        super(context, realmResults, automaticUpdate);
    }
}
