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

    private JPanel panel1;

    private Map sizeMap;

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

    /**
     * Metoda opisująca sposob rysowania elementow rozgrywki.
     */
    public void paint(Graphics g) {
        for (int i = 0; i < elements.length; i++) {
            for (int j = 0; j < elements.length; j++) {
                Element element = elements[i][j];
                if (element instanceof Finish) {
                    g.setColor(Color.magenta);
                    g.fillRect(j * sizeMap.getElementSize(), i * sizeMap.getElementSize(), sizeMap.getElementSize(), sizeMap.getElementSize());
                } else if (element instanceof Player) {
                    g.setColor(Color.cyan);
                    g.fillRect(j * sizeMap.getElementSize(), i * sizeMap.getElementSize(), sizeMap.getElementSize(), sizeMap.getElementSize());
                    //g.setColor(Color.cyan);
                    //g.fillOval(j * sizeMap.getElementSize(), i * sizeMap.getElementSize(), sizeMap.getElementSize(), sizeMap.getElementSize());
                } else if (element instanceof Wall){
                    g.setColor(Color.blue);
                    g.fillRect(j * sizeMap.getElementSize(), i * sizeMap.getElementSize(), sizeMap.getElementSize(), sizeMap.getElementSize());
                } else if (element instanceof Crate){
                    g.setColor(Color.yellow);
                    g.fillRect(j * sizeMap.getElementSize(), i * sizeMap.getElementSize(), sizeMap.getElementSize(), sizeMap.getElementSize());
                } else if (element instanceof Finish){
                    g.setColor(Color.yellow);
                    g.fillRect(j * sizeMap.getElementSize(), i * sizeMap.getElementSize(), sizeMap.getElementSize(), sizeMap.getElementSize());
                }
                else {
                    g.setColor(Color.gray);
                    g.fillRect(j * sizeMap.getElementSize(), i * sizeMap.getElementSize(), sizeMap.getElementSize(), sizeMap.getElementSize());
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


    /**
     * Metoda wyswietlajaca okno rozgrywki.
     */
    //public void draw(JFrame window){
      //  window.add(this);
    //}
}










