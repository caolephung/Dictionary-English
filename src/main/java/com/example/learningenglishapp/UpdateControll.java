package com.example.learningenglishapp;

import DictionnaryCmd.IOData_SQL;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class UpdateControll {

    @FXML
    private Label Source;

    @FXML
    private Text Word;

    @FXML
    private WebView Explain;

    @FXML
    private TextField NewExplain;

    private IOData_SQL dataSql = new IOData_SQL("jdbc:mysql://localhost:3306/dictionaryenglish");

    @FXML
    public void initialize(String source) {
        if (source.equals("English")) {
            dataSql = new IOData_SQL("jdbc:mysql://localhost:3306/dictionaryenglish");
        } else if (source.equals("VietNamese")) {
            dataSql = new IOData_SQL("jdbc:mysql://localhost:3306/dictionaryvnese");
        }
    }

    public void getSource(String source) {
        Source.setText(source);
    }

    // Phương thức để nhận từ được chọn từ view trước
    public void setWord(String word, String definition) {
        Word.setText(word);
        Explain.getEngine().loadContent(definition, "text/html");
    }

    @FXML
    private void UpdateWordButtonAction(javafx.event.ActionEvent event) {
        initialize(Source.getText());
        dataSql.updateWord(Word.getText(), NewExplain.getText());
    }

    @FXML
    private void BackButtonAction(javafx.event.ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowAllWords_view.fxml"));
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
