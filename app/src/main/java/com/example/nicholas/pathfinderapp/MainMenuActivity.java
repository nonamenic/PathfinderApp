package com.example.nicholas.pathfinderapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends AppCompatActivity {

    private Button btnProfile, btnSettings, btnGenRoute, btnHistory;
    private final String TAG = "MainMenuActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        btnGenRoute = (Button) findViewById(R.id.GenRoute_button);
        btnProfile = (Button) findViewById(R.id.profile_button);
        //btnHistory = (Button) findViewById(R.id.history_button);
        //btnSettings = (Button) findViewById(R.id.settings_button);

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        btnGenRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, GenerateRouteActivity.class);
                Log.d(TAG, "before start activity", new Exception());
                startActivity(intent);
            }
        });


        /*btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, HistoryActivity.class);
                startActivity(intent);
                finish();
            }
        });*/

        /*btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, SettingsActivity.class);
                startActivity(intent);
                finish();
            }
        });*/



    }
}
