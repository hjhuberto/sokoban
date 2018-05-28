package com.company.elements;

import java.util.List;

public interface Movable extends Element {

    boolean collision(int direction, Element element);
    boolean wallCollision(int direction, Element element, List<Wall> walls);
    Crate crateCollision(int direction, Element element, List<Crate> crates);
    void move (int x, int y);
}
