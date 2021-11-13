package com.example.a3.controllers;

import javafx.scene.input.MouseEvent;

/**
 * A controller for the MiniDrawingView.
 */
public class MiniDrawingController extends DrawingController {

    /**
     * Constructor for MiniDrawingController
     */
    public MiniDrawingController() {
        super();
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
        if (!iModel.viewPort.contains(normX, normY)) {
            super.handlePressed(normX, normY, event);
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
        super.handleReleased(normX, normY, event);
    }

    /**
     * Designate what the controller should do
     * based on state when a mouse is dragged
     * @param normX normalized x coordinate
     * @param normY normalized y coordinate
     * @param event mouse event
     */
    public void handleDragged(double normX, double normY, MouseEvent event) {
        if (iModel.viewPort.contains(normX, normY) && currentState != State.MOVING && currentState != State.RESIZING) {
            model.moveViewPort(iModel.viewPort, normX, normY);
        }
        else {
            super.handleDragged(normX, normY, event);
        }
    }
}
