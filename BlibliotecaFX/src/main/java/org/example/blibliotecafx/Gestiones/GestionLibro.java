package org.example.blibliotecafx.Gestiones;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.example.blibliotecafx.DAO.LibroDAO;
import org.example.blibliotecafx.Entities.Autor;
import org.example.blibliotecafx.Entities.Libro;

import java.util.List;

public class GestionLibro {

    @FXML
    private TextField txtTitulo;

    @FXML
    private TextField txtAutor;

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



    // Método que se llama cuando se hace clic en "Añadir Socio"
    @FXML
    public void onAñadirLibro() {
        String titulo = txtTitulo.getText();
        String isbn = txtIsbn.getText();
        String editorial = txtEditorial.getText();
       // String autor = txtAutor.getText();
        Integer anioPublicacion = Integer.parseInt(txtAnioPublicacion.getText());


        if (titulo.isEmpty() || isbn.isEmpty() || editorial.isEmpty()  || anioPublicacion.intValue() == 0) {
            showAlert(Alert.AlertType.WARNING, "Campos Vacíos", "Por favor, complete todos los campos.");
            return;
        }

        Libro libro = new Libro();
       // Autor a = new Autor();
        libro.setTitulo(titulo);
        libro.setIsbn(isbn);
        libro.setEditorial(editorial);
        // libro.setAutor(a.getNombre());
        libro.setAnioPublicacion(anioPublicacion);

        LibroDAO libroDAO = new LibroDAO();
        libroDAO.save(libro);

        showAlert(Alert.AlertType.INFORMATION, "Libro Añadido", "El libro ha sido añadido correctamente.");
    }


    @FXML
    private void onModificarLibro() {
        // Verificar si hay un autor seleccionado en la tabla
        Libro libroSeleccionado = tablaLibros.getSelectionModel().getSelectedItem();

        if (libroSeleccionado == null) {
            System.out.println("Debes seleccionar un libro para modificarlo.");
            return;
        }


        // Obtener los nuevos valores de los campos de texto
      //  String nuevoAutor = txtAutor.getText();
        String nuevoTitulo = txtTitulo.getText();
        String nuevoIsbn = txtIsbn.getText();
        String nuevaEditorial = txtEditorial.getText();
        Integer nuevoAnioPublicacion = Integer.parseInt(txtAnioPublicacion.getText());

        // Validar que los campos no estén vacíos
        if (nuevoTitulo.isEmpty() || nuevoIsbn.isEmpty() || nuevaEditorial.isEmpty()|| nuevoAnioPublicacion.intValue() == 0) {
            System.out.println("El titulo , el telefono/ la editorial y el año no pueden estar vacíos.");
            return;
        }

        // Actualizar el autor con los nuevos valores
        libroSeleccionado.setTitulo(nuevoTitulo);
       // libroSeleccionado.setAutor(nuevoAutor);
        libroSeleccionado.setIsbn(nuevoIsbn);
        libroSeleccionado.setEditorial(nuevaEditorial);
        libroSeleccionado.setAnioPublicacion(nuevoAnioPublicacion);

        // Guardar cambios en la base de datos
        LibroDAO libroDAO = new LibroDAO();
        libroDAO.update(libroSeleccionado);

        // Actualizar la tabla después de modificar
        tablaLibros.refresh();

        System.out.println("Libro modificado correctamente.");
    }



    // Método que se llama cuando se hace clic en "Eliminar Autor"
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
    }

    @FXML
    private void initialize() {
        // Configurando las columnas de la tabla
        colTitulo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitulo()));
        colIsbn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIsbn()));
        //colAutor.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEditorial()));
        colEditorial.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEditorial()));
        colAnioPublicacion.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getAnioPublicacion()).asObject());

    }
    // Método que se llama cuando se hace clic en "Buscar Autor"
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
        }
    }


    // Método que se llama cuando se hace clic en "Listar Todos los Autores"
    // Método que se llama cuando se hace clic en "Listar Todos los Autores"
    @FXML
    private void onListarTodosLosLibros() {
        // Llamar al DAO para obtener todos los autores
        LibroDAO libroDAO = new LibroDAO();
        List<Libro> libros = libroDAO.findAll();

        // Mostrar todos los autores en la tabla
        tablaLibros.getItems().clear();  // Limpiar la tabla antes de agregar nuevos resultados
        tablaLibros.getItems().addAll(libros);
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
