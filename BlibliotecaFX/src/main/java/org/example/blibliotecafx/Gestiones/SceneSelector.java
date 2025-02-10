package org.example.blibliotecafx.Gestiones;


import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.example.blibliotecafx.Main;

import java.io.IOException;
import java.util.Objects;

public class SceneSelector{

    public SceneSelector(AnchorPane currentAnchorPane, String fxml)throws IOException{
        AnchorPane nextAnchorPane = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(fxml)));
        currentAnchorPane.getChildren().removeAll();
        currentAnchorPane.getChildren().setAll(nextAnchorPane);
    }

}