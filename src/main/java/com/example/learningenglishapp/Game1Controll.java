package com.example.learningenglishapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.w3c.dom.events.Event;

import java.io.IOException;

public class Game1Controll {

    @FXML
    private Button Back;

    @FXML
    private void BackButtoActionn(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Games_view.fxml"));
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
