package com.mono.snake.game.graphics.Scenes;

import com.mono.snake.game.graphics.listener.MenuListener;
import com.mono.snake.game.logic.entity.BoardState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LostBoard extends JPanel implements ActionListener {

    private final int width;
    private final int height;
    private final MenuListener menuListener;
    private final BoardState boardState;
    private JLabel text;

    public LostBoard(int width, int height, MenuListener menuListener, BoardState state) {
        this.boardState = state;
        this.menuListener = menuListener;
        this.width = width;
        this.height = height;
        this.setSize(width, height);
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new GridLayout(9, 3));
        this.text = new JLabel("Uninitialized");
        this.text.setHorizontalAlignment(JLabel.CENTER);
        JButton menu = new JButton("Powrót do menu");
        menu.addActionListener(menuListener);

        JLabel jLabel = new JLabel("Koniec gry...");
        jLabel.setHorizontalAlignment(JLabel.CENTER);

        this.add(jLabel);
        this.add(this.text);
        this.add(menu);
        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(!this.boardState.getLostState().isMultiplayer()) {
            this.text.setText("Przegrales...");
        }else {
            if(this.boardState.getLostState().getWinnerIs() == 0) {
                this.text.setText("Remis!");

            }else if (this.boardState.getLostState().getWinnerIs() == 1) {
                this.text.setText("Wygral gracz pierwszy!");

            }else {
                this.text.setText("Wygral gracz drugi!");
            }
        }

    }
}
