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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

public class TravelReturnTrip extends AppCompatActivity implements
        View.OnClickListener,
        AdapterView.OnItemSelectedListener,
        DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener  {

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
    Button buttonSave;

    public Calendar combinedCal = new GregorianCalendar(TimeZone.getTimeZone("US/Eastern"));

    public int selectedHour, selectedMinute, selectedMonth, selectedDay, selectedYear;

    public String createRider, createUser;
    public String createStartLocation;
    public String createEndLocation;
    public long createRequestedTime;
    public Integer createCarryOnCount;
    public Integer createRollaboardCount;
    public Integer createCheckInCount;
    public String createStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_return_trip);

        //get bundle
        Intent intent = getIntent();
        Bundle departTrip = intent.getExtras();

        createRider = departTrip.getString("createRider");
        createUser = departTrip.getString("createUser");
        createStartLocation = departTrip.getString("createStartLocation");
        createEndLocation = departTrip.getString("createEndLocation");
        createRequestedTime = departTrip.getLong("createRequestedTime");
        createCarryOnCount =  departTrip.getInt("createCarryOnCount");
        createRollaboardCount =  departTrip.getInt("createRollaboardCount");
        createCheckInCount =  departTrip.getInt("createCheckInCount");
        createStatus = departTrip.getString("createStatus");

        //TextViews

        textViewOneWayHeader =findViewById(R.id.textViewOneWayHeader);
        textViewTravelDate = findViewById(R.id.textViewTravelDate);
        textViewSelectDate = findViewById(R.id.textViewSelectDate);
        textViewSelectDate.setOnClickListener(this);
        textViewTo = findViewById(R.id.textViewTo);
        textViewTravelTime = findViewById(R.id.textViewTravelTime);
        textViewSelectTime = findViewById(R.id.textViewSelectTime);
        textViewSelectTime.setOnClickListener(this);
        textViewLuggageHeader = findViewById(R.id.textViewLuggageHeader);

        //Spinners
        spinnerOrigin = findViewById(R.id.spinnerOriginReturn);
        spinnerDestination = findViewById(R.id.spinnerDestinationReturn);
        spinnerCarryOn = findViewById(R.id.spinnerCarryOn);
        spinnerRollaboard = findViewById(R.id.spinnerRollaboard);
        spinnerCheckIn = findViewById(R.id.spinnerCheckIn);

        buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(this);



        List<String> spinnerLocationsRet = new ArrayList<String>();
        spinnerLocationsRet.add("Ross");
        spinnerLocationsRet.add("DTW");

        ArrayAdapter<String> spinnerAdapterRet = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerLocationsRet);
        spinnerAdapterRet.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerOrigin.setOnItemSelectedListener(this);
        spinnerDestination.setOnItemSelectedListener(this);

        spinnerOrigin.setAdapter(spinnerAdapterRet);
        spinnerDestination.setAdapter(spinnerAdapterRet);

        int i;

        if (createEndLocation.equals("DTW")){
            i = 1;
        } else i = 0;

        spinnerOrigin.setSelection(i, true);


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

        //"remember" original trip values and preset for convenience
        spinnerCarryOn.setSelection(createCarryOnCount);
        spinnerRollaboard.setSelection(createRollaboardCount);
        spinnerCheckIn.setSelection(createCheckInCount);


    }

    @Override
    public void onClick(View v) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("triprequests");

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();


        if (v == buttonSave){

        //if user is logged in

            if(currentUser != null) {

                //get data and format properly
                String createRiderRet = currentUser.getEmail();
                String createuserIdRet = currentUser.getUid();
                String createStartLocationRet = spinnerOrigin.getSelectedItem().toString();
                String createEndLocationRet = spinnerDestination.getSelectedItem().toString();
                long createRequestedTimeRet = combinedCal.getTimeInMillis();
                Integer createCarryOnCountRet =  Integer.parseInt(spinnerCarryOn.getSelectedItem().toString());
                Integer createRollaboardCountRet =  Integer.parseInt(spinnerRollaboard.getSelectedItem().toString());
                Integer createCheckInCountRet =  Integer.parseInt(spinnerCheckIn.getSelectedItem().toString());
                String createStatusRet = "Pending";

                //translate into return tripRequest

                TripRequest createReturnTrip = new TripRequest(createRiderRet, createuserIdRet,
                        createStartLocationRet,
                        createEndLocationRet,
                        createRequestedTimeRet,
                        createCarryOnCountRet,
                        createRollaboardCountRet,
                        createCheckInCountRet,
                        createStatusRet);

                //retrieve depart bundle

                //convert bundle to depart tripRequest
                Timestamp createRequestedTimestamp = new Timestamp(createRequestedTime);

                TripRequest createDepartTrip = new TripRequest(createRider, createuserIdRet,
                        createStartLocation,
                        createEndLocation,
                        createRequestedTimestamp.getTime(),
                        createCarryOnCount,
                        createRollaboardCount,
                        createCheckInCount,
                        createStatus);

                //push to database

                myRef.push().setValue(createDepartTrip);
                myRef.push().setValue(createReturnTrip);

                //go to Upcoming Trips page
                Intent intent = new Intent(TravelReturnTrip.this, UpcomingTrips.class);
                startActivity(intent);
                Toast.makeText(this, "Trip Saved", Toast.LENGTH_SHORT).show();

            }

            //if user is not logged in

                else {

                    //rebundle depart trip data without username

                    Bundle departTrip = new Bundle();
                    //departTrip.putString("createRider", createRider);
                    departTrip.putString("createStartLocation", createStartLocation);
                    departTrip.putString("createEndLocation", createEndLocation);
                    departTrip.putLong("createRequestedTime", createRequestedTime);
                    departTrip.putInt("createCarryOnCount", createCarryOnCount);
                    departTrip.putInt("createRollaboardCount", createRollaboardCount);
                    departTrip.putInt("createCheckInCount", createCheckInCount);
                    departTrip.putString("createStatus", createStatus);

                    //get return trip data and format properly

                    //String createRiderRet = currentUser.getEmail();
                    String createStartLocationRet = spinnerOrigin.getSelectedItem().toString();
                    String createEndLocationRet = spinnerDestination.getSelectedItem().toString();
                    long createRequestedTimeRet = combinedCal.getTimeInMillis();
                    Integer createCarryOnCountRet =  Integer.parseInt(spinnerCarryOn.getSelectedItem().toString());
                    Integer createRollaboardCountRet =  Integer.parseInt(spinnerRollaboard.getSelectedItem().toString());
                    Integer createCheckInCountRet =  Integer.parseInt(spinnerCheckIn.getSelectedItem().toString());
                    String createStatusRet = "Pending";

                    //bundle Return Trip  data
                    Bundle returnTrip = new Bundle();

                    //returnTrip.putString("createRider", createRider);
                    returnTrip.putString("createStartLocationRet", createStartLocationRet);
                    returnTrip.putString("createEndLocationRet", createEndLocationRet);
                    returnTrip.putLong("createRequestedTimeRet", createRequestedTimeRet);
                    returnTrip.putInt("createCarryOnCountRet", createCarryOnCountRet);
                    returnTrip.putInt("createRollaboardCountRet", createRollaboardCountRet);
                    returnTrip.putInt("createCheckInCountRet", createCheckInCountRet);
                    returnTrip.putString("createStatusRet", createStatusRet);

                    // move to next page with bundle
                    Intent intent = new Intent(TravelReturnTrip.this, MainActivity.class);
                    intent.putExtras(returnTrip);
                    intent.putExtras(departTrip);
                    startActivity(intent);


                }

        } else if (v == textViewSelectDate) {
            showDatePicker();
        } else if (v == textViewSelectTime) {
            showTimePicker();
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        month = month + 1;
        selectedYear = year;
        selectedMonth = month;
        selectedDay = dayOfMonth;

        String date = month + "/" + dayOfMonth + "/" + year;
        textViewSelectDate.setText(date);

        combinedCal.set(year, month, dayOfMonth);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {

        String amPm;
        int hour12;
        selectedHour = hourOfDay;
        selectedMinute = minutes;

        if (hourOfDay >= 12){
            amPm = "PM";
            hour12 = hourOfDay - 12;
        } else {
            hour12 = hourOfDay;
            amPm = "AM";
        }

        if (hour12 == 0){
            hour12 = 12;
        }

        textViewSelectTime.setText(String.format("%02d:%02d", hour12, minutes) + " " + amPm);

        combinedCal.set(Calendar.HOUR_OF_DAY, hourOfDay);
        combinedCal.set(Calendar.MINUTE, minutes);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


        if (spinnerOrigin.getSelectedItem().toString() == "Ross"){
            spinnerDestination.setSelection(1, true);
            textViewTravelTime.setText(getResources().getString(R.string.gettodtwret));


        } else if (spinnerOrigin.getSelectedItem().toString() == "DTW") {
            spinnerDestination.setSelection(0, true);
            textViewTravelTime.setText(getResources().getString(R.string.leavefromdtwret));

        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void showDatePicker(){
        if (textViewSelectDate.getText().toString() == ""){
            DatePickerDialog datePicker = new DatePickerDialog(
                    this, this,
                    Calendar.getInstance().get(Calendar.YEAR),
                    Calendar.getInstance().get(Calendar.MONTH),
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            );

            datePicker.show();
        } else {
            DatePickerDialog datePicker = new DatePickerDialog(
                    this, this,
                    selectedYear,
                    selectedMonth,
                    selectedDay);

            datePicker.show();

        }



    }

    public void showTimePicker(){

        if (textViewSelectTime.getText().toString() == ""){
            TimePickerDialog timePick = new TimePickerDialog(
                    this, this,
                    12,
                    0,
                    false
            );

            timePick.show();
        } else {
            TimePickerDialog timePick = new TimePickerDialog(
                    this, this,
                    selectedHour,
                    selectedMinute,
                    false
            );

            timePick.show();
        }

    }

}
