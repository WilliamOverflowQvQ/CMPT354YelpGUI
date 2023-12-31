package com.williamoverflow.cmpt354yelpgui.entities;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Date;

public class YelpReview extends Entity{
    // Fields corresponding to the table columns
    public String review_id;
    public String user_id;
    public String business_id;
    public int stars;
    public int useful;
    public int funny;
    public int cool;
    public Date date;

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

    public YelpReview(ResultSet rs){
        super(rs);
    }

//    public static YelpReview map(ResultSet rs) throws SQLException {
//        if (rs == null) {
//            return null;
//        }
//
//        String review_id = rs.getString("review_id");
//        String user_id = rs.getString("user_id");
//        String business_id = rs.getString("business_id");
//        int stars = rs.getInt("stars");
//        int useful = rs.getInt("useful");
//        int funny = rs.getInt("funny");
//        int cool = rs.getInt("cool");
//        Date date = rs.getDate("date"); // Assumes 'date' is stored in a suitable format in the database
//
//        return new YelpReview(review_id, user_id, business_id, stars, useful, funny, cool, date);
//    }
}
