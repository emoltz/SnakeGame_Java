package com.NYU_introtojava;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class Window_JFrame extends JFrame {
    private final static double VERSION = 2.0;

    public static int WIN_WIDTH = 500;
    public static int WIN_HEIGHT = 500;

    public Window_JFrame(){
        createMenus();
        setTitle("~~~SNAKE~~~ Version:" + VERSION);
        setSize(WIN_WIDTH, WIN_HEIGHT);
        setResizable(false);

        //TODO here would be the menu for levels

//        difficultyMenu();
        add(new SnakeGame_JPanel());
        setDefaultBehavior();

    }

    ////This is for changing the dimensions of the screen (to be added)
//    public void changeDimensions(int width, int height){
//        WIN_HEIGHT = height;
//        WIN_WIDTH = width;
//    }

    public void difficultyMenu(){
        //TODO add this to the menus or make this a popup menu?
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

    public void createMenus(){
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
        //TODO add actionListener that saves it to a database


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
