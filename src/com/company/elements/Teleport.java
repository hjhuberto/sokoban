package com.company.elements;

import java.util.List;

public class Teleport implements Movable {

    private int startX;
    private int startY;
    private int direction;
    private boolean isVisible;

    @Override
    public int getStartX() {
        return this.startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }


    @Override
    public int getStartY() {
        return this.startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }


    public int getDirection() {return this.direction;}

    public void setDirection(int direction) {this.direction = direction;}

    public boolean getVisible() {return this.isVisible;}

    public void setVisible(boolean visible) {this.isVisible = visible;}



    public Teleport(){
        this.isVisible = false;
    }
    public void move(int x, int y) {
        this.setStartX(this.getStartX() + x);
        this.setStartY(this.getStartY() + y);
    }

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
