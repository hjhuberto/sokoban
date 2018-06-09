package com.company;

import com.company.elements.Element;
import com.company.elements.Player;
import com.company.elements.Wall;
import com.company.elements.Crate;
import com.company.elements.Finish;

import javax.swing.*;
import java.awt.*;





/**
 * Klasa odopowiedzialna za wyswietlenie graficznych elementow aplikacji.
 */

public class View2 extends JPanel {


    private Element[][] elements;
    private Map sizeMap;
    private Crate crate;
    private Player player;
    private int direction;
    private int dX;

    /**
     * Konstruktor klasy View2.
     */
    public View2(){
        sizeMap = new Map();
        repaint();
    }

    /**
     * Metoda przekazujaca tablice elementow.
     */
    public void setElements(Element[][] elements) {
        this.elements = elements;
    }

    public void setElements2(Element[][] elements, Player p, Crate c, int dir, int dX) {
        this.player = p;
        this.crate = c;
        this.elements = elements;
        this.direction = dir;
        this.dX = dX;
    }


    /**
     * Metoda opisująca sposob rysowania elementow rozgrywki.
     */
    public void paint(Graphics g) {

        for (int i = 0; i < elements.length; i++) {
            for (int j = 0; j < elements.length; j++) {
                Element element = elements[i][j];
                if (element instanceof Wall) {
                    g.setColor(Color.blue);
                    g.fillRect(j * sizeMap.getElementSize(), i * sizeMap.getElementSize(), sizeMap.getElementSize(), sizeMap.getElementSize());
                } else if (element instanceof Finish) {
                    if(((Finish) element).getFinished() == true) {
                        g.setColor(Color.green);
                        g.fillRect(j * sizeMap.getElementSize(), i * sizeMap.getElementSize(), sizeMap.getElementSize(), sizeMap.getElementSize());
                    }
                    else {
                        g.setColor(Color.yellow);
                        g.fillRect(j * sizeMap.getElementSize(), i * sizeMap.getElementSize(), sizeMap.getElementSize(), sizeMap.getElementSize());
                    }
                }
                else {
                    g.setColor(Color.gray);
                    g.fillRect(j * sizeMap.getElementSize(), i * sizeMap.getElementSize(), sizeMap.getElementSize(), sizeMap.getElementSize());
                }

            }
        }

        if (this.direction == 0) {
            for (int i = 0; i < elements.length; i++) {
                for (int j = 0; j < elements.length; j++) {
                    Element element = elements[i][j];
                    if (element instanceof Player) {
                        g.setColor(Color.cyan);
                        g.fillOval(((j) * sizeMap.getElementSize()), i * sizeMap.getElementSize(), sizeMap.getElementSize(), sizeMap.getElementSize());
                    } else if (element instanceof Crate) {
                        g.setColor(Color.magenta);
                        g.fillRect(j * sizeMap.getElementSize(), i * sizeMap.getElementSize(), sizeMap.getElementSize(), sizeMap.getElementSize());
                    }
                }
            }
        }

        if (this.direction == 1) {
            for (int i = 0; i < elements.length; i++) {
                for (int j = 0; j < elements.length; j++) {
                    Element element = elements[i][j];
                    if (element instanceof Player) {
                        g.setColor(Color.cyan);
                        g.fillOval(((j - 1) * sizeMap.getElementSize()) + dX, i * sizeMap.getElementSize(), sizeMap.getElementSize(), sizeMap.getElementSize());
                    }
                    if (element instanceof Crate){
                        if (((Crate) element).getDirection() == 1) {
                            g.setColor(Color.magenta);
                            g.fillRect((j - 1) * sizeMap.getElementSize() + dX, i * sizeMap.getElementSize(), sizeMap.getElementSize(), sizeMap.getElementSize());
                        }
                        else {
                            g.setColor(Color.magenta);
                            g.fillRect(j * sizeMap.getElementSize(), i * sizeMap.getElementSize(), sizeMap.getElementSize(), sizeMap.getElementSize());
                        }
                    }
                }
            }
        }

        if (this.direction == 2) {
            for (int i = 0; i < elements.length; i++) {
                for (int j = 0; j < elements.length; j++) {
                    Element element = elements[i][j];
                    if (element instanceof Player) {
                        g.setColor(Color.cyan);
                        g.fillOval(j * sizeMap.getElementSize(), (i-1) * sizeMap.getElementSize()+dX, sizeMap.getElementSize(), sizeMap.getElementSize());
                    }
                    if (element instanceof Crate){
                        if (((Crate) element).getDirection() == 2) {
                            g.setColor(Color.magenta);
                            g.fillRect(j * sizeMap.getElementSize(), (i-1) * sizeMap.getElementSize()+dX, sizeMap.getElementSize(), sizeMap.getElementSize());
                        }
                        else {
                            g.setColor(Color.magenta);
                            g.fillRect(j * sizeMap.getElementSize(), i * sizeMap.getElementSize(), sizeMap.getElementSize(), sizeMap.getElementSize());
                        }
                    }
                }
            }
        }

        if (this.direction == 3) {
            for (int i = 0; i < elements.length; i++) {
                for (int j = 0; j < elements.length; j++) {
                    Element element = elements[i][j];
                    if (element instanceof Player) {
                        g.setColor(Color.cyan);
                        g.fillOval((j+1) * sizeMap.getElementSize()-dX, i * sizeMap.getElementSize(), sizeMap.getElementSize(), sizeMap.getElementSize());
                    }
                    if (element instanceof Crate){
                        if (((Crate) element).getDirection() == 3) {
                            g.setColor(Color.magenta);
                            g.fillRect((j+1) * sizeMap.getElementSize()-dX, i * sizeMap.getElementSize(), sizeMap.getElementSize(), sizeMap.getElementSize());
                        }
                        else {
                            g.setColor(Color.magenta);
                            g.fillRect(j * sizeMap.getElementSize(), i * sizeMap.getElementSize(), sizeMap.getElementSize(), sizeMap.getElementSize());
                        }
                    }
                }
            }
        }

        if (this.direction == 4) {
            for (int i = 0; i < elements.length; i++) {
                for (int j = 0; j < elements.length; j++) {
                    Element element = elements[i][j];
                    if (element instanceof Player) {
                        g.setColor(Color.cyan);
                        g.fillOval(j * sizeMap.getElementSize(), (i+1) * sizeMap.getElementSize()-dX, sizeMap.getElementSize(), sizeMap.getElementSize());
                    }
                    if (element instanceof Crate){
                        if (((Crate) element).getDirection() == 4) {
                            g.setColor(Color.magenta);
                            g.fillRect(j * sizeMap.getElementSize(), (i+1) * sizeMap.getElementSize()-dX, sizeMap.getElementSize(), sizeMap.getElementSize());
                        }
                        else {
                            g.setColor(Color.magenta);
                            g.fillRect(j * sizeMap.getElementSize(), i * sizeMap.getElementSize(), sizeMap.getElementSize(), sizeMap.getElementSize());
                        }
                    }

                }
            }
        }
    }

    public void print(Element[][] elements) {
        for(int i = 0; i < elements.length; i++){
            for(int j = 0; j < elements.length; j++){
                Element element =  elements[i][j];
                if(element == null){
                    System.out.print('x');
                } else {
                    System.out.print('@');
                }
            }
            System.out.println();
        }
        System.out.println("-------------------------------");
    }
}









