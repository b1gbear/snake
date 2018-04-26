package  com.mono.snake.game.graphics.Scenes;

import com.mono.snake.game.graphics.listener.MenuListener;
import com.mono.snake.game.graphics.translation.MenuQuestions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseBoard extends JPanel implements ActionListener {

    private final int width;
    private final int height;
    private final MenuListener menuListener;

    public PauseBoard(int width,int height, MenuListener menuListener) {
        this.width = width;
        this.height =height;
        this.setSize(width,height);
        this.setPreferredSize(new Dimension(width,height));
        this.setLayout(new GridLayout(9,3));

        this.menuListener = menuListener;

        // Create buttons and add action listener
        JButton start = new JButton(MenuQuestions.BT_RESUME_GAME);
        JButton exit = new JButton(MenuQuestions.BT_GO_MENU);

        start.addActionListener(menuListener);
        exit.addActionListener(menuListener);

        // add JLabel

        JLabel jLabel = new JLabel(MenuQuestions.LB_PAUSE);
        jLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(jLabel);
        // Add buttons

        this.add(start);
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
