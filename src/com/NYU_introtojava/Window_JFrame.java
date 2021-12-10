package com.NYU_introtojava;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class Window_JFrame extends JFrame {
    private final static double VERSION = 2.0;

    public final static int WIN_WIDTH = 500;
    public final static int WIN_HEIGHT = 500;

    SnakeGame_JPanel snakeGame_jPanel;
    DatabaseController database = null;

    public Window_JFrame() {
        createMenus();
        setTitle("~~~SNAKE~~~ Version:" + VERSION);
        setSize(WIN_WIDTH, WIN_HEIGHT);
        setResizable(false);
        snakeGame_jPanel = new SnakeGame_JPanel();

        add(snakeGame_jPanel);
        setDefaultBehavior();

    }


    public void difficultyMenu(){

        JLabel select = new JLabel("Select Difficulty");

        add(select);
    }

    public static int getWinWidth(){
        return WIN_WIDTH;
    }

    public static int getWinHeight(){
        return WIN_HEIGHT;
    }

    public void setDefaultBehavior() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void initDatabaseConnection(){
        database = new DatabaseController();
        database.connectToDatabase();
    }

    public void createMenus(){
        initDatabaseConnection();

        BackgroundMenuBar menuBar = new BackgroundMenuBar();
        setJMenuBar(menuBar);
        menuBar.add(createFileMenu());
    }

    public JMenu createFileMenu(){

        BackgroundJMenu menu = new BackgroundJMenu("File");
        JMenuItem newGame = new JMenuItem("New Game");
        newGame.addActionListener((e)->{
            Main.run();
            dispose();
        });
        KeyStroke keyStrokeNewGame = KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK);
        newGame.setAccelerator(keyStrokeNewGame);
        menu.add(newGame);

        JMenuItem save = new JMenuItem("Save Score");


        save.addActionListener((e)->{
            Runnable r = new SaveGame(snakeGame_jPanel, database, this);
            Thread thread = new Thread(r);
            thread.start();

        });

        KeyStroke keyStrokeSave = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK);
        save.setAccelerator(keyStrokeSave);
        menu.add(save);
        menu.addSeparator();

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener((e) -> System.exit(0));
        menu.add(exit);

        return menu;
    }


}
