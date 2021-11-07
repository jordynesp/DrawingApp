package com.example.a3.views;

import com.example.a3.controllers.DrawingController;
import com.example.a3.models.DrawingModel;
import com.example.a3.models.InteractionModel;
import com.example.a3.models.ModelSubscriber;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import java.util.ArrayList;

/**
 * A view that contains buttons for showing
 * and selecting a shape tool for drawing.
 */
public class ShapeToolbar extends StackPane implements ModelSubscriber {
    private DrawingModel model;
    private InteractionModel iModel;
    private ArrayList<ToggleButton> buttonList;
    private ToggleGroup toggles;
    private Rectangle rect;
    private Rectangle square;
    private Circle circle;
    private Ellipse oval;
    private Line line;

    /**
     * Constructor for ShapeToolbar class
     */
    public ShapeToolbar() {
        buttonList = new ArrayList<>();
        toggles = new ToggleGroup();
        VBox buttons = new VBox();

        // create a button for each shape
        String[] shapeNames = {"Rect", "Square", "Circle", "Oval", "Line"};
        for (String shape : shapeNames) {
            VBox root = new VBox();
            root.setAlignment(Pos.CENTER);
            // create the shape for the proper button
            Shape buttonShape;
            switch (shape) {
                case "Rect" -> {
                    rect = new Rectangle(40, 25);
                    buttonShape = rect;
                }
                case "Square" -> {
                    square = new Rectangle(30, 30);
                    buttonShape = square;
                }
                case "Circle" -> {
                    circle = new Circle(0, 0, 17);
                    buttonShape = circle;
                }
                case "Oval" -> {
                    oval = new Ellipse(20, 12);
                    buttonShape = oval;
                }
                case "Line" -> {
                    line = new Line(0, 0, 25, 25);
                    buttonShape = line;
                }
                default -> buttonShape = null;
            }
            root.getChildren().addAll(buttonShape, new Label(shape));
            ToggleButton button = new ToggleButton("", root);
            button.setToggleGroup(toggles);
            buttonList.add(button);
            buttons.getChildren().add(button);
        }

        // make the buttons adjust to fill vertical space
        this.heightProperty().addListener( (observable, oldValue, newValue) -> {
            for (ToggleButton button : buttonList) {
                button.setPrefHeight(this.getHeight()/5);
            }
        });

        // set the width of buttons to fill all available space
        for (ToggleButton button : buttonList) {
            button.setMaxWidth(Double.MAX_VALUE);
        }

        // add selection borders for the selected button
        for (ToggleButton b : buttonList) {
            b.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID,
                    null, new BorderWidths(2))));
            b.selectedProperty().addListener((observable, oldValue, newValue) -> {
                // set the other buttons borders back to neutral
                for (ToggleButton button : buttonList) {
                    button.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID,
                            null, new BorderWidths(2))));
                }
                // highlight the border of the selected button
                b.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                        null, new BorderWidths(2))));
            });
        }

        // set the first shape as the selection default
        buttonList.get(0).setSelected(true);
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

    }


}
