module com.example.preparcial14102025 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires javafx.graphics;
    requires javafx.base;

    opens com.example.preparcial14102025 to javafx.fxml;
    exports com.example.preparcial14102025;
    exports com.example.preparcial14102025.Repositorios;
    opens com.example.preparcial14102025.Repositorios to javafx.fxml;
    exports com.example.preparcial14102025.Controladores;
    opens com.example.preparcial14102025.Controladores to javafx.fxml;
}