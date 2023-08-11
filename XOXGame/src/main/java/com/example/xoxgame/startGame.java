package com.example.xoxgame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class startGame extends Application {
    @Override
    public void start(Stage stage) {
           View view = new View(null);
           Game game = new Game(view.getButtons());

           view.setGame(game); // View sınıfına game referansını set ediyoruz

           BorderPane root = new BorderPane();
           root.setCenter(view);
           root.setBottom(game.resetBtn);
           Scene scene = new Scene(root, 500, 500);
           stage.setTitle("XOX Game");
           stage.setScene(scene);
           stage.show();

           game.initializeGame(); // Oyunu başlat
    }

    public static void main(String[] args) {
        launch();
    }
}
