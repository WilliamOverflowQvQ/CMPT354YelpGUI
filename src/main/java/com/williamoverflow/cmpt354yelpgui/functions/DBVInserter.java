package com.williamoverflow.cmpt354yelpgui.functions;

import javafx.geometry.HPos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBVInserter extends DBVFunction {
    public DBVInserter(String funcName, String tblName, Class resultType, FuncType type) {
        super(funcName, tblName, resultType, type);
    }

    protected void setFinalStatement(PreparedStatement statement) throws SQLException {
        int statementVarIndex = 1;
        for(int i = 0; i < this.components.size(); i++){
            var fc = components.get(i);
            if(fc.enabled.getValue()){
                if(fc.type == DBVFuncComp.CompType.TEXT) {
                    statement.setString(statementVarIndex, fc.userInput.getValue());
                }else{
                    statement.setObject(statementVarIndex, fc.userInput.getValue());
                }
                statementVarIndex++;
            }
        }
    }

    public String getFinalFunctionString(){
        StringBuilder ffstr = new StringBuilder();
        ffstr.append("INSERT INTO ");
        ffstr.append(tblName).append(" ");
        // INSERT INTO review (review_id, user_id, ...
        ffstr.append("(");
        for (int i = 0; i < components.size(); i++){
            if(i != 0)
                ffstr.append(", ");
            ffstr.append(components.get(i).getFunctionString());
        }
        ffstr.append(") VALUES (");
        // ) ... VALUES (?, ?...
        for (int i = 0; i < components.size(); i++){
            if(i != 0)
                ffstr.append(", ");
            ffstr.append("?");
        }
        ffstr.append(");");
        return ffstr.toString();
    }


    public int applyInserter(Connection connection, Object params) throws SQLException{
        String sql = getFinalFunctionString();
        PreparedStatement statement = connection.prepareStatement(sql);
        setFinalStatement(statement);

        return statement.executeUpdate();
    }

    protected Tab getDisplayFunctionTab(){
        Tab displayTab = new Tab(this.funcName);

        // Upper part: filter components:
        GridPane upperGrid = new GridPane();
        upperGrid.setHgap(10);
        upperGrid.setVgap(20);
//        gridPane.setGridLinesVisible(true);   // very ugly

        Label enable_titleLbl = new Label("EBL");
        enable_titleLbl.setTooltip(new Tooltip("Enable field"));
        Label field_titleLbl = new Label("Field Name");
        field_titleLbl.setTooltip(new Tooltip("Field name"));
        Label input_titleLbl = new Label("User Input");
        input_titleLbl.setTooltip(new Tooltip("User input, leave blank to disable"));


        upperGrid.add(enable_titleLbl, 0, 0);
        upperGrid.add(field_titleLbl, 1, 0);
        upperGrid.add(input_titleLbl, 2, 0);

        for (int i = 0; i < components.size(); i++) {
            Label fieldLbl = new Label(components.get(i).name);
            TextField inputTxt = new TextField();
            inputTxt.textProperty().bindBidirectional(components.get(i).userInput);

            CheckBox enabledCB = new CheckBox();
            enabledCB.setSelected(true);
            enabledCB.selectedProperty().bindBidirectional(components.get(i).enabled);

            upperGrid.add(enabledCB, 0, i + 1);
            upperGrid.add(fieldLbl, 1, i + 1);
            upperGrid.add(inputTxt, 2, i + 1);
        }
        // make all of them center
        for (var c : upperGrid.getChildren()){
            GridPane.setHalignment(c, HPos.CENTER);
        }

        displayTab.setContent(upperGrid);
        return displayTab;
    }
}
