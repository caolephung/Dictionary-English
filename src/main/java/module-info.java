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
    requires freetts;
    requires voicerss.tts;

    opens com.example.learningenglishapp to javafx.fxml;
    exports com.example.learningenglishapp;
}