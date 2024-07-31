package com.example.praxissoftware;

public class Medicine {
    private String name;
    private double dosage;
    private String instructionsForUse;

    /**
     * Konstruktor um ein Objekt vom Typ Medikament zu erstellen.
     *
     * @param name Name des Medikaments
     * @param dosierung Dosierung des Medikaments
     * @param einnahmehinweise Einnahmeweise des Medikaments
     */
    public Medicine(String name, double dosierung, String einnahmehinweise){
        this.name = name;
        this.dosage = dosierung;
        this.instructionsForUse = einnahmehinweise;
    }

    /**
     * Die Methode gibt der Name des Medikaments zurück
     *
     * @return Name des Medikaments
     */
    public String getName() {
        return name;
    }

    /**
     * Die Methode setzt der Name des Medikaments
     *
     * @param name der neue Name des Medikaments
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Die Methode gibt die Dosierung des Medikaments zurück
     *
     * @return Dosierung des Medikaments
     */
    public double getDosage() {
        return dosage;
    }

    /**
     * Die Methode gibt die Einnahmeweise des Medikaments zurück
     *
     * @return Einnahmeweise des Medikaments
     */
    public String getInstructionsForUse() {
        return instructionsForUse;
    }

}
