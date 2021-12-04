package com.NYU_introtojava;

import javax.swing.*;
import java.awt.*;

public class BackgroundJMenu extends JMenu {
    Color bgColor = new Color(153, 184, 200);

    public BackgroundJMenu(String s){
        super(s);
    }


    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        setBackground(bgColor);

    }
}
