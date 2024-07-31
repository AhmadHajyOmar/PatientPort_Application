package com.example.praxissoftware;

public class Position {
    private double x;
    private double y;

    /**
     * Konstruktor, um ein Objekt vom Typ Position zu erstellen
     *
     * @param x der Wert der x-achse
     * @param y der Wert der y-achse
     */
    public Position(double x, double y){
        this.x = x;
        this.y = y;
    }

    /**
     * Die Methode gibt den Wert der x-achse zurück
     *
     * @return der Wert der x-achse
     */
    public double getX() {
        return x;
    }

    /**
     * Die Methode gibt den Wert der y-achse zurück
     *
     * @return der Wert der y-achse
     */
    public double getY() {
        return y;
    }

    /**
     * Die Methode gibt zurück, ob die aktuelle Position ist gleich eine andere übergebene Position
     *
     * @param p eine andere Position für die Vergleichung
     * @return true, falls beide Positionen gleiche sind, ansonsten false
     */
    public boolean compareTwoPositions(Position p){
        boolean isEqual = false;
        if(this.x == p.getX() && this.y == p.getY()){
            isEqual = true;
        }
        return isEqual;
    }

    /**
     * Die Methode gibt die Eigenschaften einer Position als Zeichenkette zurück
     *
     * @return die Eigenschaften einer Position als Zeichenkette
     */
    public String toString(){
        return "("+x+","+y+")";
    }
}
