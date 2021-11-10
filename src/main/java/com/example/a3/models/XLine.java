package com.example.a3.models;

/**
 * Class to represent a line.
 */
public class XLine extends XShape {

    /**
     * Constructor for XLine
     * @param newX1 starting x coordinate
     * @param newY1 starting y coordinate
     * @param newX2 ending x coordinate
     * @param newY2 ending y coordinate
     */
    public XLine(double newX1, double newY1, double newX2, double newY2) {
        super(newX1, newY1, newX2, newY2);
    }

    /**
     * Resize the line with new ending coordinates
     * @param newX new x ending location
     * @param newY new y ending location
     */
    @Override
    public void resize(double newX, double newY) {
        width = newX;
        height = newY;
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
