package com.example.meetofinal626;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
        textViewTimeLimit = findViewById(R.id.textViewTimeLimit);

    }

    @Override
    public void onClick(View view) {




        if (buttonCoRiders == view) {
            TextView textView = findViewById(R.id.textViewRidingMembers2);
            String message = textView.getText().toString();

            Intent intent = new Intent(this, MatchedTripSummary.class);
            intent.putExtra("Extra_Message",message);
            startActivity(intent);




        }
        if (buttonCancelTrip == view) {
            Intent intent = new Intent(this, UpcomingTrips.class);
            startActivity(intent);
        }


    }
}

