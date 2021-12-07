package com.NYU_introtojava;

import static com.NYU_introtojava.SnakeGame_JPanel.*;

public class CherryChangerRunnable implements Runnable {

    SnakeGame_JPanel snakeGame_jPanel = null;
//    public CherryChangerRunnable(){
//
//    }

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
        changeCherry_x(((r * sizeOfImages)));

        r = (int) (Math.random() * rand);
        changeCherry_y(((r * sizeOfImages)));
        System.out.println("Cherry Coordinates - x:" + getCherry_x() + " y:" + getCherry_y());

    }

    //count cherries
    public void incrementCherries(){
        snakeGame_jPanel.increaseNumberOfCherries();
    }

}
