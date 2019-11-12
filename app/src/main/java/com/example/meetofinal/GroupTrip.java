package com.example.meetofinal;

import java.sql.Timestamp;
import java.util.ArrayList;

public class GroupTrip {
    public ArrayList TripRequests;
    public Timestamp ConfirmedTime;

    public GroupTrip() {
    }

    public GroupTrip(ArrayList tripRequests, Timestamp confirmedTime) {
        TripRequests = tripRequests;
        ConfirmedTime = confirmedTime;
    }
}
