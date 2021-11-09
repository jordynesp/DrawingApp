package com.example.a3.models;

import javafx.scene.paint.Color;

/**
 * The abstract supertype of all drawing shapes.
 */
public abstract class XShape {
    private Color colourName;
    private String shapeName;
    public double x, y, width, height;

    /**
     * Constructor for XShape
     * @param newX initial x location
     * @param newY initial y location
     * @param newWidth width of shape
     * @param newHeight height of shape
     */
    public XShape(double newX, double newY, double newWidth, double newHeight) {
        x = newX;
        y = newY;
        width = newWidth;
        height = newHeight;
    }

    /**
     * Resize the shape with new coordinates
     * @param newX new width location
     * @param newY new height location
     */
    public abstract void resize(double newX, double newY);

    /**
     * Set the colour of the shape
     * @param colour colour
     */
    public void setColourName(Color colour) {
        colourName = colour;
    }

    /**
     * Get the colour of the shape
     * @return colour of the shape
     */
    public Color getColourName() {
        return  colourName;
    }

    /**
     * Set the name of the shape
     * @param newShapeName shape name
     */
    public void setShapeName(String newShapeName) {
        shapeName = newShapeName;
    }

    /**
     * Get the name of the shape
     * @return shape name
     */
    public String getShapeName() {
        return shapeName;
    }


    /*
    - will need to implement collision detection for each shape
    - might need to have an abstract contains method like:
       public abstract boolean contains(double x, double y);
    - implement movement, resizing
    - drawing itself happens within the few
     */

}
