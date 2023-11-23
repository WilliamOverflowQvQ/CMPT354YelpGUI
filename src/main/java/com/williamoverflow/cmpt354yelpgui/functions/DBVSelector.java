package com.williamoverflow.cmpt354yelpgui.functions;

import javafx.geometry.HPos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBVSelector extends DBVFunction{
    public DBVSelector(String funcName, String tblName, Class resultType, FuncType type) {
        super(funcName, tblName, resultType, type);
    }

    @Override
    public String getFinalFunctionString(){
        StringBuilder ffstr = new StringBuilder();
        ffstr.append("SELECT * FROM ");
        ffstr.append(tblName).append(" ");
        ffstr.append("WHERE 1 = 1 ");
        for (var fc : components) {
            ffstr.append(fc.getFunctionString());
        }
        ffstr.append(";");
        return ffstr.toString();
    }

    protected void setFinalStatement(PreparedStatement statement) throws SQLException {
        int statementVarIndex = 1;
        for(int i = 0; i < this.components.size(); i++){
            var fc = components.get(i);
            if(fc.enabled.getValue()){
                statement.setObject(statementVarIndex, fc.userInput.getValue());
                statementVarIndex++;
            }
        }
    }

    public ResultSet applySelector(Connection connection, Object params) throws SQLException{
        String sql = getFinalFunctionString();
        PreparedStatement statement = connection.prepareStatement(sql);
        setFinalStatement(statement);

        return statement.executeQuery();
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

        Label reverse_titleLbl = new Label("NOT");
        Tooltip reverse_TT = new Tooltip("Reverse the result of this field");
        reverse_titleLbl.setTooltip(reverse_TT);
        Label proxy_titleLbl = new Label("PRXY");
        Tooltip proxy_TT = new Tooltip("Enable fuzzy (proxy) search format string");
        proxy_titleLbl.setTooltip(proxy_TT);

        upperGrid.add(enable_titleLbl, 0, 0);
        upperGrid.add(field_titleLbl, 1, 0);
        upperGrid.add(input_titleLbl, 2, 0);

        upperGrid.add(reverse_titleLbl, 3, 0);
        upperGrid.add(proxy_titleLbl, 4, 0);

        for (int i = 0; i < components.size(); i++) {
            Label fieldLbl = new Label(components.get(i).name);
            TextField inputTxt = new TextField();
            inputTxt.textProperty().bindBidirectional(components.get(i).userInput);

            CheckBox enabledCB = new CheckBox();
            enabledCB.setSelected(true);
            enabledCB.selectedProperty().bindBidirectional(components.get(i).enabled);

            CheckBox reverseCB = new CheckBox();
            reverseCB.setTooltip(reverse_TT);
            reverseCB.selectedProperty().bindBidirectional(components.get(i).reverse);

            CheckBox proxyCB = new CheckBox();
            proxyCB.setTooltip(proxy_TT);
            proxyCB.selectedProperty().bindBidirectional(components.get(i).proxyMatch);

            if(!components.get(i).allowProxy)
                proxyCB.setDisable(true);    // if disallow proxy, disable the button

            upperGrid.add(enabledCB, 0, i + 1);
            upperGrid.add(fieldLbl, 1, i + 1);
            upperGrid.add(inputTxt, 2, i + 1);
            upperGrid.add(reverseCB, 3, i + 1);
            upperGrid.add(proxyCB, 4, i + 1);
        }
        // make all of them center
        for (var c : upperGrid.getChildren()){
            GridPane.setHalignment(c, HPos.CENTER);
        }

        displayTab.setContent(upperGrid);
        return displayTab;
    }
}
