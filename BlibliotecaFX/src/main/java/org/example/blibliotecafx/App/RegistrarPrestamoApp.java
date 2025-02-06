package org.example.blibliotecafx.App;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.blibliotecafx.Entities.Libro;
import org.example.blibliotecafx.Entities.Prestamo;
import org.example.blibliotecafx.Entities.Socio;
import org.example.blibliotecafx.Service.LibroService;
import org.example.blibliotecafx.Service.PrestamoService;
import org.example.blibliotecafx.Service.SocioService;

import java.time.LocalDate;
import java.util.Date;

public class RegistrarPrestamoApp extends Application {
    private PrestamoService prestamoService = new PrestamoService();
    private LibroService libroService = new LibroService();
    private SocioService socioService = new SocioService();

    @Override
    public void start(Stage primaryStage) {
        // Campos del formulario
        ComboBox<Libro> libroComboBox = new ComboBox<>();
        libroComboBox.getItems().addAll(libroService.listarLibrosDisponibles());

        ComboBox<Socio> socioComboBox = new ComboBox<>();
        socioComboBox.getItems().addAll(socioService.listarSocios());

        DatePicker fechaPrestamoField = new DatePicker();
        DatePicker fechaDevolucionField = new DatePicker();

        Button registrarButton = new Button("Registrar Préstamo");
        registrarButton.setOnAction(e -> {
            // Crear préstamo y guardarlo
            Libro libro = libroComboBox.getValue();
            Socio socio = socioComboBox.getValue();
            LocalDate fechaPrestamo = fechaPrestamoField.getValue();
            LocalDate fechaDevolucion = fechaDevolucionField.getValue();

            Prestamo prestamo = new Prestamo();
            prestamo.setLibro(libro);
            prestamo.setSocio(socio);
            prestamo.setFechaPrestamo(fechaPrestamo);
            prestamo.setFechaDevolucion(fechaDevolucion);

            prestamoService.registrarPrestamo(prestamo);
            System.out.println("Préstamo registrado con éxito.");
        });

        // Layout y escena
        VBox root = new VBox(10);
        root.getChildren().addAll(libroComboBox, socioComboBox, fechaPrestamoField, fechaDevolucionField, registrarButton);

        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Registrar Préstamo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
