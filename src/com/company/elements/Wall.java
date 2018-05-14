package com.company.elements;

/**
 * * Klasa umozliwiajaca utworzenie obiektow Wall
 * (obiektow bedacych przeszkodami).
 */

public class Wall implements Element {

    private int startX;
    private int startY;

    /**
     * Metoda zwracajaca wspolrzedna X przez obiekty klasy Wall
     */
    @Override
    public int getStartX() {
        return this.startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    /**
     * Metoda zwracajaca wspolrzedna Y przez obiekty klasy Wall
     */
    @Override
    public int getStartY() {
        return this.startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }
}