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
        handle.moveHandle(newX2, newY2, 0.0, 0.0);
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
        handle.moveHandle(width, height, 0, 0);
    }

    /**
     * Determine if a point lies in this shape
     * @param mouseX x coordinate
     * @param mouseY y coordinate
     * @return true if point is in shape, false otherwise
     */
    @Override
    public boolean contains(double mouseX, double mouseY) {
        double length = calcDistance(x,y,width,height);
        double ratioA = (y-height) / length;
        double ratioB = (width-x) / length;
        double ratioC = -1 * ((y-height) * x + (width-x) * y) / length;
        double distanceFromLine = Math.abs(ratioA * mouseX + ratioB * mouseY + ratioC);

        return distanceFromLine <= 0.008;
    }

    /**
     * Move the shape
     * @param normX new X location
     * @param normY new Y location
     */
    @Override
    public void move(double normX, double normY) {
        double dX = normX - x;
        double dY = normY - y;
        x += dX;
        y += dY;
        width += dX;
        height += dY;
        initialX = x;
        initialY = y;
        handle.moveHandle(width, height, 0.0, 0.0);
    }

    /**
     * Calculates the distance between two points
     * @param x1 first X coordinate
     * @param y1 first Y coordinate
     * @param x2 second X coordinate
     * @param y2 second Y coordinate
     * @return the distance between two points
     */
    private double calcDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
    }

}
