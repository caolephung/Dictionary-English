module com.example.learningenglishapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;
    requires javafx.web;

    opens com.example.learningenglishapp to javafx.fxml;
    exports com.example.learningenglishapp;
}