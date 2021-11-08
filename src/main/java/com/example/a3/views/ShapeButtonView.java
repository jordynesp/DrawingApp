package com.example.a3.views;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Shape;

/**
 * A view for each shape button.
 */
public class ShapeButtonView extends VBox {
    private Shape shape;
    private String shapeName;

    /**
     * Constructor for ShapeButtonView
     */
    public ShapeButtonView(Shape newShape, String name) {
        shape = newShape;
        shapeName = name;
        Label label = new Label(shapeName);
        this.getChildren().addAll(shape, label);
        this.setAlignment(Pos.CENTER);
    }

    /**
     * Get the shape of the button graphic
     * @return the shape
     */
    public Shape getButtonShape() {
        return shape;
    }

    /**
     * Get the name of the shape
     * @return shape name
     */
    public String getShapeName() {
        return shapeName;
    }
}
