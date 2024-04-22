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

public class DeleteWordControll {

    @FXML
    private TextArea Word;

    private IOData_SQL dataSql = new IOData_SQL();

    @FXML
    private void deleteWordControll(ActionEvent event) {
        String wordToDelete = Word.getText().trim();

        if (!wordToDelete.isEmpty()) {
            dataSql.removeWord(wordToDelete);
            clearField();
            System.out.println("Xóa từ thành công.");
        } else {
            System.out.println("Vui lòng nhập từ cần xóa.");
        }
    }

    private void clearField() {
        Word.clear();
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
