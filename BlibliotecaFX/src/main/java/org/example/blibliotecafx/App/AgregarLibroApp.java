package org.example.blibliotecafx.App;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.blibliotecafx.Entities.Autor;
import org.example.blibliotecafx.Entities.Libro;
import org.example.blibliotecafx.Service.LibroService;

public class AgregarLibroApp extends Application {
    private LibroService libroService = new LibroService();

    @Override
    public void start(Stage primaryStage) {
        // Campos de texto para ingresar datos del libro
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

        // Botón para agregar el libro
        Button addButton = new Button("Agregar Libro");
        addButton.setOnAction(e -> {
            // Crear el objeto Libro
            Libro libro = new Libro();
            libro.setTitulo(tituloField.getText());
            libro.setIsbn(isbnField.getText());
            libro.setAutor(new Autor()); // Deberás crear la lógica de Autor también
            libro.setEditorial(editorialField.getText());
            libro.setAnioPublicacion(Integer.parseInt(anioField.getText()));

            // Usar el servicio para agregar el libro
            libroService.agregarLibro(libro);

            // Limpiar los campos después de agregar el libro
            tituloField.clear();
            isbnField.clear();
            autorField.clear();
            editorialField.clear();
            anioField.clear();
        });

        // Diseño de la escena
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
