package com.example.anoop.noteapp_v1.Fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.anoop.noteapp_v1.activities.NotesActivity;
import com.example.anoop.noteapp_v1.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by anoop on 10/10/2016.
 */

public class MainFragment extends Fragment {

    @BindView(R.id.mainFragImg_button)
    ImageButton addNoteFrag_imgButton;

    @BindView(R.id.mainFrag_textView)
    TextView addNoteFrag_txtView;

    public MainFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.mainFragImg_button)
    public void addNoteFragment(){
        Intent intent = new Intent(getActivity(), NotesActivity.class);
        startActivity(intent);
    }
}
