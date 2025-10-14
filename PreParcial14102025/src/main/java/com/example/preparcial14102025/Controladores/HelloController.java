package com.example.preparcial14102025.Controladores;

import com.example.preparcial14102025.Launcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class HelloController {
    @FXML
    private Pane panePrincipal;

    @FXML
    private Button btnInfo;

    @FXML
    private Button btnCrearProducto;

    @FXML
    private Label lblMenu;

    @FXML
    public void onInfo(ActionEvent event) {
        try {
            // Cargar el Dashboard usando la ruta correcta
            FXMLLoader loader = new FXMLLoader(Launcher.class.getResource("/co/edu/uniquindio/fx10/vista/Dashboard.fxml"));
            Parent dashboard = loader.load();

            // Obtener el controlador y cargar los productos
            DashboardController dashboardController = loader.getController();
            dashboardController.setMenuPrincipalController(this);
            dashboardController.cargarProductos();

            // Limpiar el panel y agregar la nueva vista
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
            // Cargar el Formulario usando la ruta correcta
            FXMLLoader loader = new FXMLLoader(Launcher.class.getResource("/co/edu/uniquindio/fx10/vista/FormularioProducto.fxml"));
            Parent formulario = loader.load();

            // Pasar la referencia del controlador del menú
            FormularioController formularioController = loader.getController();
            formularioController.setMenuprincipalController(this);

            // Limpiar el panel y agregar la nueva vista
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
     * Restaura el menú principal
     */
    public void restaurarMenuPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader(Launcher.class.getResource("/co/edu/uniquindio/fx10/vista/MenuPrincipal.fxml"));
            Parent menu = loader.load();

            panePrincipal.getChildren().clear();
            panePrincipal.getChildren().add(menu);

        } catch (IOException e) {
            mostrarAlerta("Error", "No se pudo restaurar el menú principal", Alert.AlertType.ERROR);
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
