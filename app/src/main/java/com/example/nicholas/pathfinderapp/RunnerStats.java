package com.example.nicholas.pathfinderapp;

/**
 * Created by delgado on 3/21/18.
 */

public class RunnerStats {


    private int mDistance;
    //private double mDistance;
    private long mTotalTime;
    private int mHours, mMinutes, mSeconds, mMilliSeconds;
    private String mId, mUser;
    private String timeFromate;

    //https://www.androidhive.info/2016/10/android-working-with-firebase-realtime-database/
    public RunnerStats(){

    }

    public RunnerStats( String id, String user, int distance, long totalTime) {
        mId = id;
        mUser = user;
        mDistance = distance;
        mTotalTime = totalTime;

    }

    public String getId() {
        return mId;
    }

    public int getDistance() {
        return mDistance;
    }

    public long getTotalTime() {
        return mTotalTime;
    }




    public String getUser() {
        return mUser;
    }

    //TODO figure a way to convert long into int
    //TODO convert milliseconds into seconds and then seconds into minutes...
    public void setHours(int hours) {
        mHours = hours;
    }

    public void setMinutes(int minutes) {
        mMinutes = minutes;
    }

    public void setSeconds(int seconds) {
        mSeconds = seconds;
    }

    public void setMilliSeconds(int milliSeconds) {
        mMilliSeconds = milliSeconds;
    }

    public void setTimeFromate(String timeFromate) {
        this.timeFromate = timeFromate;
    }
}
