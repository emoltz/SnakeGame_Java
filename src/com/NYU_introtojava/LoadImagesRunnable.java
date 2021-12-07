package com.NYU_introtojava;

import javax.swing.*;
import java.awt.*;

public class LoadImagesRunnable implements Runnable {


    SnakeGame_JPanel snakeGame_jPanel = null;

    public LoadImagesRunnable(SnakeGame_JPanel panel){
        snakeGame_jPanel = panel;
    }


    public void loadImages (){

        ImageIcon imageIcon1 = new ImageIcon("Assets/cherry.png");
        snakeGame_jPanel.cherry = imageIcon1.getImage();

        ImageIcon imageIcon2 = new ImageIcon("Assets/snake_body.png");
        snakeGame_jPanel.snakeBody = imageIcon2.getImage();

        ImageIcon imageIcon3 = new ImageIcon("Assets/RIP.png");
        snakeGame_jPanel.RIP = imageIcon3.getImage();
    }



    @Override
    public void run() {
        loadImages();
    }
}
