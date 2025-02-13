package org.example.blibliotecafx.Gestiones;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GestionInterfaz {

    private Stage currentStage;

    @FXML
    private void GestionLibrosOn() {
        // Cargar la vista de "Gestión de Libros"
        cargarVista("/org/example/blibliotecafx/LibrosApp.fxml", "Gestión de Libros");
    }

    @FXML
    private void GestionAutoresOn() {
        // Cargar la vista de "Gestión de Autores"
        cargarVista("/org/example/blibliotecafx/AutorApp.fxml", "Gestión de Autores");
    }

    @FXML
    private void GestionSociosOn() {
        // Cargar la vista de "Gestión de Socios"
        cargarVista("/org/example/blibliotecafx/SocioApp.fxml", "Gestión de Socios");
    }

    @FXML
    private void PrestamosOn() {
        // Cargar la vista de "Préstamos"
        cargarVista("/org/example/blibliotecafx/PrestamosApp.fxml", "Préstamos");
    }

    // Método común para cargar las vistas
    private void cargarVista(String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();

            // Cerrar la ventana principal si es necesario
            if (currentStage != null) {
                currentStage.close();
            }

            // Guardar la referencia del Stage actual
            currentStage = stage;

        } catch (IOException e) {
            e.printStackTrace(); // Mejorar con un manejo de errores adecuado
        }
    }
}
