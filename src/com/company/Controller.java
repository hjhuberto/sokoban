package com.company;

import com.company.configuration.ConfigParser;
import com.company.elements.Crate;
import com.company.elements.Finish;
import com.company.elements.Player;
import com.company.elements.Wall;

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
    private Results player1; // obiekt do przechowywania informacji o graczu oraz jego klasyfikacji

    private Component errorFrame;

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
    private Map map;
    private int dX;
    private int dir;

    private Timer timer;


    /**
     * Konstruktor klasy Controller
     */

    public Controller(){
        this.sizeMap = new Map();
        this.errorFrame = new JFrame("Error");


        /* mainMenuWindow */
        this.mainWindow = new JFrame();
        mainWindow.setBounds(0,0,sizeMap.getBoardSize(), sizeMap.getBoardSize());
        mainWindow.setTitle("SOKOBAN MENU");
        mainWindow.setResizable(false);
        mainWindow.setLocationRelativeTo(null); // ustawia okno w centrum ekranu
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        /* gamePlayWindow */
        this.gamePlayWindow = new JFrame();
        gamePlayWindow.setBounds(0,0,sizeMap.getBoardSize(), sizeMap.getBoardSize());
        gamePlayWindow.setTitle("gamePlayWindow");
        gamePlayWindow.setResizable(false);
        gamePlayWindow.setLocationRelativeTo(null); // ustawia okno w centrum ekranu
        gamePlayWindow.addKeyListener(this);

        /* bestResultsWindow */
        this.bestResultsWindow = new JFrame();
        bestResultsWindow.setBounds(0,0,sizeMap.getBoardSize(), sizeMap.getBoardSize());
        bestResultsWindow.setTitle("bestResultsWindow");
        bestResultsWindow.setResizable(false);
        bestResultsWindow.setLocationRelativeTo(null); // ustawia okno w centrum ekranu


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

        /* Elementy potrzebne do klasyfikacji gracza */
        this.player1 = new Results();

        this.timer = new Timer(40, this);
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

        ConfigParser configParser = new ConfigParser();

        Level config = configParser.getConfig(path);

        walls = config.getWalls();
        player = config.getPlayer();
        finishes = config.getFinishes();
        crates = config.getCrates();

        dir = dir;
        dir=0;

        map = new Map(walls, player, crates, finishes);


        view2 = new View2();
        view2.setElements(map.getMapArray());

        gamePlayWindow.setVisible(true);
        gamePlayWindow.requestFocus();
        mainWindow.setVisible(false);
        gamePlayWindow.add(view2);
    }

    public void openNewGameDialogWindow(){
        String nick = JOptionPane.showInputDialog("Please enter your nick: ");
        if (nick.length() > 0){
            player1.setNewPlayerNick(nick);
            try {
                System.out.println(player1.getNewPlayerNick());
                ShowGamePlay("config.json");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else{
            JOptionPane.showMessageDialog(errorFrame, "Podales niedozwolony nick. Sprobuj ponownie!!!");
        }
    }

    /**
     * Funkcja wyswietlajaca okno z najlepszymi wynikami.
     */
    public void ShowBestResults(){
        bestResultsPanel.setBounds(0,0,sizeMap.getBoardSize(), sizeMap.getBoardSize());
        bestResultsPanel.setLayout(null);
        bestResultsPanel.setBackground(Color.lightGray);
        bestResultsWindow.add(bestResultsPanel);

        player1.setResults();

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


        JLabel firstPlaceNickLabel = new JLabel("1. " + player1.getFirstPlaceNick());
        firstPlaceNickLabel.setForeground(Color.magenta);
        firstPlaceNickLabel.setFont(new Font("Lucida Fax",1,15));
        firstPlaceNickLabel.setBounds((sizeMap.getBoardSize()/2)-190, 270, 200, 50);
        bestResultsPanel.add(firstPlaceNickLabel);

        JLabel firstPlaceScoresLabel = new JLabel(Integer.toString(player1.getFirstPlaceScores()));
        firstPlaceScoresLabel.setForeground(Color.magenta);
        firstPlaceScoresLabel.setFont(new Font("Lucida Fax",1,15));
        firstPlaceScoresLabel.setBounds((sizeMap.getBoardSize()/2)+130, 270, 200, 50);
        bestResultsPanel.add(firstPlaceScoresLabel);

        JLabel secondPlaceNickLabel = new JLabel("1. " + player1.getSecondPlaceNick());
        secondPlaceNickLabel.setForeground(Color.magenta);
        secondPlaceNickLabel.setFont(new Font("Lucida Fax",1,15));
        secondPlaceNickLabel.setBounds((sizeMap.getBoardSize()/2)-190, 370, 200, 50);
        bestResultsPanel.add(secondPlaceNickLabel);

        JLabel secondPlaceScoresLabel = new JLabel(Integer.toString(player1.getSecondPlaceScores()));
        secondPlaceScoresLabel.setForeground(Color.magenta);
        secondPlaceScoresLabel.setFont(new Font("Lucida Fax",1,15));
        secondPlaceScoresLabel.setBounds((sizeMap.getBoardSize()/2)+130, 370, 200, 50);
        bestResultsPanel.add(secondPlaceScoresLabel);

        JLabel thirdPlaceNickLabel = new JLabel("3. " + player1.getThirdPlaceNick());
        thirdPlaceNickLabel.setForeground(Color.magenta);
        thirdPlaceNickLabel.setFont(new Font("Lucida Fax",1,15));
        thirdPlaceNickLabel.setBounds((sizeMap.getBoardSize()/2)-190, 470, 200, 50);
        bestResultsPanel.add(thirdPlaceNickLabel);

        JLabel thirdPlaceScoresLabel = new JLabel(Integer.toString(player1.getThirdPlaceScores()));
        thirdPlaceScoresLabel.setForeground(Color.magenta);
        thirdPlaceScoresLabel.setFont(new Font("Lucida Fax",1,15));
        thirdPlaceScoresLabel.setBounds((sizeMap.getBoardSize()/2)+130, 470, 200, 50);
        bestResultsPanel.add(thirdPlaceScoresLabel);



        ImageIcon icon1 = new ImageIcon("bestResultsBackgroundImage.jpg");
        JLabel backgroundIconLabel = new JLabel();
        backgroundIconLabel.setBounds(0,0, sizeMap.getBoardSize(), sizeMap.getBoardSize());
        backgroundIconLabel.setIcon(icon1);
        bestResultsPanel.add(backgroundIconLabel);


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
        dX = 2;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("wcisnieto strzalke w prawo");
            dir=1;

            if (player.wallCollision(dir, player, walls)) {
                System.out.println("kolizja gracz-sciana");
                return;
            }
            else if (player.crateCollision(dir, player, crates) !=null){
                Crate crate = player.crateCollision(dir, player, crates);
                if (crate.wallCollision(dir, crate, walls)) {
                    System.out.println("kolizja skrzynia-sciana");
                    return;
                }
                else if (crate.crateCollision(dir, crate, crates) != null){
                    System.out.println("kolizja skrzynia-skrzynia");
                    return;
                }
                else if (crate.crateCollision(dir, crate, crates) == null){
                    System.out.println("przesunieto skrzynie");
                    crate.move(1,0);
                    player.move(1,0);
                    this.updateGameplay(dir);
                }
            }
            else if (player.crateCollision(1, player, crates) == null) {
                System.out.println("przesunieto gracza");
                player.move(1,0);
                timer.start();
                this.updateGameplay(dir);
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            System.out.println("wcisnieto strzalke w dol");
            dir=2;

            if (player.wallCollision(dir, player, walls)) {
                System.out.println("kolizja gracz-sciana");
                return;
            }
            else if (player.crateCollision(dir, player, crates) !=null){
                Crate crate = player.crateCollision(dir, player, crates);
                if (crate.wallCollision(dir, crate, walls)) {
                    System.out.println("kolizja skrzynia-sciana");
                    return;
                }
                else if (crate.crateCollision(dir, crate, crates) != null){
                    System.out.println("kolizja skrzynia-skrzynia");
                    return;
                }
                else if (crate.crateCollision(dir, crate, crates) == null){
                    System.out.println("przesunieto skrzynie");
                    crate.move(0,1);
                    player.move(0,1);
                    this.updateGameplay(dir);
                }
            }
            else if (player.crateCollision(dir, player, crates) == null) {
                System.out.println("przesunieto gracza");
                player.move(0,1);
                timer.start();
                this.updateGameplay(dir);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("wcisnieto strzalke w lewo");
            dir=3;

            if (player.wallCollision(dir, player, walls)) {
                System.out.println("kolizja gracz-sciana");
                return;
            }
            else if (player.crateCollision(dir, player, crates) !=null){
                Crate crate = player.crateCollision(dir, player, crates);
                if (crate.wallCollision(dir, crate, walls)) {
                    System.out.println("kolizja skrzynia-sciana");
                    return;
                }
                else if (crate.crateCollision(dir, crate, crates) != null){
                    System.out.println("kolizja skrzynia-skrzynia");
                    return;
                }
                else if (crate.crateCollision(dir, crate, crates) == null){
                    System.out.println("przesunieto skrzynie");
                    crate.move(-1,0);
                    player.move(-1,0);
                    this.updateGameplay(dir);
                }
            }
            else if (player.crateCollision(dir, player, crates) == null) {
                System.out.println("przesunieto gracza");
                player.move(-1,0);
                timer.start();
                this.updateGameplay(dir);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            System.out.println("wcisnieto strzalke w gore");
            dir=4;

            if (player.wallCollision(dir, player, walls)) {
                System.out.println("kolizja gracz-sciana");
                return;
            }
            else if (player.crateCollision(dir, player, crates) !=null){
                Crate crate = player.crateCollision(dir, player, crates);
                if (crate.wallCollision(dir, crate, walls)) {
                    System.out.println("kolizja skrzynia-sciana");
                    return;
                }
                else if (crate.crateCollision(dir, crate, crates) != null){
                    System.out.println("kolizja skrzynia-skrzynia");
                    return;
                }
                else if (crate.crateCollision(dir, crate, crates) == null){
                    System.out.println("przesunieto skrzynie");
                    crate.move(0,-1);
                    player.move(0,-1);
                    this.updateGameplay(dir);
                }
            }
            else if (player.crateCollision(dir, player, crates) == null) {
                System.out.println("przesunieto gracza");
                player.move(0,-1);
                timer.start();
                this.updateGameplay(dir);
            }
        }
    }
    public void updateGameplay(int dir)
    {
        dX = dX+(sizeMap.getElementSize()/sizeMap.getSize());
        map.updateMap(walls, player, crates, finishes);
        view2.setElements2(map.getMapArray(), player, crate, dir, dX );
        view2.repaint();

        if (dX >= sizeMap.getElementSize()) {
            timer.stop();
        }
    }

    public boolean levelComplete() {
        int k = 0;
        for (int i = 0; i < crates.size(); i++) {
            Crate c = crates.get(i);
            for (int j = 0; j < crates.size(); j++) {
                Finish f = finishes.get(j);
                if (c.getStartX() == f.getStartX() && c.getStartX() == f.getStartX()) {
                    k = k+1;
                }
            }
        }
        if (k==crates.size()) return true;
        else return false;
    }

}






















