package org.example.blibliotecafx.Gestiones;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.security.Principal;

public class GestionInterfaz {
    @FXML
    private StackPane contentArea;

    @FXML
    private AnchorPane Principal;

    @FXML
    private AnchorPane Autor;

    @FXML
    private void GestionLibrosOn() throws IOException {
        new SceneSelector(Principal, "/org/example/bibliotecafx/Principal.fxml");
    }

    @FXML
    private void GestionAutoresOn() throws IOException {
        new SceneSelector(Principal, "AutorApp.fxml");
    }

    @FXML
    private void GestionSociosOn() throws IOException {
        new SceneSelector(Principal, "/org/example/bibliotecafx/Principal.fxml");
    }

    @FXML
    private void PrestamosOn() throws IOException {
        new SceneSelector(Principal, "/org/example/bibliotecafx/Principal.fxml");
    }


}

