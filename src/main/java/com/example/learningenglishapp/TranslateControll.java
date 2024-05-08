package com.example.learningenglishapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class TranslateControll {

    @FXML
    private ImageView Flag_source;

    @FXML
    private ImageView Flag_target;

    @FXML
    private TextArea inputText;

    @FXML
    private TextArea outputText;

    @FXML
    private Button translateButton;

    @FXML
    private Label source;

    @FXML
    private Label target;

    @FXML
    private Button swapButton;

    @FXML
    private static final String EN_LANG = "en";

    @FXML
    private static final String VI_LANG = "vi";

    @FXML
    private String sourceLanguage = EN_LANG;
    @FXML
    private String targetLanguage = VI_LANG;

    @FXML
    private void initialize(String sourceLanguage, String targetLanguage) {
        this.sourceLanguage = sourceLanguage;
        this.targetLanguage = targetLanguage;
    }

    @FXML
    private void translateText() {
        String input = inputText.getText();
        // Gọi hàm translateText từ TranslateAPI để dịch văn bản
        String translatedText = TranslateAPI.translateText(input, sourceLanguage, targetLanguage);

        // Hiển thị văn bản dịch trong outputTextArea
        outputText.setText(translatedText);
    }

    /** Hoán đổi vị trí 2 lá cờ. */
    @FXML
    private void swapFlag() {
        Image tmp = Flag_source.getImage();
        Flag_source.setImage(Flag_target.getImage());
        Flag_target.setImage(tmp);
    }

    /** Hoán đổi English <-> Vietnamese. */
    @FXML
    private void swapButtonAction() {
        if(source.getText().equals("English")) {
            source.setText("Vietnamese");
            target.setText("English");
            swapFlag();
            initialize(VI_LANG, EN_LANG);
            if(!outputText.getText().isEmpty()) {
                inputText.setText(outputText.getText());
                translateText();
            }

        } else if(source.getText().equals("Vietnamese")) {
            source.setText("English");
            target.setText("Vietnamese");
            swapFlag();
            initialize(EN_LANG, VI_LANG);
            if(!outputText.getText().isEmpty()) {
                inputText.setText(outputText.getText());
                translateText();
            }
        }
    }


    /** Quay trở lại welcome. */
    @FXML
    private void BackButtonAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Welcome_view.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void voiceSourceButton(ActionEvent actionEvent) throws Exception {
        String sourceLanguage = source.getText();
        if(sourceLanguage.equals("English")) {
            Sound.SpeechEnglish(inputText.getText());
        } else if(sourceLanguage.equals("Vietnamese")) {
            Sound.SpeechVietNamese(inputText.getText());
        }
    }

    @FXML
    public void voiceTargetButton(ActionEvent actionEvent) throws Exception {
        String targetLanguage = target.getText();
        if(targetLanguage.equals("English")) {
            Sound.SpeechEnglish(outputText.getText());
        } else if(targetLanguage.equals("Vietnamese")) {
            Sound.SpeechVietNamese(outputText.getText());
        }
    }


    @FXML
    private void voiceTargetButton(String sourceLanguage) throws Exception {
        if(sourceLanguage.equals("English")) {
            Sound.SpeechEnglish(outputText.getText());
        } else if(sourceLanguage.equals("Vietnamese")) {
            Sound.SpeechVietNamese(outputText.getText());
        }
    }
}
