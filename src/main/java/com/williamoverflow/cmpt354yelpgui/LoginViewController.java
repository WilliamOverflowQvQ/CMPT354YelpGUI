package com.williamoverflow.cmpt354yelpgui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.SQLException;




public class LoginViewController {
    @FXML
    public Button connectBtn;
    @FXML
    private TextField urlField;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField dbNameField;
    @FXML
    private CheckBox encryptCheckBox;

    @FXML
    private void initialize() {
        // Set default values for login controls
        urlField.setText("cypress.csil.sfu.ca");
        usernameField.setText("s_zza198");
        passwordField.setText("d4rHQtH4tM76En64");
        dbNameField.setText("zza198354");
        encryptCheckBox.setSelected(false);
    }

    @FXML
    protected void onConnectClicked(){
        try {
            YelpDBHelper.ydbh = new YelpDBHelper(
                    urlField.getText(),
                    usernameField.getText(),
                    passwordField.getText(),
                    dbNameField.getText(),
                    encryptCheckBox.isSelected()
            );
            YelpDBHelper.ydbh.connect();
            YelpDBHelper.UserYelp user = YelpDBHelper.ydbh.getUserYelpById("__hr-GtD9qh8_sYSGTRqXw");
            System.out.println(user.toString());
        }catch (SQLException ex){
            System.out.println(ex);
        }
    }
}