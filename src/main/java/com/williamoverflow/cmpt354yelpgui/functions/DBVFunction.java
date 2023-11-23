package com.williamoverflow.cmpt354yelpgui.functions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import javafx.geometry.HPos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

// This kind of class connect between abstract filter and UI filter (act like a tab)
public class DBVFunction {
    public String funcName = "";
    public String tblName = "";
    public Class resultType = null;
    public List<DBVFuncComp> components = new ArrayList<>();
    public FuncType type;
    public Tab displayTab = null;


    public DBVFunction(String funcName, String tblName, Class resultType, FuncType type){
        this.funcName = funcName;
        this.tblName = tblName;
        this.resultType = resultType;
        this.type = type;
    }

    public void addComp(DBVFuncComp fc){
        components.add(fc);
    }

    public Tab getDisplayTab(){
        if(displayTab == null)
            this.displayTab = getDisplayFunctionTab();
        return displayTab;
    }

    public String getFinalFunctionString() {
        return "ERROR";
    }


    protected Tab getDisplayFunctionTab() {
        return null;
    }


    public enum FuncType{
        FILTER,
        INSERTER,
        EDITOR,
    }


}
