package com.example.a3.models;

/**
 * Class to represent a resize handle.
 */
public class ResizeHandle {
    public double centerX, centerY, x, y, diameter;

    /**
     * Constructor for a ResizeHandle
     * @param newCenterX center x coordinate of the handle circle
     * @param newCenterY center y coordinate of the handle circle
     */
    public ResizeHandle(double newCenterX, double newCenterY) {
        centerX = newCenterX;
        centerY = newCenterY;
        diameter = 0.02;
        x = centerX - diameter/2;
        y = centerY - diameter/2;
    }

    /**
     * Determine if a point lies in this shape
     * @param mouseX x coordinate
     * @param mouseY y coordinate
     * @return true if point is in shape, false otherwise
     */
    public boolean onHandle(double mouseX, double mouseY) {
        return (Math.pow(mouseX - centerX, 2) + Math.pow(mouseY - centerY, 2)) <= Math.pow(diameter/2, 2);
    }

    /**
     * Move the handle when its shape moves
     * @param newX x position of shapes left coordinate
     * @param newY y position of shapes top coordinate
     * @param width width of shape
     * @param height height of shape
     */
    public void moveHandle(double newX, double newY, double width, double height) {
        centerX = newX + width;
        centerY = newY + height;
        x = centerX - diameter/2;
        y = centerY - diameter/2;

    }
}
