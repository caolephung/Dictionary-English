package com.example.learningenglishapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class SearchControll {

    @FXML
    private TextField textfield;

    @FXML
    private Text text;

    @FXML
    private void handleEnterpress(ActionEvent event) {
        String iptext = textfield.getText();
        text.setText(iptext);
        textfield.clear();
    }
}
