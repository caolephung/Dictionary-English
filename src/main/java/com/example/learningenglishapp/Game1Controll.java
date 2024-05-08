package com.example.learningenglishapp;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.*;
import javafx.util.Duration;
import DictionnaryCmd.ListQuestion;
import DictionnaryCmd.Question;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import com.almasb.fxgl.time.Timer;

public class Game1Controll {
    @FXML
    private ToggleGroup answer;

    private int questionCount = 0;
    private ListQuestion listQuestion;
    private int currentQuestionIndex;

    @FXML
    private Label questionLabel;

    @FXML
    private RadioButton optionARadioButton;

    @FXML
    private RadioButton optionBRadioButton;

    @FXML
    private RadioButton optionCRadioButton;

    @FXML
    private RadioButton optionDRadioButton;

    @FXML
    private Label resultLabel;

    private Timeline time;

    private int timeLeft = 15;

    @FXML
    private Label TimerLabel;

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
    
    public void initialize() {
        listQuestion = new ListQuestion();
        listQuestion.insertFromCommandline();
        currentQuestionIndex = 0;
        displayQuestion();
        initializeTimer();
        time.play();
    }

    private void displayQuestion() {
        Question question = listQuestion.get(currentQuestionIndex);
        questionLabel.setText(question.getQuestion());
        optionARadioButton.setText(question.getOption().get(0));
        optionBRadioButton.setText(question.getOption().get(1));
        optionCRadioButton.setText(question.getOption().get(2));
        optionDRadioButton.setText(question.getOption().get(3));
    }

    @FXML
    private void submitAnswer() {
        String selectedOption = null;
        if (optionARadioButton.isSelected()) {
            selectedOption = "A";
        } else if (optionBRadioButton.isSelected()) {
            selectedOption = "B";
        } else if (optionCRadioButton.isSelected()) {
            selectedOption = "C";
        } else if (optionDRadioButton.isSelected()) {
            selectedOption = "D";
        }

        if (selectedOption != null) {
            if (selectedOption.equals(listQuestion.get(currentQuestionIndex).getAnswer())) {
                resultLabel.setText("Correct!");
            } else {
                resultLabel.setText("Wrong! The correct answer is " + listQuestion.get(currentQuestionIndex).getAnswer());
            }
        }
    }



    @FXML
    private void nextQuestion() {
        resetTimer();
        currentQuestionIndex++;
        if (currentQuestionIndex < listQuestion.size()) {
            // Clear the selected radio button
            optionARadioButton.setSelected(false);
            optionBRadioButton.setSelected(false);
            optionCRadioButton.setSelected(false);
            optionDRadioButton.setSelected(false);
    
            displayQuestion();
            resultLabel.setText("");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Endgame_view.fxml"));
            try {
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @FXML
    private void retryQuestion() {
        displayQuestion();
        resultLabel.setText("");
    }

    private void updateTimerLabel() {
        int minutes = timeLeft / 60;
        int seconds = timeLeft % 60;
        String timeFormat = String.format("%02d:%02d", minutes, seconds);
        TimerLabel.setText(timeFormat);

        if (timeLeft <= 0) {
            time.stop();
            nextQuestion();
        }
    }

    @FXML
    private void resetTimer() {
        timeLeft = 15; // Reset the timer to 15 seconds
        updateTimerLabel(); // Update the timer label
        time.play();
    }

    @FXML
    private void initializeTimer() {
        time = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            timeLeft--;
            updateTimerLabel();
            if (timeLeft <= 0) {
                time.stop();
            }
        }));
        time.setCycleCount(Timeline.INDEFINITE);
    }
}