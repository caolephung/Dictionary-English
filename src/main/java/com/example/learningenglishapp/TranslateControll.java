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
    private void translateText() {
        String inputText = inputTextField.getText();
        String sourceLanguage = "en"; // Ngôn ngữ nguồn (ví dụ: tiếng Anh)
        String targetLanguage = "vi"; // Ngôn ngữ đích (ví dụ: tiếng Việt)

        // Gọi hàm translateText từ TranslateAPI để dịch văn bản
        String translatedText = TranslateAPI.translateText(inputText, sourceLanguage, targetLanguage);

        // Hiển thị văn bản dịch trong outputTextArea
        outputText.setText(translatedText);
    }

    @FXML
    private void swapButtonAction() {
        if(source.getText().equals("EN")) {
            source.setText("VI");
            target.setText("EN");
        } else if(source.getText().equals("VI")) {
            source.setText("EN");
            target.setText("VI");
        }


    }
}
