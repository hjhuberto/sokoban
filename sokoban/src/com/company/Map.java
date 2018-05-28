package com.company;

import com.company.elements.Element;
import com.company.elements.Player;
import com.company.elements.Wall;
import com.company.elements.Crate;
import com.company.elements.Finish;

import java.util.ArrayList;
import java.util.List;


/**
 * Klasa odpowiedzialna za przechowywanie wszystkich elementow rozgrywki takich jak:
 * sciany, skrzynie, miejsca docelowe dla skrzyn oraz gracz.
 */

public class Map {

    private List<Element> elements;
    private Element[][] map;
    private int size;
    private int boardSize;


    /**
     * Konstruktor Mapy inicjalizujacy elementy potrzebne do odczytania rozmiarow planszy.
     */
    public Map(){
        this.size = 12;
        this.boardSize = 696;
    }

    /**
     * Konstruktor Mapy inicjalizujacy elementy gry.
     */
    public Map(List<Wall> walls, Player player, List<Crate> crates, List<Finish> finishes){
        this.elements = new ArrayList<>();
        this.elements.addAll(finishes);
        this.elements.addAll(walls);
        this.elements.add(player);
        this.elements.addAll(crates);
        this.size = 12;
    }

    /**
     * Metoda odpowiedzialna za zwrocenie tablicy elementow.
     * Jesli tablica jest pusta, wywolywana jest metoda resetMap();
     */
    public Element[][] getMapArray() {
            this.resetMap();
        return this.map;
    }

    /**
      * @return ilosc elementow tablicy elementow
     */
    public int getSize(){
        return this.size;
    }

    /**
     * @return rozmiar plaszy
     */
    public int getBoardSize(){
        return this.boardSize;
    }

    /**
     * @return rozmiar elementu
     */
    public int getElementSize(){
        return this.boardSize/this.size;
    }

    /**
     * Metoda wpisujaca wartosci(wspolrzedne) elementow do tablicy.
     */
    public void resetMap() {
        Element[][] newMap = new Element[size][size];
        for (Element element : elements) {
            newMap[element.getStartY()][element.getStartX()] = element;
        }
        map = newMap;
    }

    /**
     * Metoda wpisujaca wartosci(wspolrzedne) elementow do tablicy.
     */
    public void updateMap(List<Wall> walls, Player player, List<Crate> crates, List<Finish> finishes){
        this.elements.clear();
        this.elements.addAll(finishes);
        this.elements.addAll(walls);
        this.elements.add(player);
        this.elements.addAll(crates);
    }


}
