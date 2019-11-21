package com.example.meetofinal626;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmTrip extends AppCompatActivity implements View.OnClickListener{
    Button buttonBackConfirm, buttonCoRiders, buttonCancelTrip;
    TextView textViewConfirmTrip, textViewConfirmYourTrip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_trip);
        buttonBackConfirm = findViewById(R.id.buttonBackConfirm);
        buttonBackConfirm.setOnClickListener(this);

        buttonCoRiders = findViewById(R.id.buttonCoRiders);
        buttonCoRiders.setOnClickListener(this);

        buttonCancelTrip = findViewById(R.id.buttonCancelTrip);
        buttonCancelTrip.setOnClickListener(this);

        textViewConfirmTrip = findViewById(R.id.textViewConfrimTrip);
        textViewConfirmYourTrip = findViewById(R.id.textViewConfirmYourTrip);

    }

    @Override
    public void onClick(View view) {
        if (buttonCoRiders == view) {
            Intent mainIntent = new Intent(ConfirmTrip.this, MatchedTripSummary.class);
        }
        if (buttonCancelTrip == view) {
            Intent mainIntent = new Intent(ConfirmTrip.this, UpcomingTrips.class);
        }


    }
}

