package com.example.nicholas.pathfinderapp;

/**
 * Created by delgado on 3/22/18.
 */

 public  class  Controller {

    private  static volatile Controller sSoleInstance;
    private String mUser;

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

    public void setUser(String user){

       mUser = user;
    }

    public String getUser(){
        return mUser;
    }
}
