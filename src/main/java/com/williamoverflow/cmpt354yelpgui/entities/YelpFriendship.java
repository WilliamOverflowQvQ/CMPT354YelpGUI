package com.williamoverflow.cmpt354yelpgui.entities;

public class YelpFriendship {
    // Fields corresponding to the table columns
    private String user_id;
    private String friend;

    // Constructor
    public YelpFriendship(String user_id, String friend) {
        this.user_id = user_id;
        this.friend = friend;
    }

    // Getters and setters for each field
    // ...

    // Optionally, you can also override toString, equals, and hashCode methods
    // ...
}
