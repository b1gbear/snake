package com.mono.snake.game.graphics.Scenes;

import com.mono.snake.game.entity.Point;
import com.mono.snake.game.entityEnum.GameState;
import com.mono.snake.game.graphics.listener.MenuListener;
import com.mono.snake.game.logic.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MenuBoard extends JPanel implements ActionListener {

    private final int width;
    private final int height;
    private final MenuListener menuListener;

    public MenuBoard(int width,int height, MenuListener menuListener) {
        this.width = width;
        this.height =height;
        this.setSize(width,height);
        this.setPreferredSize(new Dimension(width,height));
        this.setLayout(new GridLayout(9,3));

        this.menuListener = menuListener;

        // Create buttons and add action listener
        JButton starts = new JButton("Zacznij grę multiplayer!");
        JButton startm = new JButton("Zacznij grę singleplayer!");
        JButton help = new JButton("Pomoc");
        JButton exit = new JButton("Wyjscie");

        starts.addActionListener(menuListener);
        startm.addActionListener(menuListener);
        exit.addActionListener(menuListener);
        help.addActionListener(menuListener);

        // add JLabel

        JLabel jLabel = new JLabel("Menu");
        jLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(jLabel);
        // Add buttons

        this.add(starts);
        this.add(startm);
        this.add(help);
        this.add(exit);



//        this.setVisible(true);
        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
