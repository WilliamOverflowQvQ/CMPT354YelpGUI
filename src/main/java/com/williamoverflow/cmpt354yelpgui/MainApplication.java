package com.williamoverflow.cmpt354yelpgui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;



public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        FXMLLoader fxmlLoader = null;
        fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login-view.fxml"));
        Scene scene1 = new Scene(fxmlLoader.load());
        fxmlLoader = new FXMLLoader(MainApplication.class.getResource("dbviewer-view.fxml"));
        Scene scene2 = new Scene(fxmlLoader.load());
        scene2.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        scene1.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        stage.setTitle("CMPT354 DB Management");
        stage.setScene(scene1);
        stage.setResizable(false);  // I hate resize
        stage.show();


//        stage.setScene(scene2);
//        stage.setResizable(false);
//        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}