package org.example.blibliotecafx.Entities;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class BuscarLibroApp extends Application {
    private LibroService libroService = new LibroService();

    @Override
    public void start(Stage primaryStage) {
        // Campos de búsqueda
        TextField buscarField = new TextField();
        buscarField.setPromptText("Buscar libro por título o autor");

        Button buscarButton = new Button("Buscar");
        ListView<String> resultList = new ListView<>();

        buscarButton.setOnAction(e -> {
            // Buscar libros por título o autor
            String query = buscarField.getText();
            List<Libro> libros = libroService.buscarLibrosPorTitulo(query);

            if (libros.isEmpty()) {
                resultList.getItems().clear();
                resultList.getItems().add("No se encontraron libros.");
            } else {
                resultList.getItems().clear();
                for (Libro libro : libros) {
                    resultList.getItems().add(libro.getTitulo() + " - " + libro.getAutor().getNombre());
                }
            }
        });

        // Layout y escena
        VBox root = new VBox(10);
        root.getChildren().addAll(buscarField, buscarButton, resultList);

        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Buscar Libro");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
