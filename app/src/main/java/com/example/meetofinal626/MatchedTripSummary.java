package com.example.meetofinal626;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.sql.Timestamp;

public class MatchedTripSummary extends AppCompatActivity {

    TextView textViewRidingWith, textViewRidingMembers;
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matched_trip_summary);

        GroupTrip gt = new GroupTrip(new String[]{"-LvfEKD9-wGpV5-6IvVM", "-Lvclh-w5XdOnuLnIKWh"}, timestamp);
        Intent intent =getIntent();
        String message = intent.getStringExtra("Extra_Message");
        TextView textView = findViewById(R.id.textViewRidingMembers);
        textView.setText(message);
    }
}
