package org.example.blibliotecafx.Gestiones;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import org.example.blibliotecafx.DAO.AutorDAO;
import org.example.blibliotecafx.Entities.Autor;


import java.util.List;

public class GestionAutor {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtNacionalidad;

    @FXML
    private TableView<Autor> tablaAutores; // Tabla para mostrar los autores

    @FXML
    private TableColumn<Autor, String> colNombre;  // Columna para el nombre del autor

    @FXML
    private TableColumn<Autor, String> colNacionalidad;

    @FXML
    private Label lblResultado;


    // Método que se llama cuando se hace clic en "Añadir Autor"
    @FXML
    public void onAñadirAutor() {
        String nombre = txtNombre.getText();
        String nacionalidad = txtNacionalidad.getText();

        if (nombre.isEmpty() || nacionalidad.isEmpty()) {
            showAlert(AlertType.WARNING, "Campos Vacíos", "Por favor, complete todos los campos.");
            return;
        }

        Autor autor = new Autor();
        autor.setNombre(nombre);
        autor.setNacionalidad(nacionalidad);

        AutorDAO autorDAO = new AutorDAO();
        autorDAO.save(autor);

        showAlert(AlertType.INFORMATION, "Autor Añadido", "El autor ha sido añadido correctamente.");
    }

    // Método que se llama cuando se hace clic en "Modificar Autor"
    // Método para modificar un autor
    @FXML
    private void onModificarAutor() {
        // Verificar si hay un autor seleccionado en la tabla
        Autor autorSeleccionado = tablaAutores.getSelectionModel().getSelectedItem();

        if (autorSeleccionado == null) {
            System.out.println("Debes seleccionar un autor para modificarlo.");
            return;
        }
        

        // Obtener los nuevos valores de los campos de texto
        String nuevoNombre = txtNombre.getText();
        String nuevaNacionalidad = txtNacionalidad.getText();

        // Validar que los campos no estén vacíos
        if (nuevoNombre.isEmpty() || nuevaNacionalidad.isEmpty()) {
            System.out.println("El nombre y la nacionalidad no pueden estar vacíos.");
            return;
        }

        // Actualizar el autor con los nuevos valores
        autorSeleccionado.setNombre(nuevoNombre);
        autorSeleccionado.setNacionalidad(nuevaNacionalidad);

        // Guardar cambios en la base de datos
        AutorDAO autorDAO = new AutorDAO();
        autorDAO.update(autorSeleccionado);

        // Actualizar la tabla después de modificar
        tablaAutores.refresh();

        System.out.println("Autor modificado correctamente.");
    }



    // Método que se llama cuando se hace clic en "Eliminar Autor"
    @FXML
    private void onEliminarAutor() {
        String nombre = txtNombre.getText();

        if (nombre.isEmpty()) {
            showAlert(AlertType.WARNING, "Campo Vacío", "Por favor, ingrese el nombre del autor a eliminar.");
            return;
        }

        AutorDAO autorDAO = new AutorDAO();
        autorDAO.delete(nombre);

        showAlert(AlertType.INFORMATION, "Autor Eliminado", "El autor ha sido eliminado correctamente.");
    }

    @FXML
    private void initialize() {
        // Configurando las columnas de la tabla
        colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        colNacionalidad.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNacionalidad()));
    }
    // Método que se llama cuando se hace clic en "Buscar Autor"
    @FXML
    private void onBuscarAutor() {
        String nombre = txtNombre.getText(); // Obtener el nombre del autor

        if (nombre.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("El campo de nombre está vacío");
            alert.showAndWait();
            return;
        }

        // Llamar al DAO para buscar autores
        AutorDAO autorDAO = new AutorDAO();
        List<Autor> autores = autorDAO.findByName(nombre);

        if (autores.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Resultado de la búsqueda");
            alert.setHeaderText("No se encontró un autor con ese nombre.");
            alert.showAndWait();
        } else {
            // Mostrar los autores encontrados en la tabla
            tablaAutores.getItems().clear();  // Limpiar la tabla antes de agregar nuevos resultados
            tablaAutores.getItems().addAll(autores);
        }
    }


    // Método que se llama cuando se hace clic en "Listar Todos los Autores"
    // Método que se llama cuando se hace clic en "Listar Todos los Autores"
    @FXML
    private void onListarTodosLosAutores() {
        // Llamar al DAO para obtener todos los autores
        AutorDAO autorDAO = new AutorDAO();
        List<Autor> autores = autorDAO.findAll();

        // Mostrar todos los autores en la tabla
        tablaAutores.getItems().clear();  // Limpiar la tabla antes de agregar nuevos resultados
        tablaAutores.getItems().addAll(autores);
    }
    // Método para mostrar alertas
    private void showAlert(AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    @FXML
    private void limpiarCampos() {
        txtNombre.clear();
        txtNacionalidad.clear();
    }
}
