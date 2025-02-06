package org.example.blibliotecafx.App;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.blibliotecafx.Entities.Autor;
import org.example.blibliotecafx.Service.AutorService;

public class AgregarAutorApp extends Application {
    private AutorService autorService = new AutorService();

    @Override
    public void start(Stage primaryStage) {
        // Campos del formulario
        TextField nombreField = new TextField();
        nombreField.setPromptText("Nombre");

        TextField nacionalidadField = new TextField();
        nacionalidadField.setPromptText("Nacionalidad");

        Button addButton = new Button("Agregar Autor");
        addButton.setOnAction(e -> {
            // Crear el autor y guardarlo
            Autor autor = new Autor();
            autor.setNombre(nombreField.getText());
            autor.setNacionalidad(nacionalidadField.getText());

            autorService.agregarAutor(autor);
            System.out.println("Autor agregado con éxito.");

            // Limpiar campos
            nombreField.clear();
            nacionalidadField.clear();
        });

        // Layout y escena
        VBox root = new VBox(10);
        root.getChildren().addAll(nombreField, nacionalidadField, addButton);

        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Agregar Autor");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
