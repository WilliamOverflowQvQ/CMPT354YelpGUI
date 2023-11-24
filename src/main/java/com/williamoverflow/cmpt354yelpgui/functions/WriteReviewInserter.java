package com.williamoverflow.cmpt354yelpgui.functions;

import com.williamoverflow.cmpt354yelpgui.entities.YelpReview;
import com.williamoverflow.cmpt354yelpgui.entities.YelpUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class WriteReviewInserter extends DBVInserter{

    public DBVFuncComp review_idComp = new DBVFuncComp("review_id", "review_id", String.class, DBVFuncComp.CompType.INSERTER, false);

    public DBVFuncComp user_idComp = new DBVFuncComp("user_id", "user_id", String.class, DBVFuncComp.CompType.INSERTER, false);
//    public DBVFuncComp dateComp = new DBVFuncComp("date", "date", Date.class, DBVFuncComp.CompType.INSERTER, false);
    public WriteReviewInserter() {
        super("Review Insert", "review");
        addComp(new DBVFuncComp("Business ID", "business_id", String.class));
        addComp(new DBVFuncComp("Review Stars", "stars", int.class));
        addComp(review_idComp);
        addComp(user_idComp);
//        addComp(dateComp);
    }


    @Override
    public int applyInserter(Connection connection, Object params) throws SQLException {
        review_idComp.userInput.setValue("12345678");   //TODO: Remove debug settings
        if(params != null){
            YelpUser sceneUser = (YelpUser)params;
            this.user_idComp.userInput.setValue(sceneUser.getUser_id());
        }
        return super.applyInserter(connection, params);
    }
}
