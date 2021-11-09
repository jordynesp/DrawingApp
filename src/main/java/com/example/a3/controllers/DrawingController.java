package com.example.a3.controllers;

import com.example.a3.models.DrawingModel;
import com.example.a3.models.InteractionModel;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 * The controller to handle events from the view classes.
 */
public class DrawingController {
    private DrawingModel model;
    private InteractionModel iModel;
    double prevX, prevY;

    private enum State {
        READY, PREPARE_CREATE, RESIZING
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

    /**
     * Designate what the controller should do
     * based on state when a mouse is pressed
     * @param normX normalized x coordinate
     * @param normY normalized y coordinate
     * @param event mouse event
     */
    public void handlePressed(double normX, double normY, MouseEvent event) {
        switch (currentState) {
            case READY -> {
                // get ready to create a shape
                prevX = normX;
                prevY = normY;
                currentState = State.PREPARE_CREATE;
            }
        }
    }

    /**
     * Designate what the controller should do
     * based on state when a mouse is released
     * @param normX normalized x coordinate
     * @param normY normalized y coordinate
     * @param event mouse event
     */
    public void handleReleased(double normX, double normY, MouseEvent event) {
//        switch (currentState) {
//            case PREPARE_CREATE -> {
//                // cancel shape drawing
//                currentState = State.READY;
//            }
//            case RESIZING -> {
//                // finish drawing shape
//                currentState = State.READY;
//            }
//        }
        currentState = State.READY;
    }

    /**
     * Designate what the controller should do
     * based on state when a mouse is dragged
     * @param normX normalized x coordinate
     * @param normY normalized y coordinate
     * @param event mouse event
     */
    public void handleDragged(double normX, double normY, MouseEvent event) {
        switch (currentState) {
            case PREPARE_CREATE -> {
                // adjust the size of the shape being drawn
                iModel.setSelectedShape(model.createShape(prevX, prevY, iModel.getSelectedShapeName(),
                        iModel.getSelectedColour()));
                currentState = State.RESIZING;
            }
            case RESIZING -> {
                // resize the currently selected shape
                model.resizeShape(iModel.getSelectedShape(), normX, normY);
            }
        }
    }

}
