package com.williamoverflow.cmpt354yelpgui.entities;

import java.util.Date;

public class YelpTip {
    // Fields corresponding to the table columns
    private int tip_id;
    private String user_id;
    private String business_id;
    private Date date;
    private int compliment_count;

    // Constructor
    public YelpTip(int tip_id, String user_id, String business_id, Date date, int compliment_count) {
        this.tip_id = tip_id;
        this.user_id = user_id;
        this.business_id = business_id;
        this.date = date;
        this.compliment_count = compliment_count;
    }

    // Getters and setters for each field
    // ...

    // Optionally, you can also override toString, equals, and hashCode methods
    // ...
}
