package com.williamoverflow.cmpt354yelpgui;

import com.williamoverflow.cmpt354yelpgui.entities.Entity;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import com.williamoverflow.cmpt354yelpgui.functions.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
//import org.controlsfx.control.tableview2.TableView2;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.sql.ResultSet;
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
    @FXML
    public TextArea resultStrTxt;


    public List<Entity> L_DisplayEntities = null;
    public List<DBVFunction> L_FuncTabFunctions = new ArrayList<>();


    public BusinessSearchSelector businessSearchSelector = new BusinessSearchSelector();
    public UserSearchSelector userSearchSelector = new UserSearchSelector();

    public WriteReviewInserter writeReviewInserter = new WriteReviewInserter();
    public MakeFriendshipInserter makeFriendshipInserter = new MakeFriendshipInserter();

    public DBVFunction currentFunction = null;

    public DBViewerController(){

    }
    @FXML
    public void initialize(){
//        funcTabPane.getTabs().add(businessSearchSelector.getDisplayTab());
//        funcTabPane.getTabs().add(userSearchSelector.getDisplayTab());
//        funcTabPane.getTabs().add(writeReviewInserter.getDisplayTab());
//        funcTabPane.getTabs().add((makeFriendshipInserter.getDisplayTab()));
        addToFuncTabPane(new BusinessSearchSelector());
        addToFuncTabPane(new UserSearchSelector());
        addToFuncTabPane(new WriteReviewInserter());
        addToFuncTabPane(new MakeFriendshipInserter());

        this.currentFunction = L_FuncTabFunctions.get(0);

        funcTabPane.getSelectionModel().selectedItemProperty().addListener(this::onFuncTabSelectionChanged);

    }

    public void addToFuncTabPane(DBVFunction dbvFunc){
        L_FuncTabFunctions.add(dbvFunc);
        funcTabPane.getTabs().add(dbvFunc.getDisplayTab());
    }


    public void onFuncTabSelectionChanged(ObservableValue<? extends Tab> observable, Tab oldTab, Tab newTab) {
        for(var ctab : L_FuncTabFunctions){
            if(newTab == ctab.getDisplayTab()){
                currentFunction = ctab;
            }
        }

//        if(newTab == businessSearchSelector.displayTab)
//            currentFunction = businessSearchSelector;
//        if(newTab == userSearchSelector.displayTab)
//            currentFunction = userSearchSelector;
//        if(newTab == writeReviewInserter.displayTab)
//            currentFunction = writeReviewInserter;
    }

    @FXML
    public void onApplyClick(){
        if(currentFunction == null)
            return;
        String sqlRes = currentFunction.getFinalFunctionString();
        System.out.println(sqlRes);
        finSqlStrTxt.setText(sqlRes);

        try {
            if(DBVSelector.class.isAssignableFrom(currentFunction.getClass())) {
                resultStrTxt.setText("Unknown Internal Exception");
                DBVSelector current = (DBVSelector) currentFunction;
                var rs = current.applySelector(YelpDBHelper.ydbh.connection, null);
                Class c = current.resultType;
                Constructor constructor = c.getDeclaredConstructor(ResultSet.class);
                L_DisplayEntities = new ArrayList<Entity>();
                while(rs != null && rs.next()){
                    Entity e = (Entity)constructor.newInstance(rs);
                    L_DisplayEntities.add(e);
                }
                setUpTableView(displayTableView, currentFunction.resultType, L_DisplayEntities);
                resultStrTxt.setText("Returned " + L_DisplayEntities.size() + " rows");
            }
            if(DBVInserter.class.isAssignableFrom(currentFunction.getClass())) {
                resultStrTxt.setText("Unknown Internal Exception");
                DBVInserter current = (DBVInserter) currentFunction;
                int arc = current.applyInserter(YelpDBHelper.ydbh.connection, YelpDBHelper.ydbh.sceneUser);
                System.err.println("Affected " + arc + " rows");
                resultStrTxt.setText("Affected " + arc + " rows");
            }


        }catch (SQLException ex){
            String exMsg = "";
            exMsg += "Excepted SQL Exception:\n";
            exMsg += ex.getMessage();
            resultStrTxt.setText(exMsg);
            // TODO: If user entered an invalid number,
            // such as average_stars larger than 5,
            // it will cause Arithmetic overflow error
            throw new RuntimeException(ex);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException ex) {
            throw new RuntimeException(ex);
        }
    }

    private <T extends Entity> void setUpTableView(TableView<T> tableView, Class resultType, List<T> data) {

        ObservableList<T> observableData = FXCollections.observableArrayList(data);
        tableView.getSelectionModel().setCellSelectionEnabled(true);
        tableView.setItems(observableData);
        tableView.getColumns().clear();
        for (Field field : resultType.getDeclaredFields()) {
            TableColumn<T, String> column = new TableColumn<>(field.getName());
            column.setCellValueFactory(new PropertyValueFactory<>(field.getName()));

//            column.setCellFactory(tc -> {
//                TableCell<String, ?> cell = new TableCell<>();
//                cell.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
//                    if (!cell.isEmpty()) {
//                        String item = (String)cell.getItem();
//                        // 这里的item是被点击单元格的值
//                        // 处理item...
//                    }
//                });
//                return cell;
//            });



            tableView.getColumns().add(column);


        }
    }

}
