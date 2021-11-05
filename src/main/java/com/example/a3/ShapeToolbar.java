package com.example.a3;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 * A view that contains buttons for showing
 * and selecting a shape tool for drawing.
 */
public class ShapeToolbar extends StackPane {
    private Button rect;
    private Rectangle bRect;
    private Button square;
    private Rectangle bSquare;
    private Button circle;
    private Circle bCircle;
    private Button oval;
    private Ellipse bOval;
    private Button line;
    private Line bLine;

    /**
     * Constructor for ShapeToolbar class
     */
    public ShapeToolbar() {
        // make the buttons adjust to fill vertical space
        this.heightProperty().addListener( (observable, oldValue, newValue) -> {
            rect.setPrefHeight(this.getHeight()/5);
            square.setPrefHeight(this.getHeight()/5);
            circle.setPrefHeight(this.getHeight()/5);
            oval.setPrefHeight(this.getHeight()/5);
            line.setPrefHeight(this.getHeight()/5);
        });

        // initialize rect button
        VBox vRect = new VBox();
        vRect.setAlignment(Pos.CENTER);
        bRect = new Rectangle(40, 25);
        vRect.getChildren().addAll(bRect, new Label("Rect"));
        rect = new Button("", vRect);
        // initialize square button
        VBox vSquare = new VBox();
        vSquare.setAlignment(Pos.CENTER);
        bSquare = new Rectangle(30, 30);
        vSquare.getChildren().addAll(bSquare, new Label("Square"));
        square = new Button("", vSquare);
        // initialize circle button
        VBox vCircle = new VBox();
        vCircle.setAlignment(Pos.CENTER);
        bCircle = new Circle(0, 0, 17);
        vCircle.getChildren().addAll(bCircle, new Label("Circle"));
        circle = new Button("", vCircle);
        // initialize oval button
        VBox vOval = new VBox();
        vOval.setAlignment(Pos.CENTER);
        bOval = new Ellipse(20, 12);
        vOval.getChildren().addAll(bOval, new Label("Oval"));
        oval = new Button("", vOval);
        // initialize line button
        VBox vLine = new VBox();
        vLine.setAlignment(Pos.CENTER);
        bLine = new Line(0, 0, 25, 25);
        vLine.getChildren().addAll(bLine, new Label("Line"));
        line = new Button("", vLine);

        // set the width to fill all available space
        rect.setMaxWidth(Double.MAX_VALUE);
        square.setMaxWidth(Double.MAX_VALUE);
        circle.setMaxWidth(Double.MAX_VALUE);
        oval.setMaxWidth(Double.MAX_VALUE);
        line.setMaxWidth(Double.MAX_VALUE);

        // make buttons visible
        VBox buttons = new VBox();
        buttons.getChildren().addAll(rect, square, circle, oval, line);
        this.getChildren().addAll(buttons);
        this.setPrefSize(75, 500);
    }

}
