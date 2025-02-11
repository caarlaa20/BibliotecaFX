package org.example.blibliotecafx.Gestiones;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.blibliotecafx.DAO.SocioDAO;
import org.example.blibliotecafx.Entities.Socio;

import java.util.List;

public class GestionSocio {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TableView<Socio> tablaSocios;

    @FXML
    private TableColumn<Socio, String> colNombre;

    @FXML
    private TableColumn<Socio, String> colTelefono;

    @FXML
    private Label lblResultado;

    @FXML
    private void initialize() {
        // Configuración de columnas de la tabla
        colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        colTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefono()));
        listarSocios();  // Listar socios al inicializar
    }

    // Añadir socio
    @FXML
    public void onAñadirSocio() {
        String nombre = txtNombre.getText();
        String telefono = txtTelefono.getText();

        if (nombre.isEmpty() || telefono.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Campos Vacíos", "Por favor, complete todos los campos.");
            return;
        }

        Socio socio = new Socio();
        socio.setNombre(nombre);
        socio.setTelefono(telefono);

        SocioDAO socioDAO = new SocioDAO();
        socioDAO.save(socio);

        showAlert(Alert.AlertType.INFORMATION, "Socio Añadido", "El socio ha sido añadido correctamente.");
        listarSocios();
    }

    // Modificar socio
    @FXML
    private void onModificarSocio() {
        Socio socioSeleccionado = tablaSocios.getSelectionModel().getSelectedItem();

        if (socioSeleccionado == null) {
            showAlert(Alert.AlertType.WARNING, "Selección de Socio", "Por favor, seleccione un socio para modificar.");
            return;
        }

        String nuevoNombre = txtNombre.getText();
        String nuevoTelefono = txtTelefono.getText();

        if (nuevoNombre.isEmpty() || nuevoTelefono.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Campos Vacíos", "Por favor, complete todos los campos.");
            return;
        }

        socioSeleccionado.setNombre(nuevoNombre);
        socioSeleccionado.setTelefono(nuevoTelefono);

        SocioDAO socioDAO = new SocioDAO();
        socioDAO.update(socioSeleccionado);

        showAlert(Alert.AlertType.INFORMATION, "Socio Modificado", "El socio ha sido modificado correctamente.");
        listarSocios();
    }

    // Eliminar socio
    @FXML
    private void onEliminarSocio() {
        Socio socioSeleccionado = tablaSocios.getSelectionModel().getSelectedItem();

        if (socioSeleccionado == null) {
            showAlert(Alert.AlertType.WARNING, "Selección de Socio", "Por favor, seleccione un socio para eliminar.");
            return;
        }

        SocioDAO socioDAO = new SocioDAO();
        socioDAO.delete(socioSeleccionado);

        showAlert(Alert.AlertType.INFORMATION, "Socio Eliminado", "El socio ha sido eliminado correctamente.");
        listarSocios();
    }

    // Buscar socio
    @FXML
    private void onBuscarSocio() {
        String nombre = txtNombre.getText();

        if (nombre.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Campo Vacío", "Por favor, ingrese un nombre para buscar.");
            return;
        }

        SocioDAO socioDAO = new SocioDAO();
        List<Socio> socios = socioDAO.findByName(nombre);

        if (socios.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "Búsqueda sin Resultados", "No se encontraron socios con ese nombre.");
        } else {
            tablaSocios.getItems().clear();
            tablaSocios.getItems().addAll(socios);
        }
    }

    // Listar todos los socios
    @FXML
    private void onListarTodosLosSocios() {
        listarSocios();
    }

    // Actualizar la tabla con todos los socios
    private void listarSocios() {
        SocioDAO socioDAO = new SocioDAO();
        List<Socio> socios = socioDAO.findAll();
        tablaSocios.getItems().clear();
        tablaSocios.getItems().addAll(socios);
    }

    // Método para mostrar alertas
    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
