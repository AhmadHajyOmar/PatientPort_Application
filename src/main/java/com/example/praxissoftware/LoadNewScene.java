package com.example.praxissoftware;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

import java.io.IOException;

public class LoadNewScene {

    /**
     * Die Methode lädt die gegebene fxml-Datei und erstellt eine neue Scene, die in der aktuellen Stage gesetzt.
     * Vor allem wird die neue Scene skaliert.
     *
     * @param fxmlFile Name der zu geladenen fxml-Datei
     * @param stage der aktuelle Stage
     * @param widthDiv Die Breite für die Rechnung der Skalierung
     * @param heightDiv Die Höhe für die Rechnung der Skalierung
     * @throws IOException
     */
    public void loadScene(String fxmlFile, Stage stage, double widthDiv, double heightDiv) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        Scale scale = new Scale();
        parent.getTransforms().add(scale);
        scale.xProperty().bind(stage.widthProperty().divide(widthDiv));
        scale.yProperty().bind(stage.heightProperty().divide(heightDiv));
        stage.setScene(scene);
    }
}
