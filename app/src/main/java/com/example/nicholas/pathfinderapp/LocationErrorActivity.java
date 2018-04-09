package com.example.nicholas.pathfinderapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class LocationErrorActivity extends AppCompatActivity {
    private ImageButton btnMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_error);

        btnMainMenu = (ImageButton) findViewById(R.id.imageButton);

        btnMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocationErrorActivity.this, MainMenuActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
