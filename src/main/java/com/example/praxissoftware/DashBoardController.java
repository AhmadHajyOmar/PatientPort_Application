package com.example.praxissoftware;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Die Klasse DashBoardController verwaltet das Dashboard der Anwendung.
 * Sie erweitert LoadNewScene und bietet Funktionen zum Hinzufügen neuer Patienten,
 * zur Suche von Patienten und zum Wechseln zwischen verschiedenen Szenen.
 */
public class DashBoardController extends LoadNewScene {

    @FXML
    private TextField searchField;

    @FXML
    private ImageView addButton;

    @FXML
    private ListView<String> patientListView;

    /**
     * Lädt die Szene "add_new_patient.fxml", wenn der Plus-Button geklickt wird.
     *
     * @param event das auslösende Ereignis
     * @throws IOException wenn ein Fehler beim Laden der Szene auftritt
     */
    @FXML
    protected void onClickPlus(Event event) throws IOException {
        Stage stage = (Stage) searchField.getScene().getWindow();
        loadScene("add_new_patient.fxml", stage, 948, 776);
    }

    /**
     * Lädt die Szene "city.fxml", wenn der Karten-Button geklickt wird.
     *
     * @param event das auslösende Ereignis
     * @throws IOException wenn ein Fehler beim Laden der Szene auftritt
     */
    @FXML
    protected void onClickKarte(Event event) throws IOException {
        Stage stage = (Stage) searchField.getScene().getWindow();
        loadScene("city.fxml", stage, 990, 766);
    }

    /**
     * Sucht nach Patienten basierend auf dem eingegebenen Namen im Suchfeld und
     * aktualisiert die Liste der Patienten entsprechend.
     *
     * @param event das auslösende Ereignis
     * @throws IOException wenn ein Fehler auftritt
     */
    @FXML
    protected void onTypeName(Event event) throws IOException {
        String searchedName = searchField.getText();
        ArrayList<String> names = new ArrayList<>();

        for (int i = 0; i < AddNewPatientController.patienten.size(); i++) {
            Patient patient = AddNewPatientController.patienten.get(i);
            if (patient.getName().equals(searchedName)) {
                names.add(patient.getName() + "-" + patient.getIndex());
            }
        }

        if (names.isEmpty()) {
            for (int i = 0; i < AddNewPatientController.patienten.size(); i++) {
                Patient patient = AddNewPatientController.patienten.get(i);
                if (patient.getName().charAt(0) == searchedName.charAt(0)
                        || patient.getName().charAt(0) == searchedName.charAt(0) + 32
                        || patient.getName().charAt(0) == searchedName.charAt(0) - 32) {
                    names.add(patient.getName() + "-" + patient.getIndex());
                }
            }
        }

        ObservableList<String> patientNames = FXCollections.observableArrayList(names);
        patientListView.setItems(patientNames);
    }

    /**
     * Behandelt das Klicken auf einen Patienten in der Liste und lädt die Szene patientenInfo.fxml
     * mit den Informationen des ausgewählten Patienten.
     *
     * @param event das auslösende Ereignis
     * @throws IOException wenn ein Fehler beim Laden der Szene auftritt
     */
    @FXML
    protected void handleSelectedPatient(Event event) throws IOException {
        String selectedPatient = patientListView.getSelectionModel().getSelectedItem();
        if (selectedPatient != null) {
            String[] info = selectedPatient.split("-");
            String patientName = info[0];
            int patientIndex = Integer.parseInt(info[1]);
            for (Patient patient : AddNewPatientController.patienten) {
                if (patient.getName().equals(patientName) && patient.getIndex() == patientIndex) {
                    InfoController.currentPatient = patient;
                    Stage stage = (Stage) searchField.getScene().getWindow();
                    loadScene("patientenInfo.fxml", stage, 989, 755);
                    break;
                }
            }
        }
    }
}
