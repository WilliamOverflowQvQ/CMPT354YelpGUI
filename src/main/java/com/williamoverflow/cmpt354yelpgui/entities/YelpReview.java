package com.williamoverflow.cmpt354yelpgui.entities;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Date;

public class YelpReview {
    // Fields corresponding to the table columns
    private String review_id;
    private String user_id;
    private String business_id;
    private int stars;
    private int useful;
    private int funny;
    private int cool;
    private Date date;

    // Constructor
    public YelpReview(String review_id, String user_id, String business_id, int stars, int useful, int funny, int cool, Date date) {
        this.review_id = review_id;
        this.user_id = user_id;
        this.business_id = business_id;
        this.stars = stars;
        this.useful = useful;
        this.funny = funny;
        this.cool = cool;
        this.date = date;
    }

    // Getters and setters for each field
    // ...

    // Optionally, you can also override toString, equals, and hashCode methods
    // ...
    public static YelpReview map(ResultSet rs) throws SQLException {
        if (rs == null) {
            return null;
        }

        String review_id = rs.getString("review_id");
        String user_id = rs.getString("user_id");
        String business_id = rs.getString("business_id");
        int stars = rs.getInt("stars");
        int useful = rs.getInt("useful");
        int funny = rs.getInt("funny");
        int cool = rs.getInt("cool");
        Date date = rs.getDate("date"); // Assumes 'date' is stored in a suitable format in the database

        return new YelpReview(review_id, user_id, business_id, stars, useful, funny, cool, date);
    }
}
