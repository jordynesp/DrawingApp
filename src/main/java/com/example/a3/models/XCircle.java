package com.example.a3.models;

/**
 * Class to represent a circle.
 */
public class XCircle extends XSquare {

    /**
     * Constructor for XCircle
     * @param newX initial x location
     * @param newY initial y location
     * @param newWidth width of oval
     * @param newHeight height of oval
     */
    public XCircle(double newX, double newY, double newWidth, double newHeight) {
        super(newX, newY, newWidth, newHeight);
    }

    /**
     * Resize the shape with new coordinates
     * @param newX new width location
     * @param newY new height location
     */
    @Override
    public void resize(double newX, double newY) {
        super.resize(newX, newY);
    }

    /**
     * Determine if a point lies in this shape
     * @param mouseX x coordinate
     * @param mouseY y coordinate
     * @return true if point is in shape, false otherwise
     */
    @Override
    public boolean contains(double mouseX, double mouseY) {
        double centerX = x + (width/2);
        double centerY = y + (width/2);
        return (Math.pow(mouseX - centerX, 2) + Math.pow(mouseY - centerY, 2)) <= Math.pow(width/2, 2);
    }

}
