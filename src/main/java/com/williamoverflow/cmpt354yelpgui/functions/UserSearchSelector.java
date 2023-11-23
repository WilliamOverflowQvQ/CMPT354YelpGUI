package com.williamoverflow.cmpt354yelpgui.functions;

import com.williamoverflow.cmpt354yelpgui.entities.YelpUser;

import java.math.BigDecimal;

public class UserSearchSelector extends DBVSelector {

    public UserSearchSelector() {
        super("User Search", "user_yelp", YelpUser.class, FuncType.FILTER);
        addComp(new DBVFuncComp("User name", "name", String.class, DBVFuncComp.CompType.TEXT, true));
        addComp(new DBVFuncComp("Max Review #", "review_count", int.class, DBVFuncComp.CompType.SMALLER, true));
        addComp(new DBVFuncComp("Min Review #", "review_count", int.class, DBVFuncComp.CompType.LARGER, true));
        addComp(new DBVFuncComp("Max Avg Stars", "average_stars", BigDecimal.class, DBVFuncComp.CompType.SMALLER, true));
        addComp(new DBVFuncComp("Min Avg Stars", "average_stars", BigDecimal.class, DBVFuncComp.CompType.LARGER, true));
    }
}
