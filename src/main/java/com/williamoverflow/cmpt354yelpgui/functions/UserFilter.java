package com.williamoverflow.cmpt354yelpgui.functions;

import com.williamoverflow.cmpt354yelpgui.entities.YelpUser;

public class UserFilter extends DBVFunction {

    public UserFilter() {
        super("User Filter", "yelp_user", YelpUser.class, FuncType.FILTER);
        addComp(new DBVFuncComp("User name", "name", String.class, DBVFuncComp.CompType.TEXT, true));
        addComp(new DBVFuncComp("Max Review #", "review_count", int.class, DBVFuncComp.CompType.SMALLER, true));
        addComp(new DBVFuncComp("Min Review #", "review_count", int.class, DBVFuncComp.CompType.LARGER, true));
        addComp(new DBVFuncComp("Max Avg Stars", "stars", int.class, DBVFuncComp.CompType.SMALLER, true));
        addComp(new DBVFuncComp("Min Avg Stars", "stars", int.class, DBVFuncComp.CompType.LARGER, true));
    }
}
