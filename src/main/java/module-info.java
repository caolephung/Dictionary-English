module com.example.learningenglishapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;
    requires javafx.web;
    requires javafx.media;
    requires json.simple;
    requires kotlin.stdlib;
    requires java.desktop;
    requires java.sql;

    opens AppLearning to javafx.fxml;
    exports AppLearning;
}