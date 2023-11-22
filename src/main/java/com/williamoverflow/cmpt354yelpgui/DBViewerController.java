package com.williamoverflow.cmpt354yelpgui;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;

import com.williamoverflow.cmpt354yelpgui.filters.*;


public class DBViewerController {

    public TabPane filterTabPane;
    public TextArea resultsDisplay;
    public TextArea errorMessageArea;



    public BusinessFilter businessFilter = new BusinessFilter();

    public DBViewerController(){

    }
}
