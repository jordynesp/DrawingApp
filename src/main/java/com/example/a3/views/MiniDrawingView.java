package com.example.a3.views;

import com.example.a3.controllers.DrawingController;
import com.example.a3.models.DrawingModel;
import com.example.a3.models.InteractionModel;
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
    public MiniDrawingView() {
        super();
        this.myCanvas.setWidth(100);
        this.myCanvas.setHeight(100);
        this.setStyle("-fx-background-color: darkgrey");
        this.setPrefSize(100, 100);
        this.setMaxSize(100, 100);
    }

    /**
     * Draws the shapes onto the canvas in immediate-mode graphics
     */
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
     * Associate a model to the view
     * @param newModel the drawing model information
     */
    public void setModel(DrawingModel newModel) {
        super.setModel(newModel);
    }

    /**
     * Associate an interaction model to the view
     * @param newIModel interaction model
     */
    public void setInteractionModel(InteractionModel newIModel) {
        super.setInteractionModel(newIModel);
    }

    /**
     * Set a controller for the view
     * @param newController the controller
     */
    public void setController(DrawingController newController) {
        super.setController(newController);
    }

    /**
     * Update view based on model changes
     */
    public void modelChanged() {
        draw();
    }

}
