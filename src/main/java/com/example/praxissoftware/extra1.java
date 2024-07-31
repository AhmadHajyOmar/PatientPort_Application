package com.example.praxissoftware;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class extra1 {

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
    private TextField temprture;

    @FXML
    private TextField address;

    @FXML
    private TextField plz;

    @FXML
    private TextField haus_Nr;
    @FXML
    private TextField symptome;
    public static ArrayList<Patient> patienten;
    public static ArrayList<Patient> patientenKarte;

    private Random random = new Random();

    @FXML
    public void initialize() {
        addValidationHandlers();
    }

    private void addValidationHandlers() {
        nameTextField.addEventHandler(MouseEvent.MOUSE_EXITED, this::validateName);
        phoneTextField.addEventHandler(MouseEvent.MOUSE_EXITED, this::validatePhone);
        ageTextField.addEventHandler(MouseEvent.MOUSE_EXITED, this::validateAge);
        address.addEventHandler(MouseEvent.MOUSE_EXITED, this::validateAddress);
        haus_Nr.addEventHandler(MouseEvent.MOUSE_EXITED, this::validateHausNr);
        plz.addEventHandler(MouseEvent.MOUSE_EXITED, this::validatePlz);
        insurance.addEventHandler(MouseEvent.MOUSE_EXITED, this::validateInsurance);
        bloodPressure.addEventHandler(MouseEvent.MOUSE_EXITED, this::validateBloodPressure);
        bloodSugar.addEventHandler(MouseEvent.MOUSE_EXITED, this::validateBloodSugar);
        temprture.addEventHandler(MouseEvent.MOUSE_EXITED, this::validateTemperature);
        weight.addEventHandler(MouseEvent.MOUSE_EXITED, this::validateWeight);
        pulse.addEventHandler(MouseEvent.MOUSE_EXITED, this::validatePulse);
    }

    private void validateName(MouseEvent event) {
        if (!containsOnlyLetters(nameTextField.getText())) {
            displayAlert("Ungültiger Name", "Ein gültiger Name enthält nur Buchstaben.");
        }
    }

    private void validatePhone(MouseEvent event) {
        if (!containsOnlyDigits(phoneTextField.getText())) {
            displayAlert("Ungültige Telefonnummer", "Eine gültige Telefonnummer enthält nur Ziffern.");
        }
    }

    private void validateAge(MouseEvent event) {
        String ageText = ageTextField.getText();
        if (!containsOnlyDigits(ageText) || Integer.parseInt(ageText) < 18) {
            displayAlert("Ungültiges Alter", "Ein gültiges Alter enthält nur Zahlen und muss größer als 17 sein.");
        }
    }

    private void validateAddress(MouseEvent event) {
        if (!containsOnlyLetters(address.getText())) {
            displayAlert("Ungültige Adresse", "Eine gültige Adresse enthält nur Buchstaben.");
        }
    }

    private void validateHausNr(MouseEvent event) {
        if (!containsOnlyDigits(haus_Nr.getText())) {
            displayAlert("Ungültige Hausnummer", "Eine gültige Hausnummer enthält nur Ziffern.");
        }
    }

    private void validatePlz(MouseEvent event) {
        String plzText = plz.getText();
        if (!containsOnlyDigits(plzText) || !plzText.startsWith("44") || plzText.length() != 5) {
            displayAlert("Ungültige PLZ", "Eine gültige PLZ enthält nur fünf Ziffern und beginnt mit '44'.");
        }
    }

    private void validateInsurance(MouseEvent event) {
        if (!containsOnlyLetters(insurance.getText())) {
            displayAlert("Ungültige Versicherung", "Eine gültige Versicherung enthält nur Buchstaben.");
        }
    }

    private void validateBloodPressure(MouseEvent event) {
        if (!containsOnlyDigits(bloodPressure.getText())) {
            displayAlert("Ungültiger Blutdruck", "Ein gültiger Blutdruck enthält nur Ziffern.");
        }
    }

    private void validateBloodSugar(MouseEvent event) {
        if (!containsOnlyDigits(bloodSugar.getText())) {
            displayAlert("Ungültiger Blutzucker", "Ein gültiger Blutzucker enthält nur Ziffern.");
        }
    }

    private void validateTemperature(MouseEvent event) {
        if (!containsOnlyDigits(temprture.getText())) {
            displayAlert("Ungültige Temperatur", "Eine gültige Temperatur enthält nur Ziffern.");
        }
    }

    private void validateWeight(MouseEvent event) {
        if (!containsOnlyDigits(weight.getText())) {
            displayAlert("Ungültiges Gewicht", "Ein gültiges Gewicht enthält nur Ziffern.");
        }
    }

    private void validatePulse(MouseEvent event) {
        if (!containsOnlyDigits(pulse.getText())) {
            displayAlert("Ungültiger Puls", "Ein gültiger Puls enthält nur Ziffern.");
        }
    }

    @FXML
    protected void onClickAdd(Event event) throws IOException {
        String name = nameTextField.getText();
        boolean nameIsValid = containsOnlyLetters(name);

        String phone = phoneTextField.getText();
        boolean phoneIsValid = containsOnlyDigits(phone);

        String age = ageTextField.getText();
        boolean ageIsValid = containsOnlyDigits(age);
        if (Integer.parseInt(age) < 18) {
            ageIsValid = false;
        }

        String address = this.address.getText();
        boolean adresseIsValid = containsOnlyLetters(address);

        String hausNr = haus_Nr.getText();
        boolean hausNrIsValid = containsOnlyDigits(hausNr);

        String plzStr = plz.getText();
        boolean plzIsValid = containsOnlyDigits(plzStr);
        if (!plzStr.startsWith("44") || plzStr.length() != 5) {
            plzIsValid = false;
        }

        String insuranceComp = insurance.getText();
        boolean insuranceCompIsValid = containsOnlyLetters(insuranceComp);

        String bloodPre = bloodPressure.getText();
        boolean bloodPreIsValid = containsOnlyDigits(bloodPre);

        String bloodSug = bloodSugar.getText();
        boolean bloodSugIsValid = containsOnlyDigits(bloodSug);

        String bodyTemp = temprture.getText();
        boolean boyTempIsValid = containsOnlyDigits(bodyTemp);

        String bodyweight = weight.getText();
        boolean bodyweightIsValid = containsOnlyDigits(bodyweight);

        String heartPulse = pulse.getText();
        boolean heartPulseIsValid = containsOnlyDigits(heartPulse);

        String gender = genderComboBox.getValue();
        String symp = symptome.getText();

        if (!nameIsValid) {
            displayAlert("a valid name contains only letters such as \"Hadi\" ", "You have to write a valid name");
        } else if (!phoneIsValid) {
            displayAlert("a valid phone number contains only 12 numbers and beginn with 0 such as \"015712148823\" ",
                    "You have to write a valid phone number");
        } else if (!ageIsValid) {
            displayAlert("a valid age contains only numbers that are bigger than 17 such as \" 23 \" ",
                    "You have to write a valid age");
        } else if (!adresseIsValid) {
            displayAlert("a valid address contains only letters", "You have to write a valid address");
        } else if (!hausNrIsValid) {
            displayAlert("a valid haus number company contains only numbers", "You have to write a valid haus number");
        } else if (!plzIsValid) {
            displayAlert("a valid plz contains only five numbers and starts with \"44\" such as \"44135\"",
                    "You have to write a valid plz");
        } else if (!insuranceCompIsValid) {
            displayAlert("a valid insurance company contains only letters such as \" IKK \" ",
                    "You have to write a valid insurance company");
        } else if (!bloodPreIsValid) {
            displayAlert("a valid blood pressure contains only numbers such as \" 70 \" ",
                    "You have to write a valid blood pressure");
        } else if (!bloodSugIsValid) {
            displayAlert("a valid blood sugar contains only numbers such as \" 170 \" ",
                    "You have to write a valid blood sugar");
        } else if (!boyTempIsValid) {
            displayAlert("a valid body temperature contains only numbers such as \" 37.5 \" ",
                    "You have to write a valid body temperature");
        } else if (!bodyweightIsValid) {
            displayAlert("a valid body weight contains only numbers such as \" 82.4 \" ",
                    "You have to write a valid body weight");
        } else if (!heartPulseIsValid) {
            displayAlert("a valid heart pulse contains only numbers such as \" 83 \" ",
                    "You have to write a valid heart pulse");
        } else {

            int id = generateID();

            Patient patient = new Patient(id, name, age, phone, gender, address, Integer.parseInt(hausNr),
                    Integer.parseInt(plzStr));
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
                patientenKarte.add(patient);
                Position imgPosition = generatePositionCoordinates();
                patient.setPosition(imgPosition);
            } else {
                registeredPatient.addVisit(visit);
            }
            savePatientenInfo();
            openDashboard();
        }

    }

    @FXML
    private void savePatientenInfo() throws IOException {

        String patientenDB = "";
        for (int i = 0; i < patienten.size(); i++) {
            Patient patient = patienten.get(i);
            patientenDB = patientenDB + patient.getIndex() + "," + patient.getName() + "," + patient.getAge() + ","
                    + patient.getStreet() + "," + patient.getHausNumber() + "," + patient.getPlz() + ","
                    + patient.getPhoneNumber() + "," + patient.getInsurance() + "," + patient.getGender() + ","
                    + patient.getPosition().getX() + "," + patient.getPosition().getY() + ";";
            String visits = "";
            LinkedList<Visit> patientVisits = patient.getVisits();
            for (int j = 0; j < patientVisits.size(); j++) {
                Visit visit = patientVisits.get(j);
                visits = visits + visit.getBloodPressure() + "," + visit.getBloodSugar() + ","
                        + visit.getBodyTemperature() + "," + visit.getWeight() + "," + visit.getPulse() + ","
                        + visit.getSymptoms();
                if (j != patientVisits.size() - 1) {
                    visits = visits + "/";
                }
            }

            patientenDB = patientenDB + visits + ";";
            String behandlungen = "";
            LinkedList<String> behandlungenList = patient.getTreatments();
            for (int j = 0; j < behandlungenList.size(); j++) {
                behandlungen = behandlungen + behandlungenList.get(i);
                if (j != behandlungenList.size() - 1) {
                    behandlungen = behandlungen + ",";
                }
            }
            patientenDB = patientenDB + behandlungen + ";";
            String medikamente = "";
            LinkedList<Medicine> medicineListe = patient.getMedicines();
            for (int j = 0; j < medicineListe.size(); j++) {
                Medicine medicine = medicineListe.get(i);
                medikamente = medikamente + medicine.getName() + "," + medicine.getDosage() + ","
                        + medicine.getInstructionsForUse();
                if (j != medicineListe.size() - 1) {
                    medikamente = medikamente + "/";
                }
            }
            patientenDB = patientenDB + medikamente;
            if (i != patienten.size() - 1) {
                patientenDB = patientenDB + "\n";
            }
        }

        String fileName = "patientenInfo.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.write(patientenDB);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void displayAlert(String headerText, String messageText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Warning");
        alert.setHeaderText(headerText);
        alert.setContentText(messageText);
        alert.showAndWait();
    }

    @FXML
    protected void handleBackClick(Event event) throws IOException {
        openDashboard();
    }

    private void openDashboard() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ageTextField.getScene().getWindow();
        stage.setScene(scene);
    }

    private boolean containsOnlyLetters(String text) {
        boolean isValid = true;
        for (int i = 0; i < text.length(); i++) {
            if (!((text.charAt(i) >= 'A' && text.charAt(i) <= 'Z') || (text.charAt(i) >= 'a' && text.charAt(i) <= 'z'))) {
                isValid = false;
                break;
            }
        }
        if (text.trim().isEmpty()) {
            isValid = false;
        }
        return isValid;
    }

    private boolean containsOnlyDigits(String text) {
        boolean isValid = true;
        for (int i = 0; i < text.length(); i++) {
            if (!(text.charAt(i) >= '0' && text.charAt(i) <= '9')) {
                isValid = false;
                break;
            }
        }
        if (text.trim().isEmpty()) {
            isValid = false;
        }
        return isValid;
    }

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

    private Position generatePositionCoordinates() {
        Position imgPosition = null;
        do {
            double randomX = Math.round(349 + (899 - 349) * random.nextDouble());
            double randomY = Math.round(136 + (600 - 136) * random.nextDouble());
            imgPosition = new Position(randomX, randomY);
        } while (!isPositionValid(imgPosition));
        return imgPosition;
    }

    private boolean isPositionValid(Position imgPosition) {
        boolean isValid = true;
        for (int i = 0; i < patienten.size(); i++) {
            if (patienten.get(i).getPosition() != null && patienten.get(i).getPosition().equals(imgPosition)) {
                isValid = false;
            }
        }
        return isValid;
    }
}
