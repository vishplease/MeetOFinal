package com.example.meetofinal626;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MatchedTripSummary extends AppCompatActivity {

    TextView textViewRidingWith, textViewRidingMembers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matched_trip_summary);

        Intent intent =getIntent();
        String message = intent.getStringExtra("Extra_Message");
        TextView textView = findViewById(R.id.textViewRidingMembers);
        textView.setText(message);
    }
}
