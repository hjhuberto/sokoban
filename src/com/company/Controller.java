package com.company;

import com.company.configuration.ConfigParser;
import com.company.elements.Crate;
import com.company.elements.Finish;
import com.company.elements.Player;
import com.company.elements.Wall;
import com.company.elements.Teleport;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.Timer;

import java.util.concurrent.TimeUnit;

import java.util.List;

/**
 * Klasa odpowiedzialna za uruchomienie gry.
 */


public class Controller extends JPanel implements ActionListener, KeyListener {

    private Map sizeMap;
    private Dialogs dialogs;
    private Results results;
    private Parameters parameters;

    private JFrame mainWindow;
    private JFrame gamePlayWindow;
    private JFrame bestResultsWindow;

    private JPanel menuPanel;
    private JPanel gamePlayPanel;
    private JPanel bestResultsPanel;

    private JButton startGameButton;
    private JButton bestResultsButton;
    private JButton exitButton;

    private JButton bestResultsExitButton;
    private JButton gamePlayExitButton;

    private List<Wall> walls;
    private Player player;
    private List<Finish> finishes;
    private List<Crate> crates;
    private View2 view2;
    private Crate crate;
    private Teleport teleport;
    private Map map;
    private ConfigParser configParser;
    private Level config;

    private Timer timer;

    private int dX;
    private int dir;
    private String path;

    private int levelNumber;
    private String[] pathTable;

    private boolean isMoving;
    private boolean isTeleporting;


    /**
     * Konstruktor klasy Controller
     */

    public Controller(){
        this.sizeMap = new Map();
        this.results = new Results(); // Elementy potrzebne do klasyfikacji gracza
        this.dialogs = new Dialogs();
        this.parameters = new Parameters();
        this.view2 = new View2();

        /* mainMenuWindow */
        this.mainWindow = new JFrame();
        mainWindow.pack();
        mainWindow.setBounds(0,0,sizeMap.getBoardSize(), sizeMap.getBoardSize());
        mainWindow.setTitle("SOKOBAN MENU");
        //mainWindow.setResizable(true);
        mainWindow.setLocationRelativeTo(null); // ustawia okno w centrum ekranu
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        /* gamePlayWindow */
        this.gamePlayWindow = new JFrame();
        gamePlayWindow.setBounds(0,0,sizeMap.getBoardSize(), sizeMap.getBoardSize());
        gamePlayWindow.setTitle("gamePlayWindow");
        gamePlayWindow.setResizable(false);
        gamePlayWindow.setLocationRelativeTo(null); // ustawia okno w centrum ekranu
        gamePlayWindow.addKeyListener(this);
        ////
        gamePlayWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* bestResultsWindow */
        this.bestResultsWindow = new JFrame();
        bestResultsWindow.setBounds(0,0,sizeMap.getBoardSize(), sizeMap.getBoardSize());
        bestResultsWindow.setTitle("bestResultsWindow");
        bestResultsWindow.setResizable(false);
        bestResultsWindow.setLocationRelativeTo(null); // ustawia okno w centrum ekranu
        ////
        bestResultsWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        /* Panele */
        this.menuPanel = new JPanel();
        this.gamePlayPanel = new JPanel();
        this.bestResultsPanel = new JPanel();

        /* Buttony */
        this.startGameButton = new JButton("Nowa gra");
        this.bestResultsButton = new JButton("Najlepsze wyniki");
        this.exitButton = new JButton("Wyjdź z gry");

        this.bestResultsExitButton = new JButton("MG");
        this.gamePlayExitButton = new JButton("MG");

        this.timer = new Timer(10, this);

        this.pathTable = new String[3];
        pathTable [0] = "config.json";
        pathTable [1] = "config2.json";
        pathTable [2] = "config3.json";

    }


    /**
     * Metoda wyswietlająca Menu Głowne.
     */

    public void ShowMenu() {

        menuPanel.setBounds(0,0,sizeMap.getBoardSize(), sizeMap.getBoardSize());
        menuPanel.setLayout(null);
        menuPanel.setBackground(Color.lightGray);
        mainWindow.add(menuPanel);

        //JButton button1 = new JButton("Start");
        startGameButton.setBounds((sizeMap.getBoardSize()/2)-100, 100, 200, 50);
        startGameButton.addActionListener(this);
        menuPanel.add(startGameButton);

        //JButton button2 = new JButton("Wyniki");
        bestResultsButton.setBounds((sizeMap.getBoardSize()/2)-100, 160, 200, 50);
        bestResultsButton.addActionListener(this);
        menuPanel.add(bestResultsButton);

        //JButton button3 = new JButton("Wyjdz z gry");
        exitButton.setBounds((sizeMap.getBoardSize()/2)-100, 220, 200, 50);
        exitButton.addActionListener(this);
        menuPanel.add(exitButton);

        JLabel backgroundIconLabel = new JLabel("MENU");
        backgroundIconLabel.setFont(new Font("Verdana",1,20));
        backgroundIconLabel.setBounds((sizeMap.getBoardSize()/2)-32, 20, 200, 50);
        menuPanel.add(backgroundIconLabel);

        ImageIcon icon = new ImageIcon("menuBackgroundImage.jpg");
        JLabel jlabel2 = new JLabel();
        jlabel2.setBounds(0,0, sizeMap.getBoardSize(), sizeMap.getBoardSize());
        jlabel2.setIcon(icon);
        menuPanel.add(jlabel2);

        mainWindow.setVisible(true);
    }

    /**
     * Funkcja odpowiedzialna za uruchomienie gry.
     */

    public void ShowGamePlay(String path) throws IOException {
        gamePlayPanel.setBounds(0,0,sizeMap.getBoardSize(), sizeMap.getBoardSize());
        gamePlayPanel.setLayout(null);
        gamePlayPanel.setBackground(Color.lightGray);

        gamePlayExitButton.setBounds(sizeMap.getBoardSize()-115, sizeMap.getBoardSize()-100, 80, 50);
        gamePlayExitButton.addActionListener(this);
        gamePlayPanel.add(gamePlayExitButton);

        configParser = new ConfigParser();
        config = configParser.getConfig(path);
        levelNumber = 0;
        this.path = pathTable[levelNumber];

        resetLevel(this.path);
        isMoving = false;
        isTeleporting = false;
        view2.setTeleport(new Teleport());


        map = new Map(walls, player, crates, finishes);
        view2.setElements(map.getMapArray());
        view2.getParameters(parameters);

        gamePlayWindow.setVisible(true);
        gamePlayWindow.requestFocus();
        mainWindow.setVisible(false);
        gamePlayWindow.add(view2);
    }

    public void openNewGameDialogWindow(){
        String nick = dialogs.showNickInputFrame();
        if (nick.length() > 0){
            parameters.setNewPlayerNick(nick);
            // Try
            parameters.setDifficultyLevel(dialogs.showChooseDifficultyLevelWindow());
            System.out.println(parameters.getDifficultyLevel());
            // end try
            try {
                System.out.println(parameters.getNewPlayerNick());
                ShowGamePlay("config.json");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else{
            dialogs.showErrorNickFrame();
        }
    }

    public void resetLevel(String path) throws IOException{
        config = configParser.getConfig(path);
        walls = config.getWalls();
        player = config.getPlayer();
        finishes = config.getFinishes();
        crates = config.getCrates();
        parameters.resetNumberOfMoves();view2.getNumberOfMoves(parameters);
        dir=0;
        isTeleporting = false;
        isMoving = false;

    }

    /**
     * Funkcja wyswietlajaca okno z najlepszymi wynikami.
     */
    public void ShowBestResults(){
        bestResultsPanel.setBounds(0,0,sizeMap.getBoardSize(), sizeMap.getBoardSize());
        bestResultsPanel.setLayout(null);
        bestResultsPanel.setBackground(Color.lightGray);
        bestResultsWindow.add(bestResultsPanel);




        results.setResults();

        bestResultsExitButton.setBounds(sizeMap.getBoardSize()-115, sizeMap.getBoardSize()-100, 80, 50);
        bestResultsExitButton.addActionListener(this);
        bestResultsPanel.add(bestResultsExitButton);

        JLabel jlabel = new JLabel("NAJLEPSZE WYNIKI");
        jlabel.setForeground(Color.magenta);
        jlabel.setFont(new Font("Verdana",1,22));
        jlabel.setBounds((sizeMap.getBoardSize()/2)-120, 20, 260, 50);
        bestResultsPanel.add(jlabel);

        JLabel jlabel2 = new JLabel("Nick:");
        jlabel2.setForeground(Color.magenta);
        jlabel2.setFont(new Font("Lucida Fax",1,15));
        jlabel2.setBounds((sizeMap.getBoardSize()/2)-170, 170, 200, 50);
        bestResultsPanel.add(jlabel2);

        JLabel jlabel3 = new JLabel("Wynik:");
        jlabel3.setForeground(Color.magenta);
        jlabel3.setFont(new Font("Lucida Fax",1,15));
        jlabel3.setBounds((sizeMap.getBoardSize()/2)+130, 170, 200, 50);
        bestResultsPanel.add(jlabel3);


        JLabel firstPlaceNickLabel = new JLabel("1. " + results.getFirstPlaceNick());
        firstPlaceNickLabel.setForeground(Color.magenta);
        firstPlaceNickLabel.setFont(new Font("Lucida Fax",1,15));
        firstPlaceNickLabel.setBounds((sizeMap.getBoardSize()/2)-190, 270, 200, 50);
        bestResultsPanel.add(firstPlaceNickLabel);

        JLabel firstPlaceScoresLabel = new JLabel(Integer.toString(results.getFirstPlaceScores()));
        firstPlaceScoresLabel.setForeground(Color.magenta);
        firstPlaceScoresLabel.setFont(new Font("Lucida Fax",1,15));
        firstPlaceScoresLabel.setBounds((sizeMap.getBoardSize()/2)+130, 270, 200, 50);
        bestResultsPanel.add(firstPlaceScoresLabel);

        JLabel secondPlaceNickLabel = new JLabel("1. " + results.getSecondPlaceNick());
        secondPlaceNickLabel.setForeground(Color.magenta);
        secondPlaceNickLabel.setFont(new Font("Lucida Fax",1,15));
        secondPlaceNickLabel.setBounds((sizeMap.getBoardSize()/2)-190, 370, 200, 50);
        bestResultsPanel.add(secondPlaceNickLabel);

        JLabel secondPlaceScoresLabel = new JLabel(Integer.toString(results.getSecondPlaceScores()));
        secondPlaceScoresLabel.setForeground(Color.magenta);
        secondPlaceScoresLabel.setFont(new Font("Lucida Fax",1,15));
        secondPlaceScoresLabel.setBounds((sizeMap.getBoardSize()/2)+130, 370, 200, 50);
        bestResultsPanel.add(secondPlaceScoresLabel);

        JLabel thirdPlaceNickLabel = new JLabel("3. " + results.getThirdPlaceNick());
        thirdPlaceNickLabel.setForeground(Color.magenta);
        thirdPlaceNickLabel.setFont(new Font("Lucida Fax",1,15));
        thirdPlaceNickLabel.setBounds((sizeMap.getBoardSize()/2)-190, 470, 200, 50);
        bestResultsPanel.add(thirdPlaceNickLabel);

        JLabel thirdPlaceScoresLabel = new JLabel(Integer.toString(results.getThirdPlaceScores()));
        thirdPlaceScoresLabel.setForeground(Color.magenta);
        thirdPlaceScoresLabel.setFont(new Font("Lucida Fax",1,15));
        thirdPlaceScoresLabel.setBounds((sizeMap.getBoardSize()/2)+130, 470, 200, 50);
        bestResultsPanel.add(thirdPlaceScoresLabel);



        ImageIcon icon1 = new ImageIcon("bestResultsBackgroundImage.jpg");
        JLabel backgroundIconLabel = new JLabel();
        backgroundIconLabel.setBounds(0,0, sizeMap.getBoardSize(), sizeMap.getBoardSize());
        backgroundIconLabel.setIcon(icon1);
        bestResultsPanel.add(backgroundIconLabel);

        bestResultsWindow.add(bestResultsPanel);
        bestResultsWindow.setVisible(true);
        mainWindow.setVisible(false);
    }

    public void actionPerformed(ActionEvent event){
        if (event.getSource() == startGameButton) {
            openNewGameDialogWindow();
        }
        else if(event.getSource() == bestResultsButton) {
            ShowBestResults();
        }
        else if(event.getSource() == exitButton) {
            System.exit(0);
        }
        else if(event.getSource() == bestResultsExitButton) {
            bestResultsWindow.setVisible(false);
            mainWindow.setVisible(true);
        }
        else if(event.getSource() == gamePlayExitButton) {
            gamePlayWindow.setVisible(false);
            mainWindow.setVisible(true);
        }
        else if (event.getSource() == timer){
            updateGameplay(dir);
        }
    }

    public void keyTyped(KeyEvent e) {}

    public void keyReleased(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        if (isMoving == false) {
            dX = 0;
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                System.out.println("wcisnieto strzalke w prawo");
                dir = 1;
                if (isTeleporting == false) {
                    if (player.wallCollision(dir, player, walls)) {
                        System.out.println("kolizja gracz-sciana");
                        return;
                    } else if (player.crateCollision(dir, player, crates) != null) {
                        Crate crate = player.crateCollision(dir, player, crates);
                        if (e.isShiftDown()) {
                            crates.remove(crate);
                            dir = 0;
                            this.updateGameplay(dir);
                            this.levelComplete();
                            isMoving = false;
                        }

                        if (crate.wallCollision(dir, crate, walls)) {
                            System.out.println("kolizja skrzynia-sciana");
                            return;
                        } else if (crate.crateCollision(dir, crate, crates) != null) {
                            System.out.println("kolizja skrzynia-skrzynia");
                            return;
                        } else if (crate.crateCollision(dir, crate, crates) == null) {
                            System.out.println("przesunieto skrzynie");

                            if (e.isShiftDown() != true) {
                                crate.move(1, 0);
                                player.move(1, 0);
                                parameters.increaseNumberOfMoves();
                                view2.getNumberOfMoves(parameters);
                                timer.start();
                                this.updateGameplay(dir);
                                this.levelComplete();
                            }
                        }
                    } else if (player.crateCollision(dir, player, crates) == null) {
                        System.out.println("przesunieto gracza");
                        Crate crate = player.crateCollision(3, player, crates);
                        if (e.isControlDown() && player.crateCollision(3, player, crates) != null) {
                            crate.move(1, 0);
                        }
                        player.move(1, 0);
                        parameters.increaseNumberOfMoves();
                        view2.getNumberOfMoves(parameters);
                        timer.start();
                        this.updateGameplay(dir);
                        this.levelComplete();
                    }
                }
                else if(isTeleporting == true) {
                    if (teleport.wallCollision(dir, teleport, walls)) {
                        System.out.println("kolizja gracz-sciana");
                        return;
                    }
                    else {
                        teleport = view2.getTeleport();
                        teleport.move(1, 0);
                        view2.setTeleport(teleport);
                        timer.start();
                        this.updateGameplay(dir);
                        this.levelComplete();
                    }
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                System.out.println("wcisnieto strzalke w dol");
                dir = 2;
                if (isTeleporting == false) {
                    if (player.wallCollision(dir, player, walls)) {
                        System.out.println("kolizja gracz-sciana");
                        return;
                    } else if (player.crateCollision(dir, player, crates) != null) {
                        Crate crate = player.crateCollision(dir, player, crates);
                        if (e.isShiftDown()) {
                            crates.remove(crate);
                            dir = 0;
                            this.updateGameplay(dir);
                            this.levelComplete();
                            isMoving = false;
                        }
                        if (crate.wallCollision(dir, crate, walls)) {
                            System.out.println("kolizja skrzynia-sciana");
                            return;
                        } else if (crate.crateCollision(dir, crate, crates) != null) {
                            System.out.println("kolizja skrzynia-skrzynia");
                            return;
                        } else if (crate.crateCollision(dir, crate, crates) == null) {
                            System.out.println("przesunieto skrzynie");
                            if (e.isShiftDown() != true) {
                                crate.move(0, 1);
                                player.move(0, 1);
                                parameters.increaseNumberOfMoves();
                                view2.getNumberOfMoves(parameters);
                                timer.start();
                                this.updateGameplay(dir);
                                this.levelComplete();
                            }
                        }
                    } else if (player.crateCollision(dir, player, crates) == null) {
                        System.out.println("przesunieto gracza");
                        Crate crate = player.crateCollision(4, player, crates);
                        if (e.isControlDown() && player.crateCollision(4, player, crates) != null) {
                            crate.move(0, 1);
                        }
                        player.move(0, 1);
                        parameters.increaseNumberOfMoves();
                        view2.getNumberOfMoves(parameters);
                        timer.start();
                        this.updateGameplay(dir);
                        this.levelComplete();
                    }
                }
                else if(isTeleporting == true) {
                    if (teleport.wallCollision(dir, teleport, walls)) {
                        System.out.println("kolizja gracz-sciana");
                        return;
                    }
                    else {
                        teleport = view2.getTeleport();
                        teleport.move(0, 1);
                        view2.setTeleport(teleport);
                        timer.start();
                        this.updateGameplay(dir);
                        this.levelComplete();
                    }
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                System.out.println("wcisnieto strzalke w lewo");
                dir = 3;
                if (isTeleporting == false) {
                    if (player.wallCollision(dir, player, walls)) {
                        System.out.println("kolizja gracz-sciana");
                        return;
                    } else if (player.crateCollision(dir, player, crates) != null) {
                        Crate crate = player.crateCollision(dir, player, crates);
                        if (e.isShiftDown()) {
                            crates.remove(crate);
                            dir = 0;
                            this.updateGameplay(dir);
                            this.levelComplete();
                            isMoving = false;
                        }
                        if (crate.wallCollision(dir, crate, walls)) {
                            System.out.println("kolizja skrzynia-sciana");
                            return;
                        } else if (crate.crateCollision(dir, crate, crates) != null) {
                            System.out.println("kolizja skrzynia-skrzynia");
                            return;
                        } else if (crate.crateCollision(dir, crate, crates) == null) {
                            System.out.println("przesunieto skrzynie");
                            if (e.isShiftDown() != true) {
                                crate.move(-1, 0);
                                player.move(-1, 0);
                                parameters.increaseNumberOfMoves();
                                view2.getNumberOfMoves(parameters);
                                timer.start();
                                this.updateGameplay(dir);
                                this.levelComplete();
                            }
                        }
                    } else if (player.crateCollision(dir, player, crates) == null) {
                        System.out.println("przesunieto gracza");
                        Crate crate = player.crateCollision(1, player, crates);
                        if (e.isControlDown() && player.crateCollision(1, player, crates) != null) {
                            crate.move(-1, 0);
                        }
                        player.move(-1, 0);
                        parameters.increaseNumberOfMoves();
                        view2.getNumberOfMoves(parameters);
                        timer.start();
                        this.updateGameplay(dir);
                        this.levelComplete();
                    }
                }
                else if(isTeleporting == true) {
                    if (teleport.wallCollision(dir, teleport, walls)) {
                        System.out.println("kolizja gracz-sciana");
                        return;
                    }
                    else {
                        teleport = view2.getTeleport();
                        teleport.move(-1, 0);
                        view2.setTeleport(teleport);
                        timer.start();
                        this.updateGameplay(dir);
                        this.levelComplete();
                    }
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                System.out.println("wcisnieto strzalke w gore");
                dir = 4;
                if (isTeleporting == false) {
                    if (player.wallCollision(dir, player, walls)) {
                        System.out.println("kolizja gracz-sciana");
                        return;
                    } else if (player.crateCollision(dir, player, crates) != null) {
                        Crate crate = player.crateCollision(dir, player, crates);
                        if (e.isShiftDown()) {
                            crates.remove(crate);
                            dir = 0;
                            this.updateGameplay(dir);
                            this.levelComplete();
                            isMoving = false;
                        }
                        if (crate.wallCollision(dir, crate, walls)) {
                            System.out.println("kolizja skrzynia-sciana");
                            return;
                        } else if (crate.crateCollision(dir, crate, crates) != null) {
                            System.out.println("kolizja skrzynia-skrzynia");
                            return;
                        } else if (crate.crateCollision(dir, crate, crates) == null) {
                            System.out.println("przesunieto skrzynie");
                            if (e.isShiftDown() != true) {
                                crate.move(0, -1);
                                player.move(0, -1);
                                parameters.increaseNumberOfMoves();
                                view2.getNumberOfMoves(parameters);
                                timer.start();
                                this.updateGameplay(dir);
                                this.levelComplete();
                            }
                        }
                    } else if (player.crateCollision(dir, player, crates) == null) {
                        System.out.println("przesunieto gracza");
                        Crate crate = player.crateCollision(2, player, crates);
                        if (e.isControlDown() && player.crateCollision(2, player, crates) != null) {
                            crate.move(0, -1);
                        }
                        player.move(0, -1);
                        parameters.increaseNumberOfMoves();
                        view2.getNumberOfMoves(parameters);
                        timer.start();
                        this.updateGameplay(dir);
                        this.levelComplete();
                    }
                }
                else if(isTeleporting == true){
                    if (teleport.wallCollision(dir, teleport, walls)) {
                        System.out.println("kolizja gracz-sciana");
                        return;
                    }
                    else {
                        teleport = view2.getTeleport();
                        teleport.move(0, -1);
                        view2.setTeleport(teleport);
                        timer.start();
                        this.updateGameplay(dir);
                        this.levelComplete();
                    }
                }

            }

            if (e.getKeyCode() == KeyEvent.VK_R) {
                try {
                    this.resetLevel(path);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                this.updateGameplay(dir);
                isMoving = false;
            }

            if (e.getKeyCode() == KeyEvent.VK_P) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_T) {
                teleport = view2.getTeleport();
                dir=0;
                int k=0;
                for (int i = 0; i < crates.size(); i++) {
                    Crate c = crates.get(i);
                    if (c.getStartX() == teleport.getStartX() && c.getStartY() == teleport.getStartY()) {
                        k = k + 1;
                    }
                }
                if (k == 0) {
                    isTeleporting = !isTeleporting;
                    if (isTeleporting == true) {
                        teleport.setVisible(true);
                        teleport.setStartX(player.getStartX());
                        teleport.setStartY(player.getStartY());
                    } else if (isTeleporting == false) {
                        player.setStartX(teleport.getStartX());
                        player.setStartY(teleport.getStartY());
                        teleport.setVisible(false);
                    }
                    this.updateGameplay(dir);
                    isMoving = false;
                }
            }
        }
    }

    public void updateGameplay(int dir) {
        dX = dX+(sizeMap.getElementSize()/sizeMap.getSize());
        map.updateMap(walls, player, crates, finishes);
        view2.setElements2(map.getMapArray(), player, crate, dir, dX );
        view2.repaint();
        isMoving = true;

        if (dX >= sizeMap.getElementSize()) {
            timer.stop();
            isMoving = false;
            this.resetDirections();
        }
    }

    public boolean checkCompletion() {
        int k = 0;
        this.resetFinishes();
        for (int i = 0; i < crates.size(); i++) {
            Crate c = crates.get(i);
            for (int j = 0; j < finishes.size(); j++) {
                Finish f = finishes.get(j);
                if (c.getStartX() == f.getStartX() && c.getStartY() == f.getStartY()) {
                    k = k+1;
                    f.setFinished(true);
                }
            }
        }
        if (k == crates.size()) return true;
        else return false;
    }

    public void resetDirections(){
        for (int i = 0; i< crates.size(); i++) {
            Crate c = crates.get(i);
            c.setDirection(0);
        }
        player.setDirection(0);
    }

    public void resetFinishes(){
        for (int i = 0; i< finishes.size(); i++) {
            Finish f = finishes.get(i);
            f.setFinished(false);
        }
    }

    public void levelComplete(){
        if(checkCompletion() == true) {
            parameters.setNewPlayerFinalScore(view2.getCompleteLevelScore());// dodawanie punktów po ukończonym levelu do puli punktów gracza
            levelNumber = levelNumber + 1;
            if (levelNumber < 2) {
                dialogs.showNextLevelFrame();
                path = pathTable[levelNumber];
                System.out.println(path);
                try {
                    this.resetLevel(path);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                this.updateGameplay(dir);
            }
            else if (levelNumber >= 2){
                results.setResults();
                results.replaceResults(parameters.getNewPlayerFinalScore(),parameters.getNewPlayerNick());
                dialogs.showWinningLevelFrame(parameters.getNewPlayerNick(), parameters.getNewPlayerFinalScore());
                parameters.resetNewPlayerFinalScore();
                gamePlayWindow.setVisible(false);
                mainWindow.setVisible(true);
            }
        }
    }

}





























