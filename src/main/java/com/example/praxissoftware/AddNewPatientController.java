package com.example.praxissoftware;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.*;
import java.util.LinkedList;
import java.util.Random;

/**
 * Die Klasse AddNewPatientController verwaltet das Hinzufügen neuer Patientendatensätze
 * in der Anwendung. Sie erweitert LoadNewScene und bietet Funktionen zur Validierung 
 * von Eingabedaten, zum Speichern von Patientendaten und zum Laden neuer Szenen.
 */
public class AddNewPatientController extends LoadNewScene {

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private TextField ageTextField;

    @FXML
    private TextField insurance;

    @FXML
    private ComboBox<String> genderComboBox;

    @FXML
    private TextField pulse;

    @FXML
    private TextField bloodPressure;

    @FXML
    private TextField bloodSugar;

    @FXML
    private TextField weight;

    @FXML
    private TextField temperature;

    @FXML
    private TextField address;

    @FXML
    private TextField postCode;

    @FXML
    private TextField houes_Nr;

    @FXML
    private TextField symptom;

    public static LinkedList<Patient> patienten;

    public static LinkedList<Patient> patientenCityMap;

    private Random random = new Random();

    /**
     * Diese Methode validiert die eingegebenen Informationen.
     * Falls sie gültig sind, wird ein Patient mit den eingegebenen Informationen erstellt 
     * oder die bereits vorhandenen Informationen des Patienten aktualisiert, 
     * falls er/sie in der Patientenliste vorhanden ist.
     * Falls sie nicht gültig sind, erscheint eine Fehlermeldung.
     * Danach lädt die Methode eine neue Szene "dashboard" in der aktuellen Stage.
     *
     * @param event das auslösende Ereignis
     * @throws IOException wenn ein Fehler beim Laden der Szene auftritt
     */
    @FXML
    private void onClickAdd(Event event) throws IOException {
        String name = nameTextField.getText();
        boolean nameIsValid = containsOnlyLetters(name);
        String phone = phoneTextField.getText();
        boolean phoneIsValid = containsOnlyDigits(phone);

        String age = ageTextField.getText();
        boolean ageIsValid = containsOnlyDigits(age);
        if (ageIsValid == true) {
            if (Integer.parseInt(age) < 18) {
                ageIsValid = false;
            }
        }

        String addressInput = address.getText();
        boolean adresseIsValid = containsOnlyLetters(addressInput);

        String houesNr = houes_Nr.getText();
        boolean hausNrIsValid = containsOnlyDigits(houesNr);

        String postCodeStr = postCode.getText();
        boolean postIsValid = containsOnlyDigits(postCodeStr);
        if (postIsValid) {
            if (!postCodeStr.startsWith("44") || postCodeStr.length() != 5) {
                postIsValid = false;
            }
        }

        String insuranceComp = insurance.getText();
        boolean insuranceCompIsValid = containsOnlyLetters(insuranceComp);

        String bloodPre = bloodPressure.getText();
        boolean bloodPreIsValid = containsOnlyDigits(bloodPre);

        String bloodSug = bloodSugar.getText();
        boolean bloodSugIsValid = containsOnlyDigits(bloodSug);

        String bodyTemp = temperature.getText();
        boolean boyTempIsValid = containsOnlyDigits(bodyTemp);

        String bodyweight = weight.getText();
        boolean bodyweightIsValid = containsOnlyDigits(bodyweight);

        String heartPulse = pulse.getText();
        boolean heartPulseIsValid = containsOnlyDigits(heartPulse);

        String gender = genderComboBox.getValue();
        String symp = symptom.getText();

        if (!nameIsValid) {
            displayAlert("Ein gültiger Name enthält nur Buchstaben wie \"Hadi\"",
                    "Sie müssen einen gültigen Namen eingeben");
        } else if (!phoneIsValid) {
            displayAlert("Eine gültige Telefonnummer enthält nur 12 Ziffern und beginnt mit 0, wie \"015712148823\"",
                    "Sie müssen eine gültige Telefonnummer eingeben");
        } else if (!ageIsValid) {
            displayAlert("Ein gültiges Alter enthält nur Zahlen, die größer als 17 sind, wie \"23\"",
                    "Sie müssen ein gültiges Alter eingeben");
        } else if (!adresseIsValid) {
            displayAlert("Eine gültige Adresse enthält nur Buchstaben",
                    "Sie müssen eine gültige Adresse eingeben");
        } else if (!hausNrIsValid) {
            displayAlert("Eine gültige Hausnummer enthält nur Zahlen",
                    "Sie müssen eine gültige Hausnummer eingeben");
        } else if (!postIsValid) {
            displayAlert("Eine gültige Postleitzahl enthält nur fünf Ziffern und beginnt mit \"44\", wie \"44135\"",
                    "Sie müssen eine gültige Postleitzahl eingeben");
        } else if (!insuranceCompIsValid) {
            displayAlert("Eine gültige Versicherungsgesellschaft enthält nur Buchstaben, wie \"IKK\"",
                    "Sie müssen eine gültige Versicherungsgesellschaft eingeben");
        } else if (!bloodPreIsValid) {
            displayAlert("Ein gültiger Blutdruck enthält nur Zahlen, wie \"70\"",
                    "Sie müssen einen gültigen Blutdruck eingeben");
        } else if (!bloodSugIsValid) {
            displayAlert("Ein gültiger Blutzucker enthält nur Zahlen, wie \"170\"",
                    "Sie müssen einen gültigen Blutzucker eingeben");
        } else if (!boyTempIsValid) {
            displayAlert("Eine gültige Körpertemperatur enthält nur Zahlen, wie \"37.5\"",
                    "Sie müssen eine gültige Körpertemperatur eingeben");
        } else if (!bodyweightIsValid) {
            displayAlert("Ein gültiges Körpergewicht enthält nur Zahlen, wie \"82.4\"",
                    "Sie müssen ein gültiges Körpergewicht eingeben");
        } else if (!heartPulseIsValid) {
            displayAlert("Ein gültiger Puls enthält nur Zahlen, wie \"83\"",
                    "Sie müssen einen gültigen Puls eingeben");
        } else {
            int id = generateID();
            Patient patient = new Patient(id, name, age, phone, gender, addressInput, Integer.parseInt(houesNr), Integer.parseInt(postCodeStr));
            patient.setInsurance(insuranceComp);
            patient.setSymptom(symp);
            Visit visit = new Visit(bloodPre, bloodSug, heartPulse, bodyTemp, Double.parseDouble(bodyweight), symp);
            boolean patientIsRegistered = false;
            Patient registeredPatient = null;

            for (int i = 0; i < patienten.size(); i++) {
                if (patienten.get(i).getName().equalsIgnoreCase(patient.getName())
                        && patienten.get(i).getAge().equals(patient.getAge())
                        && patienten.get(i).getPhoneNumber().equals(patient.getPhoneNumber())
                        && patienten.get(i).getStreet().equals(patient.getStreet())
                        && patienten.get(i).getHausNumber() == patient.getHausNumber()
                        && patienten.get(i).getPlz() == patient.getPlz()
                        && patienten.get(i).getGender().equals(patient.getGender())
                        && patienten.get(i).getInsurance().equals(patient.getInsurance())) {
                    patientIsRegistered = true;
                    registeredPatient = patienten.get(i);
                }
            }

            if (!patientIsRegistered) {
                patient.addVisit(visit);
                patienten.add(patient);
                patientenCityMap.add(patient);
                Position imgPosition = generatePositionCoordinates();
                patient.setPosition(imgPosition);
            } else {
                registeredPatient.addVisit(visit);
            }
            savePatientenInfo(patienten, "patientenInfo.txt");
            savePatientenInfo(patientenCityMap, "patientenVisitCity.txt");
            Stage stage = (Stage) nameTextField.getScene().getWindow();
            loadScene("dashboard.fxml", stage, 989, 797);
        }
    }

    /**
     * Speichert die Informationen der Patienten in einer Textdatei (patientenInfo.txt).
     *
     * @param patienten die Liste der Patienten, deren Informationen gespeichert werden sollen
     * @param filename der Name der Datei, in die die Informationen gespeichert werden sollen
     * @throws IOException wenn ein Fehler beim Speichern auftritt
     */
    @FXML
    public void savePatientenInfo(LinkedList<Patient> patienten, String filename) throws IOException {
        StringBuilder patientenDB = new StringBuilder();

        for (Patient patient : patienten) {
            patientenDB.append(formatPatientInfo(patient)).append(";");
            patientenDB.append(formatVisits(patient.getVisits())).append(";");
            patientenDB.append(formatTreatments(patient.getTreatments())).append(";");
            patientenDB.append(formatMedicines(patient.getMedicines()));
            patientenDB.append("\n");
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            bw.write(patientenDB.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Gibt die Informationen des gegebenen Patienten als String zurück.
     *
     * @param patient ein Objekt vom Typ Patient
     * @return einen String, der alle Informationen des Patienten enthält
     */
    private String formatPatientInfo(Patient patient) {
        return patient.getIndex() + "," +
                patient.getName() + "," +
                patient.getAge()

                + "," +
                patient.getStreet() + "," +
                patient.getHausNumber() + "," +
                patient.getPlz() + "," +
                patient.getPhoneNumber() + "," +
                patient.getInsurance() + "," +
                patient.getGender() + "," +
                patient.getPosition().getX() + "," +
                patient.getPosition().getY();
    }

    /**
     * Gibt die Informationen der gegebenen LinkedList, die Visit-Objekte enthält, als String zurück.
     *
     * @param visits eine LinkedList, die Elemente vom Typ Visit speichert, die die Besuche repräsentieren
     * @return einen String, der den Inhalt der LinkedList repräsentiert
     */
    private String formatVisits(LinkedList<Visit> visits) {
        StringBuilder visitsToSave = new StringBuilder();
        for (int i = 0; i < visits.size(); i++) {
            Visit visit = visits.get(i);
            visitsToSave.append(visit.getBloodPressure()).append(",")
                    .append(visit.getBloodSugar()).append(",")
                    .append(visit.getBodyTemperature()).append(",")
                    .append(visit.getWeight()).append(",")
                    .append(visit.getPulse()).append(",")
                    .append(visit.getSymptoms());
            if (i != visits.size() - 1) {
                visitsToSave.append("/");
            }
        }
        return visitsToSave.toString();
    }

    /**
     * Gibt die Informationen der gegebenen LinkedList, die String-Objekte enthält, als String zurück.
     *
     * @param treatments eine LinkedList, die Elemente vom Typ String speichert, die die Behandlungen repräsentieren
     * @return einen String, der den Inhalt der LinkedList repräsentiert
     */
    private String formatTreatments(LinkedList<String> treatments) {
        StringBuilder treatmentsToSave = new StringBuilder();
        for (int i = 0; i < treatments.size(); i++) {
            treatmentsToSave.append(treatments.get(i));
            if (i != treatments.size() - 1) {
                treatmentsToSave.append(",");
            }
        }
        return treatmentsToSave.toString();
    }

    /**
     * Gibt die Informationen der gegebenen LinkedList, die Medicine-Objekte enthält, als String zurück.
     *
     * @param medicines eine LinkedList, die Elemente vom Typ Medicine speichert
     * @return einen String, der den Inhalt der LinkedList repräsentiert
     */
    private String formatMedicines(LinkedList<Medicine> medicines) {
        StringBuilder medicinesToSave = new StringBuilder();
        for (int i = 0; i < medicines.size(); i++) {
            Medicine medicine = medicines.get(i);
            medicinesToSave.append(medicine.getName()).append(",")
                    .append(medicine.getDosage()).append(",")
                    .append(medicine.getInstructionsForUse());
            if (i != medicines.size() - 1) {
                medicinesToSave.append("/");
            }
        }
        return medicinesToSave.toString();
    }

    /**
     * Zeigt einen Alert mit einem bestimmten Header-Text und einer bestimmten Nachricht an.
     *
     * @param headerText der Wert des Header-Textes des Alerts
     * @param messageText der Wert des Inhalts-Textes des Alerts
     */
    private void displayAlert(String headerText, String messageText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Warnung");
        alert.setHeaderText(headerText);
        alert.setContentText(messageText);
        alert.showAndWait();
    }

    /**
     * Lädt die neue Szene "dashboard" in der aktuellen Stage.
     * Diese Methode wird ausgeführt, wenn der Nutzer auf den weißen Pfeil (oben links in der Stage) klickt.
     *
     * @param event das auslösende Ereignis
     * @throws IOException wenn ein Fehler beim Laden der Szene auftritt
     */
    @FXML
    private void handleBackClick(Event event) throws IOException {
        Stage stage = (Stage) nameTextField.getScene().getWindow();
        loadScene("dashboard.fxml", stage, 989, 797);
    }

    /**
     * Überprüft, ob der gegebene Text nur aus Buchstaben [A-Z], [a-z] und Leerzeichen besteht.
     *
     * @param text der zu überprüfende Text
     * @return true, falls der Eingabestring gültig ist, ansonsten false
     */
    private boolean containsOnlyLetters(String text) {
        boolean isValid = true;
        for (int i = 0; i < text.length(); i++) {
            if (!((text.charAt(i) >= 'A' && text.charAt(i) <= 'Z') || (text.charAt(i) >= 'a' && text.charAt(i) <= 'z') || (text.charAt(i) == ' '))) {
                isValid = false;
                break;
            }
        }
        if (text.trim().isEmpty()) {
            isValid = false;
        }
        return isValid;
    }

    /**
     * Überprüft, ob der gegebene Text nur aus Ziffern [0-9] besteht.
     *
     * @param text der zu überprüfende Text
     * @return true, falls der Eingabestring gültig ist, ansonsten false
     */
    private boolean containsOnlyDigits(String text) {
        boolean isValid = true;
        for (int i = 0; i < text.length(); i++) {
            // Überprüfung, ob jedes Zeichen eine Ziffer ist
            if (!(text.charAt(i) >= '0' && text.charAt(i) <= '9')) {
                isValid = false;
                break;
            }
        }
        return isValid;
    }

    /**
     * Generiert eine einzigartige Identifikationsnummer und gibt diese zurück.
     * Zuerst wird eine Identifikationsnummer generiert und überprüft, ob sie schon zu einem der Patienten gehört.
     * Falls dies der Fall ist, wird die Identifikationsnummer zurückgegeben, ansonsten wird so lange eine neue
     * Identifikationsnummer generiert, bis eine Identifikationsnummer erstellt wird, die zu keinem der Patienten gehört.
     *
     * @return eine Identifikationsnummer zwischen 1 und 800
     */
    private int generateID() {
        int id = 0;
        do {
            boolean indexExists = false;

            id = random.nextInt((800 - 1) + 1) + 1;
            for (int i = 0; i < patienten.size(); i++) {

                if (patienten.get(i).getIndex() == id) {
                    indexExists = true;
                    break;
                }
            }
            if (indexExists == false) {
                break;
            }
        } while (true);

        return id;
    }

    /**
     * Generiert zwei zufällige Gleitkommazahlen innerhalb eines bestimmten Intervalls,
     * die die x-Achse und y-Achse repräsentieren.
     * Nachdem die Position mit den zwei Zahlen als Parameter erstellt wird, wird überprüft, ob diese Position gültig ist.
     * Die Position ist gültig, wenn sie nicht zu einem der Patienten gehört. Falls dies der Fall ist,
     * wird die Position zurückgegeben, ansonsten wird erneut eine neue Position erstellt, bis eine gültige Position
     * erstellt wird. In diesem Fall wird die gültige Position zurückgegeben.
     *
     * @return ein Objekt vom Typ Position
     */
    private Position generatePositionCoordinates() {
        Position imgPosition = null;
        do {
            double randomX = Math.round(349 + (899 - 349) * random.nextDouble());
            double randomY = Math.round(136 + (600 - 136) * random.nextDouble());
            imgPosition = new Position(randomX, randomY);
        } while (!isPositionValid(imgPosition));
        return imgPosition;
    }

    /**
     * Überprüft, ob die gegebene Position gültig ist.
     *
     * @param imgPosition die zu überprüfende Position
     * @return true, falls die Position nicht zu einem der Patienten gehört, ansonsten false
     */
    private boolean isPositionValid(Position imgPosition) {
        boolean isValid = true;
        for (int i = 0; i < patienten.size(); i++) {
            if (patienten.get(i).getPosition() != null
                    && patienten.get(i).getPosition().compareTwoPositions(imgPosition)) {
                isValid = false;
            }
        }
        return isValid;
    }
}
