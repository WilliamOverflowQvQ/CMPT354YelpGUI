package com.williamoverflow.cmpt354yelpgui;

import com.williamoverflow.cmpt354yelpgui.entities.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;




public class LoginViewController {
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
    public Label infoArea;
    @FXML
    public Button connectBtn;
    @FXML
    public Button loginBtn;

    @FXML
    private void initialize() {


        // Set default values for login controls
        urlField.setText("cypress.csil.sfu.ca");
        usernameField.setText("s_zza198");
        passwordField.setText("d4rHQtH4tM76En64");
        dbNameField.setText("zza198354");
        sceneuserField.setText("J");
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

            String connectSucceed = "";
            connectSucceed += "CONNECT SUCCESSFUL!\n";
            connectSucceed += "Connected as: " + YelpDBHelper.ydbh.username + "\n";
            connectSucceed += "On DB: " + YelpDBHelper.ydbh.dbname + "\n";
            connectSucceed += "Login to use DB-viewer ...";
            infoArea.setText(connectSucceed);
        }catch (SQLException ex) {
            System.out.println(ex);
            String connectFailed = "";


            int code = ex.getErrorCode();
            switch (code) {
                case (0):
                    connectFailed += "Expected error code 0:\n";
                    connectFailed += "This is a jdbc driver error.\n";
                    connectFailed += "Plz make sure you have ssl connection permissions, ";
                    connectFailed += "otherwise do not check the encrypted connection. ";
                    connectFailed += "You may also exam your login info is correct.\n\n";
                    break;
                case (18456):
                    connectFailed += "Expected error code 18456:\n";
                    connectFailed += "This is a login account error.\n";
                    connectFailed += "Your account or password is wrong\n\n";
                    connectFailed += ex.toString();
                default:
                    connectFailed += "Encountered an unexpected error:\n";
                    connectFailed += ex.toString();
                    break;
            }
            infoArea.setText(connectFailed);
        }catch(ClassNotFoundException ex){
            System.err.println("WARNING: JDBC for sql server DNE!");
            System.err.println(ex);
            String internalError = "";
            internalError += "WARNING: JDBC for sql server DNE, this may be an error during compiling\n";
            internalError += " or you are using this application incorrectly...\n\n";
            internalError += ex.toString();
        }
    }

    @FXML
    protected void onLoginClicked(){
        if(YelpDBHelper.ydbh.isClosed()){
            infoArea.setText("ERROR: You need to connect to a DB first!");
            return;
        }
        if(sceneuserField.getText().isEmpty()){
            infoArea.setText("WARNING: Scene user field is empty!");
        }
        try {
            YelpDBHelper.ydbh.sceneUser = null;
            YelpUser sceneUser = YelpDBHelper.ydbh.getUserYelpByName(sceneuserField.getText());
            YelpDBHelper.ydbh.sceneUser = sceneUser;

            if(sceneUser != null) {
                String loginSuccessful = "";
                loginSuccessful += "LOGIN SUCCESSFUL!\n";
                loginSuccessful += "You are now login in as: " + YelpDBHelper.ydbh.sceneUser.name + "\n\n";

                loginSuccessful += "CONNECT SUCCESSFUL!\n";
                loginSuccessful += "Connected as: " + YelpDBHelper.ydbh.username + "\n";
                loginSuccessful += "On DB: " + YelpDBHelper.ydbh.dbname + "\n";
                loginSuccessful += "Proceed to DB-viewer ...\n\n";
                infoArea.setText(loginSuccessful);


                switchToDBViewer();
            }else{
                String loginFailed = "";
                loginFailed += "ERROR: The scene user you entered do not exist!\n\n";
                infoArea.setText(loginFailed);
            }
        }catch (SQLException ex){
            String loginFailed = "";
            loginFailed += "ERROR: encountered an sql error!\n";
            loginFailed += ex.toString();
            infoArea.setText(loginFailed);
        }
    }

    private void switchToDBViewer(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("dbviewer-view.fxml"));
            Stage stage = (Stage) loginBtn.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);

            stage.show();
        }catch (IOException ex){
            System.err.println(ex);
        }
    }

}