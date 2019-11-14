package com.example.meetofinal;

import java.sql.Timestamp;
import java.util.ArrayList;

public class GroupTrip {
    public String[] tripRequests;
  //  public ArrayList TripRequests;
    public Timestamp confirmedTime;

    public GroupTrip() {
    }

    public GroupTrip(ArrayList tripRequests, Timestamp confirmedTime) {
        tripRequests = tripRequests;
        confirmedTime = confirmedTime;
    }
}
