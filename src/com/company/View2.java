package com.company;

import com.company.elements.Element;
import com.company.elements.Player;
import com.company.elements.Wall;
import com.company.elements.Crate;
import com.company.elements.Finish;
import com.company.elements.Teleport;

import javax.swing.*;
import java.awt.*;





/**
 * Klasa odopowiedzialna za wyswietlenie graficznych elementow aplikacji.
 */

public class View2 extends JPanel {


    private Element[][] elements;
    private JPanel panel1;
    private Map sizeMap;
    private int direction;
    private int dX;
    private Player player;
    private Crate crate;
    private Parameters parameters;
    private Clock clock;
    private Teleport teleport;

    /**
     * Konstruktor klasy View2.
     */
    public View2(){
        this.parameters = new Parameters();
        this.clock = new Clock();
        sizeMap = new Map();
        repaint();
    }

    public void getParameters(Parameters p){
        this.parameters.setNewPlayerNick(p.getNewPlayerNick());
        this.parameters.setNewPlayerScore(p.getNewPlayerScore());

        this.parameters.setDifficultyLevel(p.getDifficultyLevel());
        this.parameters.setNumberOfResets(p.getNumberOfResets());
    }
    public void getNumberOfMoves(Parameters p){
        this.parameters.setNumberOfMoves(p.getNumberOfMoves());
    }
    void countScore(){this.parameters.setNewPlayerScore(this.parameters.getNumberOfMoves() * 7);}

    public int getCompleteLevelScore(){return this.parameters.getNewPlayerScore()+7;}


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

    public void setTeleport(Teleport teleport) {this.teleport = teleport;}

    public Teleport getTeleport() {return this.teleport;}


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
            if (teleport.getVisible() == true) {
                g.setColor(Color.red);
                g.fillOval(((teleport.getStartX()) * sizeMap.getElementSize()), teleport.getStartY() * sizeMap.getElementSize(), sizeMap.getElementSize(), sizeMap.getElementSize());
            }
        }

        if (this.direction == 1) {

            for (int i = 0; i < elements.length; i++) {
                for (int j = 0; j < elements.length; j++) {
                    Element element = elements[i][j];
                    if (element instanceof Player){
                        if (((Player) element).getDirection() == 1) {
                            g.setColor(Color.cyan);
                            g.fillOval((j - 1) * sizeMap.getElementSize() + dX, i * sizeMap.getElementSize(), sizeMap.getElementSize(), sizeMap.getElementSize());
                        }
                        else {
                            g.setColor(Color.cyan);
                            g.fillOval(j * sizeMap.getElementSize(), i * sizeMap.getElementSize(), sizeMap.getElementSize(), sizeMap.getElementSize());
                        }
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
            if (teleport.getVisible() == true) {
                g.setColor(Color.red);
                g.fillOval(((teleport.getStartX() - 1) * sizeMap.getElementSize()) + dX, teleport.getStartY() * sizeMap.getElementSize(), sizeMap.getElementSize(), sizeMap.getElementSize());
            }
        }

        if (this.direction == 2) {
            for (int i = 0; i < elements.length; i++) {
                for (int j = 0; j < elements.length; j++) {
                    Element element = elements[i][j];
                    if (element instanceof Player){
                        if (((Player) element).getDirection() == 2) {
                            g.setColor(Color.cyan);
                            g.fillOval(j * sizeMap.getElementSize(), (i-1) * sizeMap.getElementSize()+dX, sizeMap.getElementSize(), sizeMap.getElementSize());
                        }
                        else {
                            g.setColor(Color.cyan);
                            g.fillOval(j * sizeMap.getElementSize(), i * sizeMap.getElementSize(), sizeMap.getElementSize(), sizeMap.getElementSize());
                        }
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
            if (teleport.getVisible() == true) {
                g.setColor(Color.red);
                g.fillOval(teleport.getStartX() * sizeMap.getElementSize(), (teleport.getStartY() - 1) * sizeMap.getElementSize() + dX, sizeMap.getElementSize(), sizeMap.getElementSize());
            }
        }

        if (this.direction == 3) {

            for (int i = 0; i < elements.length; i++) {
                for (int j = 0; j < elements.length; j++) {
                    Element element = elements[i][j];
                    if (element instanceof Player){
                        if (((Player) element).getDirection() == 3) {
                            g.setColor(Color.cyan);
                            g.fillOval((j+1) * sizeMap.getElementSize()-dX, i * sizeMap.getElementSize(), sizeMap.getElementSize(), sizeMap.getElementSize());
                        }
                        else {
                            g.setColor(Color.cyan);
                            g.fillOval(j * sizeMap.getElementSize(), i * sizeMap.getElementSize(), sizeMap.getElementSize(), sizeMap.getElementSize());
                        }
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
            if (teleport.getVisible() == true) {
                g.setColor(Color.red);
                g.fillOval((teleport.getStartX() + 1) * sizeMap.getElementSize() - dX, teleport.getStartY() * sizeMap.getElementSize(), sizeMap.getElementSize(), sizeMap.getElementSize());
            }
        }

        if (this.direction == 4) {
            for (int i = 0; i < elements.length; i++) {
                for (int j = 0; j < elements.length; j++) {
                    Element element = elements[i][j];
                    if (element instanceof Player){
                        if (((Player) element).getDirection() == 4) {
                            g.setColor(Color.cyan);
                            g.fillOval(j * sizeMap.getElementSize(), (i+1) * sizeMap.getElementSize()-dX, sizeMap.getElementSize(), sizeMap.getElementSize());
                        }
                        else {
                            g.setColor(Color.cyan);
                            g.fillOval(j * sizeMap.getElementSize(), i * sizeMap.getElementSize(), sizeMap.getElementSize(), sizeMap.getElementSize());
                        }
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
            if (teleport.getVisible() == true) {
                g.setColor(Color.red);
                g.fillOval(teleport.getStartX() * sizeMap.getElementSize(), (teleport.getStartY() + 1) * sizeMap.getElementSize() - dX, sizeMap.getElementSize(), sizeMap.getElementSize());
            }
        }
        // score
        this.countScore();
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 15));
        g.drawString("Your nick: " + parameters.getNewPlayerNick() + "  |  Your scores: " + parameters.getNewPlayerScore(), 15, 15);
        g.drawString("Difficulty level: " + parameters.getDifficultyLevel() + "  |  Moves: "  + parameters.getNumberOfMoves() + "  |  Time: " + clock.getSecondsPassed()+ "  |  Number of resets: ", 15, 40);
    }
}









