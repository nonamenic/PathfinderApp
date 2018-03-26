package com.example.nicholas.pathfinderapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private ListView mListView;
    private DatabaseReference mDatabaseReference;
    private ArrayList<String> mArrayList = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private final String TAG = "HistroyActivity";
    private Controller mController = Controller.getSoleInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        mDatabaseReference = mController.getRunnerDataBase();

        mListView = (ListView) findViewById(R.id.database_list_view);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mArrayList);

        mListView.setAdapter(adapter);

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String entry = dataSnapshot.getValue(String.class);
                mArrayList.add(entry);

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
