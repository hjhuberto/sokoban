package com.company;

public class Parameters {
    // gracz
    private String newPlayerNick;
    private int newPlayerScores;

    private int newPlayerFinalScores;

    // poziom trudnosci
    private String difficultyLevel;
    private int numberOfResets;
    private int numberOfMoves;

    public void Parameters(){
        this.newPlayerNick = "";
        this.newPlayerScores = 0;

        this.newPlayerFinalScores = 0;

        this.difficultyLevel = "";
        this.numberOfResets = 0;
        this.numberOfMoves = 0;
    }

    public void increaseNumberOfMoves(){ numberOfMoves = numberOfMoves + 1; }

    public void resetNumberOfMoves(){ numberOfMoves = 0; }

    public void resetNewPlayerFinalScore(){ newPlayerFinalScores = 0;}



    public String getNewPlayerNick(){return newPlayerNick;}
    public int getNewPlayerScore(){return newPlayerScores;}
    public int getNewPlayerFinalScore(){return newPlayerFinalScores;}


    public String getDifficultyLevel(){return difficultyLevel;}
    public int getNumberOfResets(){return numberOfResets;}
    public int getNumberOfMoves(){return numberOfMoves;}

    public void setNewPlayerNick(String nick){this.newPlayerNick = nick;}
    public void setNewPlayerScore(int score){this.newPlayerScores = score;}

    public void setNewPlayerFinalScore(int score){this.newPlayerFinalScores = this.newPlayerFinalScores + score;}

    public void setDifficultyLevel(String diff){this.difficultyLevel = diff;}
    public void setNumberOfResets(int resets){this.numberOfResets = resets;}
    public void setNumberOfMoves(int moves){ this.numberOfMoves = moves;}
}
