package com.williamoverflow.cmpt354yelpgui.functions;

import com.williamoverflow.cmpt354yelpgui.entities.YelpUser;

import java.sql.Connection;
import java.sql.SQLException;

public class MakeFriendshipInserter extends DBVInserter{

    DBVFuncComp user_idComp = new DBVFuncComp("Scene user", "user_id", String.class);
    DBVFuncComp friendComp = new DBVFuncComp("Make friend", "friend", String.class);

    public MakeFriendshipInserter() {
        super("Make Friend", "friendship");
        user_idComp.allowEdit = false;
        addComp(user_idComp);
        addComp(friendComp);
    }

    @Override
    public int applyInserter(Connection connection, Object params) throws SQLException {
        // fill in param with YelpUser
        YelpUser sceneUser = (YelpUser) params;
        this.user_idComp.userInput.setValue(sceneUser.user_id);
        return super.applyInserter(connection, params);
    }
}
