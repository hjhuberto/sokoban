package com.company.elements;

import java.util.List;

/**
 *  Klasa umożliwiająca utworzenie obiektów Player
 * (obiektów będących przeszkodami).
 */

public class Player implements Movable{

    private int startX;
    private int startY;
    private int direction;

    /**
     * Metoda zwracająca wspołrzedna X przez obiekty klasy Player
     */

    @Override
    public int getStartX() {
        return this.startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    /**
     * Metoda zwracajaca wspolrzedną Y przez obiekty klasy Player
     */
    @Override
    public int getStartY() {
        return this.startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }


    public int getDirection() {return this.direction;}

    public void setDirection(int direction) {this.direction = direction;}



    @Override
    public void move(int x, int y) {
        if (x == 1 && y == 0) {
            this.setDirection(1);
        } else if (x == 0 && y == 1){
            this.setDirection(2);
        }else if (x == -1 && y == 0){
            this.setDirection(3);
        }else if (x == 0 && y == -1){
            this.setDirection(4);
        }
        this.setStartX(this.getStartX() + x);
        this.setStartY(this.getStartY() + y);
    }

    @Override
    public boolean collision(int direction, Element element){
        if (direction == 1) {
            if (((this.getStartX() + 1) == element.getStartX()) && (this.getStartY() == element.getStartY())) {
                return true;
            } else {
                return false;
            }
        }
        else if (direction == 2) {
            if ((this.getStartX() == element.getStartX()) && ((this.getStartY() + 1) == element.getStartY())) {
                return true;
            } else {
                return false;
            }
        }
        else if (direction == 3) {
            if (((this.getStartX() - 1) == element.getStartX()) && (this.getStartY() == element.getStartY())) {
                return true;
            } else {
                return false;
            }
        }
        else if (direction == 4) {
            if ((this.getStartX() == element.getStartX()) && ((this.getStartY() - 1) == element.getStartY())) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean wallCollision(int direction, Element element, List<Wall> walls){
        if (direction == 1){
            for (int i = 0; i < walls.size(); i++) {
                Wall wall = walls.get(i);
                if (this.collision(1, wall)) {
                    return true;
                }
            }
            return false;
        }
        else if (direction == 2){
            for (int i = 0; i < walls.size(); i++) {
                Wall wall = walls.get(i);
                if (this.collision(2, wall)) {
                    return true;
                }
            }
            return false;
        }
        else if (direction == 3){
            for (int i = 0; i < walls.size(); i++) {
                Wall wall = walls.get(i);
                if (this.collision(3, wall)) {
                    return true;
                }
            }
            return false;
        }
        else if (direction == 4){
            for (int i = 0; i < walls.size(); i++) {
                Wall wall = walls.get(i);
                if (this.collision(4, wall)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }



    @Override
    public Crate crateCollision(int direction, Element element, List<Crate> crates){
        if(direction == 1){
            for (int i = 0; i < crates.size(); i++){
                Crate crate = crates.get(i);
                if (this.collision(1, crate)){
                    return crate;
                }
            }
            return null;
        }
        else if(direction == 2){
            for (int i = 0; i < crates.size(); i++){
                Crate crate = crates.get(i);
                if (this.collision(2, crate)){
                    return crate;
                }
            }
            return null;
        }
        else if(direction == 3){
            for (int i = 0; i < crates.size(); i++){
                Crate crate = crates.get(i);
                if (this.collision(3, crate)){
                    return crate;
                }
            }
            return null;
        }
        else if(direction == 4){
            for (int i = 0; i < crates.size(); i++){
                Crate crate = crates.get(i);
                if (this.collision(4, crate)){
                    return crate;
                }
            }
            return null;
        }
        return null;
    }


}
