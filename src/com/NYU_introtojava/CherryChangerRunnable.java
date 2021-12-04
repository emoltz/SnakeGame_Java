package com.NYU_introtojava;

import static com.NYU_introtojava.SnakeGame_JPanel.*;

public class CherryChangerRunnable implements Runnable {

    @Override
    public void run(){
        drawNewCherry();
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

}
