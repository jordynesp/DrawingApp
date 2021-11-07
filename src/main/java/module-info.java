module com.example.a3 {
    requires javafx.controls;
    requires javafx.fxml;


    exports com.example.a3.views;
    opens com.example.a3.views to javafx.fxml;
    exports com.example.a3.models;
    opens com.example.a3.models to javafx.fxml;
    exports com.example.a3.controllers;
    opens com.example.a3.controllers to javafx.fxml;
    exports com.example.a3.application;
    opens com.example.a3.application to javafx.fxml;
}