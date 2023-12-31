package com.williamoverflow.cmpt354yelpgui.functions;

import com.williamoverflow.cmpt354yelpgui.entities.YelpBusiness;

public class BusinessSearchSelector extends DBVSelector {
    public BusinessSearchSelector(){
        super("Business Search", "business", YelpBusiness.class, FuncType.FILTER);
        addComp(new DBVFuncComp("Min Stars", "stars", int.class, DBVFuncComp.CompType.LARGER, true));
        addComp(new DBVFuncComp("City", "city", String.class, DBVFuncComp.CompType.TEXT, true));
        addComp(new DBVFuncComp("Name", "name", String.class, DBVFuncComp.CompType.TEXT, true));
    }
}
