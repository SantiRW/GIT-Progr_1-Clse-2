package com.example.preparcial14102025.Controladores;

import com.example.preparcial14102025.Launcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Pane panePrincipal;

    @FXML
    private Button btnInfo;

    @FXML
    private Button btnCrearProducto;

    @FXML
    private VBox contenedorPrincipal;

    @FXML
    public void onInfo(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(Launcher.class.getResource("/com/example/preparcial14102025/Dashboard.fxml"));
            Parent dashboard = loader.load();

            DashboardController dashboardController = loader.getController();
            dashboardController.setMenuPrincipalController(this);
            dashboardController.cargarProductos();

            panePrincipal.getChildren().clear();
            panePrincipal.getChildren().add(dashboard);

        } catch (IOException e) {
            mostrarAlerta("Error", "No se pudo cargar el Dashboard: " + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    public void onCrearProducto(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(Launcher.class.getResource("/com/example/preparcial14102025/Formulario.fxml"));
            Parent formulario = loader.load();

            FormularioController formularioController = loader.getController();
            formularioController.setMenuprincipalController(this);

            panePrincipal.getChildren().clear();
            panePrincipal.getChildren().add(formulario);

        } catch (IOException e) {
            mostrarAlerta("Error", "No se pudo cargar el Formulario: " + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    public Pane getPanePrincipal() {
        return panePrincipal;
    }

    /**
     * Recarga completamente la escena del menú principal
     */
    public void recargarMenuPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader(Launcher.class.getResource("/com/example/preparcial14102025/Menu.fxml"));
            Parent nuevoMenu = loader.load();

            // Obtener el Stage actual y reemplazar la escena
            Stage stage = (Stage) panePrincipal.getScene().getWindow();
            Scene scene = new Scene(nuevoMenu, 800, 600);
            stage.setScene(scene);

        } catch (IOException e) {
            mostrarAlerta("Error", "No se pudo recargar el menú principal", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}