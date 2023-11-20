package com.williamoverflow.cmpt354yelpgui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.SQLException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
        YelpDBHelper dbHelper = new YelpDBHelper();

        try {
            dbHelper.connect();
            YelpDBHelper.UserYelp user = dbHelper.getUserYelpById("__hr-GtD9qh8_sYSGTRqXw");
            welcomeText.setText("got user name: " + user.name);
        }catch (SQLException ex){
            System.out.println(ex);
        }
    }
}