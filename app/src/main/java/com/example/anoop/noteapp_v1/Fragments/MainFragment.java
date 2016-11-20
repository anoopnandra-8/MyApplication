package com.example.anoop.noteapp_v1.fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.anoop.noteapp_v1.activities.NotesActivity;
import com.example.anoop.noteapp_v1.R;
import com.example.anoop.noteapp_v1.realm.RealmUserController;
import com.example.anoop.noteapp_v1.session.SessionManager;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmChangeListener;

/**
 * Created by anoop on 10/10/2016.
 */

public class MainFragment extends Fragment {

    private LayoutInflater inflater;
    private SessionManager sessionManager;
    private Realm realm;
    private RealmChangeListener realmChangeListener;
    private String EMAIl, NAME;
    
    @BindView(R.id.mainFragImg_button)
    ImageButton addNoteFrag_imgButton;

    @BindView(R.id.mainFrag_textView)
    TextView addNoteFrag_txtView;
/*
    @BindView(R.id.clear_btn)
    Button clearBtn;
*/

    public MainFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.sessionManager = new SessionManager(getActivity());
        this.realm = RealmUserController.with(getActivity()).getRealm();
        realmChangeListener = new RealmChangeListener() {
            @Override
            public void onChange(Object element) {
                RealmUserController.with(getActivity()).refresh();
            }
        };

        checkUserLogin();
    }

    private void checkUserLogin(){
        if(sessionManager.checkLogin()==true){
            HashMap<String, String> user  = sessionManager.getUserDetails();
            String EMAIl = user.get(sessionManager.KEY_EMAIL);
            String LOGNAME = user.get(sessionManager.KEY_NAME);

            Log.i("DBSAVED", RealmUserController.getInstance().getUsers().toString());

            Log.i("CHECK_NAME", LOGNAME);
            Log.i("CHECK_EMAIL", EMAIl);

        }
    }

    @OnClick(R.id.mainFragImg_button)
    public void addNoteFragment(){
        Intent intent = new Intent(getActivity(), NotesActivity.class);
        startActivity(intent);
    }
/*
    @OnClick(R.id.clear_btn)
    public void clearDB(){
        session.clearSession();
        RealmUserController.getInstance().clearAllUserData();
        checkUserLogin();
        Toast.makeText(getActivity(), String.valueOf(RealmUserController.getInstance().getDBSize()), Toast.LENGTH_SHORT).show();
    }*/
}
