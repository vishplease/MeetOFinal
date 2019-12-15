package com.example.meetofinal626;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Calendar;

public class MatchedTripSummary extends AppCompatActivity implements View.OnClickListener  {

    TextView textViewRidingWith, textViewRidingMembers,textViewRidingMembers2;
    TextView textViewTripSummary, textViewLeave, textViewTimeSchedule, textViewLuggage;
    TextView textViewNumber1, textViewNumber2, textViewNumber3, textViewNumber4, textViewNumber5, textViewNumber6;
    ImageView imageView0, imageView2, imageView4;
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    Button buttonGoBack;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matched_trip_summary);

        buttonGoBack = findViewById(R.id. buttonGoBack);
        buttonGoBack.setOnClickListener(this);

        textViewRidingMembers = findViewById(R.id.textViewRidingMembers);
        textViewRidingWith = findViewById(R.id.textViewRidingWIth);
        textViewTripSummary = findViewById(R.id.textViewTripSummary);
        textViewLeave = findViewById(R.id.textViewLeave);
        textViewTimeSchedule = findViewById(R.id.textViewTimeSchedule);
        textViewRidingMembers2 = findViewById(R.id.textViewRidingMembers2);
        textViewLuggage = findViewById(R.id.textViewLuggage);
        textViewNumber1 = findViewById(R.id.textViewNumber1);
        textViewNumber2 = findViewById(R.id.textViewNumber2);
        textViewNumber3 = findViewById(R.id.textViewNumber3);
        textViewNumber4 = findViewById(R.id.textViewNumber4);
        textViewNumber5 = findViewById(R.id.textViewNumber5);
        textViewNumber6 = findViewById(R.id.textViewNumber6);



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference().child("triprequests");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Log.e("MatchedTripSummary",snapshot.getKey());
                    if (snapshot.getKey().equals("-LvhRaahW5g51esX-7ZF")) {
                        GroupTrip foundGroupTrip = snapshot.getValue(GroupTrip.class);
                        String findGroupTrip = foundGroupTrip.riderID;
                        textViewRidingMembers.setText(findGroupTrip);
                        Integer findLuggage = foundGroupTrip.handBag;
                        textViewNumber1.setText(String.valueOf(findLuggage));
                        Integer findLuggage1 = foundGroupTrip.carryOn;
                        textViewNumber2.setText(String.valueOf(findLuggage1));
                        Integer findLuggage3 = foundGroupTrip.checkIn;
                        textViewNumber3.setText(String.valueOf(findLuggage3));





                    } else if (snapshot.getKey().equals("-LvwORwHTEiPWYjc22mH")) {
                        GroupTrip foundGroupTrip = snapshot.getValue(GroupTrip.class);
                        String findGroupTrip2 = foundGroupTrip.riderID;
                        textViewRidingMembers2.setText(findGroupTrip2);
                        Integer findLuggage4 = foundGroupTrip.handBag;
                        textViewNumber4.setText(String.valueOf(findLuggage4));
                        Integer findLuggage5 = foundGroupTrip.carryOn;
                        textViewNumber5.setText(String.valueOf(findLuggage5));
                        Integer findLuggage6 = foundGroupTrip.checkIn;
                        textViewNumber6.setText(String.valueOf(findLuggage6));
                    }
                    Calendar calendar = Calendar.getInstance();
                    String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

                    TextView textViewTimeSchedule = findViewById(R.id.textViewTimeSchedule);
                    textViewTimeSchedule.setText(currentDate);


                }


            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onClick(View view) {
        if (buttonGoBack == view) {
        Intent mainIntent = new Intent(MatchedTripSummary.this,UpcomingTrips.class);
        startActivity(mainIntent);
        }
    }
}