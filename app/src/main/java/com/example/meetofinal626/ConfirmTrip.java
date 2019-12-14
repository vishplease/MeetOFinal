package com.example.meetofinal626;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;

public class ConfirmTrip extends AppCompatActivity implements View.OnClickListener{
    Button buttonBackConfirm, buttonCoRiders, buttonCancelTrip;
    TextView textViewConfirmTrip, textViewConfirmYourTrip, textViewRidingMembers2;
    TextView textViewMustConfirm, textViewTimeLimit;
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
        textViewRidingMembers2 = findViewById(R.id.textViewRidingMembers2);
        textViewMustConfirm = findViewById(R.id.textViewMustConfirm);

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        textViewTimeLimit = findViewById(R.id.textViewTimeLimit);
        textViewTimeLimit.setText(currentDate);


    }

    @Override
    public void onClick(View view) {



        if (buttonCoRiders == view) {
            Intent intent = new Intent(this, MatchedTripSummary.class);
            startActivity(intent);




        }
        if (buttonCancelTrip == view) {
            Intent intent = new Intent(this, UpcomingTrips.class);
            startActivity(intent);
        }
        if (buttonBackConfirm == view) {
            Intent intent = new Intent(ConfirmTrip.this, UpcomingTrips.class);
            startActivity(intent);
        }
    }



}

