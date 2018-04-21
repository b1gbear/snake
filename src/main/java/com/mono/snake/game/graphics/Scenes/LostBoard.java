package com.mono.snake.game.graphics.Scenes;

import com.mono.snake.game.graphics.listener.MenuListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LostBoard extends JPanel implements ActionListener {

    private final int width;
    private final int height;
    private final MenuListener menuListener;

    public LostBoard(int width, int height, MenuListener menuListener) {
        this.menuListener = menuListener;
        this.width = width;
        this.height = height;
        this.setSize(width, height);
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new GridLayout(9, 3));

        // Create buttons and add action listener
        JButton replay = new JButton("Zacznij od nowa");
        JButton menu = new JButton("Powrót do menu");

        replay.addActionListener(menuListener);
        menu.addActionListener(menuListener);

        // add JLabel

        JLabel jLabel = new JLabel("Przegrales...");
        jLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(jLabel);
        // Add buttons

        this.add(replay);
        this.add(menu);

//        this.setVisible(true);
//        this.repaint();
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
