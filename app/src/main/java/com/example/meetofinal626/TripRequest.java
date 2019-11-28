package com.example.meetofinal626;

import com.google.firebase.auth.FirebaseUser;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TripRequest {
    public FirebaseUser riderID;
    public String startLocation, endLocation;
    public Calendar requestedTime;
    public int handBag, carryOn, checkIn;
    public boolean status;

    public TripRequest(FirebaseUser rider) {
        this.riderID = rider;
    }

    public TripRequest(FirebaseUser riderID,
                       String startLocation,
                       String endLocation,
                       Calendar requestedTime,
                       int handBag,
                       int carryOn,
                       int checkIn,
                       boolean status) {
        this.riderID = riderID;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.requestedTime = requestedTime;
        this.handBag = handBag;
        this.carryOn = carryOn;
        this.checkIn = checkIn;
        this.status = status;
    }
}
