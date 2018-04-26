package com.mono.snake.game.graphics.entity;

import javax.swing.*;
import java.awt.*;


/**
 * Graphic point
 */
public class GraphicPoint extends JPanel {

    /**
     * Default color
     */
    public static final Color DEFAULT_COLOR = Color.white;

    /**
     * Graphic Point Constructor
     */
    public GraphicPoint(){
        this.setBackground(DEFAULT_COLOR);
    }

    /**
     * Change color
     * @param color Color to repaint
     */
    public void changeColor(Color color){
        this.setBackground(color);
        this.repaint();
    }


}