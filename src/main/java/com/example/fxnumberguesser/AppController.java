package com.example.fxnumberguesser;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Random;

public class AppController {

    @FXML
    private Button button;

    @FXML
    private TextField number;

    @FXML
    private Label score, highScore;
    private final Random random = new Random();
    private int userNumber, originalScore = 20, randomNumber = random.nextInt(1,21);

    @FXML
    protected void onButtonClick() {
        String mainScore = "Score: x";
        String highScore = "High Score: x";
        try{
           userNumber = Integer.parseInt(number.getText());
           if (userNumber ==randomNumber){
               setScore(highScore, originalScore, this.highScore);
               button.setDisable(true);
               System.out.println("Correct Guess: ");
           } else {
               System.out.println("Incorrect Guess, Try again");
               originalScore--;
           }
       } catch (NumberFormatException e){
           dialogBox();
           System.out.println(e.getMessage());
       }
        System.out.println("Random Number: " + randomNumber + ", user Number " + userNumber);
        number.setText("");
        setScore(mainScore, originalScore, this.score);
    }

    @FXML
    protected void reset(){
        this.number.setText("");
        this.score.setText("Score: 0");
        this.highScore.setText("High Score: 0");
        this.originalScore = 20;
        this.randomNumber = random.nextInt(1,21);
        this.button.setDisable(false);
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