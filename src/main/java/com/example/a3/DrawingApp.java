package com.example.a3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * The main application class.
 */
public class DrawingApp extends Application {
    @Override
    public void start(Stage stage) {

        StackPane temp = new StackPane();

        Scene scene = new Scene(temp, 320, 240);
        stage.setTitle("Drawing Application!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}