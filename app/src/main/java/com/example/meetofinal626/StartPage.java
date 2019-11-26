package com.example.meetofinal626;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StartPage extends AppCompatActivity implements View.OnClickListener {

    Button buttonGetStarted;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        buttonGetStarted = findViewById(R.id.buttonGetStarted);
        buttonGetStarted.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in already signed in and take him to Portal Activity directly.
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser != null) {
            Intent intent = new Intent(StartPage.this, UpcomingTrips.class);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(StartPage.this, TravelOneWay.class);
        startActivity(intent);
    }
}
