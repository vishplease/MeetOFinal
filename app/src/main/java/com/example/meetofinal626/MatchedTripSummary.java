package com.example.meetofinal626;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class MatchedTripSummary extends AppCompatActivity {

    TextView textViewRidingWith, textViewRidingMembers,textViewRidingMembers2;
    TextView textViewTripSummary, textViewLeave, textViewTimeSchedule, textViewLuggage;
    TextView textViewNumber1, textViewNumber2, textViewNumber3;
    ImageView imageView0, imageView2, imageView4;
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matched_trip_summary);

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

                    } else if (snapshot.getKey().equals("-LvhRaam-ObUAFJ1tqMC")) {
                        GroupTrip foundGroupTrip = snapshot.getValue(GroupTrip.class);
                        String findGroupTrip2 = foundGroupTrip.riderID;
                        textViewRidingMembers2.setText(findGroupTrip2);

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
}