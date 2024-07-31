package com.example.praxissoftware;
import java.time.LocalDate;

/**
 * Die Klasse Visit repräsentiert einen Patientenbesuch und enthält Informationen 
 * wie Blutdruck, Blutzucker, Puls, Körpertemperatur, Gewicht und Symptome.
 */
public class Visit {

    private LocalDate visitDate;
    private String bloodPressure;
    private String bloodSugar;
    private String pulse;
    private String bodyTemperature;
    private Double weight;
    private String symptoms;

    /**
     * Konstruktor zur Initialisierung eines Besuchs mit den angegebenen Parametern.
     *
     * @param bloodPressure der Blutdruck des Patienten
     * @param bloodSugar der Blutzuckerspiegel des Patienten
     * @param pulse der Puls des Patienten
     * @param bodyTemperature die Körpertemperatur des Patienten
     * @param weight das Gewicht des Patienten
     * @param symptoms die Symptome des Patienten
     */
    public Visit(String bloodPressure, String bloodSugar, String pulse, String bodyTemperature, Double weight, String symptoms) {
        this.bloodPressure = bloodPressure;
        this.bloodSugar = bloodSugar;
        this.pulse = pulse;
        this.bodyTemperature = bodyTemperature;
        this.weight = weight;
        this.symptoms = symptoms;
    }

    /**
     * Gibt den Blutdruck des Patienten zurück.
     *
     * @return der Blutdruck des Patienten
     */
    public String getBloodPressure() {
        return bloodPressure;
    }

    /**
     * Gibt den Blutzuckerspiegel des Patienten zurück.
     *
     * @return der Blutzuckerspiegel des Patienten
     */
    public String getBloodSugar() {
        return bloodSugar;
    }

    /**
     * Gibt den Puls des Patienten zurück.
     *
     * @return der Puls des Patienten
     */
    public String getPulse() {
        return pulse;
    }

    /**
     * Gibt die Körpertemperatur des Patienten zurück.
     *
     * @return die Körpertemperatur des Patienten
     */
    public String getBodyTemperature() {
        return bodyTemperature;
    }

    /**
     * Gibt das Gewicht des Patienten zurück.
     *
     * @return das Gewicht des Patienten
     */
    public Double getWeight() {
        return weight;
    }

    /**
     * Gibt die Symptome des Patienten zurück.
     *
     * @return die Symptome des Patienten
     */
    public String getSymptoms() {
        return symptoms;
    }
}