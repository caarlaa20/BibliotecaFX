package org.example.blibliotecafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Cargar la vista principal desde el archivo FXML
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PrincipalApp.fxml"));
        Parent root = fxmlLoader.load();

        // Configurar la escena y mostrar la ventana
        stage.setTitle("Biblioteca");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);  // Lanzar la aplicación desde la clase HelloApplication
    }
}
