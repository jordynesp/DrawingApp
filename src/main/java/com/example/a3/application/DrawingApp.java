package com.example.a3.application;

import com.example.a3.controllers.DrawingController;
import com.example.a3.models.DrawingModel;
import com.example.a3.models.InteractionModel;
import com.example.a3.views.MainUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The main application class.
 */
public class DrawingApp extends Application {
    @Override
    public void start(Stage stage) {
        // Model(s)
        DrawingModel model = new DrawingModel();
        InteractionModel iModel = new InteractionModel();
        // View(s)
        MainUI mainUI = new MainUI();
        // Controller(s)
        DrawingController controller = new DrawingController();
        // Set up MVC
        mainUI.setModel(model);
        mainUI.setInteractionModel(iModel);
        mainUI.setController(controller);
        controller.setModel(model);
        controller.setInteractionModel(iModel);
        model.addSub(mainUI);
        iModel.addSub(mainUI);

        // set the stage
        Scene scene = new Scene(mainUI, 624, 500);
        stage.setTitle("Drawing Application");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}