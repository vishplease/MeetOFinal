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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class UpcomingTrips extends AppCompatActivity implements View.OnClickListener {

    //declaring objects
    FloatingActionButton buttonAddTrip;
    TextView textViewStatus, textviewTripStart, textViewTripEnd, textViewTripDate, textViewTripTime;
    EditText editTextEmail;

    ArrayList<TripRequest> list;

    //List<TripRequest> tripRequestList = new ArrayList<>();
    //private TripRequest[] tripRequestArray;

    TripsAdapter adapter;
    DatabaseReference reference;
    RecyclerView rv;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_trips);

        //connecting objects to the UI
        buttonAddTrip = findViewById(R.id.buttonAddTrip);
        buttonAddTrip.setOnClickListener(this);
        textViewStatus = findViewById(R.id.textViewStatus);
        textviewTripStart = findViewById(R.id.textViewTripStart);
        textViewTripEnd = findViewById(R.id.textViewTripEnd);
        textViewTripDate = findViewById(R.id.textViewTripDate);
        textViewTripTime = findViewById(R.id.textViewTripTime);
        editTextEmail = findViewById(R.id.editTextEmail);

        rv = findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);



        mAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        final String user_id = mAuth.getCurrentUser().getEmail();

        if (currentUser != null) {

            //Read from the database
            reference = FirebaseDatabase.getInstance().getReference("triprequests");

            reference.orderByChild("riderID").equalTo(user_id).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    list = new ArrayList<TripRequest>();
                    for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                        TripRequest t = dataSnapshot1.getValue(TripRequest.class);
                        list.add(t);
                    }



                    adapter = new TripsAdapter(UpcomingTrips.this, list);
                    rv.setAdapter(adapter);


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(UpcomingTrips.this, "Hmmm something went wrong", Toast.LENGTH_SHORT).show();

                }
            });
/*
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            final DatabaseReference myRef = database.getReference("triprequests");
            //create an array
            final ArrayList upcomingTrips = new ArrayList<>();

            myRef.orderByChild("riderID").equalTo(user_id).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    TripRequest tripRequest = dataSnapshot.getValue(TripRequest.class);
                    //populating an array with YOUR trip requests
                    upcomingTrips.add(tripRequest);
                    //new adapter object and sets recycler view adapter to this adapter
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

 */

            //if user is not logged in
        } else {
            Intent intent = new Intent(UpcomingTrips.this, StartPage.class);
            startActivity(intent);

        }


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
