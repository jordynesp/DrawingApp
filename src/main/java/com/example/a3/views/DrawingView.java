package com.example.a3.views;

import com.example.a3.controllers.DrawingController;
import com.example.a3.models.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * A view that contains a canvas to show the
 * drawing and allow user interaction.
 */
public class DrawingView extends StackPane implements ModelSubscriber {
    private Canvas myCanvas;
    private GraphicsContext gc;
    private DrawingModel model;
    private InteractionModel iModel;

    /**
     * Constructor for DrawingView
     */
    public DrawingView() {
        myCanvas = new Canvas(500,500);
        gc = myCanvas.getGraphicsContext2D();
        this.setStyle("-fx-background-color: lightgrey");
        this.getChildren().add(myCanvas);

        // re-draw canvas when application is resized
        myCanvas.widthProperty().addListener((observable, oldVal, newVal) -> {
            myCanvas.setWidth(newVal.doubleValue());
            draw();
        });
        myCanvas.heightProperty().addListener((observable, oldVal, newVal) -> {
            myCanvas.setHeight(newVal.doubleValue());
            draw();
        });
    }

    /**
     * Draws the shapes onto the canvas in immediate-mode graphics
     */
    public void draw() {
        double width = myCanvas.getWidth();
        double height = myCanvas.getHeight();
        gc.clearRect(0, 0, width, height);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2.0);
        for (XShape shape : model.getShapes()) {
            gc.setFill(shape.getColourName());
            switch (shape.getShapeName()) {
                case "Rect", "Square" -> {
                    gc.fillRect(shape.x*width, shape.y*height, shape.width*width, shape.height*height);
                    gc.strokeRect(shape.x*width, shape.y*height, shape.width*width, shape.height*height);
                }
                case "Circle", "Oval" -> {
                    gc.fillOval(shape.x*width, shape.y*height, shape.width*width, shape.height*height);
                    gc.strokeOval(shape.x*width, shape.y*height, shape.width*width, shape.height*height);
                }
                case "Line" -> {
                    gc.setStroke(shape.getColourName());
                    gc.setLineWidth(3.0);
                    gc.strokeLine(shape.x*width, shape.y*height, shape.width*width, shape.height*height);
                    gc.setStroke(Color.BLACK);
                    gc.setLineWidth(2.0);
                }
            }
        }
    }

    /**
     * Associate a model to the view
     * @param newModel the drawing model information
     */
    public void setModel(DrawingModel newModel) {
        model = newModel;
    }

    /**
     * Associate an interaction model to the view
     * @param newIModel interaction model
     */
    public void setInteractionModel(InteractionModel newIModel) {
        iModel = newIModel;
    }

    /**
     * Set a controller for the view
     * @param newController the controller
     */
    public void setController(DrawingController newController) {
        double width = myCanvas.getWidth();
        double height = myCanvas.getHeight();
        myCanvas.setOnMousePressed(e -> newController.handlePressed(e.getX()/width,e.getY()/height,e));
        myCanvas.setOnMouseReleased(e -> newController.handleReleased(e.getX()/width,e.getY()/height,e));
        myCanvas.setOnMouseDragged(e -> newController.handleDragged(e.getX()/width,e.getY()/height,e));
    }

    /**
     * Update view based on model changes
     */
    public void modelChanged() {
        draw();
    }

}
