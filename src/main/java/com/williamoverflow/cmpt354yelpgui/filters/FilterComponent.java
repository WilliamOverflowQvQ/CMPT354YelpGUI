package com.williamoverflow.cmpt354yelpgui.filters;

import javafx.beans.InvalidationListener;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

// FilterComponent in each filter
public class FilterComponent {


    public Property<Boolean> enabled = new SimpleBooleanProperty();     // when false, this filter comp will not work
    public Property<Boolean> reverse = new SimpleBooleanProperty();     // when true ... NOT xxx ...
    public Property<Boolean> proxyMatch = new SimpleBooleanProperty();  // when true: name LIKE "%illiam"

    public Property<String> userInput = new SimpleStringProperty();     // user input txt will be here
    public boolean allowProxy = false;      // define whether this comp can have proxy

    public CompType type = CompType.TEXT;   // type of this comp

    public String name = "";                // shown name in label
    public String sqlName = "";             // used when constructing sql statement

    public FilterComponent(String name, String sqlName, CompType type, boolean allowProxy){
        this.name = name;
        this.sqlName = sqlName;
        this.type = type;
        this.allowProxy = allowProxy;
    }


    public String getFilterStringQuestioned(){
        if(!this.enabled.getValue()){
            return "";
        }

        String res = "";
        switch(this.type){

            case TEXT -> {
                res += this.proxyMatch.getValue() ? "LIKE ? " : "= ? ";
            }
            case BETWEEN -> {
                res += "BETWEEN ? AND ? ";
            }
            case VALUE -> {
                res += this.proxyMatch.getValue() ? "= ? " : "= ? ";
            }
            case LARGER -> {
                res += this.proxyMatch.getValue() ? "> ? " : ">= ? ";
            }
            case SMALLER -> {
                res += this.proxyMatch.getValue() ? "< ? " : "<= ? ";
            }
        }
        res = sqlName + " " + res;
        res = (this.reverse.getValue() ? "NOT " : "") + res;
        res = "AND " + res;
        return res;
    }


    public String getFilterStringFilled(String input1, String input2){
        if(!this.enabled.getValue()){
            return " ";
        }

        String res = "";
        switch (this.type) {
            case TEXT -> {
                if(input1.isEmpty()){
                    res = " ";
                }
                else{
                    res += this.proxyMatch.getValue() ? "LIKE " : "= ";
                    res += this.proxyMatch.getValue() ?  "'" : "'%";
                    res += input1;
                    res += this.proxyMatch.getValue() ?  "'" : "%' ";
                }
            }
            case BETWEEN -> {
                if(input1.isEmpty() && input2.isEmpty()){
                    res += " ";
                }
                else if(input1.isEmpty()){
                    res += this.proxyMatch.getValue() ? "< " : "<= ";
                    res += input2 + " ";
                }
                else if(input2.isEmpty()){
                    res += this.proxyMatch.getValue() ? "> " : ">= ";
                    res += input1 + " ";
                }
            }
            case VALUE -> {
                if(input1.isEmpty()){
                    res += " ";
                }else{
                    res += "= ";
                    res += input1 + " ";
                }
            }

        }
        if(this.reverse.getValue()){
            res = "NOT " + res;
        }


        res = this.sqlName + " " + res;
        res = "AND " + res;
        return res;
    }


    public enum CompType {
        TEXT,
        BETWEEN,
        VALUE,
        LARGER,
        SMALLER,

    }
}
