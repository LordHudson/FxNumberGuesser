package com.example.fxnumberguesser;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.util.Random;

public class AppController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button button;

    @FXML
    private TextField number;

    @FXML
    private Label score, highScore, message;
    private final Random random = new Random();
    private int originalScore = 20, randomNumber = random.nextInt(1,21);

    @FXML
    protected void onButtonClick() {
        String mainScore = "\uD83D\uDCAF Score: x";
        String highScore = "\uD83E\uDD47 High Score: x";
        String noNumberMessage = "⛔ No number entered!";
        String tooLow = "\uD83D\uDCC9 Too low!";
        String tooHigh = "\uD83D\uDCC8 Too high!";
        String correctScore = "\uD83C\uDF89 Correct Number!";
        String gameOver = "\uD83D\uDCA5 You lost the game";
        try{
            int userNumber = Integer.parseInt(this.number.getText());
           if (userNumber == this.randomNumber){
               setScore(highScore, this.originalScore, this.highScore);
               this.message.setText(correctScore);
               this.anchorPane.setStyle("-fx-background-color:#60b347;");
               this.button.setDisable(true);
           } else {
               originalScore--;
               String msg = (userNumber < this.randomNumber ? ((this.originalScore == 0) ? gameOver:tooLow):tooHigh);
               this.message.setText(msg);
               if (this.message.getText().equals(gameOver)){
                   this.button.setDisable(true);
               }
           }
       } catch (NumberFormatException e){
            this.message.setText(noNumberMessage);
           dialogBox();
       }
        this.number.setText("");
        setScore(mainScore, this.originalScore, this.score);
    }

    @FXML
    protected void reset(){
        this.number.setText("");
        this.score.setText("\uD83D\uDCAF Score: 0");
        this.highScore.setText("\uD83E\uDD47 High Score: 0");
        this.originalScore = 20;
        this.randomNumber = random.nextInt(1,21);
        this.button.setDisable(false);
        this.anchorPane.setStyle("-fx-background-color:#222;");
        this.message.setText("Start Guessing...");
    }
    private void dialogBox(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Number Guesser Error");
        alert.setHeaderText("Please enter a number between 1 and 20");
        alert.showAndWait();
    }
    private void setScore(String s, int x, Label label){
        String mainScore = s.replace("x", String.valueOf(x));
        label.setText(mainScore);
    }
}