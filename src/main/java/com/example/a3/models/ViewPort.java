package com.example.a3.models;

/**
 * A class for the viewport of the document.
 */
public class ViewPort extends XRectangle {

    /**
     * Constructor for ViewPort
     * @param newX initial x location
     * @param newY initial y location
     * @param newWidth width of viewport
     * @param newHeight height of viewport
     */
    public ViewPort(double newX, double newY, double newWidth, double newHeight) {
        super(newX, newY, newWidth, newHeight);
    }

    /**
     * Move the viewport (within document constraints)
     * @param normX new X location
     * @param normY new Y location
     */
    @Override
    public void move(double normX, double normY) {
        x = normX - (width / 2);
        y = normY - (height / 2);
        if (x + width >= 1) {
            x = 1 - width;
        }
        if (x <= 0) {
            x = 0;
        }
        if (y + height >= 1) {
            y = 1 - height;
        }
        if (y <= 0) {
            y = 0;
        }
        initialX = x;
        initialY = y;
        handle.moveHandle(x, y, width, height);
    }

    /**
     * Move the viewport based on main view panning
     * @param newX newest X location
     * @param newY newest Y location
     * @param prevX X location on mouse press
     * @param prevY Y location on mouse press
     */
    public void pan(double newX, double newY, double prevX, double prevY) {
        x = x + (prevX - newX);
        y = y + (prevY - newY);
        if (x + width >= 1) {
            x = 1 - width;
        }
        if (x <= 0) {
            x = 0;
        }
        if (y + height >= 1) {
            y = 1 - height;
        }
        if (y <= 0) {
            y = 0;
        }
        initialX = x;
        initialY = y;
        handle.moveHandle(x, y, width, height);
    }

}
