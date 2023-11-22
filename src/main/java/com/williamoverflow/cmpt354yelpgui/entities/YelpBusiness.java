package com.williamoverflow.cmpt354yelpgui.entities;
import java.sql.ResultSet;
import java.sql.SQLException;

public class YelpBusiness extends Entity {
    // Fields corresponding to the table columns
    public String business_id;
    public String name;
    public String address;
    public String city;

    public String postal_code;
    public int stars;
    public int review_count;

    public String getBusiness_id() {
        return business_id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public int getStars() {
        return stars;
    }

    public int getReview_count() {
        return review_count;
    }

    // Constructor
    public YelpBusiness(String business_id, String name, String address, String city, String postal_code, int stars, int review_count) {
        this.business_id = business_id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.postal_code = postal_code;
        this.stars = stars;
        this.review_count = review_count;
    }

    // Getters and setters for each field
    // ...

    // Optionally, you can also override toString, equals, and hashCode methods
    // ...

    public static YelpBusiness map(ResultSet rs) throws SQLException {
        if (rs == null) {
            return null;
        }

        String business_id = rs.getString("business_id");
        String name = rs.getString("name");
        String address = rs.getString("address");
        String city = rs.getString("city");
        String postal_code = rs.getString("postal_code");
        int stars = rs.getInt("stars"); // Assuming stars is a decimal value in your database
        int review_count = rs.getInt("review_count");

        return new YelpBusiness(business_id, name, address, city, postal_code, stars, review_count);
    }
}
