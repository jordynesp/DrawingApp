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
        READY, PREPARE_CREATE, RESIZING, SELECTED, MOVING, PREPARE_RESIZE
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
                // check if on a shape
                boolean hit = model.checkHit(normX, normY);
                if (hit) {
                    iModel.setSelectedShape(model.whichShape(normX, normY));
                    currentState = State.SELECTED;
                } else {
                    // get ready to create shape
                    iModel.setSelectedShape(null);
                    prevX = normX;
                    prevY = normY;
                    currentState = State.PREPARE_CREATE;
                }
            }
        }
        System.out.println("Current state is: " + currentState);
    }

    /**
     * Designate what the controller should do
     * based on state when a mouse is released
     * @param normX normalized x coordinate
     * @param normY normalized y coordinate
     * @param event mouse event
     */
    public void handleReleased(double normX, double normY, MouseEvent event) {
        switch (currentState) {
            case PREPARE_CREATE -> {
                // cancel shape drawing
                iModel.setSelectedShape(null);
                currentState = State.READY;
            }
            case RESIZING, MOVING -> {
                // finish manipulating shape;
                currentState = State.SELECTED;
            }
            case SELECTED -> {
                // check if on a shape
                boolean hit = model.checkHit(normX, normY);
                if (hit) {
                    iModel.setSelectedShape(model.whichShape(normX, normY));
                }
                else {
                    iModel.setSelectedShape(null);
                    currentState = State.READY;
                    System.out.println("triggering this");
                }
            }
        }
        System.out.println("Current state is: " + currentState);

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
                model.notifySubscribers();
                currentState = State.RESIZING;
            }
            case RESIZING -> {
                // resize the currently selected shape
                model.resizeShape(iModel.getSelectedShape(), normX, normY);
            }
            case SELECTED -> {
                // get ready to move shape
                boolean onShape = iModel.getSelectedShape().contains(normX, normY);
                if (onShape) {
                    currentState = State.MOVING;
                }
            }
            case MOVING -> {
                // move the shape
                model.moveShape(iModel.getSelectedShape(), normX, normY);
            }
        }
        System.out.println("Current state is: " + currentState);

    }

}
