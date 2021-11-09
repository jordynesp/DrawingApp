package com.example.a3.models;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

/**
 * The interaction model that stores all elements related to view state.
 */
public class InteractionModel {
    private ArrayList<ModelSubscriber> subs;
    private Color selectedColour;
    private Shape selectedToolShape;
    private String selectedShapeName;
    private XShape selectedShape;

    /**
     * Constructor for InteractionModel
     */
    public InteractionModel() {
        subs = new ArrayList<>();
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
     * Set the selected colour
     * @param newColour selected colour
     */
    public void setSelectedColour(Color newColour) {
        selectedColour = newColour;
        notifySubscribers();
    }

    /**
     * Get the selected colour
     * @return the selected colour
     */
    public Color getSelectedColour() {
        return selectedColour;
    }

    /**
     * Set the selected toolbar shape
     * @param shape selected toolbar shape
     */
    public void setSelectedToolShape(Shape shape, String name) {
        selectedToolShape = shape;
        selectedShapeName = name;
        notifySubscribers();
    }

    /**
     * Get the selected toolbar shape
     * @return selected toolbar shape
     */
    public Shape getSelectedToolShape() {
        return selectedToolShape;
    }

    /**
     * Get the selected toolbar shape name
     * @return selected toolbar shape name
     */
    public String getSelectedShapeName() {
        return selectedShapeName;
    }

    /**
     * Set the currently selected shape
     * @param newShape selected shape
     */
    public void setSelectedShape(XShape newShape) {
        selectedShape = newShape;
    }

    /**
     * Get the currently selected shape
     * @return selected shape
     */
    public XShape getSelectedShape() {
        return selectedShape;
    }

}
