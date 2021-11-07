package com.example.a3.controllers;

import com.example.a3.models.DrawingModel;
import com.example.a3.models.InteractionModel;

/**
 * The controller to handle events from the view classes.
 */
public class DrawingController {
    private DrawingModel model;
    private InteractionModel iModel;
    private enum State {
        READY
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
}
