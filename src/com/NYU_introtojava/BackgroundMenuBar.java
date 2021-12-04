package com.NYU_introtojava;

import javax.swing.*;
import java.awt.*;

public class BackgroundMenuBar extends JMenuBar {

    Color bgColor = new Color(153, 184, 200);
    public void setColor(Color color){
        bgColor = color;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(bgColor);
        g2d.fillRect(0,0,getWidth() - 1, getHeight() -1);
    }
}
