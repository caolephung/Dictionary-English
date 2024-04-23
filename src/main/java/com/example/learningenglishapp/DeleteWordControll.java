package com.example.learningenglishapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import DictionnaryCmd.IOData_SQL;

import java.io.IOException;

public class DeleteWordControll {

    @FXML
    private ImageView Flag_source;

    @FXML
    private ImageView Flag_target;

    @FXML
    private Label source;

    @FXML
    private Label target;

    @FXML
    private TextArea Word;

    private IOData_SQL dataSql = new IOData_SQL("jdbc:mysql://localhost:3306/dictionaryenglish");

    @FXML
    public void initialize(String source) {
        if (source.equals("English")) {
            dataSql = new IOData_SQL("jdbc:mysql://localhost:3306/dictionaryenglish");
        } else if (source.equals("VietNamese")) {
            dataSql = new IOData_SQL("jdbc:mysql://localhost:3306/dictionaryvnese");
        }
    }

    @FXML
    private void DeleteWord(ActionEvent event) {
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
    private void swapFlag() {
        Image tmp = Flag_source.getImage();
        Flag_source.setImage(Flag_target.getImage());
        Flag_target.setImage(tmp);
    }

    @FXML
    private void swapButtonAction() throws IOException {
        if (source.getText().equals("English")) {
            source.setText("VietNamese");
            target.setText("English");
            swapFlag();
            initialize("VietNamese");
        } else if (source.getText().equals("VietNamese")) {
            source.setText("English");
            target.setText("VietNamese");
            swapFlag();
            initialize("English");
        }
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
