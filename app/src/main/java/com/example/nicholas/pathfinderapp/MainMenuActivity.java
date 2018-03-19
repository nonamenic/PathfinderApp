package com.example.nicholas.pathfinderapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends AppCompatActivity {

    private Button btnProfile, btnSettings, btnMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        btnMap = (Button) findViewById(R.id.map_button);
        btnProfile = (Button) findViewById(R.id.profile_button);
        btnSettings = (Button) findViewById(R.id.settings_button);

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
