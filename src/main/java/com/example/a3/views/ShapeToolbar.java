package com.example.a3.views;

import com.example.a3.controllers.DrawingController;
import com.example.a3.models.DrawingModel;
import com.example.a3.models.InteractionModel;
import com.example.a3.models.ModelSubscriber;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import java.util.HashMap;

/**
 * A view that contains buttons for showing
 * and selecting a shape tool for drawing.
 */
public class ShapeToolbar extends StackPane implements ModelSubscriber {
    private DrawingModel model;
    private InteractionModel iModel;
    private HashMap<ToggleButton, ShapeButtonView> buttonShapes;
    private ToggleGroup toggles;

    /**
     * Constructor for ShapeToolbar class
     */
    public ShapeToolbar() {
        toggles = new ToggleGroup();
        buttonShapes = new HashMap<>();
        VBox buttons = new VBox();

        // create a button for each shape
        String[] shapeNames = {"Rect", "Square", "Circle", "Oval", "Line"};
        for (String shape : shapeNames) {
            Shape buttonShape;
            // create the shape for the proper button
            switch (shape) {
                case "Rect" -> buttonShape = new Rectangle(40, 25);
                case "Square" -> buttonShape = new Rectangle(30, 30);
                case "Circle" -> buttonShape = new Circle(0, 0, 17);
                case "Oval" -> buttonShape = new Ellipse(20, 12);
                case "Line" -> buttonShape = new Line(0, 0, 25, 25);
                default -> buttonShape = null;
            }
            // create a button for each shape
            ShapeButtonView buttonGraphic = new ShapeButtonView(buttonShape, shape);
            ToggleButton button = new ToggleButton("", buttonGraphic);
            button.setToggleGroup(toggles);
            buttonShapes.put(button, buttonGraphic);
            buttons.getChildren().add(button);

            // set the first shape as the selection default
            if (shape.equals("Rect")) {
                button.setSelected(true);
                // highlight the border of the selected button
                button.setBorder(new Border(new BorderStroke(Color.valueOf("Aqua"), BorderStrokeStyle.SOLID,
                        null, new BorderWidths(2))));
                buttonShapes.get(button).getButtonShape().setFill(Color.valueOf("Aqua"));
            }
        }

        // make the buttons adjust to fill vertical space
        this.heightProperty().addListener( (observable, oldValue, newValue) -> {
            for (ToggleButton button : buttonShapes.keySet()) {
                button.setPrefHeight(this.getHeight()/5);
            }
        });

        // make sure there is always a button selected
        toggles.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                oldValue.setSelected(true);
            }
        });

        // set the width of buttons to fill all available space
        for (ToggleButton button : buttonShapes.keySet()) {
            button.setMaxWidth(Double.MAX_VALUE);
        }

        // make buttons visible
        this.getChildren().addAll(buttons);
        this.setPrefSize(61, 500);
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
        // initialize iModel selection
        for (ToggleButton button : buttonShapes.keySet()) {
            if (buttonShapes.get(button).getShapeName().equals("Rect")) {
                iModel.setSelectedToolShape(buttonShapes.get(button).getButtonShape(),
                        buttonShapes.get(button).getShapeName());
            }
        }
    }

    /**
     * Set a controller for the view
     * @param newController the controller
     */
    public void setController(DrawingController newController) {
        // set the border of the selected button
        for (ToggleButton button : buttonShapes.keySet()) {
            button.selectedProperty().addListener((observable, oldValue, newValue) -> {
                newController.handleSelectedToolShape(buttonShapes.get(button).getButtonShape(),
                        buttonShapes.get(button).getShapeName());
            });
        }
    }

    /**
     * Update view based on model changes
     */
    public void modelChanged() {
        // set the other buttons borders back to neutral
        for (ToggleButton button : buttonShapes.keySet()) {
            button.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID,
                    null, new BorderWidths(2))));
            Shape shape = buttonShapes.get(button).getButtonShape();
            shape.setFill(Color.BLACK);
            shape.setStroke(Color.BLACK);
        }
        // set the border for the selected button
        ((ToggleButton) toggles.getSelectedToggle()).setBorder(new Border(new BorderStroke(iModel.getSelectedColour(),
                BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
        Shape selection = buttonShapes.get((ToggleButton) toggles.getSelectedToggle()).getButtonShape();
        selection.setFill(iModel.getSelectedColour());
        selection.setStroke(iModel.getSelectedColour());
    }


}
