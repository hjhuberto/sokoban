package com.company;

import com.company.elements.Crate;
import com.company.elements.Finish;
import com.company.elements.Player;
import com.company.elements.Wall;

import java.util.List;

/**
 * Klasa odopowiedzialna za przypisanie danych (z pliku konfiuguracyjnego) do obiektow.
 */

public class Level {

    private Player player;
    private List<Wall> walls;
    private List<Crate> crates;
    private List<Finish> finishes;


    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<Wall> getWalls() {
        return this.walls;
    }

    public void setWalls(List<Wall> walls) {
        this.walls = walls;
    }

    public List<Crate> getCrates() {
        return this.crates;
    }

    public void setCrates(List<Crate> crates) {
        this.crates = crates;
    }

    public List<Finish> getFinishes() {
        return this.finishes;
    }

    public void setFinishes(List<Finish> finishes) {
        this.finishes = finishes;
    }
}
