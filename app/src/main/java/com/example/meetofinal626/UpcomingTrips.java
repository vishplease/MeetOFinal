package com.example.meetofinal626;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class UpcomingTrips extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton buttonAddTrip;
    private List<TripRequest> upcomingTrips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_trips);

        buttonAddTrip = findViewById(R.id.buttonAddTrip);
        buttonAddTrip.setOnClickListener(this);

        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);
        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        upcomingTrips = new ArrayList<>();
        upcomingTrips.add(new TripRequest("Angad Banga"));
        upcomingTrips.add(new TripRequest("Hiraku Ozeki"));
        upcomingTrips.add(new TripRequest("Rahul Bhardwaj"));
        upcomingTrips.add(new TripRequest("Vish Chandawarkar"));

        TripsAdapter adapter = new TripsAdapter(upcomingTrips);
        rv.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(UpcomingTrips.this, TravelOneWay.class);
        startActivity(intent);
    }
}
