package org.example.blibliotecafx.Gestiones;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.blibliotecafx.DAO.AutorDAO;
import org.example.blibliotecafx.DAO.LibroDAO;
import org.example.blibliotecafx.Entities.Autor;
import org.example.blibliotecafx.Entities.Libro;

import java.util.List;

public class GestionLibro {

    @FXML
    private TextField txtTitulo;

    @FXML
    private ComboBox<Autor> comboAutor;  // ComboBox para seleccionar autor

    @FXML
    private TextField txtIsbn;

    @FXML
    private TextField txtEditorial;

    @FXML
    private TextField txtAnioPublicacion;

    @FXML
    private TextField txtPrestado;

    @FXML
    private TableView<Libro> tablaLibros;

    @FXML
    private TableColumn<Libro, String> colTitulo;

    @FXML
    private TableColumn<Libro, String> colIsbn;

    @FXML
    private TableColumn<Libro, String> colEditorial;

    @FXML
    private TableColumn<Libro, String> colAutor;

    @FXML
    private TableColumn<Libro, Integer> colAnioPublicacion;

    @FXML
    private TableColumn<Libro, Boolean> colPrestado;

    // Método para cargar autores en el ComboBox
    @FXML
    private void initialize() {
        // Configurando las columnas de la tabla
        colTitulo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitulo()));
        colIsbn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIsbn()));
        colEditorial.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEditorial()));
        colAnioPublicacion.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getAnioPublicacion()).asObject());

        // Cargar autores en el ComboBox
        AutorDAO autorDAO = new AutorDAO();
        List<Autor> autores = autorDAO.findAll();
        comboAutor.getItems().addAll(autores);

        // Establecer cómo se debe mostrar el nombre del autor en el ComboBox
        comboAutor.setCellFactory(param -> new ListCell<Autor>() {
            @Override
            protected void updateItem(Autor item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getNombre());  // Muestra el nombre del autor
                }
            }
        });

        // Configuración de la columna para mostrar el nombre del autor en la tabla
        colAutor.setCellValueFactory(cellData -> {
            // Obtener el nombre del autor asociado al libro
            Autor autor = cellData.getValue().getAutor();
            return new SimpleStringProperty(autor != null ? autor.getNombre() : "Desconocido");
        });
    }



    @FXML
    private void onBuscarLibro() {
        String titulo = txtTitulo.getText();
        /// String autor = txtAutor.getText();
        String isbn = txtIsbn.getText();
        // Obtener el nombre del autor

        if (titulo.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("El campo de titulo está vacío");
            alert.showAndWait();
            return;

        }

        // Llamar al DAO para buscar autores
        LibroDAO libroDAO = new LibroDAO();
        List<Libro> libros = libroDAO.findByTitulo(titulo);

        // LibroDAO libro1DAO = new LibroDAO();
        // List<Libro> libros1 = libroDAO.findByAutor(autor);

        LibroDAO libro2DAO = new LibroDAO();
        List<Libro> libros2 = libroDAO.findByIsbn(isbn);


        if (libros.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Resultado de la búsqueda");
            alert.setHeaderText("No se encontró un libro con ese nombre.");
            alert.showAndWait();
        } else {
            // Mostrar los autores encontrados en la tabla
            tablaLibros.getItems().clear();  // Limpiar la tabla antes de agregar nuevos resultados
            tablaLibros.getItems().addAll(libros);
            limpiarCampos();
        }
    }

    @FXML
    private void limpiarCampos() {
        txtTitulo.clear();
        txtIsbn.clear();
        txtEditorial.clear();
        txtAnioPublicacion.clear();
        comboAutor.getSelectionModel().clearSelection();  // Limpiar selección del ComboBox
    }

    @FXML
    public void onAñadirLibro() {
        String titulo = txtTitulo.getText();
        String isbn = txtIsbn.getText();
        String editorial = txtEditorial.getText();
        Integer anioPublicacion = Integer.parseInt(txtAnioPublicacion.getText());
        Autor autor = comboAutor.getValue();  // Obtener el autor seleccionado desde el ComboBox

        if (titulo.isEmpty() || isbn.isEmpty() || editorial.isEmpty() || anioPublicacion == 0 || autor == null) {
            showAlert(Alert.AlertType.WARNING, "Campos Vacíos", "Por favor, complete todos los campos.");
            return;
        }

        // Crear y configurar el libro
        Libro libro = new Libro();
        libro.setTitulo(titulo);
        libro.setIsbn(isbn);
        libro.setEditorial(editorial);
        libro.setAnioPublicacion(anioPublicacion);
        libro.setAutor(autor);  // Asocia el autor al libro

        // Guardar el libro en la base de datos
        LibroDAO libroDAO = new LibroDAO();
        libroDAO.save(libro);
        showAlert(Alert.AlertType.INFORMATION, "Libro Añadido", "El libro ha sido añadido correctamente.");

        limpiarCampos();
    }

    @FXML
    private void onModificarLibro() {
        // Verificar si hay un libro seleccionado en la tabla
        Libro libroSeleccionado = tablaLibros.getSelectionModel().getSelectedItem();

        if (libroSeleccionado == null) {
            System.out.println("Debes seleccionar un libro para modificarlo.");
            return;
        }

        // Obtener los nuevos valores de los campos de texto
        String nuevoTitulo = txtTitulo.getText();
        String nuevoIsbn = txtIsbn.getText();
        String nuevaEditorial = txtEditorial.getText();
        Integer nuevoAnioPublicacion = Integer.parseInt(txtAnioPublicacion.getText());
        Autor nuevoAutor = comboAutor.getValue();  // Obtener el nuevo autor seleccionado

        // Validar que los campos no estén vacíos
        if (nuevoTitulo.isEmpty() || nuevoIsbn.isEmpty() || nuevaEditorial.isEmpty() || nuevoAnioPublicacion == 0 || nuevoAutor == null) {
            System.out.println("El titulo, el ISBN, la editorial, el año y el autor no pueden estar vacíos.");
            return;
        }

        // Actualizar los valores del libro
        libroSeleccionado.setTitulo(nuevoTitulo);
        libroSeleccionado.setIsbn(nuevoIsbn);
        libroSeleccionado.setEditorial(nuevaEditorial);
        libroSeleccionado.setAnioPublicacion(nuevoAnioPublicacion);
        libroSeleccionado.setAutor(nuevoAutor);  // Actualizar el autor

        // Guardar cambios en la base de datos
        LibroDAO libroDAO = new LibroDAO();
        libroDAO.update(libroSeleccionado);

        // Actualizar la tabla después de modificar
        tablaLibros.refresh();

        System.out.println("Libro modificado correctamente.");
        limpiarCampos();
    }

    @FXML
    private void onEliminarLibro() {
        String titulo = txtTitulo.getText();

        if (titulo.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Campo Vacío", "Por favor, ingrese el nombre del libro a eliminar.");
            return;
        }

        LibroDAO libroDAO = new LibroDAO();
        libroDAO.delete(libroDAO.findByTitulo(titulo).get(0));

        showAlert(Alert.AlertType.INFORMATION, "Libro Eliminado", "El libro ha sido eliminado correctamente.");

        limpiarCampos();
    }

    @FXML
    private void onListarTodosLosLibros() {
        // Llamar al DAO para obtener todos los libros
        LibroDAO libroDAO = new LibroDAO();
        List<Libro> libros = libroDAO.findAll();

        // Mostrar todos los libros en la tabla
        tablaLibros.getItems().clear();  // Limpiar la tabla antes de agregar nuevos resultados
        tablaLibros.getItems().addAll(libros);
        limpiarCampos();
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
