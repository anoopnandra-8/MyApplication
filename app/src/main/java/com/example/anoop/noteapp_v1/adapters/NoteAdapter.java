package com.example.anoop.noteapp_v1.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.anoop.noteapp_v1.models.Note;

/**
 * Created by anoop on 20/11/2016.
 */

public class NoteAdapter extends RealmRecyclerViewAdapter<Note> {
    static Context context;

    public NoteAdapter(Context context){
        this.context=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
