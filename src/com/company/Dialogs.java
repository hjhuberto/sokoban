package com.company;

import javax.swing.*;
import java.awt.*;


public class Dialogs {
    private String[] choices = { "Easy", "Medium", "High", "Nightmare"};

    private Component winningLevelFrame;
    private Component errorNickFrame;
    private Component nextLevelFrame;

    public Dialogs(){
        this.winningLevelFrame = new JFrame("YOU WIN!!!");
        this.errorNickFrame = new JFrame("Error");
        this.nextLevelFrame = new JFrame("Next level");
    }

    public void showWinningLevelFrame(String nick, int scores){ JOptionPane.showMessageDialog(winningLevelFrame, "Gratulacje " + nick +"!!!\nWygrałeś :)\nZdobyłeś: " + scores + " punktów.","Winning message",
            JOptionPane.INFORMATION_MESSAGE); }


    public void showErrorNickFrame(){ JOptionPane.showMessageDialog(errorNickFrame, "Podales niedozwolony nick. Sprobuj ponownie!!!"); }

    public void showNextLevelFrame(){ JOptionPane.showMessageDialog(nextLevelFrame, "Kliknij ok, aby przejść do następnej planszy!!!","Next Level message",
            JOptionPane.INFORMATION_MESSAGE); }




    public String showNickInputFrame(){
        String nick = JOptionPane.showInputDialog("Please enter your nick: ");
        return nick;
    }

    public String showChooseDifficultyLevelWindow(){
        String difficultyLevel = (String) JOptionPane.showInputDialog(null, "Choose now...",
                "Wybierz poziom trudności", JOptionPane.QUESTION_MESSAGE, null,  // Use
                // default
                // icon
                choices, // Array of choices
                choices[1]); // Initial choice
        return difficultyLevel;
    }
}
