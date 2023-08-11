package com.example.xoxgame;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class Game {
    private char currentPlayer = 'X';
    private Button[][] buttons;
    private boolean gameOver = false;
    public Button resetBtn = new Button("Restart");

    public Game(Button[][] buttons) {
        this.buttons = buttons;

    }

    public void initializeGame() {
        currentPlayer='X';
        gameOver = false;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
                buttons[row][col].setDisable(false);
            }
        }
        resetBtn.setMinSize(70,40);
        resetBtn.setVisible(false);
    }

    public void makeMove(int row, int col) {
        if (!gameOver && buttons[row][col].getText().isEmpty() && currentPlayer == 'X') {
            buttons[row][col].setText(String.valueOf(currentPlayer));
            buttons[row][col].setDisable(true);
            if (checkWin()) {
                showAlert(currentPlayer + " wins!");
                gameOver = true;
            } else if (checkDraw()) {
                showAlert("It's a draw!");
                gameOver = true;
            } else {
                currentPlayer = 'O';
                computerMove();
            }
        }
    }

    private void computerMove() {
        if (!gameOver) {
            // Kazanmaya odaklı oyun algoritması
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (buttons[row][col].getText().isEmpty()) {
                        buttons[row][col].setText(String.valueOf(currentPlayer));

                        if (checkWin()) {
                            showAlert("Computer wins!");
                            gameOver = true;
                            return; // Bilgisayar kazanma hamlesini yaptı
                        }

                        buttons[row][col].setText(""); // Hamleyi geri al
                    }
                }
            }

            // Rastgele boş hücreye hamle yap (kazanma hamlesi yoksa)
            while (true) {
                int randomRow = (int) (Math.random() * 3);
                int randomCol = (int) (Math.random() * 3);
                if (buttons[randomRow][randomCol].getText().isEmpty()) {
                    buttons[randomRow][randomCol].setText(String.valueOf(currentPlayer));
                    buttons[randomRow][randomCol].setDisable(true);
                    if (checkWin()) {
                        showAlert("Computer wins!");
                        gameOver = true;
                    } else if (checkDraw()) {
                        showAlert("It's a draw!");
                        gameOver = true;
                    }
                    currentPlayer = 'X';
                    break;
                }
            }
        }
    }

    private boolean checkWin() {
        // Kazanan durumları kontrol et
        for (int i = 0; i < 3; i++) {
            if (!buttons[i][0].getText().isEmpty() &&
                    buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                    buttons[i][1].getText().equals(buttons[i][2].getText())) {
                return true;
            }
            if (!buttons[0][i].getText().isEmpty() &&
                    buttons[0][i].getText().equals(buttons[1][i].getText()) &&
                    buttons[1][i].getText().equals(buttons[2][i].getText())) {
                return true;
            }
        }
        if (!buttons[0][0].getText().isEmpty() &&
                buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][2].getText())) {
            return true;
        }
        if (!buttons[0][2].getText().isEmpty() &&
                buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][0].getText())) {
            return true;
        }
        return false;
    }

    private boolean checkDraw() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().isEmpty()) {
                    return false; // Henüz boş hücre var
                }
            }
        }
        return true; // Tüm hücreler dolu, berabere durumu
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
        resetBtn.setVisible(true);

        resetBtn.setOnAction(e ->{
            initializeGame();
        });
    }

}

