package com.williamoverflow.cmpt354yelpgui;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;




public class LoginViewController {
    @FXML
    public Button connectBtn;
    @FXML
    public TextField urlField;
    @FXML
    public TextField usernameField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public TextField dbNameField;
    @FXML
    public TextField sceneuserField;
    @FXML
    public CheckBox encryptCheckBox;

    @FXML
    public Label logininfoArea;

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

            String loginSucceed = "";
            loginSucceed += "LOGIN SUCCEED\n";
            loginSucceed += "Login as: " + YelpDBHelper.ydbh.username + "\n";
            loginSucceed += "On DB: " + YelpDBHelper.ydbh.dbname + "\n";
            loginSucceed += "Proceed to DB-viewer ...";
            logininfoArea.setText(loginSucceed);
        }catch (SQLException ex) {
            System.out.println(ex);
            String loginFailed = "";


            int code = ex.getErrorCode();
            switch (code) {
                case (0):
                    loginFailed += "Expected error code 0:\n";
                    loginFailed += "This is a jdbc driver error.\n";
                    loginFailed += "Plz make sure you have ssl connection permissions, ";
                    loginFailed += "otherwise do not check the encrypted connection. ";
                    loginFailed += "You may also exam your login info is correct.\n\n";
                    break;
                case (18456):
                    loginFailed += "Expected error code 18456:\n";
                    loginFailed += "This is a login account error.\n";
                    loginFailed += "Your account or password is wrong\n\n";
                    loginFailed += ex.toString();
                default:
                    loginFailed += "Encountered an unexpected error:\n";
                    loginFailed += ex.toString();
                    break;
            }
            logininfoArea.setText(loginFailed);
        }catch(ClassNotFoundException ex){
            System.err.println("WARNING: JDBC for sql server DNE!");
            System.err.println(ex);
            String internalError = "";
            internalError += "WARNING: JDBC for sql server DNE, this may be an error during compiling\n";
            internalError += " or you are using this application incorrectly...\n\n";
            internalError += ex.toString();
        }
    }
}