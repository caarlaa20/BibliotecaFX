package org.example.blibliotecafx.Gestiones;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.blibliotecafx.DAO.SocioDAO;
import org.example.blibliotecafx.Entities.Socio;

import java.util.List;

public class GestioSocio {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtDireccion;
    @FXML
    private TableView<Socio> tablaSocios;

    @FXML
    private TableColumn<Socio, String> colNombre;

    @FXML
    private TableColumn<Socio, String> colTelefono;

    @FXML
    private TableColumn<Socio, String> colDireccion;





    // Método que se llama cuando se hace clic en "Añadir Socio"
    @FXML
    public void onAñadirSocio() {
        String nombre = txtNombre.getText();
        String telefono = txtTelefono.getText();
        String direccion = txtDireccion.getText();

        if (nombre.isEmpty() || telefono.isEmpty() || direccion.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Campos Vacíos", "Por favor, complete todos los campos.");
            return;
        }

        Socio socio = new Socio();
        socio.setNombre(nombre);
        socio.setTelefono(telefono);
        socio.setDireccion(direccion);

        SocioDAO socioDAO = new SocioDAO();
        socioDAO.save(socio);

        showAlert(Alert.AlertType.INFORMATION, "Socio Añadido", "El socio ha sido añadido correctamente.");
        limpiarCampos();
    }

    // Método que se llama cuando se hace clic en "Modificar Autor"
    // Método para modificar un autor
    @FXML
    private void onModificarSocio() {
        // Verificar si hay un autor seleccionado en la tabla
        Socio socioSeleccionado = tablaSocios.getSelectionModel().getSelectedItem();

        if (socioSeleccionado == null) {
            System.out.println("Debes seleccionar un socio para modificarlo.");
            return;
        }


        // Obtener los nuevos valores de los campos de texto
        String nuevoNombre = txtNombre.getText();
        String nuevoTelefono = txtTelefono.getText();
        String nuevoDireccion = txtDireccion.getText();

        // Validar que los campos no estén vacíos
        if (nuevoNombre.isEmpty() || nuevoTelefono.isEmpty() || nuevoDireccion.isEmpty()) {
            System.out.println("El nombre y el telefono no pueden estar vacíos.");
            return;
        }

        // Actualizar el autor con los nuevos valores
        socioSeleccionado.setNombre(nuevoNombre);
        socioSeleccionado.setTelefono(nuevoTelefono);
        socioSeleccionado.setDireccion(nuevoDireccion);

        // Guardar cambios en la base de datos
        SocioDAO socioDAO = new SocioDAO();
        socioDAO.update(socioSeleccionado);

        // Actualizar la tabla después de modificar
        tablaSocios.refresh();

        System.out.println("Socio modificado correctamente.");

        limpiarCampos();
    }



    // Método que se llama cuando se hace clic en "Eliminar Autor"
    @FXML
    private void onEliminarSocio() {
        String nombre = txtNombre.getText();

        if (nombre.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Campo Vacío", "Por favor, ingrese el nombre del socio a eliminar.");
            return;
        }

        SocioDAO socioDAO = new SocioDAO();
        socioDAO.delete(nombre);

        showAlert(Alert.AlertType.INFORMATION, "Socio Eliminado", "El socio ha sido eliminado correctamente.");
        limpiarCampos();
    }

    @FXML
    private void initialize() {
        // Configurando las columnas de la tabla
        colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        colTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefono()));
        colDireccion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDireccion()));

    }
    // Método que se llama cuando se hace clic en "Buscar Autor"
    @FXML
    private void onBuscarSocio() {
        String nombre = txtNombre.getText(); // Obtener el nombre del autor

        if (nombre.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("El campo de nombre está vacío");
            alert.showAndWait();
            return;
        }

        // Llamar al DAO para buscar autores
        SocioDAO socioDAO = new SocioDAO();
        List<Socio> socios = socioDAO.findByName(nombre);

        if (socios.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Resultado de la búsqueda");
            alert.setHeaderText("No se encontró un socio con ese nombre.");
            alert.showAndWait();
        } else {
            // Mostrar los autores encontrados en la tabla
            tablaSocios.getItems().clear();  // Limpiar la tabla antes de agregar nuevos resultados
            tablaSocios.getItems().addAll(socios);
        }
        limpiarCampos();
    }


    // Método que se llama cuando se hace clic en "Listar Todos los Autores"
    // Método que se llama cuando se hace clic en "Listar Todos los Autores"
    @FXML
    private void onListarTodosLosSocios() {
        // Llamar al DAO para obtener todos los autores
        SocioDAO socioDAO = new SocioDAO();
        List<Socio> socios = socioDAO.findAll();

        // Mostrar todos los autores en la tabla
        tablaSocios.getItems().clear();  // Limpiar la tabla antes de agregar nuevos resultados
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
    @FXML
    private void limpiarCampos() {
        txtNombre.clear();
        txtTelefono.clear();
        txtDireccion.clear();
    }

}
