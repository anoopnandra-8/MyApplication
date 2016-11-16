package com.example.anoop.noteapp_v1.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

    private LayoutInflater inflater;

    @BindView(R.id.mainFragImg_button)
    ImageButton addNoteFrag_imgButton;

    @BindView(R.id.mainFrag_textView)
    TextView addNoteFrag_txtView;

    @BindView(R.id.test_btn)
    Button alertDialogue_btn;

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

    @OnClick(R.id.test_btn)
    public void alertDialogue_btn(){
        inflater = MainFragment.this.getActivity().getLayoutInflater();
        View content = inflater.inflate(R.layout.progress_dialogue, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()); //context?
        builder.setView(content)
                .setTitle("Test")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
