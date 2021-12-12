package com.NYU_introtojava;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SnakeGame_JPanel extends JPanel implements ActionListener {

    Window_JFrame currentWindow;

    public static final int sizeOfImages = 15;//in pixels

    //refresh rate
    private static final int REFRESH_RATE = 100;

    //max number of dots on the boats
    private final int ALL_LOCATIONS = (Window_JFrame.getWinWidth() * Window_JFrame.getWinHeight()/(100));


    //stores xy coordinates of snake
    private final int[] x = new int[ALL_LOCATIONS];
    private final int[] y = new int[ALL_LOCATIONS];

    //assets
    Image cherry;
    private int numberOfCherries = -1;
    Image snakeBody;
    Image RIP;

    //booleans
    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    public boolean inGame;

    //timer
    private Timer timer;

    //private int dots;
    private int sizeOfSnake = 3;
    private static int cherry_x;
    private static int cherry_y;

    public SnakeGame_JPanel(Window_JFrame window){
        this();
        currentWindow = window;
    }
    public SnakeGame_JPanel() {
        addKeyListener(new KeyListener());
        inGame = true;
//        loadImages();

        LoadImagesRunnable s = new LoadImagesRunnable(this);
        Thread loadImagesThread = new Thread(s);
        loadImagesThread.start();

        setBackground(new Color(0,77,117));
        startGame();
        setFocusable(true);

    }

    public int getCherry_x(){
        return cherry_x;
    }

    public int getCherry_y(){
        return cherry_y;
    }

    public void changeCherry_x(int n){
        cherry_x = n;
    }
    public static void changeCherry_y(int n){
        cherry_y = n;
    }

    private void startGame(){
        for(int z = 0; z < sizeOfSnake; z++){
            x[z] = sizeOfImages * (sizeOfImages/2) - z * sizeOfImages;
            y[z] = sizeOfImages * (sizeOfImages/2);
        }
//        drawNewCherry();
        CherryChangerRunnable r = new CherryChangerRunnable(this);
        new Thread(r).start();
        timer = new Timer(REFRESH_RATE, this);
        timer.start();
    }

//    private void loadImages(){
//        ImageIcon imageIcon1 = new ImageIcon("Assets/cherry.png");
//        cherry = imageIcon1.getImage();
//
//        ImageIcon imageIcon2 = new ImageIcon("Assets/snake_body.png");
//        snakeBody = imageIcon2.getImage();
//    }

    public int getNumberOfCherries(){
        return numberOfCherries;
    }
    public void printNumberOfCherries(){
        System.out.println("Number of Cherries: " + numberOfCherries);
    }

    public void increaseNumberOfCherries(){
        numberOfCherries += 1;
        printNumberOfCherries();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        doDrawing(g);
    }

    private void doDrawing(Graphics g){
        if (inGame) {

            g.drawImage(cherry, cherry_x, cherry_y, sizeOfImages,sizeOfImages,this);

            for (int i = 0; i < sizeOfSnake; i++) {
                g.drawImage(snakeBody, x[i], y[i],sizeOfImages,sizeOfImages, this);
            }

            Toolkit.getDefaultToolkit().sync();

        } else {
            gameOver(g);
        }
    }

    protected void move(){
        for (int z = sizeOfSnake; z > 0; z--) {
            x[z] = x[(z - sizeOfImages/10)];
            y[z] = y[(z - sizeOfImages/10)];
        }

        if (leftDirection) {
            x[0] -= sizeOfImages;
        }

        if (rightDirection) {
            x[0] += sizeOfImages;
        }

        if (upDirection) {
            y[0] -= sizeOfImages;
        }

        if (downDirection) {
            y[0] += sizeOfImages;
        }
    }

    protected void checkCollision(){
        //if it hits itself
        for (int z = sizeOfSnake; z > 0; z--) {

            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
                break;
            }
        }

        //TODO wrap snake -- don't stop the game when it hits the end?
        //hits bottom
        if (y[0] >= Window_JFrame.getWinHeight() - sizeOfImages * 5) {
            inGame = false;
        }

        //Hit upper wall
        if (y[0] <= 0) {
            inGame = false;
        }

        //right side
        if (x[0] >= Window_JFrame.getWinWidth()) {
            inGame = false;
        }

        //hit left side
        if (x[0] <= 0) {
            inGame = false;
        }

        //stop the timer
        if (!inGame) {
            timer.stop();
        }
    }

    protected void checkCherry(){
        if ((x[0] == cherry_x) && (y[0] == cherry_y)) {
            sizeOfSnake++;
//            drawNewCherry();
            CherryChangerRunnable r = new CherryChangerRunnable(this);
            new Thread(r).start();
        }
    }

    private void gameOver(Graphics g){
        String msg = "You Died.";
        Font myFont = new Font("Courier", Font.BOLD, 14);
        FontMetrics fontMetrics = getFontMetrics(myFont);

        g.setColor(Color.white);
        g.setFont(myFont);
        g.drawImage(RIP, 150,250,200, 200, this);
        g.drawString(msg, (Window_JFrame.getWinWidth() - fontMetrics.stringWidth(msg)) / 2, Window_JFrame.getWinHeight() / 3);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        if(inGame){
//            ActionPerformedRunnable r = new ActionPerformedRunnable(this);
//            Thread actionThread = new Thread(r);
//            actionThread.start();
//        }
        if(inGame){
            checkCherry();
            checkCollision();
            move();
        }
        repaint();
    }

    private class KeyListener extends KeyAdapter{

        @Override
        public void keyPressed(KeyEvent e){
            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
//                System.out.println("SNAKE WENT LEFT");
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
//                System.out.println("SNAKE WENT RIGHT");
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_UP) && (!downDirection)) {
//                System.out.println("SNAKE WENT UP");
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
//                System.out.println("SNAKE WENT DOWN");
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
            }
        }
    }
}
