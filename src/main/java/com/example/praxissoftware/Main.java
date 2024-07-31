package com.example.praxissoftware;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Die Klasse Main ist der Einstiegspunkt der Anwendung.
 * Sie lädt die gespeicherten Patientendaten und initialisiert die Hauptbühne der Anwendung.
 */
public class Main extends Application {

    /**
     * Startet die Anwendung und lädt die Hauptszene.
     *
     * @param stage die Hauptbühne der Anwendung
     * @throws IOException wenn ein Fehler beim Laden der FXML-Datei auftritt
     */
    @Override
    public void start(Stage stage) throws IOException {
        AddNewPatientController.patienten = loadPatientenDaten("patientenInfo.txt");
        AddNewPatientController.patientenCityMap = loadPatientenDaten("patientenVisitCity.txt");
        System.out.println(AddNewPatientController.patienten.size());
        System.out.println(AddNewPatientController.patientenCityMap.size());
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent);
        stage.setTitle("PatientPort");

        Scale scale = new Scale();
        parent.getTransforms().add(scale);

        scale.xProperty().bind(stage.widthProperty().divide(693));
        scale.yProperty().bind(stage.heightProperty().divide(512));

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Der Haupteinstiegspunkt der Anwendung.
     *
     * @param args die Argumente der Befehlszeile
     */
    public static void main(String[] args) {
        launch(); // ruft die Methode start auf
    }

    /**
     * Diese Methode wird aufgerufen, wenn das Programm startet.
     * Sie liest alle gespeicherten Daten der Patienten und speichert diese Daten in der Liste der Patienten.
     *
     * @param filename der Name der Datei, die die Patientendaten enthält
     * @return eine Liste der geladenen Patienten
     */
    private LinkedList<Patient> loadPatientenDaten(String filename) {
        LinkedList<Patient> patients = new LinkedList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] lineDaten = line.split(";");
                Patient patient = parsePatient(lineDaten[0]);
                LinkedList<Visit> visits = parseVisits(lineDaten[1]);
                LinkedList<String> treatments = new LinkedList<>();
                LinkedList<Medicine> medicines = new LinkedList<>();
                if (lineDaten.length > 2) {
                    treatments = parseBehandlungen(lineDaten[2]);
                    medicines = parseMedikamente(lineDaten[3]);
                }
                patient.setVisits(visits);
                patient.setTreatments(treatments);
                patient.setMedicines(medicines);
                patients.add(patient);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patients;
    }

    /**
     * Konvertiert eine Zeichenkette in ein Patient-Objekt.
     *
     * @param data die Zeichenkette mit den Patientendaten
     * @return das Patient-Objekt
     */
    private Patient parsePatient(String data) {
        String[] personalInfo = data.split(",");
        Patient patient = new Patient(
                Integer.parseInt(personalInfo[0]), personalInfo[1], personalInfo[2],
                personalInfo[6], personalInfo[8], personalInfo[3],
                Integer.parseInt(personalInfo[4]), Integer.parseInt(personalInfo[5])
        );
        patient.setInsurance(personalInfo[7]);
        double x = Double.parseDouble(personalInfo[9]);
        double y = Double.parseDouble(personalInfo[10]);
        Position position = new Position(x, y);
        patient.setPosition(position);
        return patient;
    }

    /**
     * Konvertiert eine Zeichenkette in eine Liste von Visit-Objekten.
     *
     * @param data die Zeichenkette mit den Besuchsdaten
     * @return die Liste der Besuchs-Objekte
     */
    private LinkedList<Visit> parseVisits(String data) {
        LinkedList<Visit> visits = new LinkedList<>();
        String[] visitsLine = data.split("/");
        for (String visitData : visitsLine) {
            String[] visitLine = visitData.split(",");
            Visit visit = new Visit(
                    visitLine[0], visitLine[1], visitLine[4],
                    visitLine[2], Double.parseDouble(visitLine[3]), visitLine[5]
            );
            visits.add(visit);
        }
        return visits;
    }

    /**
     * Konvertiert eine Zeichenkette in eine Liste von Behandlungs-Strings.
     *
     * @param data die Zeichenkette mit den Behandlungsdaten
     * @return die Liste der Behandlungs-Strings
     */
    private LinkedList<String> parseBehandlungen(String data) {
        LinkedList<String> treatments = new LinkedList<>();
        String[] behandlungenLine = data.split(",");
        for (String behandlung : behandlungenLine) {
            treatments.add(behandlung);
        }
        return treatments;
    }

    /**
     * Konvertiert eine Zeichenkette in eine Liste von Medicine-Objekten.
     *
     * @param data die Zeichenkette mit den Medikamentendaten
     * @return die Liste der Medikamenten-Objekte
     */
    private LinkedList<Medicine> parseMedikamente(String data) {
        LinkedList<Medicine> medicines = new LinkedList<>();
        String[] medicineLine = data.split("/");
        for (String medicineData : medicineLine) {
            String[] medikamentInfo = medicineData.split(",");
            Medicine medicine = new Medicine(medikamentInfo[0], Double.parseDouble(medikamentInfo[1]), medikamentInfo[2]);
            medicines.add(medicine);
        }
        return medicines;
    }
}
