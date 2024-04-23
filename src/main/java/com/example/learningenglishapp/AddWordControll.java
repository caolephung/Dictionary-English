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

public class AddWordControll {

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

    @FXML
    private TextArea Explain;

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
    private void AddWord(ActionEvent event) {
        String word = Word.getText().trim();
        String explanation = Explain.getText().trim();

        if (!word.isEmpty() && !explanation.isEmpty()) {
//            explanation = formatWord(word, explanation);
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

