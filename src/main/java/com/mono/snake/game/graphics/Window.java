package com.mono.snake.game.graphics;

import javax.swing.*;
import java.awt.*;

/**
 * Window of our applicatio
 */
public class Window extends JFrame{
    public Window()
    {
        this.setTitle("Snake");
        this.setSize(900,900);
        this.setResizable(true);
        this.setLayout(new GridLayout(0, 1));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
