package com.example.praxissoftware;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.*;
import java.net.URL;
import java.util.*;

/**
 * Die Klasse CityMapController verwaltet die Anzeige und Interaktion der Stadtkarte,
 * auf der die Positionen der Patienten angezeigt werden.
 * Sie erweitert LoadNewScene und implementiert Initializable.
 */
public class CityMapController extends LoadNewScene implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView back;

    @FXML
    private ListView<String> patientenList;

    @FXML
    private ImageView city;

    public static List<Position> positions = new ArrayList<>();

    public static List<ImageView> imageViews = new ArrayList<>();

    @FXML
    private TextArea addresstxt;

    @FXML
    private TextArea nameteltxt;

    private Patient choosedPatient;

    @FXML
    private ImageView selectedAddress;

    @FXML
    private ImageView unselectedAddress;

    /**
     * Diese Methode lädt die Szene "dashboard.fxml" in der aktuellen Stage.
     * Sie wird ausgeführt, wenn der Nutzer auf den "Zurück"-Button klickt.
     *
     * @param event das auslösende Ereignis
     * @throws IOException wenn ein Fehler beim Laden der Szene auftritt
     */
    @FXML
    protected void handleBackClick(Event event) throws IOException {
        Stage stage = (Stage) back.getScene().getWindow();
        loadScene("dashboard.fxml", stage, 989, 797);
    }

    /**
     * Diese Methode lädt die Szene "treatment.fxml" mit den Informationen des ausgewählten Patienten.
     *
     * @param event das auslösende Ereignis
     * @throws IOException wenn ein Fehler beim Laden der Szene auftritt
     */
    @FXML
    protected void addPatientInfo(Event event) throws IOException {
        if (choosedPatient != null) {
            TreatmentController.currentPatient = choosedPatient;
            Stage stage = (Stage) back.getScene().getWindow();
            loadScene("treatment.fxml", stage, 989, 724);
        }
    }

    /**
     * Initialisiert die Stadtkarte und lädt die Positionen der Patienten.
     *
     * @param url die URL zur Initialisierung
     * @param resourceBundle das ResourceBundle zur Initialisierung
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageViews = new LinkedList<>();
        for (int i = 0; i < AddNewPatientController.patientenCityMap.size(); i++) {
            Patient patient = AddNewPatientController.patientenCityMap.get(i);

            if (!patient.isRegistered()) {
                Image image = new Image(getClass().getResource("/images/Adresse2.png").toExternalForm());
                ImageView addressImg = new ImageView(image);
                addressImg.setFitHeight(25);
                addressImg.setFitWidth(25);
                Position imgPosition = patient.getPosition();
                addressImg.setLayoutX(imgPosition.getX());
                addressImg.setLayoutY(imgPosition.getY());
                addressImg.setVisible(true);
                positions.add(imgPosition);
                patient.setImageView(addressImg);
                imageViews.add(addressImg);
                patient.setRegistered(true);
            } else {
                ImageView addressImg = patient.getImageView();
                imageViews.add(addressImg);
            }

            anchorPane.getChildren().addAll(imageViews.get(i));

            LinkedList<String> patientNamesArrayList = new LinkedList<>();
            patientNamesArrayList = sortPatientAndPositions(patientNamesArrayList);
            updatePatientsName(patientNamesArrayList);
        }
    }

    /**
     * Behandelt das Klicken auf einen Patienten in der Liste.
     *
     * @param event das auslösende Ereignis
     */
    @FXML
    void handlePatientClick(Event event) {
        String selectedPatient = patientenList.getSelectionModel().getSelectedItem();
        if (selectedPatient != null) {
            if (!AddNewPatientController.patientenCityMap.isEmpty()) {
                for (int i = 0; i < AddNewPatientController.patientenCityMap.size(); i++) {
                    Patient patient = AddNewPatientController.patientenCityMap.get(i);

                    String[] info = selectedPatient.split("-");
                    if (patient.getName().equals(info[0]) && patient.getIndex() == Integer.parseInt(info[1])) {
                        ImageView address = patient.getImageView();
                        if (address.getFitHeight() == 30 && address.getFitWidth() == 30) {
                            patientenList.getSelectionModel().clearSelection();
                            address.setImage(unselectedAddress.getImage());
                            address.setFitWidth(25);
                            address.setFitHeight(25);
                            nameteltxt.setText("");
                            addresstxt.setText("");
                        } else {
                            unselectOtherAddress();
                            address.setImage(selectedAddress.getImage());
                            address.setOnMouseClicked(selectedAddress.getOnMouseClicked());
                            address.setFitWidth(30);
                            address.setFitHeight(30);
                            nameteltxt.setText(patient.getName() + "\n\n" + patient.getPhoneNumber());
                            addresstxt.setText(patient.getStreet() + " str. " + patient.getHausNumber()
                                    + "\n\n" + patient.getPlz() + ", Dortmund");
                            InfoController.currentPatient = patient;
                            choosedPatient = patient;
                            InfoController.redirection = "City";
                        }
                    }
                }
            }
        }
    }

    /**
     * Hebt die Auswahl anderer Adressen auf der Karte auf.
     */
    private void unselectOtherAddress() {
        for (int i = 0; i < AddNewPatientController.patientenCityMap.size(); i++) {
            AddNewPatientController.patientenCityMap.get(i).getImageView().setImage(unselectedAddress.getImage());
            AddNewPatientController.patientenCityMap.get(i).getImageView().setFitWidth(25);
            AddNewPatientController.patientenCityMap.get(i).getImageView().setFitHeight(25);
        }
    }

    /**
     * Entfernt den ausgewählten Patienten von der Karte und aktualisiert die Datei patientenVisitCity.txt.
     *
     * @param event das auslösende Ereignis
     * @throws IOException wenn ein Fehler beim Entfernen des Patienten auftritt
     */
    @FXML
    protected void removePatient(Event event) throws IOException {
        if (choosedPatient != null) {
            AddNewPatientController.patientenCityMap.remove(choosedPatient);
            Position position = choosedPatient.getPosition();
            positions.remove(position);
            new AddNewPatientController().savePatientenInfo(AddNewPatientController.patientenCityMap, "patientenVisitCity.txt");
            Stage stage = (Stage) city.getScene().getWindow();
            loadScene("city.fxml", stage, 990, 766);
        }
    }

    /**
     * Behandelt das Klicken auf eine Adresse und lädt die Szene patientenInfo.fxml.
     *
     * @param event das auslösende Ereignis
     * @throws IOException wenn ein Fehler beim Laden der Szene auftritt
     */
    @FXML
    protected void handleAddressClick(Event event) throws IOException {
        choosedPatient.getImageView().setImage(unselectedAddress.getImage());
        choosedPatient.getImageView().setFitHeight(25);
        choosedPatient.getImageView().setFitWidth(25);
        Stage stage = (Stage) back.getScene().getWindow();
        loadScene("patientenInfo.fxml", stage, 989, 724);
    }

    /**
     * Sortiert die Patienten und ihre Positionen nach der Entfernung zur Stadtmitte.
     *
     * @param patients die Liste der Patienten
     * @return die sortierte Liste der Patienten
     */
    private LinkedList<String> sortPatientAndPositions(LinkedList<String> patients) {
        LinkedList<Double> distances = new LinkedList<>();

        for (int i = 0; i < positions.size(); i++) {
            double distance = Math.round(calculateDistance(positions.get(i)));
            distances.add(distance);
            AddNewPatientController.patientenCityMap.get(i).setDistance(distance);
        }

        Collections.sort(distances);
        LinkedList<Double> distancesSortedAbsteigend = new LinkedList<>();
        for (int i = distances.size() - 1; i >= 0; i--) {
            distancesSortedAbsteigend.add(distances.get(i));
        }

        for (int i = 0; i < distancesSortedAbsteigend.size(); i++) {
            for (int j = 0; j < AddNewPatientController.patientenCityMap.size(); j++) {
                Patient patient = AddNewPatientController.patientenCityMap.get(j);
                if (patient.getDistance() == distancesSortedAbsteigend.get(i)) {
                    String zeile = patient.getName() + "-" + patient.getIndex() + "-"
                            + patient.getPosition().toString() + "-" + distancesSortedAbsteigend.get(i);
                    patients.add(zeile);
                    break;
                }
            }
        }
        return patients;
    }

    /**
     * Berechnet die Entfernung einer Position zur Stadtmitte.
     *
     * @param p die Position
     * @return die Entfernung zur Stadtmitte
     */
    private double calculateDistance(Position p) {
        double cityX =

                city.getLayoutX();
        double cityY = city.getLayoutY();
        double distance = Math.sqrt(((cityX - p.getX()) * (cityX - p.getX())) + ((cityY - p.getY()) * (cityY - p.getY())));
        return distance;
    }

    /**
     * Aktualisiert die Liste der Patientennamen in der ListView.
     *
     * @param list die Liste der Patientennamen
     */
    private void updatePatientsName(LinkedList<String> list) {
        ObservableList<String> patientNames = FXCollections.observableArrayList(list);
        patientenList.setItems(patientNames);
    }
}
