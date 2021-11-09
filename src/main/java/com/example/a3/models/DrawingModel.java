package com.example.a3.models;

import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * The model that stores all elements of the drawing.
 */
public class DrawingModel {
    private ArrayList<ModelSubscriber> subs;
    private ArrayList<XShape> shapes;

    /**
     * Constructor for DrawingModel
     */
    public DrawingModel() {
        subs = new ArrayList<>();
        shapes = new ArrayList<>();
    }

    /**
     * Add a new view subscriber to the model
     * @param newSub a view
     */
    public void addSub(ModelSubscriber newSub) {
        subs.add(newSub);
    }

    /**
     * Notify all subscribers that the model has changed
     */
    public void notifySubscribers() {
        subs.forEach(ModelSubscriber::modelChanged);
    }

    /**
     * Get the list of shapes to be drawn
     * @return list of shapes
     */
    public ArrayList<XShape> getShapes() {
        return shapes;
    }

    /**
     * Create a shape given a colour and shape tool
     * @param normX normalized x coordinate
     * @param normY normalized y coordinate
     * @param selectedShapeName name of shape to be drawn
     * @param selectedColour colour of shape to be drawn
     */
    public XShape createShape(double normX, double normY, String selectedShapeName, Color selectedColour) {
        XShape shape;
        switch (selectedShapeName) {
            case "Rect" -> shape = new XRectangle(normX, normY, 0.0, 0.0);
            case "Oval" -> shape = new XOval(normX, normY, 0.0, 0.0);
            case "Line" -> shape = new XLine(normX, normY, normX, normY);
            default -> shape = null;
        }
        shape.setColourName(selectedColour);
        shape.setShapeName(selectedShapeName);
        shapes.add(shape);
        notifySubscribers();
        return shape;
    }

    /**
     * Resize the selected shape
     * @param selectedShape selected shape
     * @param normX new location for width
     * @param normY new location for height
     */
    public void resizeShape(XShape selectedShape, double normX, double normY) {
        selectedShape.resize(normX, normY);
        notifySubscribers();
    }
}
