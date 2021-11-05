package com.example.a3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The main application class.
 */
public class DrawingApp extends Application {
    @Override
    public void start(Stage stage) {

        MainUI mainUI = new MainUI();

        Scene scene = new Scene(mainUI, 650, 500);
        stage.setTitle("Drawing Application");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}