package com.example.a3.controllers;

import com.example.a3.models.DrawingModel;
import com.example.a3.models.InteractionModel;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * The controller to handle events from the view classes.
 */
public class DrawingController {
    protected DrawingModel model;
    protected InteractionModel iModel;
    protected double prevX, prevY, noOffsetX, noOffsetY;

    protected enum State {
        READY, PREPARE_CREATE, RESIZING, SELECTED, MOVING
    }

    protected State currentState;

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
     * @param name selected shape's name
     */
    public void handleSelectedToolShape(String name) {
        iModel.setSelectedToolShape(name);
    }

    /**
     * Delete the selected shape if there is one
     */
    public void deleteSelected() {
        if (currentState == State.SELECTED) {
            model.deleteSelected(iModel.getSelectedShape());
            iModel.setSelectedShape(null);
            currentState = State.READY;
        }
    }

    /**
     * Set the width of the viewport
     * @param newWidth the new width
     */
    public void setViewPortWidth(double newWidth) {
        iModel.viewPort.width = newWidth;
        iModel.notifySubscribers();
    }

    /**
     * Set the height of the viewport
     * @param newHeight the new height
     */
    public void setViewPortHeight(double newHeight) {
        iModel.viewPort.height = newHeight;
        iModel.notifySubscribers();
    }

    /**
     * Designate what the controller should do
     * based on state when a mouse is pressed
     * @param normX normalized x coordinate
     * @param normY normalized y coordinate
     * @param event mouse event
     */
    public void handlePressed(double normX, double normY, MouseEvent event) {
        prevX = normX;
        prevY = normY;
        noOffsetX = event.getX()/2000;
        noOffsetY = event.getY()/2000;
        switch (currentState) {
            case READY -> {
                // check if on a shape
                boolean hit = model.checkHit(normX, normY);
                if (hit) {
                    iModel.setSelectedShape(model.whichShape(normX, normY));
                    model.setZOrdering(iModel.getSelectedShape());
                    currentState = State.SELECTED;
                } else {
                    // get ready to create shape
                    iModel.setSelectedShape(null);
                    prevX = normX;
                    prevY = normY;
                    currentState = State.PREPARE_CREATE;
                }
            }
            case SELECTED -> {
                // if the handle was pressed, get ready to resize
                if (iModel.getSelectedShape() != null) {
                    boolean handleHit = iModel.getSelectedShape().handle.onHandle(normX, normY);
                    if (handleHit) {
                        currentState = State.RESIZING;
                    } else {
                        boolean onThisShape = iModel.getSelectedShape().contains(normX, normY);
                        if (onThisShape) {
                            currentState = State.MOVING;
                        } else {
                            boolean onAnotherShape = model.checkHit(normX, normY);
                            if (onAnotherShape) {
                                iModel.setSelectedShape(model.whichShape(normX, normY));
                                model.setZOrdering(iModel.getSelectedShape());
                            }
                        }
                    }
                }
                else {
                    currentState = State.READY;
                }
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
                if (prevX == normX && prevY == normY) {
                    boolean hit = model.checkHit(normX, normY);
                    if (hit) {
                        iModel.setSelectedShape(model.whichShape(normX, normY));
                        model.setZOrdering(iModel.getSelectedShape());
                    } else {
                        if (prevX == normX && prevY == normY) {
                            iModel.setSelectedShape(null);
                            currentState = State.READY;
                        }
                    }
                }
            }
        }
    }

    /**
     * Designate what the controller should do
     * based on state when a mouse is dragged
     * @param normX normalized x coordinate
     * @param normY normalized y coordinate
     * @param event mouse event
     */
    public void handleDragged(double normX, double normY, MouseEvent event) {
        // handle view panning
        if (event.isSecondaryButtonDown()) {
            double newX = event.getX()/2000;
            double newY = event.getY()/2000;
            model.pan(iModel.viewPort, newX, newY, noOffsetX, noOffsetY);
        }
        else {
            switch (currentState) {
                case PREPARE_CREATE -> {
                    // adjust the size of the shape being drawn
                    iModel.setSelectedShape(model.createShape(prevX, prevY, iModel.getSelectedShapeName(),
                            iModel.getSelectedColour()));
                    model.setZOrdering(iModel.getSelectedShape());
                    model.notifySubscribers();
                    currentState = State.RESIZING;
                }
                case RESIZING -> {
                    // resize the currently selected shape
                    model.resizeShape(iModel.getSelectedShape(), normX, normY);
                }
                case SELECTED -> {
                    if (iModel.getSelectedShape() != null) {
                        boolean onShapeXY = iModel.getSelectedShape().contains(normX, normY);
                        if (onShapeXY) {
                            boolean onShapePrevXY = iModel.getSelectedShape().contains(prevX, prevY);
                            if (onShapePrevXY) {
                                // get ready to move shape
                                currentState = State.MOVING;
                            }
                        }
                    } else {
                        currentState = State.READY;
                    }
                }
                case MOVING -> {
                    // move the shape
                    model.moveShape(iModel.getSelectedShape(), normX, normY);
                }
            }
        }
    }

}
