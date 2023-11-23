package com.williamoverflow.cmpt354yelpgui.entities;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public abstract class Entity {

    public Entity(ResultSet rs) {
        if (rs == null) {
            throw new IllegalArgumentException("ResultSet cannot be null");
        }
        // Iterate over all declared fields of the class
        try {
            for (Field f : this.getClass().getDeclaredFields()) {
                // Get the value from the ResultSet
                Object value = rs.getObject(f.getName());
                f.set(this, value);
            }
        }catch (SQLException ex){
            System.err.println(ex);
            throw new RuntimeException(ex);
        }catch (IllegalAccessException ex){
            System.err.println(ex);
            throw new RuntimeException(ex);
        }
    }

    public Entity() {

    }

//    public static Entity map(ResultSet rs) throws SQLException {
//        return null;
//    }


}
