package com.example.praxissoftware;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class ChartController extends LoadNewScene implements Initializable {

    @FXML
    private TextArea name;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView back;

    @FXML
    private LineChart<Number, Number> lineChart;

    public static String dataName;
    public static String patientName;
    public static LinkedList<Visit> visits = new LinkedList<>();

    /**
     * Die Methode initialisiert die aktuelle Scene.
     * Die Methode erzeugt ein Liniendiagramm mit der Informationen der Patient.
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setText(patientName);
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of Visits");
        yAxis.setLabel(dataName);

        lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setLayoutX(39);
        lineChart.setLayoutY(187);
        lineChart.setPrefWidth(700);
        lineChart.setPrefHeight(500);
        lineChart.setTitle("Patientport, 2024");

        XYChart.Series<Number, Number> funktion = new XYChart.Series<>();
        funktion.setName(dataName);
        LinkedList<Double> data = new LinkedList<>();
        if(dataName.equals("Blood Pressure")){
            for(int i = 0; i < visits.size(); i++){
                data.add(Double.parseDouble(visits.get(i).getBloodPressure()));
            }
        }
        if(dataName.equals("Blood Sugar")){
            for(int i = 0; i < visits.size(); i++){
                data.add(Double.parseDouble(visits.get(i).getBloodSugar()));
            }
        }
        if(dataName.equals("Body Temperature")){
            for(int i = 0; i < visits.size(); i++){
                data.add(Double.parseDouble(visits.get(i).getBodyTemperature()));
            }
        }
        if(dataName.equals("Weight")){
            for(int i = 0; i < visits.size(); i++){
                data.add(visits.get(i).getWeight());
            }
        }
        if(dataName.equals("Pulse")){
            for(int i = 0; i < visits.size(); i++){
                data.add(Double.parseDouble(visits.get(i).getPulse()));
            }
        }

        for(int i = 0; i < data.size(); i++){
            funktion.getData().add(new XYChart.Data<>(i, data.get(i)));
        }

        lineChart.getData().add(funktion);
        anchorPane.getChildren().add(lineChart);
    }

    /**
     * Die Methode lädt die neue Scene "patientenInfo" in der aktuellen Stage.
     * Die Methode wird ausgeführt, wenn der Nutzer auf der weissen Pfeil (Oben-Links in Stage) klickt.
     *
     * @param event
     * @throws IOException
     */
    public void handleBackClick(Event event) throws IOException {
        Stage stage = (Stage) back.getScene().getWindow();
        loadScene("patientenInfo.fxml", stage, 989, 755);
    }

}
