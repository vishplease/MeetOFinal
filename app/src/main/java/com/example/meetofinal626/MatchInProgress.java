package com.example.meetofinal626;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MatchInProgress extends AppCompatActivity implements View.OnClickListener{
    Button buttonEdit, buttonGoBack, buttonCancel;
    TextView textViewNotYet, TextViewNotify;

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

        setContentView(R.layout.activity_match_in_progress);

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


        buttonEdit = findViewById(R.id.buttonEdit);
        buttonEdit.setOnClickListener(this);

        buttonGoBack = findViewById(R.id.buttonGoBack);
        buttonGoBack.setOnClickListener(this);

        buttonCancel = findViewById(R.id.buttonCancel);
        buttonCancel.setOnClickListener(this);


        mAuth = FirebaseAuth.getInstance();


    }

    @Override
    public void onClick(View view) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("triprequests");



        if (buttonGoBack == view) {
            Intent mainIntent = new Intent(MatchInProgress.this, UpcomingTrips.class);
            startActivity(mainIntent);
        }
        if (buttonEdit == view) {
            //Intent mainIntent = new Intent(MatchInProgress.this, TravelOneWay.class);
        }
        if (buttonCancel == view) {

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


            Toast.makeText(this, "Trip Deleted", Toast.LENGTH_SHORT).show();
            Intent mainIntent = new Intent(MatchInProgress.this, UpcomingTrips.class);
            startActivity(mainIntent);
        }

    }
}
