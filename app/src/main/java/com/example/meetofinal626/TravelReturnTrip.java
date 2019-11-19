package com.example.meetofinal626;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TravelReturnTrip extends AppCompatActivity implements View.OnClickListener {

    Button buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_return_trip);

        buttonNext = findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(TravelReturnTrip.this, TravelSaveTrip.class);
        startActivity(intent);
    }
}
