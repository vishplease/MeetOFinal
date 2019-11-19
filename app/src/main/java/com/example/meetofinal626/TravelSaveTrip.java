package com.example.meetofinal626;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TravelSaveTrip extends AppCompatActivity implements View.OnClickListener {

    Button buttonSaveTrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_save_trip);

        buttonSaveTrip = findViewById(R.id.buttonSaveTrip);
        buttonSaveTrip.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(TravelSaveTrip.this, MainActivity.class);
        startActivity(intent);
    }
}
