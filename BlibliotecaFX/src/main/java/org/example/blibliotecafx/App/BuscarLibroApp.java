package org.example.blibliotecafx.App;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.blibliotecafx.Entities.Libro;
import org.example.blibliotecafx.HelloApplication;
import org.example.blibliotecafx.Service.LibroService;

import java.util.List;

public class BuscarLibroApp extends HelloApplication {
    private LibroService libroService = new LibroService();

    @Override
    public void start(Stage primaryStage) {
        // Campos de búsqueda
        TextField buscarField = new TextField();
        buscarField.setPromptText("Buscar libro por título, autor o ISBN");

        // Botón de búsqueda
        Button buscarButton = new Button("Buscar");
        buscarButton.setOnAction(e -> {
            // Lógica de búsqueda
            String query = buscarField.getText();
            List<Libro> librosEncontrados = libroService.buscarLibrosPorTitulo(query); // Puedes hacer más búsquedas

            // Mostrar resultados
            if (librosEncontrados.isEmpty()) {
                System.out.println("No se encontraron libros.");
            } else {
                librosEncontrados.forEach(libro -> System.out.println(libro.getTitulo()));
            }
        });

        // Diseño de la escena
        VBox root = new VBox(10);
        root.getChildren().addAll(buscarField, buscarButton);

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("Buscar Libro");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
