package com.example.a3.views;

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
public class ColourToolbar extends StackPane {
    private ArrayList<ToggleButton> colourRects;
    private ToggleGroup toggles;

    /**
     * Constructor for ColourToolbar
     */
    public ColourToolbar() {
        toggles = new ToggleGroup();

        // create colour bar of rectangles
        VBox colourBar = new VBox();
        colourRects = new ArrayList<>();
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
            // change the border of the most recently selected button
            button.selectedProperty().addListener((observable, oldValue, newValue) -> {
                // set the other buttons borders back to neutral
                for (ToggleButton b : colourRects) {
                    b.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID,
                            null, new BorderWidths(4))));
                }
                // highlight the border of the selected button
                button.setBorder(new Border(new BorderStroke(Color.valueOf(name), BorderStrokeStyle.SOLID,
                        null, new BorderWidths(4))));
            });

            colourBar.getChildren().add(button);
            colourRects.add(button);
        }
        colourBar.setAlignment(Pos.CENTER);

        // make the colour buttons adjust to fill vertical space
        this.heightProperty().addListener( (observable, oldValue, newValue) -> {
            for (ToggleButton b : colourRects) {
                b.setPrefHeight(this.getHeight()/8);
            }
        });

        // set the first colour as selection default
        colourRects.get(0).setSelected(true);
        // make buttons visible
        this.getChildren().addAll(colourBar);
        this.setPrefSize(62, 500);
    }

}
