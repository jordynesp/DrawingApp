package com.example.a3.models;

/**
 * Class to represent a circle.
 */
public class XCircle extends XShape {
    private double initialX, initialY;

    /**
     * Constructor for XCircle
     * @param newX initial x location
     * @param newY initial y location
     * @param newWidth width of oval
     * @param newHeight height of oval
     */
    public XCircle(double newX, double newY, double newWidth, double newHeight) {
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

        // top right quad
        if (newY < initialY && newX > initialX) {
            y = newY;
            width = initialY - newY;
            height = initialY - newY;
        }
        // bottom left quad
        else if (newY > initialY && newX < initialX) {
            x = newX;
            width = initialX - newX;
            height = initialX - newX;
        }
        // top left quad
        else if (newY < initialY && newX < initialX) {
            width = initialX - newX;
            height = initialY - newY;
            if (width < height) {
                height = width;
            }
            else {
                width = height;
            }
            x = initialX - width;
            y = initialY - height;
        }
        // bottom right quad
        else {
            width = newX - initialX;
            height = newY - initialY;
            if (width < height) {
                height = width;
            }
            else {
                width = height;
            }
        }
    }
}
