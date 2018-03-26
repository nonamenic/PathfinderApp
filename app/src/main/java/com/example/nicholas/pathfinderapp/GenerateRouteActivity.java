package com.example.nicholas.pathfinderapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class GenerateRouteActivity extends AppCompatActivity {

    private Button btnStart, btnStop, btnSmallRun, btnMediumRun, btnSmallRunKm, btnMediumRunKm, btnSubmit;
    private ImageButton btnMainMenu;
    private Chronometer stopWatch;
    private int mLowDistance;
    private DatabaseReference runnerDataBase;
    private Controller mController = Controller.getSoleInstance();
    private Switch kmMileSwitch;
    private final String TAG = "GenerateRouteActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_route);

        runnerDataBase = FirebaseDatabase.getInstance().getReference();
        mController.setRunnerDataBase(runnerDataBase);
       // Log.d(TAG, "Updating question text", new Exception());
        btnStart = (Button) findViewById(R.id.start_button);
        btnMainMenu = (ImageButton) findViewById(R.id.image_button);
        btnStop = (Button) findViewById(R.id.stop_button);
        btnSmallRun = (Button) findViewById(R.id.smallRun_button);
        btnMediumRun = (Button) findViewById(R.id.mediumRun_button);
        btnSmallRunKm = (Button) findViewById(R.id.smallRun_buttonKM);
        btnMediumRunKm = (Button) findViewById(R.id.mediumRun_buttonKM);
        stopWatch = (Chronometer) findViewById(R.id.stop_watch);
        kmMileSwitch = (Switch) findViewById(R.id.kmMile_switch);
        btnSubmit = (Button) findViewById(R.id.refresh_button);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(kmMileSwitch.isChecked()){
                    btnSmallRunKm.setVisibility(View.GONE);
                    btnMediumRunKm.setVisibility(View.GONE);
                    btnMediumRun.setVisibility(View.VISIBLE);
                    btnSmallRun.setVisibility(View.VISIBLE);
                }else{
                    btnSmallRunKm.setVisibility(View.VISIBLE);
                    btnMediumRunKm.setVisibility(View.VISIBLE);
                    btnMediumRun.setVisibility(View.GONE);
                    btnSmallRun.setVisibility(View.GONE);
                }
            }
        });





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


    private void addStats(){
        //TODO change distance later when you can pull actual distance traveled
        long time = SystemClock.elapsedRealtime() - stopWatch.getBase();



        String id = runnerDataBase.push().getKey();
        RunnerStats runnerStats = new RunnerStats(id, mController.getUser(), mLowDistance, time);

        runnerDataBase.child(id).setValue(runnerStats);

    }



}
