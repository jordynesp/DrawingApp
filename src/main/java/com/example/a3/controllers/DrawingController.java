package com.example.a3.controllers;

import com.example.a3.models.DrawingModel;
import com.example.a3.models.InteractionModel;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 * The controller to handle events from the view classes.
 */
public class DrawingController {
    private DrawingModel model;
    private InteractionModel iModel;

    private enum State {
        READY, PREPARE_CREATE, RESIZING
        // ready, moving, prepare_create, resizing
    }

    private State currentState;

    /**
     * Constructor for DrawingController
     */
    public DrawingController() {
        currentState = State.READY;
    }

    /**
     * Associate a model to the controller
     * @param newModel the drawing model information
     */
    public void setModel(DrawingModel newModel) {
        model = newModel;
    }

    /**
     * Associate an interaction model to the controller
     * @param newIModel interaction model
     */
    public void setInteractionModel(InteractionModel newIModel) {
        iModel = newIModel;
    }

    /**
     * Set the selected colour
     */
    public void handleSelectedColour(Color newColour) {
        iModel.setSelectedColour(newColour);
    }

    /**
     * Set the selected shape in the toolbar
     * @param shape selected shape
     */
    public void handleSelectedToolShape(Shape shape, String name) {
        iModel.setSelectedToolShape(shape, name);
    }

}
