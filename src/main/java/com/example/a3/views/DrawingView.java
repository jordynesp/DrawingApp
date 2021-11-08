package com.example.a3.views;

import com.example.a3.controllers.DrawingController;
import com.example.a3.models.DrawingModel;
import com.example.a3.models.InteractionModel;
import com.example.a3.models.ModelSubscriber;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
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
        myCanvas = new Canvas(500, 500);
        gc = myCanvas.getGraphicsContext2D();
        gc.setLineWidth(5.0);
        this.getChildren().addAll(myCanvas);
        this.setPrefSize(500, 500);
        this.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
        draw();
    }

    /**
     * Draws the shapes onto the canvas in immediate-mode graphics
     */
    public void draw() {
        double width = this.getWidth();
        double height = this.getHeight();
        gc.clearRect(0, 0, width, height);
        gc.setStroke(Color.BLACK);
//        gc.strokeRect(0, 0, width, height);
        gc.setFill(Color.HOTPINK);
        gc.fillRect(30, 30, 40, 40);
        gc.strokeRect(30, 30, 40, 40);


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

    }

    /**
     * Update view based on model changes
     */
    public void modelChanged() {
        draw();
    }

}
