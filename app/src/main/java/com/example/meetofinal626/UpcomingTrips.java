package com.example.meetofinal626;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class UpcomingTrips extends AppCompatActivity implements View.OnClickListener {

    //declaring objects
    FloatingActionButton buttonAddTrip;
    private List<TripRequest> upcomingTrips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_trips);

        //adding the floating add trip button
        buttonAddTrip = findViewById(R.id.buttonAddTrip);
        buttonAddTrip.setOnClickListener(this);

        RecyclerView rv = findViewById(R.id.rv);
        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        upcomingTrips = new ArrayList<>();
        //Angad here - I am having difficulty understanding how firebase UserID and Calendar classes are stored.  So I don't know how to pull it down into this array. Will chat through it with you all later this weekend

        upcomingTrips.add(new TripRequest("Angad", "AA", "DTW", Timestamp.valueOf("2019-11-28 06:30:00"), 3, 1, 2, false));
        //upcomingTrips.add(new TripRequest("Rahul", "AA", "DTW", Timestamp.valueOf("2019-11-28 06:30:00"), 3, 1, 2, false));
        //upcomingTrips.add(new TripRequest("Vish", "AA", "DTW", Timestamp.valueOf("2019-11-28 06:30:00"), 3, 1, 2, false));
        //upcomingTrips.add(new TripRequest("Hikaru", "AA", "DTW", Timestamp.valueOf("2019-11-28 06:30:00"), 3, 1, 2, false));


        TripsAdapter adapter = new TripsAdapter(upcomingTrips);
        rv.setAdapter(adapter);
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
