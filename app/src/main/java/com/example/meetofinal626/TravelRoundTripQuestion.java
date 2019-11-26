package com.example.meetofinal626;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TravelRoundTripQuestion extends AppCompatActivity implements View.OnClickListener {

    Button buttonYes, buttonNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_round_trip_question);

        buttonYes = findViewById(R.id.buttonYes);
        buttonNo = findViewById(R.id.buttonNo);

        buttonYes.setOnClickListener(this);
        buttonNo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == buttonYes){
            Intent intent = new Intent(TravelRoundTripQuestion.this, TravelReturnTrip.class);
            startActivity(intent);
        }
        else if (v == buttonNo){
            Intent intent = new Intent(TravelRoundTripQuestion.this, TravelSaveTrip.class);
            startActivity(intent);
        }

    }
}
