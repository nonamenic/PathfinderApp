package com.example.nicholas.pathfinderapp;

import android.widget.ArrayAdapter;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

/**
 * Created by delgado on 3/22/18.
 */

 public  class  Controller {

    private  static volatile Controller sSoleInstance;
    private String mUser;
    private ArrayList<String> mArrayList = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private DatabaseReference runnerDataBase;

    private Controller(){

        if (sSoleInstance != null){
            throw new RuntimeException("Use getSoleInstance() method to get"
                    +" the single instance of this class.");
        }

    }

    public static Controller getSoleInstance(){
        if(sSoleInstance == null){
            synchronized (Controller.class){
                if (sSoleInstance == null){
                    sSoleInstance = new Controller();
                }
            }
        }

        return sSoleInstance;
    }

    public DatabaseReference getRunnerDataBase() {
        return runnerDataBase;
    }

    public void setRunnerDataBase(DatabaseReference runnerDataBase) {
        this.runnerDataBase = runnerDataBase;
    }

    public void setUser(String user){

       mUser = user;
    }

    public String getUser(){
        return mUser;
    }
}
