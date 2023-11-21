package com.williamoverflow.cmpt354yelpgui.entities;


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
}
