package com.example.anoop.noteapp_v1.models;

/**
 * Created by anoop on 16/11/2016.
 */

public class Users {

    //@PrimaryKey
    private String email, name, password;

    public void setEmail(String email){
        this.email=email;
    }

    public String getEmail(){
        return email;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public String getPassword(){
        return password;
    }
}
