package com.example.a3.models;

import javafx.scene.paint.Color;

/**
 * The abstract supertype of all drawing shapes.
 */
public abstract class XShape {
    private Color colourName;
    private String shapeName;
    public double x, y, width, height, initialX, initialY;
    public ResizeHandle handle;
    private int z;

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
        initialX = newX;
        initialY = newY;
        double handleX = x + width;
        double handleY = y + height;
        handle = new ResizeHandle(handleX, handleY);
    }

    /**
     * Resize the shape with new coordinates
     * @param newX new width location
     * @param newY new height location
     */
    public abstract void resize(double newX, double newY);

    /**
     * Determine if a point lies in this shape
     * @param mouseX x coordinate
     * @param mouseY y coordinate
     * @return true if point is in shape, false otherwise
     */
    public abstract boolean contains(double mouseX, double mouseY);

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

    /**
     * Move the shape
     * @param normX new X location
     * @param normY new Y location
     */
    public void move(double normX, double normY) {
        x = normX-(width/2);
        y = normY-(height/2);
        initialX = x;
        initialY = y;
        handle.moveHandle(x, y, width, height);
    }

    /**
     * Set the z value of the shape
     * @param newZ the z value
     */
    public void setZ(int newZ) {
        z = newZ;
    }

    /**
     * Get the z value of the shape
     * @return the z value
     */
    public int getZ() {
        return z;
    }

}
