package com.williamoverflow.cmpt354yelpgui.entities;
import java.util.Date;

public class YelpCheckIn {
    // Fields corresponding to the table columns
    private int checkin_id;
    private String business_id;
    private Date date;

    // Constructor
    public YelpCheckIn(int checkin_id, String business_id, Date date) {
        this.checkin_id = checkin_id;
        this.business_id = business_id;
        this.date = date;
    }

    // Getters and setters for each field
    // ...

    // Optionally, you can also override toString, equals, and hashCode methods
    // ...
}