package com.example.meetofinal626;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

public class TravelOneWay extends AppCompatActivity implements
        View.OnClickListener,
        AdapterView.OnItemSelectedListener,
        DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener {


    TextView textViewOneWayHeader,
            textViewTo,
            textViewTravelDate,
            textViewSelectDate,
            textViewTravelTime,
            textViewSelectTime,
            textViewLuggageHeader;

    Spinner spinnerOrigin,
            spinnerDestination,
            spinnerCarryOn,
            spinnerRollaboard,
            spinnerCheckIn;
    Button buttonNext;

    public boolean startLocation, endLocation;

    public Calendar combinedCal = new GregorianCalendar(TimeZone.getTimeZone("EST"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_one_way);

        textViewOneWayHeader =findViewById(R.id.textViewOneWayHeader);

        textViewTravelDate = findViewById(R.id.textViewTravelDate);

        textViewSelectDate = findViewById(R.id.textViewSelectDate);
        textViewSelectDate.setOnClickListener(this);

        textViewTo = findViewById(R.id.textViewTo);

        textViewTravelTime = findViewById(R.id.textViewTravelTime);
        textViewSelectTime = findViewById(R.id.textViewSelectTime);
        textViewSelectTime.setOnClickListener(this);

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

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("triprequests");

        if (v == buttonNext){

            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

            //if user is logged in
            if(currentUser != null) {
                //Toast.makeText(this, "User is logged in", Toast.LENGTH_SHORT).show();


                //upload data to the database

                //get data and format properly
                FirebaseUser createRider = currentUser;
                String createStartLocation = spinnerOrigin.getSelectedItem().toString();
                String createEndLocation = spinnerDestination.getSelectedItem().toString();
                Calendar createRequestedTime = combinedCal;
                Integer createCarryOnCount =  Integer.parseInt(spinnerCarryOn.getSelectedItem().toString());
                Integer createRollaboardCount =  Integer.parseInt(spinnerRollaboard.getSelectedItem().toString());
                Integer createCheckInCount =  Integer.parseInt(spinnerCheckIn.getSelectedItem().toString());
                boolean createStatus = false;

                //create a new TripRequest

                TripRequest createTrip = new TripRequest(createRider,
                        createStartLocation,
                        createEndLocation,
                        createRequestedTime,
                        createCarryOnCount,
                        createRollaboardCount,
                        createCheckInCount,
                        createStatus);

                //push to database

                myRef.push().setValue(createTrip);

                // move to next page
                Intent intent = new Intent(TravelOneWay.this, TravelRoundTripQuestion.class);
                startActivity(intent);

            }
            //if user is not logged in
            else {

                //add in code to hold data onto next page until registered and saved
            }




        } else if (v == textViewSelectDate){
            showDatePicker();
        } else if (v == textViewSelectTime){
            showTimePicker();
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        if (spinnerOrigin.getSelectedItem().toString() == "Ross"){
            spinnerDestination.setSelection(1);
            //true is Ross, false is DTW
            //startLocation = true;
            //endLocation = false;


        } else if (spinnerOrigin.getSelectedItem().toString() == "DTW") {
            spinnerDestination.setSelection(0);
            //true is Ross, false is DTW
            //startLocation = false;
            //endLocation = true;


        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void showDatePicker(){
        DatePickerDialog datePicker = new DatePickerDialog(
                this, this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );

        datePicker.show();

    }

    public void showTimePicker(){
        TimePickerDialog timePick = new TimePickerDialog(
                this, this,
                12,
                0,
                false
        );

        timePick.show();

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = month + "/" + dayOfMonth + "/" + year;
        textViewSelectDate.setText(date);
        
        combinedCal.set(year, month, dayOfMonth);

    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {

        String amPm;
        int hour12;

        if (hourOfDay >= 12){
            amPm = "PM";
            hour12 = hourOfDay - 12;
        } else {
            hour12 = hourOfDay;
            amPm = "AM";
        }

        textViewSelectTime.setText(String.format("%02d:%02d", hour12, minutes) + " " + amPm);

        combinedCal.set(Calendar.HOUR_OF_DAY, hourOfDay);
        combinedCal.set(Calendar.MINUTE, minutes);
        
    }
    

}
