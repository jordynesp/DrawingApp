package com.example.a3.views;

import com.example.a3.controllers.DrawingController;
import com.example.a3.models.DrawingModel;
import com.example.a3.models.InteractionModel;
import com.example.a3.models.ModelSubscriber;
import javafx.geometry.Pos;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;

/**
 * A view that contains buttons for showing
 * and selecting a colour for drawing.
 */
public class ColourToolbar extends StackPane implements ModelSubscriber {
    private ArrayList<ToggleButton> buttons;
    private ToggleGroup toggles;
    private DrawingModel model;
    private InteractionModel iModel;

    /**
     * Constructor for ColourToolbar
     */
    public ColourToolbar() {
        toggles = new ToggleGroup();

        // create colour bar of rectangles
        VBox colourBar = new VBox();
        buttons = new ArrayList<>();
        String[] colourNames = {"Aqua", "Violet", "Green", "Gold", "Orange", "Coral", "Fuchsia", "Peru"};
        for (String name : colourNames) {
            // create a button for each colour
            ToggleButton button = new ToggleButton(name);
            button.setFont(new Font(11));
            button.setToggleGroup(toggles);
            button.setBackground(new Background(new BackgroundFill(Color.valueOf(name), null, null)));
            button.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, null,
                    new BorderWidths(4))));
            button.setMaxWidth(Double.MAX_VALUE);

            colourBar.getChildren().add(button);
            buttons.add(button);
        }
        colourBar.setAlignment(Pos.CENTER);

        // make the colour buttons adjust to fill vertical space
        this.heightProperty().addListener( (observable, oldValue, newValue) -> {
            for (ToggleButton b : buttons) {
                b.setPrefHeight(this.getHeight()/8);
            }
        });

        // set the first colour as selection default
        buttons.get(0).setSelected(true);
        buttons.get(0).setBorder(new Border(new BorderStroke(Color.valueOf(buttons.get(0).getText()),
                BorderStrokeStyle.SOLID, null, new BorderWidths(4))));

        // make sure there is always a button selected
        toggles.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                oldValue.setSelected(true);
            }
        });

        // make buttons visible
        this.getChildren().addAll(colourBar);
        this.setPrefSize(62, 500);
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
        iModel.setSelectedColour(Color.valueOf("Aqua"));
    }

    /**
     * Set a controller for the view
     * @param newController the controller
     */
    public void setController(DrawingController newController) {
        // set the border of the selected button
        for (ToggleButton button : buttons) {
            button.selectedProperty().addListener((observable, oldValue, newValue) -> {
                newController.handleSelectedColour(Color.valueOf(button.getText()));
            });
        }
    }

    /**
     * Update view based on model changes
     */
    public void modelChanged() {
        // set the other buttons borders back to neutral
        for (ToggleButton b : buttons) {
            b.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID,
                    null, new BorderWidths(4))));
        }
        // highlight the border of the selected button
        ((ToggleButton) toggles.getSelectedToggle()).setBorder(new Border(new BorderStroke(iModel.getSelectedColour(),
                    BorderStrokeStyle.SOLID, null, new BorderWidths(4))));
    }

}
