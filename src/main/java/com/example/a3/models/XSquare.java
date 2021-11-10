package com.example.a3.models;

/**
 * Class to represent a square.
 */
public class XSquare extends XShape {
    private double initialX, initialY;

    /**
     * Constructor for XSquare
     * @param newX initial x location
     * @param newY initial y location
     * @param newWidth width of square
     * @param newHeight height of square
     */
    public XSquare(double newX, double newY, double newWidth, double newHeight) {
        super(newX, newY, newWidth, newHeight);
        // keep track of the original top/left location
        initialX = newX;
        initialY = newY;
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

    /**
     * Determine if a point lies in this shape
     * @param mouseX x coordinate
     * @param mouseY y coordinate
     * @return true if point is in shape, false otherwise
     */
    @Override
    public boolean contains(double mouseX, double mouseY) {
        return false;
    }

}
