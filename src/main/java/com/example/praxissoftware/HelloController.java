package com.example.praxissoftware;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import java.io.IOException;

/**
 * Die Klasse HelloController verwaltet die Startseite der Anwendung.
 * Sie erweitert LoadNewScene und bietet eine Funktion zum Wechseln zur Dashboard-Szene.
 */
public class HelloController extends LoadNewScene {

    @FXML
    private Button start;

    @FXML
    private Pane pane;

    /**
     * Lädt die Szene "dashboard.fxml", wenn der Start-Button geklickt wird.
     *
     * @param event das auslösende Ereignis
     * @throws IOException wenn ein Fehler beim Laden der Szene auftritt
     */
    @FXML
    protected void onButtonClick(Event event) throws IOException {
        Stage stage = (Stage) start.getScene().getWindow();
        loadScene("dashboard.fxml", stage, 989, 797);
    }
}
