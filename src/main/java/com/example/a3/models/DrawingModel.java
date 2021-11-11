package com.example.a3.models;

import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * The model that stores all elements of the drawing.
 */
public class DrawingModel {
    private ArrayList<ModelSubscriber> subs;
    private ArrayList<XShape> shapes;
    private int nextZ;

    /**
     * Constructor for DrawingModel
     */
    public DrawingModel() {
        subs = new ArrayList<>();
        shapes = new ArrayList<>();
        nextZ = 0;
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
            case "Square" -> shape = new XSquare(normX, normY, 0.0, 0.0);
            case "Circle" -> shape = new XCircle(normX, normY, 0.0, 0.0);
            case "Oval" -> shape = new XOval(normX, normY, 0.0, 0.0);
            case "Line" -> shape = new XLine(normX, normY, normX, normY);
            default -> shape = null;
        }
        shape.setColourName(selectedColour);
        shape.setShapeName(selectedShapeName);
        setZOrdering(shape);
        shapes.add(shape);
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

    /**
     * Check if any shape was hit
     * @param normX mouse X coordinate
     * @param normY mouse Y coordinate
     * @return true if clicked a shape, false otherwise
     */
    public boolean checkHit(double normX, double normY) {
        return shapes.stream().anyMatch(s -> s.contains(normX,normY));
    }

    /**
     * Determines which shape was selected
     * @param normX mouse X coordinate
     * @param normY mouse Y coordinate
     * @return the hit shape
     */
    public XShape whichShape(double normX, double normY) {
        XShape found = null;
        for (XShape shape : shapes) {
            if (shape.contains(normX,normY)) {
                found = shape;
            }
        }
        return found;
    }

    /**
     * Move the selected shape
     * @param selectedShape selected shape
     * @param normX mouse X location
     * @param normY mouse Y location
     */
    public void moveShape(XShape selectedShape, double normX, double normY) {
        selectedShape.move(normX,normY);
        notifySubscribers();
    }

    /**
     * Delete the selected shape
     * @param selectedShape shape to be deleted
     */
    public void deleteSelected(XShape selectedShape) {
        shapes.remove(selectedShape);
        notifySubscribers();
    }

    /**
     * Set the Z value of a shape
     * @param shape the shape who's Z value needs to be set
     */
    public void setZOrdering(XShape shape) {
        shape.setZ(nextZ);
        nextZ++;
        shapes.sort(Comparator.comparingInt(XShape::getZ));
        notifySubscribers();
    }
}
