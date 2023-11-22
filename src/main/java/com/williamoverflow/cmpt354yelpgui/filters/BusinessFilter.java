package com.williamoverflow.cmpt354yelpgui.filters;

public class BusinessFilter extends Filter{




    public BusinessFilter(){
        this.components.add(new FilterComponent("Min Stars", "stars", FilterComponent.CompType.LARGER, true));
        this.components.add(new FilterComponent("City", "city", FilterComponent.CompType.TEXT, true));
        this.components.add(new FilterComponent("Name", "name", FilterComponent.CompType.TEXT, true));

    }
}
