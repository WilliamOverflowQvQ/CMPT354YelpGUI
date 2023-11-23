package com.williamoverflow.cmpt354yelpgui.functions;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

import java.lang.reflect.Type;

// SelectorComponent in each filter
public class DBVFuncComp {
    public Property<Boolean> enabled = new SimpleBooleanProperty(true);     // when false, this filter comp will not work
    public Property<Boolean> reverse = new SimpleBooleanProperty(false);     // when true ... NOT xxx ...
    public Property<Boolean> proxyMatch = new SimpleBooleanProperty(false);  // when true: name LIKE "%illiam"

    public Property<String> userInput = new SimpleStringProperty("");     // user input txt will be here
    public Type valType = null;
    public boolean allowProxy = false;      // define whether this comp can have proxy

    public boolean hidden = false;
    public CompType type = CompType.TEXT;   // type of this comp

    public String name = "";                // shown name in label
    public String sqlName = "";             // used when constructing sql statement

    public DBVFuncComp(String name, String sqlName, Type valType){
        this.name = name;
        this.sqlName = sqlName;
        this.valType = valType;
        this.type = CompType.INSERTER;
    }

    public DBVFuncComp(String name, String sqlName, Type valType, CompType type, boolean allowProxy){
        this.name = name;
        this.sqlName = sqlName;
        this.type = type;
        this.valType = valType;
        this.allowProxy = allowProxy;
    }


    public String getSelectorStringQuestioned(){
        if(userInput.getValue().isEmpty()){
            this.enabled.setValue(false);
        }

        if(!this.enabled.getValue()){
            return "";
        }

        String res = "";
        switch(this.type){
            case TEXT -> {
                res += "UPPER(" + sqlName + ") ";
                res += this.proxyMatch.getValue() ? "LIKE UPPER(?) " : "= UPPER(?) ";
                if(this.proxyMatch.getValue()){
                    if(!userInput.getValue().contains("%")){
                        userInput.setValue("%" + userInput.getValue() + "%");
                    }
                }
            }
            case BETWEEN -> {
                res += "BETWEEN ? AND ? ";
            }
            case VALUE -> {
                res += sqlName + " ";
                res += this.proxyMatch.getValue() ? "= ? " : "= ? ";
            }
            case LARGER -> {
                res += sqlName + " ";
                res += this.proxyMatch.getValue() ? "> ? " : ">= ? ";
            }
            case SMALLER -> {
                res += sqlName + " ";
                res += this.proxyMatch.getValue() ? "< ? " : "<= ? ";
            }
        }
        res = (this.reverse.getValue() ? "NOT " : "") + res;
        res = "AND " + res;
        return res;
    }



    public String getInserterStringQuestioned(){
        return this.sqlName + " ";
    }

    public String getFunctionString(){
        switch(this.type){
            case TEXT, BETWEEN, VALUE, LARGER, SMALLER -> {
                return getSelectorStringQuestioned();
            }
            case INSERTER -> {
                return getInserterStringQuestioned();
            }
            case UPDATER -> {
                return "";
            }
        }
        return "";
    }

    public enum CompType {
        HIDDEN,
        TEXT,
        BETWEEN,
        VALUE,
        LARGER,
        SMALLER,
        INSERTER,
        UPDATER,
    }

//
//    public String getSelectorStringFilled(String input1, String input2){
//        if(!this.enabled.getValue()){
//            return " ";
//        }
//
//        String res = "";
//        switch (this.type) {
//            case TEXT -> {
//                if(input1.isEmpty()){
//                    res = " ";
//                }
//                else{
//                    res += this.proxyMatch.getValue() ? "LIKE " : "= ";
//                    res += this.proxyMatch.getValue() ?  "'" : "'%";
//                    res += input1;
//                    res += this.proxyMatch.getValue() ?  "'" : "%' ";
//                }
//            }
//            case BETWEEN -> {
//                if(input1.isEmpty() && input2.isEmpty()){
//                    res += " ";
//                }
//                else if(input1.isEmpty()){
//                    res += this.proxyMatch.getValue() ? "< " : "<= ";
//                    res += input2 + " ";
//                }
//                else if(input2.isEmpty()){
//                    res += this.proxyMatch.getValue() ? "> " : ">= ";
//                    res += input1 + " ";
//                }
//            }
//            case VALUE -> {
//                if(input1.isEmpty()){
//                    res += " ";
//                }else{
//                    res += "= ";
//                    res += input1 + " ";
//                }
//            }
//
//        }
//        if(this.reverse.getValue()){
//            res = "NOT " + res;
//        }
//
//
//        res = this.sqlName + " " + res;
//        res = "AND " + res;
//        return res;
//    }
}
