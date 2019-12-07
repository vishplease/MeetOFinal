package com.example.meetofinal626;

import com.google.firebase.auth.FirebaseUser;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TripRequest {
    public String riderID;
    public String startLocation, endLocation;
    public Timestamp requestedTime;
    public int handBag, carryOn, checkIn;
    public String status;

    public TripRequest() {};

    public TripRequest(String rider) {
        this.riderID = rider;
    }

    public TripRequest(String riderID,
                       String startLocation,
                       String endLocation,
                       Timestamp requestedTime,
                       int handBag,
                       int carryOn,
                       int checkIn,
                       String status) {
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
