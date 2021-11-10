package com.example.a3.views;

import com.example.a3.controllers.DrawingController;
import com.example.a3.models.DrawingModel;
import com.example.a3.models.InteractionModel;
import com.example.a3.models.ModelSubscriber;

/**
 * A view that contains a miniature DrawingView.
 */
public class MiniDrawingView extends DrawingView implements ModelSubscriber {

    /**
     * Constructor for MiniDrawingView
     */
    public MiniDrawingView() {
        super();
        this.myCanvas.setWidth(100);
        this.myCanvas.setHeight(100);
        this.setStyle("-fx-background-color: darkgrey");
        this.setPrefSize(100, 100);
        this.setMaxSize(100, 100);
    }

    /**
     * Draws the shapes onto the canvas in immediate-mode graphics
     */
    public void draw() {
        super.draw();
    }

    /**
     * Associate a model to the view
     * @param newModel the drawing model information
     */
    public void setModel(DrawingModel newModel) {
        super.setModel(newModel);
    }

    /**
     * Associate an interaction model to the view
     * @param newIModel interaction model
     */
    public void setInteractionModel(InteractionModel newIModel) {
        super.setInteractionModel(newIModel);
    }

    /**
     * Set a controller for the view
     * @param newController the controller
     */
    public void setController(DrawingController newController) {
        super.setController(newController);
    }

    /**
     * Update view based on model changes
     */
    public void modelChanged() {
        draw();
    }

}
