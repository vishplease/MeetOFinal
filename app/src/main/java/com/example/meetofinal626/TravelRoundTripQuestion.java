package com.example.meetofinal626;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Timestamp;

public class TravelRoundTripQuestion extends AppCompatActivity implements View.OnClickListener {

    Button buttonYes, buttonNo;

    public String createRider;
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
        setContentView(R.layout.activity_travel_round_trip_question);

        buttonYes = findViewById(R.id.buttonYes);
        buttonNo = findViewById(R.id.buttonNo);

        buttonYes.setOnClickListener(this);
        buttonNo.setOnClickListener(this);

        Intent intent = getIntent();
        Bundle departTrip = intent.getExtras();

        createRider = departTrip.getString("createRider");
        createStartLocation = departTrip.getString("createStartLocation");
        createEndLocation = departTrip.getString("createEndLocation");
        createRequestedTime = departTrip.getLong("createRequestedTime");
        createCarryOnCount =  departTrip.getInt("createCarryOnCount");
        createRollaboardCount =  departTrip.getInt("createRollaboardCount");
        createCheckInCount =  departTrip.getInt("createCheckInCount");
        createStatus = departTrip.getString("createStatus");
    }

    @Override
    public void onClick(View v) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("triprequests");

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        //if user is logged in

        if(currentUser != null) {

            if (v == buttonYes){ //roundtrip - send two bundles with username intent
                Bundle departTrip = new Bundle();
                departTrip.putString("createRider", createRider);
                departTrip.putString("createStartLocation", createStartLocation);
                departTrip.putString("createEndLocation", createEndLocation);
                departTrip.putLong("createRequestedTime", createRequestedTime);
                departTrip.putInt("createCarryOnCount", createCarryOnCount);
                departTrip.putInt("createRollaboardCount", createRollaboardCount);
                departTrip.putInt("createCheckInCount", createCheckInCount);
                departTrip.putString("createStatus", createStatus);

                Intent intent = new Intent(TravelRoundTripQuestion.this, TravelReturnTrip.class);
                intent.putExtras(departTrip);
                startActivity(intent);
            }
            else if (v == buttonNo){ //oneway - upload to database and send to upcoming trips

                Timestamp createRequestedTimestamp = new Timestamp(createRequestedTime);

                TripRequest createTrip = new TripRequest(createRider,
                        createStartLocation,
                        createEndLocation,
                        createRequestedTimestamp,
                        createCarryOnCount,
                        createRollaboardCount,
                        createCheckInCount,
                        createStatus);


                //push to database

                myRef.push().setValue(createTrip);

                Intent intent = new Intent(TravelRoundTripQuestion.this, UpcomingTrips.class);
                startActivity(intent);
                Toast.makeText(this, "Trip Saved", Toast.LENGTH_SHORT).show();
            }


        }

        //if user is not logged in

        else {

            if (v == buttonYes){
                Bundle departTrip = new Bundle();
                //departTrip.putString("createRider", createRider);
                departTrip.putString("createStartLocation", createStartLocation);
                departTrip.putString("createEndLocation", createEndLocation);
                departTrip.putLong("createRequestedTime", createRequestedTime);
                departTrip.putInt("createCarryOnCount", createCarryOnCount);
                departTrip.putInt("createRollaboardCount", createRollaboardCount);
                departTrip.putInt("createCheckInCount", createCheckInCount);
                departTrip.putString("createStatus", createStatus);

                Intent intent = new Intent(TravelRoundTripQuestion.this, TravelReturnTrip.class);
                intent.putExtras(departTrip);
                startActivity(intent);

            }
            else if (v == buttonNo){


                Bundle departTrip = new Bundle();
                //departTrip.putString("createRider", createRider);
                departTrip.putString("createStartLocation", createStartLocation);
                departTrip.putString("createEndLocation", createEndLocation);
                departTrip.putLong("createRequestedTime", createRequestedTime);
                departTrip.putInt("createCarryOnCount", createCarryOnCount);
                departTrip.putInt("createRollaboardCount", createRollaboardCount);
                departTrip.putInt("createCheckInCount", createCheckInCount);
                departTrip.putString("createStatus", createStatus);

                Intent intent = new Intent(TravelRoundTripQuestion.this, MainActivity.class);
                intent.putExtras(departTrip);
                startActivity(intent);
            }


        }



    }
}
