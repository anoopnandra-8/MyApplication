package com.example.anoop.noteapp_v1.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by anoop on 20/11/2016.
 */

public class Note extends RealmObject {
    @PrimaryKey
    private long noteID;
    private String title;
    private String note;
    private String time;
    private String date;
    private String emailID;
    private Boolean archive;
    private  Boolean delete;
    private String colour;
    private Boolean lock;
    private String lockPassword;
    private Boolean reminder;

    public void setNoteID(long noteID){
        this.noteID=noteID;
    }

    public long getNoteID(){
        return noteID;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public String getTitle(){
        return title;
    }

    public void setNote(String note){
        this.note=note;
    }

    public String getNote(){
        return note;
    }

    public void setTime(String time){
        this.time=time;
    }

    public String getTime(){
        return time;
    }

    public void setDate(String date){
        this.date=date;
    }

    public String getDate(){
        return date;
    }

    public void setEmailID(String emailID){
        this.emailID=emailID;
    }

    public String getEmailID(){
        return emailID;
    }

    public void setArchive(Boolean archive){
        this.archive=archive;
    }

    public Boolean getArchive(){
        return archive;
    }

    public void setDelete(Boolean delete){
        this.delete=delete;
    }

    public Boolean getDelete(){
        return delete;
    }

    public void setColour(String colour){
        this.colour=colour;
    }

    public String getColour(){
        return colour;
    }

    public void setLock(Boolean lock){
        this.lock=lock;
    }

    public Boolean getLock(){
        return lock;
    }

    public void setLockPassword(String lockPassword){
        this.lockPassword=lockPassword;
    }

    public String getLockPassword(){
        return lockPassword;
    }

    public void setReminder(Boolean reminder){
        this.reminder=reminder;
    }

    public Boolean getReminder(){
        return reminder;
    }
}
