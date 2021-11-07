package com.example.a3.views;

import javafx.scene.layout.BorderPane;

/**
 * A view that contains and lays out the shape toolbar,
 * the drawing surface, and the colour toolbar.
 */
public class MainUI extends BorderPane {
    private ShapeToolbar shapeToolbar;
    private DrawingView drawingView;
    private ColourToolbar colourToolbar;

    public MainUI() {
        // create the toolbar and canvas views to store
        shapeToolbar = new ShapeToolbar();
        drawingView = new DrawingView();
        colourToolbar = new ColourToolbar();

        this.setLeft(shapeToolbar);
        this.setCenter(drawingView);
        this.setRight(colourToolbar);

        this.setPrefSize(624, 500);
    }
}
