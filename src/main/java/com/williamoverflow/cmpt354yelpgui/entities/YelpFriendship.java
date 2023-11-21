package com.williamoverflow.cmpt354yelpgui.entities;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public static YelpFriendship map(ResultSet rs) throws SQLException {
        if (rs == null) {
            return null;
        }

        String user_id = rs.getString("user_id");
        String friend = rs.getString("friend");

        return new YelpFriendship(user_id, friend);
    }
}