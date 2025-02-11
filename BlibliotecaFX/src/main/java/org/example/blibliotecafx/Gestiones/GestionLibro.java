package org.example.blibliotecafx.Gestiones;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.blibliotecafx.Entities.Libro;
import org.example.blibliotecafx.Service.LibroService;

import java.util.List;

public class GestionLibro {

    @FXML
    private TextField txtTitulo;

    @FXML
    private TextField txtIsbn;

    @FXML
    private TextField txtEditorial;

    @FXML
    private TextField txtAnioPublicacion;

    @FXML
    private TableView<Libro> tablaLibros;

    @FXML
    private TableColumn<Libro, String> colTitulo;

    @FXML
    private TableColumn<Libro, String> colIsbn;

    @FXML
    private TableColumn<Libro, String> colEditorial;

    @FXML
    private TableColumn<Libro, String> colAnioPublicacion;

    @FXML
    private Label lblResultado;

    private LibroService libroService = new LibroService();

    // Método que se llama cuando se hace clic en "Añadir Libro"
    @FXML
    public void onAñadirLibro() {
        String titulo = txtTitulo.getText();
        String isbn = txtIsbn.getText();
        String editorial = txtEditorial.getText();
        String anioPublicacionStr = txtAnioPublicacion.getText();

        if (titulo.isEmpty() || isbn.isEmpty() || editorial.isEmpty() || anioPublicacionStr.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Campos Vacíos", "Por favor, complete todos los campos.");
            return;
        }

        int anioPublicacion = Integer.parseInt(anioPublicacionStr);

        Libro libro = new Libro();
        libro.setTitulo(titulo);
        libro.setIsbn(isbn);
        libro.setEditorial(editorial);
        libro.setAnioPublicacion(anioPublicacion);

        libroService.agregarLibro(libro);

        showAlert(Alert.AlertType.INFORMATION, "Libro Añadido", "El libro ha sido añadido correctamente.");
    }

    // Método que se llama cuando se hace clic en "Modificar Libro"
    @FXML
    private void onModificarLibro() {
        Libro libroSeleccionado = tablaLibros.getSelectionModel().getSelectedItem();

        if (libroSeleccionado == null) {
            showAlert(Alert.AlertType.WARNING, "Selección Vacía", "Por favor, selecciona un libro para modificar.");
            return;
        }

        String nuevoTitulo = txtTitulo.getText();
        String nuevoIsbn = txtIsbn.getText();
        String nuevaEditorial = txtEditorial.getText();
        String nuevoAnioPublicacionStr = txtAnioPublicacion.getText();

        if (nuevoTitulo.isEmpty() || nuevoIsbn.isEmpty() || nuevaEditorial.isEmpty() || nuevoAnioPublicacionStr.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Campos Vacíos", "Por favor, complete todos los campos.");
            return;
        }

        int nuevoAnioPublicacion = Integer.parseInt(nuevoAnioPublicacionStr);

        libroSeleccionado.setTitulo(nuevoTitulo);
        libroSeleccionado.setIsbn(nuevoIsbn);
        libroSeleccionado.setEditorial(nuevaEditorial);
        libroSeleccionado.setAnioPublicacion(nuevoAnioPublicacion);

        libroService.actualizarLibro(libroSeleccionado);

        tablaLibros.refresh();

        showAlert(Alert.AlertType.INFORMATION, "Libro Modificado", "El libro ha sido modificado correctamente.");
    }

    // Método que se llama cuando se hace clic en "Eliminar Libro"
    @FXML
    private void onEliminarLibro() {
        Libro libroSeleccionado = tablaLibros.getSelectionModel().getSelectedItem();

        if (libroSeleccionado == null) {
            showAlert(Alert.AlertType.WARNING, "Selección Vacía", "Por favor, selecciona un libro para eliminar.");
            return;
        }

        libroService.eliminarLibro(libroSeleccionado);

        tablaLibros.getItems().remove(libroSeleccionado);

        showAlert(Alert.AlertType.INFORMATION, "Libro Eliminado", "El libro ha sido eliminado correctamente.");
    }

    // Método que se llama cuando se hace clic en "Buscar Libro"
    @FXML
    private void onBuscarLibro() {
        String titulo = txtTitulo.getText();

        if (titulo.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Campo Vacío", "Por favor, ingrese el título del libro.");
            return;
        }

        List<Libro> libros = libroService.buscarLibrosPorTitulo(titulo);

        if (libros.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "Resultado de la Búsqueda", "No se encontraron libros con ese título.");
        } else {
            tablaLibros.getItems().clear();
            tablaLibros.getItems().addAll(libros);
        }
    }

    // Método que se llama cuando se hace clic en "Listar Todos los Libros"
    @FXML
    private void onListarTodosLosLibros() {
        List<Libro> libros = libroService.listarLibrosDisponibles();

        tablaLibros.getItems().clear();
        tablaLibros.getItems().addAll(libros);
    }

    // Inicializar las columnas de la tabla
    @FXML
    private void initialize() {
        colTitulo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitulo()));
        colIsbn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIsbn()));
        colEditorial.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEditorial()));
        colAnioPublicacion.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getAnioPublicacion())));
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
