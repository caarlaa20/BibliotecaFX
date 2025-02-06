package org.example.blibliotecafx.Entities;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AgregarLibroApp extends Application {
    private LibroService libroService = new LibroService();

    @Override
    public void start(Stage primaryStage) {
        // Campos del formulario
        TextField tituloField = new TextField();
        tituloField.setPromptText("Título");

        TextField isbnField = new TextField();
        isbnField.setPromptText("ISBN");

        TextField autorField = new TextField();
        autorField.setPromptText("Autor");

        TextField editorialField = new TextField();
        editorialField.setPromptText("Editorial");

        TextField anioField = new TextField();
        anioField.setPromptText("Año de publicación");

        // Botón para agregar libro
        Button addButton = new Button("Agregar Libro");
        addButton.setOnAction(e -> {
            // Crear el libro y guardarlo
            Libro libro = new Libro();
            libro.setTitulo(tituloField.getText());
            libro.setIsbn(isbnField.getText());
            libro.setAutor(new Autor());  // Este es un ejemplo simplificado
            libro.setEditorial(editorialField.getText());
            libro.setAnioPublicacion(Integer.parseInt(anioField.getText()));

            libroService.agregarLibro(libro);
            System.out.println("Libro agregado con éxito.");

            // Limpiar campos
            tituloField.clear();
            isbnField.clear();
            autorField.clear();
            editorialField.clear();
            anioField.clear();
        });

        // Layout y escena
        VBox root = new VBox(10);
        root.getChildren().addAll(tituloField, isbnField, autorField, editorialField, anioField, addButton);

        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Agregar Libro");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
