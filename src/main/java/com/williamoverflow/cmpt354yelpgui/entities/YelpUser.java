package com.williamoverflow.cmpt354yelpgui.entities;


import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;

public class YelpUser extends Entity{
    public String getUser_id() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public int getReview_count() {
        return review_count;
    }

    public Timestamp getYelping_since() {
        return yelping_since;
    }

    public int getUseful() {
        return useful;
    }

    public int getFunny() {
        return funny;
    }

    public int getCool() {
        return cool;
    }

    public int getFans() {
        return fans;
    }

    public BigDecimal getAverage_stars() {
        return average_stars;
    }

    public String user_id;
    public String name;
    public int review_count;
    public Timestamp yelping_since;
    public int useful;
    public int funny;
    public int cool;
    public int fans;
    public BigDecimal average_stars;    // double not working, need to be BigDecimal

    public YelpUser(String user_id, String name, int review_count, Timestamp yelping_since, int useful, int funny, int cool, int fans, BigDecimal average_stars){
        this.user_id = user_id;
        this.name = name;
        this.review_count = review_count;
        this.yelping_since = yelping_since;
        this.useful = useful;
        this.funny = funny;
        this.cool = cool;
        this.fans = fans;
        this.average_stars = average_stars;
    }

    public YelpUser(ResultSet rs){
        super(rs);
    }


//    public static YelpUser map(ResultSet rs) throws SQLException {
//        if(rs == null){
//            return null;
//        }
//        String user_id = rs.getString("user_id");
//        String name = rs.getString("name");
//        int review_count = rs.getInt("review_count");
//        Timestamp yelping_since = rs.getTimestamp("yelping_since");
//        int useful = rs.getInt("useful");
//        int funny = rs.getInt("funny");
//        int cool = rs.getInt("cool");
//        int fans = rs.getInt("fans");
//        double average_stars = rs.getDouble("average_stars");
//
//        return new YelpUser(user_id, name, review_count, yelping_since, useful, funny, cool, fans, average_stars);
//    }
}
