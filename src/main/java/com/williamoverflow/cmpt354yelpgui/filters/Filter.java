package com.williamoverflow.cmpt354yelpgui.filters;

import java.util.ArrayList;
import java.util.List;


import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.layout.GridPane;

public class Filter {
    public List<FilterComponent> components = new ArrayList<>();
    public GridPane displayGrid = null;

    public Filter(){
        displayGrid = new GridPane();
    }

    public String getFinalFilterString(){
        StringBuilder ffstr = new StringBuilder();
        for (var fc : components) {
            ffstr.append(fc.getFilterStringQuestioned());
        }
        return ffstr.toString();
    }





}
