package com.NYU_introtojava;

import javax.swing.*;

import static com.NYU_introtojava.SnakeGame_JPanel.inGame;

public class ActionPerformedRunnable extends JPanel implements Runnable{

    SnakeGame_JPanel snakeGame_jPanel = null;

    public ActionPerformedRunnable(SnakeGame_JPanel s){
        snakeGame_jPanel = s;
    }

    @Override
    public void run() {
        snakeGame_jPanel.checkCherry();
        snakeGame_jPanel.checkCollision();
        snakeGame_jPanel.move();

    }
}
