package com.example.a3.models;

/**
 * Class to represent a rectangle.
 */
public class XRectangle extends XShape {

    /**
     * Constructor for XRectangle
     * @param newX initial x location
     * @param newY initial y location
     * @param newWidth width of rectangle
     * @param newHeight height of rectangle
     */
    public XRectangle(double newX, double newY, double newWidth, double newHeight) {
        super(newX, newY, newWidth, newHeight);
    }

    /**
     * Resize the shape with new coordinates
     * @param newX new width location
     * @param newY new height location
     */
    @Override
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

    /**
     * Determine if a point lies in this shape
     * @param mouseX x coordinate
     * @param mouseY y coordinate
     * @return true if point is in shape, false otherwise
     */
    @Override
    public boolean contains(double mouseX, double mouseY) {
        return mouseX >= x && mouseX <= x+width && mouseY >= y && mouseY <= y+height;
    }


}
