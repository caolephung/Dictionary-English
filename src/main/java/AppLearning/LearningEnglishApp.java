package AppLearning;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LearningEnglishApp extends Application {
    @FXML
    public void start(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/AppView/Welcome_view.fxml"));
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Learning English App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}