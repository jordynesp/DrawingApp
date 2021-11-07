package com.example.a3.models;

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

}
