package com.example.learningenglishapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

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

    @FXML
    private void CorrectButtonAction(ActionEvent event) {
        // Đường dẫn đến file âm thanh
        String path = "src/main/resources/com/example/learningenglishapp/Sound/Bomman.mp3";
        Media media = new Media(new File(path).toURI().toString());

        // Tạo một đối tượng MediaPlayer với đối tượng Media
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        // Bắt đầu phát âm thanh
        mediaPlayer.play();
    }

    @FXML
    private void InCorrectButtonAction(ActionEvent event) {
        // Đường dẫn đến file âm thanh
        String path = "src/main/resources/com/example/learningenglishapp/Sound/Cau-noi-do-ngu-do-an-hai-mp3-www_tiengdong_com.mp3";
        Media media = new Media(new File(path).toURI().toString());

        // Tạo một đối tượng MediaPlayer với đối tượng Media
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        // Bắt đầu phát âm thanh
        mediaPlayer.play();
    }

}
