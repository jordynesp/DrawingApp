package com.example.a3.models;

/**
 * Class to represent an oval.
 */
public class XOval extends XRectangle {

    /**
     * Constructor for XOval
     * @param newX initial x location
     * @param newY initial y location
     * @param newWidth width of oval
     * @param newHeight height of oval
     */
    public XOval(double newX, double newY, double newWidth, double newHeight) {
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
        double xRadius = width/2;
        double yRadius = height/2;
        if (xRadius <= 0.0 || yRadius <= 0.0) {
            return false;
        }
        double normX = mouseX - (x + width/2);
        double normY = mouseY - (y + height/2);

        return (normX*normX)/(xRadius*xRadius) + (normY*normY)/(yRadius*yRadius) <= 1.0;
    }

}
