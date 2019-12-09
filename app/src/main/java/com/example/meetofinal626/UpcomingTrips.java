package com.example.meetofinal626;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class UpcomingTrips extends AppCompatActivity implements View.OnClickListener {

    //declaring objects
    FloatingActionButton buttonAddTrip;
    TextView textViewStatus, textviewTripStart, textViewTripEnd, textViewTripDate, textViewTripTime;
    EditText editTextEmail;
    List<TripRequest> tripRequestList = new ArrayList<>();
    private TripRequest[] tripRequestArray;
    TripsAdapter tripsAdapter;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_trips);

        //connecting objects to the UI
        buttonAddTrip = findViewById(R.id.buttonAddTrip);
        textViewStatus = findViewById(R.id.textViewStatus);
        textviewTripStart = findViewById(R.id.textViewTripStart);
        textViewTripEnd = findViewById(R.id.textViewTripEnd);
        textViewTripDate = findViewById(R.id.textViewTripDate);
        textViewTripTime = findViewById(R.id.textViewTripTime);
        editTextEmail = findViewById(R.id.editTextEmail);
        final RecyclerView rv = (RecyclerView)findViewById(R.id.rv);

        buttonAddTrip.setOnClickListener(this);
        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        mAuth = FirebaseAuth.getInstance();

        //Read from the database

        String user_id = mAuth.getCurrentUser().getEmail();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("triprequests");

        final ArrayList upcomingTrips = new ArrayList<>();

        myRef.orderByChild("riderID").equalTo(user_id).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                TripRequest tripRequest = dataSnapshot.getValue(TripRequest.class);
                upcomingTrips.add(tripRequest);
                TripsAdapter adapter = new TripsAdapter(upcomingTrips);
                rv.setAdapter(adapter);
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemLogout) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(UpcomingTrips.this, StartPage.class);
            startActivity(intent);
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(UpcomingTrips.this, TravelOneWay.class);
        startActivity(intent);
    }
}
