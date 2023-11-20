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
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login-view.fxml"));


        Scene scene = new Scene(fxmlLoader.load());

        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        stage.setTitle("CMPT354 DB Management");
        stage.setScene(scene);
        stage.setResizable(false);  // I hate resize
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}