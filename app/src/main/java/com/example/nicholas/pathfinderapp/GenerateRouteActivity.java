package com.example.nicholas.pathfinderapp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.nfc.Tag;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;

public class GenerateRouteActivity extends AppCompatActivity implements LocationListener {


    private ImageButton btnMainMenu;
    private Button btnStart, btnStop, btnSmallRun, btnMediumRun, btnLongRun;
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    private Chronometer stopWatch;
    private int mLowDistance;
    private DatabaseReference runnerDataBase;
    private Controller mController = Controller.getSoleInstance();
    private final String TAG = "GenerateRouteActivity";
    public static double lat;
    public static double lng;
    GenerateRouteClass cls = new GenerateRouteClass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_route);
        Log.d(TAG,"on create");

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);



        if(checkLocationPermission()) {
            Log.d(TAG,"entered location permission yes");

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            //btnSmallRun.setEnabled(false);
            //btnMediumRun.setEnabled(false);
            //Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
           //lat = location.getLatitude();
            //lng = location.getLongitude();
            Log.d(TAG,"a;lskdjfal;skdjfalksdjfalksdjflkajsdlkfjsalkdjflksd" + lat + lng);
            cls.genRouteMethod(40.00265226,-83.01460162);
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),"ERROR: No location permission given", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM,0,0);
            toast.show();checkLocationPermission();
            Log.d(TAG,"Entered Else loop, no permission");
            //btnMediumRun.setEnabled(false);
            //btnSmallRun.setEnabled(false);
        }

        runnerDataBase = FirebaseDatabase.getInstance().getReference();
        mController.setRunnerDataBase(runnerDataBase);
       // Log.d(TAG, "Updating question text", new Exception());
        btnStart = (Button) findViewById(R.id.start_button);
        btnMainMenu = (ImageButton) findViewById(R.id.image_button);
        btnStop = (Button) findViewById(R.id.stop_button);
        btnSmallRun = (Button) findViewById(R.id.smallRun_button);
        btnMediumRun = (Button) findViewById(R.id.mediumRun_button);
        stopWatch = (Chronometer) findViewById(R.id.stop_watch);

        btnMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GenerateRouteActivity.this, MainMenuActivity.class);
                startActivity(intent);
                finish();
            }
        });



        btnSmallRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "You selected a short run!", Toast.LENGTH_LONG);
                mLowDistance = 1;
                toast.setGravity(Gravity.BOTTOM, 0, 0);
                toast.show();
            }
        });

        btnMediumRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "You selected a medium run!", Toast.LENGTH_LONG);
                mLowDistance = 5;
                toast.setGravity(Gravity.BOTTOM, 0, 0);
                toast.show();
            }
        });


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("https://www.google.com/maps/dir/?api=1&origin=Paris,France&destination=Cherbourg,France&travelmode=driving&waypoints=Versailles,France%7CChartres,France%7CLe+Mans,France%7CCaen,France");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW);
                mapIntent.setData(gmmIntentUri);
                stopWatch.setBase(SystemClock.elapsedRealtime());
                stopWatch.start();
                startActivity(mapIntent);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopWatch.stop();
                Toast toast = Toast.makeText(getApplicationContext(), "You have added this run.", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.BOTTOM, 0, 0);
                toast.show();
                addStats();
            }
        });


    }
    public boolean checkLocationPermission() {
        String permission = "android.permission.ACCESS_FINE_LOCATION";
        int res = this.checkCallingOrSelfPermission(permission);
        return (res == PackageManager.PERMISSION_GRANTED);
    }

    @Override
    public void onLocationChanged(Location location) {
        lat = location.getLatitude();
        lng = location.getLongitude();
        Log.d("location change:", lat +" " + lng );
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude","disable");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude","enable");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude","status");
    }


    private void addStats(){
        //TODO change distance later when you can pull actual distance traveled
        long time = SystemClock.elapsedRealtime() - stopWatch.getBase();



        String id = runnerDataBase.push().getKey();
        RunnerStats runnerStats = new RunnerStats(id, mController.getUser(), mLowDistance, time);

        runnerDataBase.child(id).setValue(runnerStats);

    }



}
