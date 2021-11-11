package com.example.a3.views;

import com.example.a3.controllers.DrawingController;
import com.example.a3.models.DrawingModel;
import com.example.a3.models.InteractionModel;
import com.example.a3.models.ModelSubscriber;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

/**
 * A view that contains and lays out the shape toolbar,
 * the drawing surface, and the colour toolbar.
 */
public class MainUI extends BorderPane implements ModelSubscriber {
    private ShapeToolbar shapeToolbar;
    private DrawingView drawingView;
    private ColourToolbar colourToolbar;
    private MiniDrawingView miniDrawingView;
    private DrawingModel model;
    private InteractionModel iModel;

    public MainUI() {
        // create the toolbar and canvas views to store
        shapeToolbar = new ShapeToolbar();
        drawingView = new DrawingView(2000, 2000);
        colourToolbar = new ColourToolbar();
        miniDrawingView = new MiniDrawingView(2000, 2000);

        StackPane drawingViews = new StackPane(drawingView, miniDrawingView);
        StackPane.setAlignment(miniDrawingView, Pos.TOP_LEFT);

        this.setLeft(shapeToolbar);
        this.setCenter(drawingViews);
        this.setRight(colourToolbar);

        // make the canvas view resize based on the main application
        this.widthProperty().addListener((observable, oldValue, newValue) -> {
            drawingView.setPrefWidth(newValue.doubleValue()-shapeToolbar.getWidth()-colourToolbar.getWidth());
        });
        this.heightProperty().addListener((observable, oldValue, newValue) -> {
            drawingView.setPrefHeight(newValue.doubleValue());
        });

        this.setPrefSize(624, 500);
    }

    /**
     * Associate a model to the view
     * @param newModel the drawing model information
     */
    public void setModel(DrawingModel newModel) {
        model = newModel;
        shapeToolbar.setModel(newModel);
        drawingView.setModel(newModel);
        colourToolbar.setModel(newModel);
        miniDrawingView.setModel(newModel);
        model.addSub(shapeToolbar);
        model.addSub(colourToolbar);
        model.addSub(drawingView);
        model.addSub(miniDrawingView);
    }

    /**
     * Associate an interaction model to the view
     * @param newIModel interaction model
     */
    public void setInteractionModel(InteractionModel newIModel) {
        iModel = newIModel;
        shapeToolbar.setInteractionModel(newIModel);
        drawingView.setInteractionModel(newIModel);
        colourToolbar.setInteractionModel(newIModel);
        miniDrawingView.setInteractionModel(newIModel);
        iModel.addSub(shapeToolbar);
        iModel.addSub(colourToolbar);
        iModel.addSub(drawingView);
        iModel.addSub(miniDrawingView);
    }

    /**
     * Set a controller for the view
     * @param newController the controller
     */
    public void setController(DrawingController newController) {
        shapeToolbar.setController(newController);
        drawingView.setController(newController);
        colourToolbar.setController(newController);
        miniDrawingView.setController(newController);
    }

    /**
     * Updates view based on model changes
     */
    public void modelChanged() {}

}
