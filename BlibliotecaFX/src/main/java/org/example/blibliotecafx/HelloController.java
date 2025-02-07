package org.example.blibliotecafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.blibliotecafx.App.AgregarAutorApp;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        AgregarAutorApp a=new AgregarAutorApp();
        a.start(null);
    }

    private void abrirAgregarAutor() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AgregarAutorApp.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Agregar Autor");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
