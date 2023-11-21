package com.williamoverflow.cmpt354yelpgui.entities;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class YelpUser {
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


    public static YelpUser map(ResultSet resultSet) throws SQLException {
        String user_id = resultSet.getString("user_id");
        String name = resultSet.getString("name");
        int review_count = resultSet.getInt("review_count");
        Timestamp yelping_since = resultSet.getTimestamp("yelping_since");
        int useful = resultSet.getInt("useful");
        int funny = resultSet.getInt("funny");
        int cool = resultSet.getInt("cool");
        int fans = resultSet.getInt("fans");
        double average_stars = resultSet.getDouble("average_stars");

        return new YelpUser(user_id, name, review_count, yelping_since, useful, funny, cool, fans, average_stars);
    }
}
