package com.example.a3.models;

/**
 * Class to represent an oval.
 */
public class XOval extends XShape {
    private double initialX, initialY;

    /**
     * Constructor for XOval
     * @param newX initial x location
     * @param newY initial y location
     * @param newWidth width of oval
     * @param newHeight height of oval
     */
    public XOval(double newX, double newY, double newWidth, double newHeight) {
        super(newX, newY, newWidth, newHeight);
        initialX = newX;
        initialY = newY;
    }

    /**
     * Resize the shape with new coordinates
     * @param newX new width location
     * @param newY new height location
     */
    public void resize(double newX, double newY) {
        x = initialX;
        y = initialY;
        width = newX - initialX;
        height = newY - initialY;
        if (width < 0) {
            x = newX;
            width = initialX - newX;
        }
        if (height < 0) {
            y = newY;
            height = initialY - newY;
        }
    }
}
