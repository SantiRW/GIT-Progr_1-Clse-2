package com.example.preparcial14102025.Controladores;

import com.example.preparcial14102025.Launcher;
import com.example.preparcial14102025.Modelos.Moto;
import com.example.preparcial14102025.Repositorios.MotoRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.io.IOException;

public class FormularioController {

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtPrecio;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnCancelar;

    private MotoRepository moto;
    private HelloController menuPrincipalController;

    @FXML
    public void initialize() {
        moto = MotoRepository.getInstancia();
    }

    public void setMenuprincipalController(HelloController menuPrincipalController) {
        this.menuPrincipalController = menuPrincipalController;
    }

    @FXML
    private void onGuardarProducto() {
        if (!validarCampos()) {
            return;
        }

        try {
            String placa = txtCodigo.getText().trim();
            String marca = txtNombre.getText().trim();
            String modelo = txtDescripcion.getText().trim();
            String ano = txtPrecio.getText().trim();

            if (moto.buscarMotoPorPlaca(placa) != null) {
                mostrarAlerta("Error", "Ya existe una moto con esa placa", Alert.AlertType.ERROR);
                return;
            }

            Moto moto1 = new Moto(modelo, placa, marca, ano);
            moto.agregarMoto(moto1);

            mostrarAlerta("Éxito", "Moto creada correctamente", Alert.AlertType.INFORMATION);
            volverAlMenuPrincipal();

        } catch (Exception e) {
            mostrarAlerta("Error", "Error al guardar la moto: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void onCancelar() {
        volverAlMenuPrincipal();
    }

    private void volverAlMenuPrincipal() {
        if (menuPrincipalController != null) {
            menuPrincipalController.recargarMenuPrincipal();
        }
    }

    private boolean validarCampos() {
        if (txtCodigo.getText().trim().isEmpty()) {
            mostrarAlerta("Error de validación", "La placa es obligatoria", Alert.AlertType.WARNING);
            return false;
        }
        if (txtNombre.getText().trim().isEmpty()) {
            mostrarAlerta("Error de validación", "La marca es obligatoria", Alert.AlertType.WARNING);
            return false;
        }
        if (txtDescripcion.getText().trim().isEmpty()) {
            mostrarAlerta("Error de validación", "El modelo es obligatorio", Alert.AlertType.WARNING);
            return false;
        }
        if (txtPrecio.getText().trim().isEmpty()) {
            mostrarAlerta("Error de validación", "El año es obligatorio", Alert.AlertType.WARNING);
            return false;
        }
        return true;
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}