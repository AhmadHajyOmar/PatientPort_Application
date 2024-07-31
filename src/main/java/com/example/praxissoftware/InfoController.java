package com.example.praxissoftware;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class InfoController extends LoadNewScene implements Initializable {

    public static String redirection = "Dashboard";

    public static Patient currentPatient;

    @FXML
    private ListView<String> medicineListeUI;

    @FXML
    private TextArea address;

    @FXML
    private TextArea age;

    @FXML
    private ImageView back;

    @FXML
    private ListView<String> treatmentListeUI;

    @FXML
    private TextArea bloodPressure;

    @FXML
    private TextArea bloodSugar;

    @FXML
    private TextArea bodyTemperature;

    @FXML
    private TextArea gender;

    @FXML
    private TextArea insuranceComp;

    @FXML
    private TextArea name;

    @FXML
    private TextArea phone;

    @FXML
    private TextArea pulse;

    @FXML
    private ListView<String> symptomsListeUI;

    @FXML
    private TextArea weight;

    /**
     * Die Methode lädt eine neue Scene um ein Liniendiagramm basierend auf den Patientendaten zu zeigen.
     * Die Methode setzt den Wert von den Attributen der Klasse ChartController und lädt die Scene (chartInfo)
     *
     * @param dataName Name der Daten, die im Liniendiagramm angezeigt werden muss
     * @throws IOException
     */
    private void displayChart(String dataName) throws IOException{
        ChartController.visits = currentPatient.getVisits();
        ChartController.patientName = currentPatient.getName();
        ChartController.dataName = dataName;
        Stage stage = (Stage) weight.getScene().getWindow();
        loadScene("chartInfo.fxml",stage, 989, 724);
    }

    /**
     * Die Methode ruft die Methode displayChart() auf, um ein Liniendiagramm für den Blutdruck aller Besuche anzuzeigen.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    protected void displayLineChartBloodPressure(Event event) throws IOException{
        displayChart("Blood Pressure");
    }

    /**
     * Die Methode ruft die Methode displayChart() auf, um ein Liniendiagramm für den Blutzucker aller Besuche anzuzeigen.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    protected void displayLineChartBloodSugar(Event event) throws IOException{
        displayChart("Blood Sugar");
    }

    /**
     * Die Methode ruft die Methode displayChart() auf, um ein Liniendiagramm für die Körpertemperatur aller Besuche anzuzeigen.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    protected void displayLineChartBodyTemperature(Event event) throws IOException{
        displayChart("Body Temperature");
    }

    /**
     * Die Methode ruft die Methode displayChart() auf, um ein Liniendiagramm für das Gewicht aller Besuche anzuzeigen.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    protected void displayLineChartWeight(Event event) throws IOException{
        displayChart("Weight");
    }

    /**
     * Die Methode ruft die Methode displayChart() auf, um ein Liniendiagramm für den Impuls aller Besuche anzuzeigen.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    protected void displayLineChartPulse(Event event) throws IOException{
        displayChart("Pulse");
    }

    /**
     * Die Methode initialisiert die aktuelle Scene.
     * Die Methode setzt der Informationen der Patient in den entsprechenden Textfeldern,
     * außerdem wird auch die ListViews mit den Symptomen, Behandlungen und Medikamente aktualisiert.
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if(currentPatient != null){
            name.setText(currentPatient.getName());
            age.setText(currentPatient.getAge());
            address.setText(currentPatient.getStreet()+" str." + currentPatient.getHausNumber()
            +" "+ currentPatient.getPlz()+", Dortmund");
            phone.setText(currentPatient.getPhoneNumber());
            insuranceComp.setText(currentPatient.getInsurance());
            gender.setText(currentPatient.getGender());
            LinkedList<Visit> visits = currentPatient.getVisits();

            if(!visits.isEmpty()){
                Visit lastVisit = visits.peekLast();
                bloodPressure.setText(lastVisit.getBloodPressure());
                bloodSugar.setText(lastVisit.getBloodPressure());
                bodyTemperature.setText(lastVisit.getBodyTemperature());
                weight.setText(String.valueOf(lastVisit.getWeight()));
                pulse.setText(lastVisit.getPulse());
            }
            LinkedList<String> symptoms = new LinkedList<>();
            LinkedList<String> treatment = new LinkedList<>();
            LinkedList<String> medicines = new LinkedList<>();
            for(int i = 0; i < visits.size(); i++){
                symptoms.add(i+1 +". "+visits.get(i).getSymptoms());
            }
            LinkedList<String> treatmentsListe = currentPatient.getTreatments();
            LinkedList<Medicine> medicinesListe = currentPatient.getMedicines();

            for(int i = 0; i < treatmentsListe.size(); i++){
                treatment.add(i+1 +". " + treatmentsListe.get(i));
            }

            for(int i = 0; i < medicinesListe.size(); i++){
                Medicine m = medicinesListe.get(i);
                medicines.add(m.getName()+"-("+m.getDosage()+")-mg " + "["+m.getInstructionsForUse()+"] per day");
            }

            updatePatientInfo(symptomsListeUI, symptoms);

            updatePatientInfo(treatmentListeUI, treatment);

            updatePatientInfo(medicineListeUI, medicines);
        }
    }

    /**
     * Die Methode aktualisiert die Liste einer bestimmten Information der Patient, die anzeigt werden muss.
     * Die Methode setzt die Informationen der Liste info im entsprechenden ListView.
     *
     * @param listView ein ListView, um Informationen einer Liste anzuzeigen
     * @param info ein LinkedList, die die Informationen
     */
    private void updatePatientInfo(ListView listView, LinkedList<String> info){
        ObservableList<String> daten = FXCollections.observableArrayList(info);
        listView.setItems(daten);
    }

    /**
     * Die Methode lädt eine neue Scene "dashboard" oder "city" in der aktuellen Stage.
     * Die Methode wird ausgeführt, wenn der Nutzer auf der weissen Pfeil (Oben-Links in Stage) klickt.
     * Falls der Wert vom Attribut redirection gleich "City" ist, dann wird die Scene "city" in der aktuellen Stage
     * geladen, falls der Wert vom Attribut redirection gleich "Dashboard" ist, dann wird die Scene "dashboard" in der
     * aktuellen Scene geladen.
     *
     * @param mouseEvent
     * @throws IOException
     */
    public void handleBackClick(MouseEvent mouseEvent) throws IOException {
        String fxmlFileName = "";
        if(redirection.equals("City")){
            fxmlFileName = "city.fxml";
            redirection = "Dashboard";
        }else{
            fxmlFileName = "dashboard.fxml";
        }
        Stage stage = (Stage) weight.getScene().getWindow();
        loadScene(fxmlFileName, stage, 989, 797);
    }




}
