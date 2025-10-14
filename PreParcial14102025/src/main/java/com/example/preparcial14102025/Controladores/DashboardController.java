package com.example.preparcial14102025.Controladores;

import com.example.preparcial14102025.Launcher;
import com.example.preparcial14102025.Modelos.Moto;
import com.example.preparcial14102025.Repositorios.MotoRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class DashboardController {


    @FXML
    private VBox contenedorPrincipal;

    @FXML
    private Label lblTitulo;

    @FXML
    private TableView<Moto> tablaMotos;

    @FXML
    private TableColumn<Moto, String> colMarca;

    @FXML
    private TableColumn<Moto, String> colModelo;

    @FXML
    private TableColumn<Moto, String> colPlaca;

    @FXML
    private TableColumn<Moto, String> colAño;

    @FXML
    private Button btnAñadirMoto;

    @FXML
    private Button btnRegresar;

    private MotoRepository moto;
    private ObservableList<Moto> listaMotos;
    private HelloController menuPrincipalController;

    @FXML
    public void initialize() {
        moto = MotoRepository.getInstancia();

        // Configurar las columnas de la tabla
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colAño.setCellValueFactory(new PropertyValueFactory<>("año"));

        // Formatear la columna de precio
        colModelo.setCellFactory(column -> new TableCell<Moto, String>() {
            @Override
            protected void updateItem(String modelo, boolean empty) {
                super.updateItem(modelo, empty);
                if (empty || modelo == null) {
                    setText(null);
                } else {
                    setText(String.format("$%.2f", modelo));
                }
            }
        });

        // Cargar los productos
        cargarProductos();
    }

    /**
     * Establece la referencia al controlador del menú principal
     */
    public void setMenuPrincipalController(HelloController menuPrincipalController) {
        this.menuPrincipalController = menuPrincipalController;
    }

    /**
     * Carga los productos en la tabla
     */
    public void cargarProductos() {
        listaMotos = FXCollections.observableArrayList(moto.getMotos());
        tablaMotos.setItems(listaMotos);
    }

    /**
     * Maneja el evento de click en el botón "Eliminar"
     */
    @FXML
    private void onEliminarProducto() {
        Moto motoSeleccionada = tablaMotos.getSelectionModel().getSelectedItem();

        if (motoSeleccionada == null) {
            mostrarAlerta("Advertencia", "Por favor seleccione una moto para eliminar", Alert.AlertType.WARNING);
            return;
        }

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar eliminación");
        confirmacion.setHeaderText("¿Está seguro de eliminar el producto?");
        confirmacion.setContentText("Producto: " + motoSeleccionada.getPlaca());

        confirmacion.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                moto.eliminarMoto(motoSeleccionada);
                cargarProductos();
                mostrarAlerta("Éxito", "Producto eliminado correctamente", Alert.AlertType.INFORMATION);
            }
        });
    }

    /**
     * Regresa al menú principal
     */
    @FXML
    private void onRegresar() {
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
