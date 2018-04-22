package com.mono.snake.game.graphics.Scenes;

import com.mono.snake.game.graphics.listener.MenuListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpBoard extends JPanel implements ActionListener {

    private final int width;
    private final int height;
    private final MenuListener menuListener;

    public HelpBoard(int width, int height, MenuListener menuListener) {
        this.width = width;
        this.height =height;
        this.setSize(width,height);
        this.setPreferredSize(new Dimension(width,height));
        this.setLayout(new GridLayout(9,3));

        this.menuListener = menuListener;

        // Create buttons and add action listener
        JButton menu = new JButton("Wyjscie do menu");

        menu.addActionListener(menuListener);

        // add JLabel

        JLabel jLabel = new JLabel("Pomoc");
        jLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(jLabel);
        JLabel helpText = new JLabel(
                "<html>Gra snake. Tryby: multiplayer i singleplayer<br>"+
        "Gracz 1: Poruszaj się WASD oraz przyspieszaj V.<br>"+
        "Gracz 2: Poruszaj się sztrzałkami oraz przyspieszaj M.<br>"+
       "Nacisnij ESC aby zapauzować, lub wyjść.<br><br></html>" );
        helpText.setHorizontalAlignment(JLabel.CENTER);


        this.add(helpText);
        this.add(menu);

        this.setVisible(true);
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
