package com.example.meetofinal626;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;

public class ConfirmTrip extends AppCompatActivity implements View.OnClickListener{
    Button buttonBackConfirm, buttonCoRiders, buttonCancelTrip;
    TextView textViewConfirmTrip, textViewConfirmYourTrip, textViewRidingMembers2;
    TextView textViewMustConfirm, textViewTimeLimit;


    public String createRider, createUser;
    public String createStartLocation;
    public String createEndLocation;
    public long createRequestedTime;
    public Integer createCarryOnCount;
    public Integer createRollaboardCount;
    public Integer createCheckInCount;
    public String createStatus;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_trip);

        Intent intent = getIntent();
        Bundle tripRequest = intent.getExtras();

        createRider = tripRequest.getString("createRider");
        createUser = tripRequest.getString("createUser");
        createStartLocation = tripRequest.getString("createStartLocation");
        createEndLocation = tripRequest.getString("createEndLocation");
        createRequestedTime = tripRequest.getLong("createRequestedTime");
        createCarryOnCount =  tripRequest.getInt("createCarryOnCount");
        createRollaboardCount =  tripRequest.getInt("createRollaboardCount");
        createCheckInCount =  tripRequest.getInt("createCheckInCount");
        createStatus = tripRequest.getString("createStatus");


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

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("triprequests");

        if (buttonCoRiders == view) {
            Intent intent = new Intent(this, MatchedTripSummary.class);
            startActivity(intent);




        }
        if (buttonCancelTrip == view) {

            final Long deleteRequestedTime = createRequestedTime;
            myRef.orderByChild("requestedTime").equalTo(deleteRequestedTime).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    //this works, but could be an issue if two people request a trip at precisely the same time
                    String deleteKey = dataSnapshot.getKey();
                    myRef.child(deleteKey).setValue(null);
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            Intent intent = new Intent(this, UpcomingTrips.class);
            startActivity(intent);
        }
        if (buttonBackConfirm == view) {
            Toast.makeText(this, "Trip Deleted", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ConfirmTrip.this, UpcomingTrips.class);
            startActivity(intent);
        }
    }



}

