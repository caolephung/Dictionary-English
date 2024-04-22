package com.example.learningenglishapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import DictionnaryCmd.IOData_SQL;

import java.io.IOException;

public class AddWordControll {

    @FXML
    private TextArea Word;

    @FXML
    private TextArea Explain;

    private IOData_SQL dataSql = new IOData_SQL();

    @FXML
    private void addWordControll(ActionEvent event) {
        String word = Word.getText().trim();
        String explanation = Explain.getText().trim();

        if (!word.isEmpty() && !explanation.isEmpty()) {
            explanation = formatWord(word, explanation);
            dataSql.addWord(word, explanation);
            clearFields();
            System.out.println("Thêm từ thành công.");
        } else {
            System.out.println("Vui lòng nhập cả từ và giải nghĩa.");
        }
    }

    private void clearFields() {
        Word.clear();
        Explain.clear();
    }

    private String formatWord(String word, String explanation) {
        return String.format("<html><i>%s</i><br/><ul><li><font color='#cc0000'><b>%s</b></font></li></ul></html>", word, explanation);
    }

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
}

