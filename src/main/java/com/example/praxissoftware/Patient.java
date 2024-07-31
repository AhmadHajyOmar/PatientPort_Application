package com.example.praxissoftware;


import javafx.scene.image.ImageView;

import java.util.LinkedList;

public class Patient {
    private int index;
    private String name;
    private String age;
    private String phoneNumber;
    private String gender;
    private String insurance;
    private String street;
    private int hausNumber;
    private int plz;
    private double distance;
    private Position position;
   private ImageView imageView;
   private boolean registered;
   private LinkedList<String> treatments = new LinkedList<>();

   private LinkedList<Medicine> medicines;
   private LinkedList<Visit> visits;
   private String symptom;

    /**
     * Konstruktor um ein Objekt vom Typ Patient zu erstellen.
     *
     * @param index Die Identifikationsnummer dem Patients
     * @param name Der Name dem Patients
     * @param age Der Alter dem Patients
     * @param phoneNumber Die Tele-nummer dem Patients
     * @param gender Das Geschlecht dem Patients
     * @param street Der Name der Strasse dem Patients
     * @param hausNumber Die Hausnummer dem Patients
     * @param plz Die Postleitzahl dem Patients
     */
  public Patient(int index, String name, String age, String phoneNumber, String gender, String street, int hausNumber, int plz) {
        this.index = index;
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.street = street;
        this.hausNumber = hausNumber;
        this.plz = plz;
        this.visits = new LinkedList<>();
        this.medicines = new LinkedList<>();
    }

    /**
     * Die Methode gibt der Name dem Patients zurück
     *
     * @return Der Name dem Patients
     */
    public String getName() {
        return name;
    }

    /**
     * Die Methode setzt der Name dem Patients
     *
     * @param name der neue Name dem Patients
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Die Methode gibt der Alter dem Patients
     *
     * @return Der Alter dem Patients.
     */
    public String getAge() {
        return age;
    }

    /**
     * Die Methode gibt die Tele-Nummer dem Patients zurück.
     *
     * @return die Tele-Nummer dem Patients.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Die Methode gibt des Geschlechtes dem Patients zurück.
     *
     * @return Das Geschlecht dem Patients.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Die Methode gibt die Identifikationsnummer dem Patients zurück
     *
     * @return die Identifikationsnummer dem Patients
     */
    public int getIndex() {
        return index;
    }

    /**
     * Die Methode gibt der Name der Krankenversicherung dem Patients zurück.
     *
     * @return der Name der Krankenversicherung dem Patients
     */
    public String getInsurance() {
        return insurance;
    }

    /**
     * Die Methode setzt der Name der Krankenversicherung dem Patients
     *
     * @param insurance der Name der Krankenversicherung dem Patients
     */
    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    /**
     * Die Methode gibt der Name der Strasse dem Patients zurück
     *
     * @return der Name der Strasse der Patient
     */
    public String getStreet() {
        return street;
    }

    /**
     * Die Methode gibt die Hausnummer dem Patients zurück
     *
     * @return die Hausnummer der Paient
     */
    public int getHausNumber() {
        return hausNumber;
    }

    /**
     * Die Methode gibt die Postleitzahl dem Patients zurück
     *
     * @return die Postleitzahl dem Patients
     */
    public int getPlz() {
        return plz;
    }

    /**
     * Die Methode gibt zurück, ob der Patient schon registriert ist oder nicht
     *
     * @return ob der Patient registriert ist
     */
    public boolean isRegistered() {
        return registered;
    }

    /**
     * Die Methode setzt, ob der Patient schon registriert ist oder nicht
     *
     * @param registered registriert oder nicht als boolescher Wert
     */
    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    /**
     * Die Methode gibt den Abstand zwischen der Adresse der Praxis und der Patient zurück
     *
     * @return der Abstand zwischen beide Adressen (Praxis und Patient)
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Die Methode setzt den Abstand  zwischen der Adresse der Praxis und des Patients
     *
     * @param distance der Abstand zwischen beide Adressen (Praxis und Patient)
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * Die Methode gibt die Position des Patients zurück
     *
     * @return die Position des Patients (x,y)
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Die Methode setzt die Position des Patients
     *
     * @param position die Position des Patients (x,y)
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Die Methode gibt das Bild der Adresse des Patients zurück
     *
     * @return das Bild der Adresse des Patients
     */
    public ImageView getImageView() {
        return imageView;
    }

    /**
     * Die Methode setzt das Bild der Adresse des Patients
     *
     * @param imageView das Bild der Adresse des Patients
     */
    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    /**
     * Die Methode setzt die Symptome des Patients
     *
     * @param symptom die neuen Symptome des Patients
     */
    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    /**
     * Die Methode gibt die Liste alle Medikamente zurück
     *
     * @return die Liste alle Medikamente
     */
    public LinkedList<Medicine> getMedicines() {
        return medicines;
    }

    /**
     * Die Methode setzt die Liste alle Medikamente
     *
     * @param medicines die neue Liste alle Medikamente
     */
    public void setMedicines(LinkedList<Medicine> medicines) {
        this.medicines = medicines;
    }

    /**
     * Die Methode fügt in der Liste der Medikamente ein neues Medikament hinzu
     *
     * @param m ein Medikament, das in der Liste hinzufügt werden muss
     */
    public void addMedicine(Medicine m){
      medicines.add(m);
    }

    /**
     * Die Methode gibt die Liste alle Besuche zurück
     *
     * @return die Liste alle Besuche
     */
    public LinkedList<Visit> getVisits() {
        return visits;
    }

    /**
     * Die Methode setzt die Liste alle Besuche
     *
     * @param visits die neue Liste alle Besuche
     */
    public void setVisits(LinkedList<Visit> visits) {
        this.visits = visits;
    }

    /**
     * Die Methode fügt in der Liste der Besuche ein neuer Besuch hinzu
     *
     * @param v ein neuer Besuch, das in der Liste hinzufügt werden muss
     */
    public void addVisit(Visit v){
      visits.add(v);
    }

    /**
     * Die Methode gibt die Liste alle Behandlungen zurück
     *
     * @return die Liste alle Behandlungen
     */
    public LinkedList<String> getTreatments() {
        return treatments;
    }

    /**
     * Die Methode setzt die Liste alle Behandlungen zurück
     *
     * @param behandlungen die Liste alle Behandlungen
     */
    public void setTreatments(LinkedList<String> behandlungen) {
        this.treatments = behandlungen;
    }


    /**
     * Die Methode fügt in der Liste der Behandlungen eine neue Behandlung hinzu
     *
     * @param behandlung eine neue Behandlung, das in der Liste hinzufügt werden muss
     */
    public void addTreatment(String behandlung){
      treatments.add(behandlung);
    }
}
