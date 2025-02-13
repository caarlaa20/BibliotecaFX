package org.example.blibliotecafx.Gestiones;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.blibliotecafx.DAO.LibroDAO;
import org.example.blibliotecafx.DAO.SocioDAO;
import org.example.blibliotecafx.DAO.PrestamoDAO;
import org.example.blibliotecafx.Entities.Prestamo;
import org.example.blibliotecafx.Entities.Socio;
import org.example.blibliotecafx.Entities.Libro;

import java.util.List;

public class GestionPrestamo {

    @FXML
    private ComboBox<Socio> comboSocio;

    @FXML
    private ComboBox<Libro> comboLibro;

    @FXML
    private DatePicker fechaPrestamo;

    @FXML
    private DatePicker fechaDevolucion;

    @FXML
    private TableView<Prestamo> tablaPrestamos;

    @FXML
    private TableColumn<Prestamo, String> colSocio;

    @FXML
    private TableColumn<Prestamo, String> colLibro;

    @FXML
    private TableColumn<Prestamo, String> colFechaPrestamo;

    @FXML
    private TableColumn<Prestamo, String> colFechaDevolucion;

    private PrestamoDAO prestamoDAO = new PrestamoDAO();
    private SocioDAO socioDAO = new SocioDAO();
    private LibroDAO libroDAO = new LibroDAO();

    // Cargar todos los préstamos activos
    @FXML
    private void cargarPrestamos() {
        List<Prestamo> prestamos = prestamoDAO.findPrestamosActivos();
        System.out.println("Préstamos activos encontrados: " + prestamos.size());
        for (Prestamo p : prestamos) {
            System.out.println("Socio: " + p.getSocio().getNombre() + ", Libro: " + p.getLibro().getTitulo());
        }
        tablaPrestamos.getItems().clear();
        tablaPrestamos.getItems().addAll(prestamos);
    }


    // Cargar los socios y libros en los ComboBox
    private void cargarComboBoxes() {
        // Cargar socios en el ComboBox
        List<Socio> socios = socioDAO.findAll();
        comboSocio.getItems().clear();
        comboSocio.getItems().addAll(socios);

        // Cargar libros en el ComboBox
        List<Libro> libros = libroDAO.findAll();
        comboLibro.getItems().clear();
        comboLibro.getItems().addAll(libros);
    }

    // Registrar un nuevo préstamo
    @FXML
    public void onRegistrarPrestamo() {
        Socio socio = comboSocio.getValue();
        Libro libro = comboLibro.getValue();
        if (socio != null && libro != null && fechaPrestamo.getValue() != null) {
            Prestamo prestamo = new Prestamo();
            prestamo.setSocio(socio);
            prestamo.setLibro(libro);
            prestamo.setFechaPrestamo(fechaPrestamo.getValue());
            prestamo.setFechaDevolucion(null); // Asegúrate de que la fecha de devolución sea null para préstamos activos

            prestamoDAO.save(prestamo); // Guardar el préstamo
            cargarPrestamos(); // Actualizar la lista de préstamos
            showAlert(Alert.AlertType.INFORMATION, "Préstamo Registrado", "El préstamo se ha registrado correctamente.");
        } else {
            showAlert(Alert.AlertType.WARNING, "Campos Incompletos", "Por favor, complete todos los campos.");
        }
    }



    // Listar los libros prestados
    @FXML
    private void onListarLibrosPrestados() {
        List<Prestamo> prestamos = prestamoDAO.findPrestamosActivos();

        // Depuración: Verificar los datos de los préstamos activos
        if (prestamos.isEmpty()) {
            System.out.println("No hay libros prestados.");
        }

        tablaPrestamos.getItems().clear();
        tablaPrestamos.getItems().addAll(prestamos);
    }

    // Listar los libros prestados a un socio específico
    @FXML
    private void onListarLibrosDeSocio() {
        Socio socio = comboSocio.getValue();
        if (socio != null) {
            List<Prestamo> prestamos = prestamoDAO.findPrestamosPorSocio(socio.getId());

            // Depuración: Verificar los préstamos encontrados para el socio
            if (prestamos.isEmpty()) {
                System.out.println("No hay libros prestados para este socio.");
            }

            tablaPrestamos.getItems().clear();
            tablaPrestamos.getItems().addAll(prestamos);
        } else {
            showAlert(Alert.AlertType.WARNING, "Seleccionar Socio", "Por favor, seleccione un socio.");
        }
    }

    // Inicialización de la vista
    @FXML
    private void initialize() {
        // Configurar las columnas de la tabla
        colSocio.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSocio().getNombre()));
        colLibro.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLibro().getTitulo())); // Mapeo del libro
        colFechaPrestamo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFechaPrestamo().toString()));
        colFechaDevolucion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFechaDevolucion() != null ? cellData.getValue().getFechaDevolucion().toString() : "No devuelto"));

        cargarComboBoxes();
        cargarPrestamos();
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
        comboLibro.setValue(null);
        comboSocio.setValue(null);
        fechaPrestamo.setValue(null);
        fechaDevolucion.setValue(null);
    }

}
