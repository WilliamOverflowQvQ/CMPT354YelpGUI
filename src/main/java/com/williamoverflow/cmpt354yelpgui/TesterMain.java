package com.williamoverflow.cmpt354yelpgui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TesterMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = null;
        fxmlLoader = new FXMLLoader(MainApplication.class.getResource("dbviewer-view.fxml"));
        Scene scene1 = new Scene(fxmlLoader.load());


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