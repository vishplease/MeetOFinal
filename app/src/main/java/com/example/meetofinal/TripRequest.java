package com.example.meetofinal;

import java.sql.Timestamp;

public class TripRequest {
    public String rider, startLocation, endLocation;
    public Timestamp requestedTime;
    public int handBag, carryOn, checkIn;
    public boolean status;

    public TripRequest() {

    }

    public TripRequest(String rider, String startLocation, String endLocation, Timestamp requestedTime, int handBag, int carryOn, int checkIn, boolean status) {
        this.rider = rider;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.requestedTime = requestedTime;
        this.handBag = handBag;
        this.carryOn = carryOn;
        this.checkIn = checkIn;
        this.status = status;
    }
}
