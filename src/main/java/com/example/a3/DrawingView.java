package com.example.a3;

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
public class DrawingView extends StackPane {
    private Canvas myCanvas;
    private GraphicsContext gc;

    private InteractionModel iModel;


    public DrawingView() {
        myCanvas = new Canvas(500, 500);
        gc = myCanvas.getGraphicsContext2D();
        gc.setLineWidth(5.0);
        this.getChildren().addAll(myCanvas);
        this.setPrefSize(500, 500);
        this.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));

    }

}
