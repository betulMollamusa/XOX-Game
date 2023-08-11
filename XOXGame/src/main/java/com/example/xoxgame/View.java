package com.example.xoxgame;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class View extends GridPane {
    private Button[][] buttons = new Button[3][3];
    private Game game;

    public View(Game game) {
        this.game = game;
        initializeGrid();
    }

    private void initializeGrid() {
        setAlignment(Pos.CENTER);
        setHgap(2);
        setVgap(2);

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Button button = createButton(row, col);
                buttons[row][col] = button;
                add(button, col, row);
            }
        }
    }

    public Button[][] getButtons() {
        return buttons;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    private Button createButton(int row, int col) {
        Button button = new Button();
        button.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 24));
        button.setMinSize(150, 150);
        button.setMaxSize(150, 150);
        button.setAlignment(Pos.CENTER);
        button.setWrapText(true);
        button.setStyle("-fx-background-color: lightblue");
        button.setOnAction(event -> game.makeMove(row, col));

        return button;
    }



}
