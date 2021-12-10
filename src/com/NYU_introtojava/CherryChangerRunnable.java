package com.NYU_introtojava;

import static com.NYU_introtojava.SnakeGame_JPanel.*;

public class CherryChangerRunnable implements Runnable {

    SnakeGame_JPanel snakeGame_jPanel;

    public CherryChangerRunnable(SnakeGame_JPanel s){
        snakeGame_jPanel = s;
    }

    @Override
    public void run(){
        drawNewCherry();
        incrementCherries();
    }

    private void drawNewCherry(){
        //calculate random position for cherry
        int rand = sizeOfImages;
        int r = (int) (Math.random() * rand);
        snakeGame_jPanel.changeCherry_x(((r * sizeOfImages)));

        r = (int) (Math.random() * rand);
        changeCherry_y(((r * sizeOfImages)));
        System.out.println("Cherry Coordinates - x:" + snakeGame_jPanel.getCherry_x() + " y:" + snakeGame_jPanel.getCherry_y());

    }

    //count cherries
    public void incrementCherries(){
        snakeGame_jPanel.increaseNumberOfCherries();
    }

}
