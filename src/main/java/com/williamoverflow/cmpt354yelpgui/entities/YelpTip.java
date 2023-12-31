package com.williamoverflow.cmpt354yelpgui.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Date;

public class YelpTip extends Entity{
    // Fields corresponding to the table columns
    public int tip_id;
    public String user_id;
    public String business_id;
    public Date date;
    public int compliment_count;

    // Constructor
    public YelpTip(int tip_id, String user_id, String business_id, Date date, int compliment_count) {
        this.tip_id = tip_id;
        this.user_id = user_id;
        this.business_id = business_id;
        this.date = date;
        this.compliment_count = compliment_count;
    }

    public YelpTip(ResultSet rs){
        super(rs);
    }

//    public static YelpTip mapResultSetToYelpTip(ResultSet rs) throws SQLException {
//        if (rs == null) {
//            return null;
//        }
//
//        int tip_id = rs.getInt("tip_id");
//        String user_id = rs.getString("user_id");
//        String business_id = rs.getString("business_id");
//        Date date = rs.getDate("date"); // Assumes 'date' is stored in a suitable format in the database
//        int compliment_count = rs.getInt("compliment_count");
//
//        return new YelpTip(tip_id, user_id, business_id, date, compliment_count);
//    }
}
