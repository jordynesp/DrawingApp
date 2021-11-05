package com.example.a3;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * A view that contains buttons for showing
 * and selecting a colour for drawing.
 */
public class ColourToolbar extends StackPane {

    public ColourToolbar() {

        VBox root = new VBox();
        root.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, null, null)));
        this.getChildren().addAll(root);
        this.setPrefSize(75, 500);

    }


}
