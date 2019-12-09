package com.example.meetofinal626;

import java.sql.Timestamp;

public class GroupTrip {
    public String[] tripRequests;
    public Timestamp confirmedTime;


    public GroupTrip() {
    }

    public GroupTrip(String[] tripRequests, Timestamp confirmedTime) {
        this.tripRequests = tripRequests;
        this.confirmedTime = confirmedTime;
    }
}
