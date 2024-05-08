package com.example.learningenglishapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

import javax.swing.text.html.ImageView;

import DictionnaryCmd.WordDropGame;

public class Game2Controll {
    @FXML
    private Label scrambledWordLabel;
    @FXML
    private TextField guessTextField;
    @FXML
    private Button submitGuessButton;
    @FXML
    private Label feedbackLabel;
    @FXML
    private Button revealAnswerButton;
    @FXML
    private Label answerLabel;
    @FXML
    private Button nextQuestionButton;

    private WordDropGame game;
    private String currentWord;

    public void initialize() {
        try {
            game = new WordDropGame("src/main/java/DataFile/words.txt");
            nextQuestion();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void nextQuestion() {
        currentWord = game.getRandomWord();
        scrambledWordLabel.setText(game.scrambleWord(currentWord));
        feedbackLabel.setText("");
        answerLabel.setText("");
    }

    @FXML
    private void submitGuess(ActionEvent event) {
        String guess = guessTextField.getText();
        if (guess.equalsIgnoreCase(currentWord)) {
            feedbackLabel.setText("Correct!");
            nextQuestion();
        } else {
            feedbackLabel.setText("Incorrect, try again.");
        }
        guessTextField.clear();
    }

    @FXML
    private void revealAnswer(ActionEvent event) {
        answerLabel.setText("The correct word was: " + currentWord);
    }

    @FXML
    private void nextQuestion(ActionEvent event) {
        nextQuestion();
    }
    
    @FXML
    private void BackButtonAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Games_view.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
