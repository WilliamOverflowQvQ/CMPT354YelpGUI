package com.williamoverflow.cmpt354yelpgui.entities;
import java.sql.ResultSet;
import java.sql.SQLException;

public class YelpFriendship extends Entity{
    // Fields corresponding to the table columns
    public String user_id;
    public String friend;

    // Constructor
    public YelpFriendship(String user_id, String friend) {
        this.user_id = user_id;
        this.friend = friend;
    }

    public YelpFriendship(ResultSet rs){
        super(rs);
    }


//    public static YelpFriendship map(ResultSet rs) throws SQLException {
//        if (rs == null) {
//            return null;
//        }
//
//        String user_id = rs.getString("user_id");
//        String friend = rs.getString("friend");
//
//        return new YelpFriendship(user_id, friend);
//    }
}
