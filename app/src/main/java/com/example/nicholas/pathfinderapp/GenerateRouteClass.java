package com.example.nicholas.pathfinderapp;

import android.app.Activity;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;
import com.android.volley.RequestQueue;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;


/**
 * Created by Nicholas on 3/26/2018.
 */

public class GenerateRouteClass {


    public static double getDistanceBetweenLongitude(double latitude){
        double milesIn1DegOfLong= Math.cos(Math.toRadians(latitude))*69.172;
//System.out.println(Math.cos(Math.toRadians(latitude))*69.172);
        return Math.abs(milesIn1DegOfLong);

    }
    public static double getRight(double dist, double curLongitude, double distBetweenDegs){
        double bottomRight=0;
        bottomRight=(dist/distBetweenDegs)+curLongitude;
        return bottomRight;

    }


    public static double getUpper(double dist, double curLatitude){

        return (dist/69)+curLatitude;
    }


    public static String genRouteMethod(double lat, double lng, double dist, RequestQueue queue) {
        double currentLocationLat=40.00265226;
        double currentLocationLong=-83.01460162;
        double distBetweenLng=0.0;
        distBetweenLng=getDistanceBetweenLongitude(currentLocationLat);
        double distBetweenPoints=0.0;
        //System.out.println(distBetweenLng);
        final StringBuffer resp = new StringBuffer();

        double routeDist= dist;
        final RequestQueue qu = queue;


        Log.d("TAG", resp.toString());

        int bringInAttempts=0;

        double rightLong=0.0;//getRight(routeDist/4, currentLocationLong, distBetweenLng);
        double topLat=0.0;//getUpper(routeDist/4, currentLocationLat);

        String origin = Double.toString(currentLocationLat) + "," + Double.toString(currentLocationLong);
        String destination=Double.toString(currentLocationLat) + "," + Double.toString(currentLocationLong);
        rightLong=getRight((routeDist/4)*Math.pow(.6, bringInAttempts), currentLocationLong, distBetweenLng);


        bringInAttempts=0;
        distBetweenPoints=0;
        String bottomRight = Double.toString(currentLocationLat) + "," + Double.toString(rightLong);




        topLat=getUpper((routeDist/4)*Math.pow(.6, bringInAttempts), currentLocationLat);
        bringInAttempts=0;
        distBetweenPoints=0;
        String topRight = Double.toString(topLat) + "," + Double.toString(rightLong);

        double upperLeftLat=topLat;
        double upperLeftLong=currentLocationLong;

        double distBetweenPoints2=0.0;
        bringInAttempts++;
        upperLeftLong=getRight((routeDist/4)*Math.pow(.6, bringInAttempts), rightLong, distBetweenLng); //rightlong just for top left point
        upperLeftLat=getUpper((routeDist/4)*Math.pow(.6, bringInAttempts), currentLocationLat);//lat only for top left point
        bringInAttempts++;

        String topLeft = Double.toString(upperLeftLat) + "," + Double.toString(upperLeftLong);

        //do not need origin if using current location for start
        String URL="https://www.google.com/maps/dir/?api=1&origin=";
        URL=URL + origin + "&destination=" + destination +"&travelmode=walking&waypoints="+bottomRight+"%7C"+topRight+"%7C"+topLeft;
        //System.out.println(URL);
        Log.d("TAG", URL);
        //https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=40,-83&destinations=40.0,-82.9891618746764%7C40.007246376811594,-82.9891618746764%7C40.007246376811594,-83.0%7C40,-83&key=AIzaSyAoA1iVXsmRxn7jQae_cKIQxG4p34NMe78
        return URL;


    }
}
