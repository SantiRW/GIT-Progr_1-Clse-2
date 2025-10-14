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
    private TextField txtPLaca;

    @FXML
    private TextField txtMarca;

    @FXML
    private TextField txtModelo;

    @FXML
    private TextField txtAño;

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

    /**
     * Establece el controlador del Menu para poder regresar
     */
    public void setMenuprincipalController(HelloController menuPrincipalController) {
        this.menuPrincipalController = menuPrincipalController;
    }

    /**
     * Maneja el evento de guardar producto
     */
    @FXML
    private void onGuardarProducto() {
        if (!validarCampos()) {
            return;
        }

        try {
            String placa = txtPLaca.getText().trim();
            String marca = txtMarca.getText().trim();
            String modelo = txtModelo.getText().trim();
            String ano  = txtAño.getText().trim();

            // Verificar si el código ya existe
            if (moto.buscarMotoPorPlaca(placa) != null) {
                mostrarAlerta("Error", "Ya existe un producto con ese código", Alert.AlertType.ERROR);
                return;
            }

            // Crear y guardar el producto
            Moto moto1 = new Moto(modelo, ano, placa, marca);
            moto.agregarMoto(moto1);

            mostrarAlerta("Éxito", "Producto creado correctamente", Alert.AlertType.INFORMATION);

            // Volver al Menu Principal
            volverAlMenuPrincipal();

        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "El precio y stock deben ser valores numéricos válidos", Alert.AlertType.ERROR);
        }
    }

    /**
     * Maneja el evento de cancelar
     */
    @FXML
    private void onCancelar() {
        volverAlMenuPrincipal();
    }

    /**
     * Vuelve a mostrar el menú principal
     */
    private void volverAlMenuPrincipal() {
        if (menuPrincipalController != null) {
            try {
                FXMLLoader loader = new FXMLLoader(Launcher.class.getResource("/co/edu/uniquindio/fx10/vista/MenuPrincipal.fxml"));
                Parent menu = loader.load();

                menuPrincipalController.getPanePrincipal().getChildren().clear();
                menuPrincipalController.getPanePrincipal().getChildren().add(menu);

            } catch (IOException e) {
                mostrarAlerta("Error", "No se pudo volver al menú principal", Alert.AlertType.ERROR);
                e.printStackTrace();
            }
        }
    }

    /**
     * Valida que los campos del formulario estén completos
     */
    private boolean validarCampos() {
        if (txtModelo.getText().trim().isEmpty()) {
            mostrarAlerta("Error de validación", "El código es obligatorio", Alert.AlertType.WARNING);
            return false;
        }
        if (txtMarca.getText().trim().isEmpty()) {
            mostrarAlerta("Error de validación", "El nombre es obligatorio", Alert.AlertType.WARNING);
            return false;
        }
        if (txtPLaca.getText().trim().isEmpty()) {
            mostrarAlerta("Error de validación", "La descripción es obligatoria", Alert.AlertType.WARNING);
            return false;
        }
        if (txtAño.getText().trim().isEmpty()) {
            mostrarAlerta("Error de validación", "El precio es obligatorio", Alert.AlertType.WARNING);
            return false;
        }
        return true;
    }

    /**
     * Muestra una alerta al usuario
     */
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}

