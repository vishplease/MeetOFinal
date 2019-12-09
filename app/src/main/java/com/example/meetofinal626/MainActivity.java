package com.example.meetofinal626;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Timestamp;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;


    TextView textViewHeadline;
    EditText editTextEmail;
    EditText editTextPassword;
    Button buttonLogIn;
    Button buttonRegister;

    public String createRider;
    public String createStartLocation;
    public String createEndLocation;
    public long createRequestedTime;
    public Integer createCarryOnCount;
    public Integer createRollaboardCount;
    public Integer createCheckInCount;
    public String createStatus;

    public String createRiderRet;
    public String createStartLocationRet;
    public String createEndLocationRet;
    public long createRequestedTimeRet;
    public Integer createCarryOnCountRet;
    public Integer createRollaboardCountRet;
    public Integer createCheckInCountRet;
    public String createStatusRet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //get depart bundle
        Intent intent = getIntent();
        Bundle departTrip = intent.getExtras();

        createStartLocation = departTrip.getString("createStartLocation");
        createEndLocation = departTrip.getString("createEndLocation");
        createRequestedTime = departTrip.getLong("createRequestedTime");
        createCarryOnCount =  departTrip.getInt("createCarryOnCount");
        createRollaboardCount =  departTrip.getInt("createRollaboardCount");
        createCheckInCount =  departTrip.getInt("createCheckInCount");
        createStatus = departTrip.getString("createStatus");



        //get return bundle

        Bundle returnTrip = intent.getExtras();
        createStartLocationRet = returnTrip.getString("createStartLocationRet");
        createEndLocationRet = returnTrip.getString("createEndLocationRet");
        createRequestedTimeRet = returnTrip.getLong("createRequestedTimeRet");
        createCarryOnCountRet =  returnTrip.getInt("createCarryOnCountRet");
        createRollaboardCountRet =  returnTrip.getInt("createRollaboardCountRet");
        createCheckInCountRet =  returnTrip.getInt("createCheckInCountRet");
        createStatusRet = returnTrip.getString("createStatusRet");



        textViewHeadline = findViewById(R.id.textViewHeadline);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogIn = findViewById(R.id.buttonLogIn);
        buttonRegister = findViewById(R.id.buttonRegister);

        buttonLogIn.setOnClickListener(this);
        buttonRegister.setOnClickListener(this);


        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {

        if (v == buttonRegister) {
            if(editTextEmail.getText().toString().isEmpty() || editTextPassword.getText().toString().isEmpty()){
                Toast.makeText(MainActivity.this, "Please enter a valid email and password", Toast.LENGTH_SHORT).show();
            }
            else {
                makeNewUsers(editTextEmail.getText().toString(), editTextPassword.getText().toString());
            }


        } else if (v == buttonLogIn){
            if(editTextEmail.getText().toString().isEmpty() || editTextPassword.getText().toString().isEmpty()){
                Toast.makeText(MainActivity.this, "Please enter a valid email and password", Toast.LENGTH_SHORT).show();
            }
            else {
                loginNewUsers(editTextEmail.getText().toString(), editTextPassword.getText().toString());
            }
        }

    }


    public void makeNewUsers (String email, String password ){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Registration Successful. Please sign in to save your trip.", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }


    public void loginNewUsers (final String email, String password){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("triprequests");

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            //get new user's email
                            createRiderRet = email;
                            createRider = email;


                            //create return tripRequest

                            Timestamp createRequestedTimestampReturn = new Timestamp(createRequestedTimeRet);

                            TripRequest createReturnTrip = new TripRequest(createRiderRet,
                                    createStartLocationRet,
                                    createEndLocationRet,
                                    createRequestedTimestampReturn.getTime(),
                                    createCarryOnCountRet,
                                    createRollaboardCountRet,
                                    createCheckInCountRet,
                                    createStatusRet);

                            //retrieve depart bundle

                            //convert bundle to depart tripRequest

                            Timestamp createRequestedTimestamp = new Timestamp(createRequestedTime);

                            TripRequest createDepartTrip = new TripRequest(createRider,
                                    createStartLocation,
                                    createEndLocation,
                                    createRequestedTimestamp.getTime(),
                                    createCarryOnCount,
                                    createRollaboardCount,
                                    createCheckInCount,
                                    createStatus);

                            //push to database

                            if (createStartLocationRet != null){ //if return trip exists push roundtrip
                                myRef.push().setValue(createDepartTrip);
                                myRef.push().setValue(createReturnTrip);
                            } else { //if oneway then push one trip
                                myRef.push().setValue(createDepartTrip);

                            }




                            // Take user to Upcoming Trips Activity when login is successful
                            Intent intent = new Intent(MainActivity.this, UpcomingTrips.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void loginSuccess () {

    }

    public boolean passwordCheck() {
        if (editTextPassword.getText().toString().length() < 6) {
            Toast.makeText(MainActivity.this, "Please enter a password at least 6 characters long.", Toast.LENGTH_SHORT).show();
            return false;
        } else return true;

    }



}


