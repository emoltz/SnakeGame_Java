package com.NYU_introtojava;

import javax.swing.*;

public class SaveGame implements Runnable{
    SnakeGame_JPanel snakeGame_jPanel;
    DatabaseController database;
    Window_JFrame window_jFrame;

    public SaveGame(SnakeGame_JPanel s, DatabaseController d, Window_JFrame w){
        snakeGame_jPanel = s;
        database = d;
        window_jFrame = w;
    }


    @Override
    public void run(){
        String name = JOptionPane.showInputDialog(window_jFrame,"Enter Your Name:");
        int score = snakeGame_jPanel.getNumberOfCherries();
        database.addNewRecord(name, score);

    }
}
