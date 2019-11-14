package com.example.meetofinal;

import java.sql.Timestamp;
import java.util.ArrayList;

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
