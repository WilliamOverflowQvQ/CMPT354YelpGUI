package com.williamoverflow.cmpt354yelpgui.entities;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class YelpUser extends Entity{
    public String user_id;
    public String name;
    public int reviewCount;
    public Timestamp yelpingSince;
    public int useful;
    public int funny;
    public int cool;
    public int fans;
    public double averageStars;

    public YelpUser(String user_id, String name, int reviewCount, Timestamp yelpingSince, int useful, int funny, int cool, int fans, double averageStars){
        this.user_id = user_id;
        this.name = name;
        this.reviewCount = reviewCount;
        this.yelpingSince = yelpingSince;
        this.useful = useful;
        this.funny = funny;
        this.cool = cool;
        this.fans = fans;
        this.averageStars = averageStars;
    }


    public static YelpUser map(ResultSet rs) throws SQLException {
        if(rs == null){
            return null;
        }
        String user_id = rs.getString("user_id");
        String name = rs.getString("name");
        int review_count = rs.getInt("review_count");
        Timestamp yelping_since = rs.getTimestamp("yelping_since");
        int useful = rs.getInt("useful");
        int funny = rs.getInt("funny");
        int cool = rs.getInt("cool");
        int fans = rs.getInt("fans");
        double average_stars = rs.getDouble("average_stars");

        return new YelpUser(user_id, name, review_count, yelping_since, useful, funny, cool, fans, average_stars);
    }
}
