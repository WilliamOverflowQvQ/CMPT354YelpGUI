package com.williamoverflow.cmpt354yelpgui;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import com.williamoverflow.cmpt354yelpgui.filters.*;


public class DBViewerController {
    @FXML
    public TabPane filterTabPane;
    @FXML
    public TextArea resultsDisplay;
    @FXML
    public TextArea errorMessageArea;



    public BusinessFilter businessFilter = new BusinessFilter();

    public DBViewerController(){

    }
    @FXML
    public void initialize(){
        filterTabPane.getTabs().add(businessFilter.getDisplayFilterTab());
    }
}
