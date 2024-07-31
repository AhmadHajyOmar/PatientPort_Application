package com.example.praxissoftware;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class TreatmentController extends LoadNewScene{

    @FXML
    private TextField treatments;

    @FXML
    private TextField medicineInstructions;

    @FXML
    private TextField medicineDosage;

    @FXML
    private TextField medicineName;

    public static Patient currentPatient;

    /**
     * Die Methode, wird aufgerufen, wenn der Nutzer auf der Pfeil (ganz Oben - Links) klicken
     * um eine neue Scene "city" in der aktuellen Stage zu laden.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    protected void handleBackClick(Event event) throws IOException {
        Stage stage = (Stage) medicineName.getScene().getWindow();
        loadScene("city.fxml", stage, 990, 766);
    }

    /**
     * Die Methode wird aufgerufen, wenn wir auf confirm klicken, um ein neues Medikament hinzufügen.
     * Die Methode überprüft, ob alle nötwendige Eingaben (Behandlung, Name - Dosierung - Einnahmeweise des Medikamentes)
     * nicht leer sind, wenn es der Fall ist, wird ein neues Medikament in der entsprechenden Liste hinzufügt
     *
     * @param event
     */
    @FXML
    protected void onClickAddtreatment(Event event){
        String treatmentPlan = treatments.getText();
        String medicine_Name = medicineName.getText();
        String dosage = medicineDosage.getText();
        String instructions = medicineInstructions.getText();

        if(!medicine_Name.trim().isEmpty()
                && !treatmentPlan.trim().isEmpty()
                && !dosage.trim().isEmpty()
                && !instructions.trim().isEmpty()){
            Medicine medicine = new Medicine(medicine_Name,Double.parseDouble(dosage), instructions);
            currentPatient.addMedicine(medicine);
            currentPatient.addTreatment(treatmentPlan);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setContentText("New treatment and medicines has been added");
            alert.showAndWait();
            treatments.setText(null);
            medicineName.setText(null);
            medicineDosage.setText(null);
            medicineInstructions.setText(null);
        }

    }
}
