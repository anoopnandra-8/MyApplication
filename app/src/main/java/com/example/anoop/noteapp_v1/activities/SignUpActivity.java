package com.example.anoop.noteapp_v1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.anoop.noteapp_v1.R;
import com.example.anoop.noteapp_v1.models.Users;
import com.example.anoop.noteapp_v1.realm.RealmUserController;
import com.example.anoop.noteapp_v1.session.SessionManager;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmChangeListener;

/**
 * Created by anoop on 20/11/2016.
 */

public class SignUpActivity extends AppCompatActivity {

    @BindView(R.id.input_name)
    EditText name;

    @BindView(R.id.input_email)
    EditText email;

    @BindView(R.id.input_password)
    EditText password;

    @BindView(R.id.signup_btn)
    Button signUp;

    private Realm realm;
    private RealmChangeListener realmChangeListener;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);
        ButterKnife.bind(this);

        sessionManager = new SessionManager(this);
        this.realm = RealmUserController.with(this).getRealm();

        realmChangeListener = new RealmChangeListener() {
            @Override
            public void onChange(Object element) {
                RealmUserController.with(SignUpActivity.this).refresh();
            }
        };

    }

    @OnClick(R.id.signup_btn)
    public void userSignUp(){
        if(validate()==true){
            Users users = new Users();
            users.setName(name.getText().toString());
            users.setEmail(email.getText().toString());
            try {
                users.setPassword(md5Algorithm(password.getText().toString()));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            realm.beginTransaction();
            realm.copyToRealm(users);
            realm.commitTransaction();

            sessionManager.createUserSession(name.getText().toString(), email.getText().toString());

            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
            try {
                intent.putExtra("pass", md5Algorithm(password.getText().toString()));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            startActivity(intent);
        }
    }

    private boolean validate(){
        CharSequence charEmail = email.getText().toString();
        if(name.getText().toString().equals("") || email.getText().toString().equals("") || password.getText().toString().equals("")){
            Toast.makeText(SignUpActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(charEmail).matches()){
            Toast.makeText(SignUpActivity.this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!RealmUserController.getInstance().checkEmailPrimaryKey(email.getText().toString())){
            Toast.makeText(SignUpActivity.this, "Email has already been taken", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(password.getText().toString().length() < 6){
            Toast.makeText(SignUpActivity.this, "Password needs to be more than 6 characters", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


    private static String md5Algorithm(String password) throws NoSuchAlgorithmException {
        String result = password;
        if(password != null){
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            BigInteger hash = new BigInteger(1, md.digest());
            result = hash.toString(16);
            while (result.length() < 32){
                result = "0" + result;
            }
        }
        return result;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}
