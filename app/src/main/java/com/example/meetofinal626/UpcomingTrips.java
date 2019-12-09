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
    RecyclerView rv;
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
        rv = findViewById(R.id.rv);

        buttonAddTrip.setOnClickListener(this);
        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        mAuth = FirebaseAuth.getInstance();

        // Read from the database

        //String user_id = mAuth.getCurrentUser().getEmail();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("triprequests");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //Toast.makeText(UpcomingTrips.this, snapshot.getValue().toString(), Toast.LENGTH_SHORT).show();
                    //TripRequest tripRequestTest = snapshot.getValue(TripRequest.class);
                    //tripRequestList.add(tripRequestTest);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


//        myRef.addChildEventListener(new ChildEventListener() {
////            @Override
//           public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
////
//                TripRequest upcomingtrip = dataSnapshot.getValue(TripRequest.class);
//
//                //taking all the firebase trip requests and putting it into a list
//
//              for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                  Toast.makeText(UpcomingTrips.this, snapshot.getValue().toString(), Toast.LENGTH_SHORT).show();
//                    //TripRequest tripRequestTest = snapshot.getValue(TripRequest.class);
//                    //tripRequestList.add(tripRequestTest);
//                    }
//
//              //convert list into an array; array is the size of the list
//              /*tripRequestArray = new TripRequest[tripRequestList.size()];
//              tripRequestArray = tripRequestList.toArray(tripRequestArray);
//
//              //setting up the array as a recycler view
//               tripsAdapter = new TripsAdapter(tripRequestArray);
//               rv.setAdapter(tripsAdapter);*/
//
//
//
////                String foundStatus = upcomingtrip.status;
////                String foundStart = upcomingtrip.startLocation;
////                String foundEnd = upcomingtrip.endLocation;
////                Timestamp foundTime = upcomingtrip.requestedTime;
////
////                textViewStatus.setText(foundStatus);
////                textviewTripStart.setText(foundStart);
////                textViewTripEnd.setText(foundEnd);
////
////
////
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });


        //converting the calendar into an easier date/time
        Calendar calendar = new GregorianCalendar(2013,1,28,13,24,56);

        //test array for the card view
//        upcomingTrips = new ArrayList<>();
//
//        upcomingTrips.add(new TripRequest("Angad", "AA", "DTW", new Timestamp(calendar.getTimeInMillis()), 3, 1, 2, "Match in Progress"));
        //upcomingTrips.add(new TripRequest("Rahul", "AA", "DTW",  calendar, 3, 1, 2, "Match in Progress"));
        //upcomingTrips.add(new TripRequest("Vish", "AA", "DTW",  calendar, 3, 1, 2, "Match in Progress"));
        //upcomingTrips.add(new TripRequest("Hikaru", "AA", "DTW",  calendar, 3, 1, 2, "Match in Progress"));
        //upcomingTrips.add(new TripRequest("Vish", "AA", "DTW", Timestamp.valueOf("2019-11-28 06:30:00"), 3, 1, 2, false));
        //upcomingTrips.add(new TripRequest("Hikaru", "AA", "DTW", Timestamp.valueOf("2019-11-28 06:30:00"), 3, 1, 2, false));


        //TripsAdapter adapter = new TripsAdapter(upcomingTrips);

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
