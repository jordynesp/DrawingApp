package com.example.a3.views;

import com.example.a3.controllers.MiniDrawingController;
import com.example.a3.models.ModelSubscriber;
import com.example.a3.models.XShape;
import javafx.scene.paint.Color;

/**
 * A view that contains a miniature DrawingView.
 */
public class MiniDrawingView extends DrawingView implements ModelSubscriber {

    /**
     * Constructor for MiniDrawingView
     */
    public MiniDrawingView(double newDocWith, double newDocHeight) {
        super(newDocWith, newDocHeight);
        this.myCanvas.setWidth(100);
        this.myCanvas.setHeight(100);
        this.setStyle("-fx-background-color: darkgrey");
        this.setPrefSize(100, 100);
        this.setMaxSize(100, 100);
    }

    /**
     * Draws the shapes onto the canvas in immediate-mode graphics
     */
    @Override
    public void draw() {
        double width = myCanvas.getWidth();
        double height = myCanvas.getHeight();
        gc.clearRect(0, 0, width, height);
        gc.setLineWidth(1.0);

        for (XShape shape : model.getShapes()) {
            gc.setFill(shape.getColourName());
            gc.setStroke(Color.BLACK);
            if (shape.getShapeName().equals("Line")) {
                gc.setStroke(shape.getColourName());
            }

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
                    gc.setLineWidth(2.0);
                    gc.strokeLine(shape.x*width, shape.y*height, shape.width*width, shape.height*height);
                    gc.setStroke(Color.BLACK);
                    gc.setLineWidth(1.0);
                }
            }
            if (shape.equals(iModel.getSelectedShape())) {
                gc.setStroke(Color.RED);
                if (shape.getShapeName().equals("Line")) {
                    gc.setLineWidth(2.0);
                    gc.strokeLine(shape.x*width, shape.y*height, shape.width*width, shape.height*height);
                }
                else {
                    gc.strokeRect(shape.x * width, shape.y * height,
                            shape.width * width, shape.height * height);
                }
            }
        }
    }

    /**
     * Set a controller for the mini view
     * @param newController the mini controller
     */
    public void setController(MiniDrawingController newController) {
        // re-draw canvas when application is resized
        myCanvas.widthProperty().addListener((observable, oldVal, newVal) -> {
            myCanvas.setWidth(newVal.doubleValue());
            draw();
        });
        myCanvas.heightProperty().addListener((observable, oldVal, newVal) -> {
            myCanvas.setHeight(newVal.doubleValue());
            draw();
        });
        // event handlers for interaction on canvas
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
