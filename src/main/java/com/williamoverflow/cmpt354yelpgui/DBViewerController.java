package com.williamoverflow.cmpt354yelpgui;

import com.williamoverflow.cmpt354yelpgui.entities.Entity;
import com.williamoverflow.cmpt354yelpgui.entities.YelpBusiness;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import com.williamoverflow.cmpt354yelpgui.functions.*;
import javafx.scene.control.cell.PropertyValueFactory;
//import org.controlsfx.control.tableview2.TableView2;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DBViewerController {
    @FXML
    public TabPane funcTabPane;
    @FXML
    public TableView<Entity> displayTableView;
    @FXML
    public TextArea finSqlStrTxt;
    @FXML
    public Button applyBtn;
    @FXML
    public Button resetBtn;
    @FXML
    public TextArea statTxt;


    public List<Entity> L_DisplayEntities = null;

    public BusinessFilter businessFilter = new BusinessFilter();
    public UserFilter userFilter = new UserFilter();

    public DBViewerController(){

    }
    @FXML
    public void initialize(){
        funcTabPane.getTabs().add(businessFilter.getDisplayFunctionTab());
        funcTabPane.getTabs().add(userFilter.getDisplayFunctionTab());
    }

    @FXML
    public void onApplyClick(){
        String sqlRes = businessFilter.getFinalFunctionString();
        System.out.println(sqlRes);
        finSqlStrTxt.setText(sqlRes);
        L_DisplayEntities = new ArrayList<>();
        try {
            var rs = businessFilter.applyFunction(YelpDBHelper.ydbh.connection);

            while(rs.next()){
                var bus = YelpBusiness.map(rs);
//                System.out.println(bus.name);
                L_DisplayEntities.add(bus);
            }
            setUpTableView(displayTableView, businessFilter.funcResult, L_DisplayEntities);

        }catch (SQLException ex){
            System.err.println(ex);
        }
    }

    private <T extends Entity> void setUpTableView(TableView<T> tableView, Type resultType, List<T> data) {
        Class<Entity> entityType = (Class<Entity>) resultType;
        ObservableList<T> observableData = FXCollections.observableArrayList(data);
        tableView.setItems(observableData);
        tableView.getColumns().clear();
        for (Field field : entityType.getDeclaredFields()) {
            TableColumn<T, String> column = new TableColumn<>(field.getName());
            column.setCellValueFactory(new PropertyValueFactory<>(field.getName()));
            tableView.getColumns().add(column);
        }
    }
}
