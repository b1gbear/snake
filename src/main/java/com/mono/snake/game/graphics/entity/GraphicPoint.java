package com.mono.snake.game.graphics.entity;

import javax.swing.*;
import java.awt.*;


/**
 * Graphic point
 */
public class GraphicPoint extends JPanel {

    public static Color DEFAULT_COLOR = Color.white;

    public GraphicPoint(){
        this.setBackground(DEFAULT_COLOR);
    }

    public void changeColor(Color d){
        this.setBackground(d);
        this.repaint();
    }


}