package com.williamoverflow.cmpt354yelpgui.entities;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Date;

public class YelpCheckIn extends Entity{
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


    public static YelpCheckIn map(ResultSet rs) throws SQLException {
        if (rs == null) {
            return null;
        }

        int checkin_id = rs.getInt("checkin_id");
        String business_id = rs.getString("business_id");
        Date date = rs.getTimestamp("date"); // Assuming 'date' is stored as a timestamp in your database

        return new YelpCheckIn(checkin_id, business_id, date);
    }
}