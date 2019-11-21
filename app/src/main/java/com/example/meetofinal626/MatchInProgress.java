package com.example.meetofinal626;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MatchInProgress extends AppCompatActivity implements View.OnClickListener{
    Button buttonEdit, buttonGoBack, buttonCancel;
    TextView textViewNotYet, TextViewNotify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_match_in_progress);
        buttonEdit = findViewById(R.id.buttonEdit);
        buttonEdit.setOnClickListener(this);

        buttonGoBack = findViewById(R.id.buttonGoBack);
        buttonGoBack.setOnClickListener(this);

        buttonCancel = findViewById(R.id.buttonCancel);
        buttonCancel.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (buttonGoBack == view) {
            Intent mainIntent = new Intent(MatchInProgress.this, UpcomingTrips.class);
        }
        if (buttonEdit == view) {
            Intent mainIntent = new Intent(MatchInProgress.this, TravelOneWay.class);
        }
        if (buttonCancel == view) {
            Intent mainIntent = new Intent(MatchInProgress.this, UpcomingTrips.class);
        }

    }
}
