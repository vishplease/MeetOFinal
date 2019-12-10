package com.example.meetofinal626;

import java.sql.Timestamp;

public class GroupTrip {
    public String[] tripRequests;
    public Timestamp timestamp;
    public Integer carryOn;
    public Integer checkIn;
    public Integer handBag;
    public String riderID;


    public GroupTrip(String[] strings, Timestamp timestamp) {
    }

    public GroupTrip(String[] tripRequests, Timestamp confirmedTime, Integer carryOn,Integer checkIn,Integer handBag,String riderID) {
        this.tripRequests = tripRequests;
        this.timestamp = confirmedTime;
        this.carryOn = carryOn;
        this.checkIn = checkIn;
        this.handBag = handBag;
        this.riderID = riderID;
    }
    public GroupTrip(){

    }
}
