package com.example.nicholas.pathfinderapp;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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

    public static double checkDist(String lat1, String lng1, String lat2, String lng2) throws Exception{
        String url = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&mode=walking&origins=" + lat1+","+lng1+"&destinations="+lat2+","+lng2+"&key=AIzaSyAoA1iVXsmRxn7jQae_cKIQxG4p34NMe78";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        //int responseCode = con.getResponseCode();
        //System.out.println("\nSending 'GET' request to URL : " + url);
        //System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        //print in String
        String respString=new String(response.toString());
        Log.d("TAG2","\n\n"+response.toString()+"\n\n");
        //Read JSON response and print
        //JSONObject myResponse = new JSONObject(response.toString());
        //System.out.println("result after Reading JSON Response");

        if(respString.indexOf("text")!=-1){
            // String distText = respString.substring(respString.indexOf("text"));
            String newString = respString.substring(respString.indexOf("text"));
            String newString2 = newString.substring(newString.indexOf("\"")+5);
            int indexpace = newString2.indexOf(" ");

            Log.d("TAG3",newString2.substring(0,indexpace));

            return  Double.parseDouble(newString2.substring(0,indexpace));
        }
        else{ return 0.0;}
    }



    public static String genRouteMethod(double lat, double lng, double dist) {
        double currentLocationLat=40.00265226;
        double currentLocationLong=-83.01460162;
        double distBetweenLng=0.0;
        distBetweenLng=getDistanceBetweenLongitude(currentLocationLat);
        double distBetweenPoints=0.0;
        //System.out.println(distBetweenLng);

        double routeDist= dist;
/*
miles KM middle mile
1-3 1.609-4.828 2
4-12 6.437-19.312 8
*/






        int bringInAttempts=0;

        double rightLong=0.0;//getRight(routeDist/4, currentLocationLong, distBetweenLng);
        double topLat=0.0;//getUpper(routeDist/4, currentLocationLat);

        String origin = Double.toString(currentLocationLat) + "," + Double.toString(currentLocationLong);
        String destination=Double.toString(currentLocationLat) + "," + Double.toString(currentLocationLong);
        //while ((distBetweenPoints<(routeDist/4)*.50 || distBetweenPoints>(routeDist/4)*1.50) && bringInAttempts<15)
        //{//check distance to bottom right
            rightLong=getRight((routeDist/4)*Math.pow(.6, bringInAttempts), currentLocationLong, distBetweenLng);

            /*try {
                distBetweenPoints=checkDist(Double.toString(currentLocationLat), Double.toString(currentLocationLong), Double.toString(currentLocationLat),Double.toString(rightLong));
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                Log.d("TAG","while loop");
            }
            bringInAttempts++;
        }*/

        bringInAttempts=0;
        distBetweenPoints=0;
        String bottomRight = Double.toString(currentLocationLat) + "," + Double.toString(rightLong);



        //while ((distBetweenPoints<(routeDist/4)*.50 || distBetweenPoints>(routeDist/4)*1.50)&& bringInAttempts<15)
        //{//check distance to bottom right
            topLat=getUpper((routeDist/4)*Math.pow(.6, bringInAttempts), currentLocationLat);

            //try {
            //    distBetweenPoints=GenerateRouteClass.checkDist(Double.toString(currentLocationLat), Double.toString(rightLong), Double.toString(topLat),Double.toString(rightLong));
            //} catch (Exception e) {
            //    // TODO Auto-generated catch block
            //    e.printStackTrace();
            //}
            //bringInAttempts++;
        //}
        bringInAttempts=0;
        distBetweenPoints=0;
        String topRight = Double.toString(topLat) + "," + Double.toString(rightLong);

        double upperLeftLat=topLat;
        double upperLeftLong=currentLocationLong;

        double distBetweenPoints2=0.0;
        /*try {
            distBetweenPoints=checkDist(Double.toString(topLat),Double.toString(rightLong), Double.toString(upperLeftLat), Double.toString(upperLeftLong));
        } catch (Exception e1) {
// TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            distBetweenPoints2=checkDist(Double.toString(upperLeftLat), Double.toString(upperLeftLong), Double.toString(currentLocationLat), Double.toString(currentLocationLong));
        } catch (Exception e1) {
// TODO Auto-generated catch block
            e1.printStackTrace();
        }*/
        bringInAttempts++;
        //while ((distBetweenPoints<(routeDist/4)*.50 || distBetweenPoints>(routeDist/4)*1.50 || distBetweenPoints2<(routeDist/4)*.50 || distBetweenPoints2>(routeDist/4)*1.50)&& bringInAttempts<15)
        //{//check distance to bottom right
            upperLeftLong=getRight((routeDist/4)*Math.pow(.6, bringInAttempts), rightLong, distBetweenLng); //rightlong just for top left point
            upperLeftLat=getUpper((routeDist/4)*Math.pow(.6, bringInAttempts), currentLocationLat);//lat only for top left point

            /*try {
                distBetweenPoints=checkDist(Double.toString(topLat),Double.toString(rightLong), Double.toString(upperLeftLat), Double.toString(upperLeftLong));
                distBetweenPoints2=checkDist(Double.toString(upperLeftLat), Double.toString(upperLeftLong), Double.toString(currentLocationLat), Double.toString(currentLocationLong));

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }*/
            bringInAttempts++;
        //}

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
