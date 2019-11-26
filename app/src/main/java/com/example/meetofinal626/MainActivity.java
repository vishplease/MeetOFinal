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


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;


    TextView textViewHeadline;
    EditText editTextEmail;
    EditText editTextPassword;
    Button buttonLogIn;
    Button buttonRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                            Intent intent = new Intent(MainActivity.this, UpcomingTrips.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }


    public void loginNewUsers (String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
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


