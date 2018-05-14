package com.company.elements;


/**
 *  Klasa umozliwiajaca utworzenie obiektow Finish
 * (obiektow bedacych miejscami docelowymi dla skrzyn).
 */

public class Finish implements Element {

    private int startX;
    private int startY;

    /**
     * Metoda zwracajaca wspołrzedną X przez obiekty klasy Finish
     */
    @Override
    public int getStartX() {return this.startX; }

    /**
     * Metoda zwracajaca wspołrzedną X przez obiekty klasy Finish
     */
    public void setStartX(int startX) { this.startX = startX; }

    /**
     * Metoda zwracajaca wspołrzedną Y przez obiekty klasy Finish
     */
    @Override
    public int getStartY() {return this.startY; }

    public void setStartY(int startY) {this.startY = startY; }
}