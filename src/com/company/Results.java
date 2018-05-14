package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Formatter;
import java.util.Scanner;

/**
 * Klasa odpowiedzialna za zapisywanie i odczytywanie z pliku najlepszych wynikow.
 */

public class Results {

    private Scanner R_file;
    private Formatter F_file;

    private Component errorFrame;

    private String newPlayerNick;
    private int newPlayerScores;

    private String firstPlaceNick;
    private String secondPlaceNick;
    private String thirdPlaceNick;

    private int firstPlaceScores;
    private int secondPlaceScores;
    private int thirdPlaceScores;

    public Results(){
        this.errorFrame = new JFrame("Error");

        this.newPlayerNick = "";
        this.newPlayerScores = 0;

        this.firstPlaceNick = "";
        this.secondPlaceNick = "";
        this.thirdPlaceNick = "";

        this.firstPlaceScores = 0;
        this.secondPlaceScores = 0;
        this.thirdPlaceScores = 0;
    }

    /** Metody do formatowania pliku.
     *
     */
    public void openFormatFile(){
        try{
            F_file = new Formatter(new File("results.txt"));
        } catch(Exception e) {
            JOptionPane.showMessageDialog(errorFrame, "Error. Could not find file.");
        }
    }

    public void closeFormatFile(){
        F_file.close();
    }

    public void saveToFile(){
        F_file.format("%s %s%n%s %s%n%s %s%n", firstPlaceNick, Integer.toString(firstPlaceScores), secondPlaceNick, Integer.toString(secondPlaceScores),
        thirdPlaceNick, Integer.toString(thirdPlaceScores));
    }


    /* ------------------------------------------------------------------------------------------------- */
    /**Metody do odczytywania z pliku.
      *
      */
    public void openReadFile(){
        try{
            R_file = new Scanner(new File("results.txt"));
        } catch(Exception e) {
            JOptionPane.showMessageDialog(errorFrame, "Error. Could not find file.");
        }
    }

    public void readFile(){
        while(R_file.hasNext()) {
            firstPlaceNick = R_file.next();
            firstPlaceScores = Integer.parseInt(R_file.next());
            secondPlaceNick = R_file.next();
            secondPlaceScores = Integer.parseInt(R_file.next());
            thirdPlaceNick = R_file.next();
            thirdPlaceScores = Integer.parseInt(R_file.next());
            System.out.printf("%s %s\n%s %s\n%s %s\n", firstPlaceNick, firstPlaceScores, secondPlaceNick, secondPlaceScores, thirdPlaceNick, thirdPlaceScores);
        }
    }

    public void closeReadFile(){
        R_file.close();
    }

    /* ------------------------------------------------------------------------------------------------- */
    /**Metody do porownywania i zamiany wynikow.
     *
     */

    public void setNewPlayerNick(String nick){
        newPlayerNick = nick;
    }

    public void setResults(){
        openReadFile();
        readFile();
        closeReadFile();
    }

    public int compareResult(){

        if (newPlayerScores > firstPlaceScores){
            return 1;
        } else if (newPlayerScores > secondPlaceScores){
            return 2;
        } else if (newPlayerScores > thirdPlaceScores) {
            return 3;
        } else return 0;
    }

    public void replaceResults(){
        if (compareResult() == 1){
            firstPlaceScores = newPlayerScores;
            firstPlaceNick = newPlayerNick;
        } else if (compareResult() == 2){
            secondPlaceScores = newPlayerScores;
            secondPlaceNick = newPlayerNick;
        } else if (compareResult() == 3){
            thirdPlaceScores = newPlayerScores;
            thirdPlaceNick = newPlayerNick;
        }
        openFormatFile();
        saveToFile();
        closeFormatFile();
    }


    public String getFirstPlaceNick(){return firstPlaceNick;}
    public String getSecondPlaceNick(){return secondPlaceNick;}
    public String getThirdPlaceNick(){return thirdPlaceNick;}
    public String getNewPlayerNick(){return newPlayerNick;};

    public int getFirstPlaceScores(){return firstPlaceScores;}
    public int getSecondPlaceScores(){return secondPlaceScores;}
    public int getThirdPlaceScores(){return thirdPlaceScores;}
    public int getNewPlayerScores() {return newPlayerScores;}

}
