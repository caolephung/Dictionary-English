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

//    @FXML
//    private void SoundButtonAction(ActionEvent event) {
//        // Đường dẫn đến file âm thanh
//        String path = "com/example/learningenglishapp/Sound/Cau-noi-ui-doi-oi-de-vl-sound-effect-bomman.mp3";
//        // Tạo đối tượng Media từ file âm thanh
//        Media media = new Media(new File(path).toURI().toString());
//
//        // Tạo đối tượng MediaPlayer từ Media
//        MediaPlayer mediaPlayer = new MediaPlayer(media);
//
//        // Bắt đầu phát âm thanh
//        mediaPlayer.play();
//    }
}
