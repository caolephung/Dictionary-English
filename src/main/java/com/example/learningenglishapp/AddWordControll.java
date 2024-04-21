package com.example.learningenglishapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AddWordControll {

    @FXML
    private TextArea Word;

    @FXML
    private TextArea Explain;


    private void addWordToDictionary(String PATH) {
        String word = Word.getText().trim();
        String explanation = Explain.getText().trim();

        if (!word.isEmpty() && !explanation.isEmpty()) {
            String formattedWord = formatWord(word, explanation);

            // Write the formatted word to the text file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH, true))) {
                writer.write(formattedWord);
                writer.newLine();
                writer.flush();
                clearFields();
            } catch (IOException e) {
                System.out.println("An error occurred while adding word to dictionary: " + e.getMessage());
            }
        } else {
            System.out.println("Please enter both word and explanation.");
        }
    }

    private String formatWord(String word, String explanation) {
        return String.format("<html><i>%s</i><br/><ul><li><font color='#cc0000'><b>%s</b></font></li></ul></html>", word, explanation);
    }

    private void clearFields() {
        Word.clear();
        Explain.clear();
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
