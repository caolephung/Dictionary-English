package com.example.learningenglishapp;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

public class TranslateControll {

    @FXML
    private TextField inputTextField;

    @FXML
    private TextArea outputText;

    @FXML
    private Button translateButton;

    @FXML
    private Text source;

    @FXML
    private Text target;

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
        String input = inputTextField.getText();
        // Gọi hàm translateText từ TranslateAPI để dịch văn bản
        String translatedText = TranslateAPI.translateText(input, sourceLanguage, targetLanguage);

        // Hiển thị văn bản dịch trong outputTextArea
        outputText.setText(translatedText);
    }

    @FXML
    private void swapButtonAction() {
        if(source.getText().equals("EN")) {
            source.setText("VI");
            target.setText("EN");
            initialize(VI_LANG, EN_LANG);
            inputTextField.setText(outputText.getText());
            translateText();

        } else if(source.getText().equals("VI")) {
            source.setText("EN");
            target.setText("VI");
            initialize(EN_LANG, VI_LANG);
            inputTextField.setText(outputText.getText());
            translateText();
        }
    }
}
