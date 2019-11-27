package com.example.meetofinal626;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class TravelOneWay extends AppCompatActivity implements
        View.OnClickListener,
        AdapterView.OnItemSelectedListener {


    TextView textViewOneWayHeader,
            textViewTo,
            textViewTravelDate,
            textViewTravelTime,
            textViewLuggageHeader;

    Spinner spinnerOrigin,
            spinnerDestination,
            spinnerCarryOn,
            spinnerRollaboard,
            spinnerCheckIn;
    Button buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_one_way);

        textViewOneWayHeader =findViewById(R.id.textViewOneWayHeader);
        textViewTravelDate = findViewById(R.id.textViewTravelDate);
        textViewTo = findViewById(R.id.textViewTo);
        textViewTravelTime = findViewById(R.id.textViewTravelTime);
        textViewLuggageHeader = findViewById(R.id.textViewLuggageHeader);

        spinnerOrigin = findViewById(R.id.spinnerOrigin);
        spinnerDestination = findViewById(R.id.spinnerDestination);
        spinnerCarryOn = findViewById(R.id.spinnerCarryOn);
        spinnerRollaboard = findViewById(R.id.spinnerRollaboard);
        spinnerCheckIn = findViewById(R.id.spinnerCheckIn);


        buttonNext = findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(this);

        spinnerOrigin.setOnItemSelectedListener(this);

        List<String> spinnerLocations = new ArrayList<String>();
        spinnerLocations.add("Ross");
        spinnerLocations.add("DTW");

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerLocations);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOrigin.setAdapter(spinnerAdapter);

        spinnerDestination.setOnItemSelectedListener(this);
        spinnerDestination.setAdapter(spinnerAdapter);
        spinnerDestination.setSelection(1);

        spinnerCarryOn.setOnItemSelectedListener(this);
        spinnerRollaboard.setOnItemSelectedListener(this);
        spinnerCheckIn.setOnItemSelectedListener(this);

        List<String> baggageCount = new ArrayList<>();
        baggageCount.add("0");
        baggageCount.add("1");
        baggageCount.add("2");
        baggageCount.add("3");
        baggageCount.add("4");

        ArrayAdapter<String> spinnerAdapterBags = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, baggageCount);
        spinnerAdapterBags.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCarryOn.setAdapter(spinnerAdapterBags);
        spinnerRollaboard.setAdapter(spinnerAdapterBags);
        spinnerCheckIn.setAdapter(spinnerAdapterBags);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(TravelOneWay.this, TravelRoundTripQuestion.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        if (spinnerOrigin.getSelectedItem().toString() == "Ross"){
            spinnerDestination.setSelection(1);
        } else if (spinnerOrigin.getSelectedItem().toString() == "DTW") {
            spinnerDestination.setSelection(0);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
