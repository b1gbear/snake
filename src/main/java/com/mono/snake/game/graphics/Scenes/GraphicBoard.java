package com.mono.snake.game.graphics.Scenes;

import com.mono.snake.game.entity.Fruit;
import com.mono.snake.game.entity.Point;
import com.mono.snake.game.entity.Snake;
import com.mono.snake.game.entityEnum.GameState;
import com.mono.snake.game.graphics.entity.GraphicPoint;
import com.mono.snake.game.graphics.listener.MenuListener;
import com.mono.snake.game.logic.Board;
import com.mono.snake.game.logic.entity.BoardState;
import com.mono.snake.game.snake_consciousness.SnakeConsciousness;
import com.mono.snake.game.snake_consciousness.SnakeConsciousnessPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Board of game
 */
public class GraphicBoard extends JPanel implements ActionListener {

    private  int wWidth ;
    private  int wHeight;

    ArrayList<ArrayList<GraphicPoint>> points;

    private Point size;
    private BoardState state;
    private final MenuListener menulistener;

    public GraphicBoard(Point size, BoardState state, int wWidth, int wHeight, MenuListener menuListener) {
        this.menulistener = menuListener;
        this.state = state;
        this.size = size;
        this.wHeight = wHeight;
        this.wWidth = wWidth;
        this.points = new ArrayList<>();
        initBoard(size);
    }

    private void initBoard(Point size ) {
        this.setLayout(new GridLayout(size.getY(),size.getX(),0,0));
        this.removeAll();

        for(int i = 0 ; i < size.getX();i++)
        {
            points.add(new ArrayList<>());
            for (int j = 0; j < size.getY(); j++) {
                GraphicPoint graphicPoint = new GraphicPoint();
                points.get(i).add(graphicPoint);
                this.add(graphicPoint);
            }
        }


        setBackground(Color.black);
        setFocusable(true);
        setDoubleBuffered(true);

        setPreferredSize(new Dimension(wWidth, wHeight));
        initGame();
    }



    public void resetScreen(){
        for(int i = 0 ; i < size.getY();i++) {
            for (int j = 0; j < size.getX(); j++) {
                points.get(i).get(j).changeColor(Color.WHITE);
            };
        }
    }

    private void initGame() {
        resetScreen();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        resetScreen();
        doDrawing(g,state);
    }

    public void doDrawing(Graphics g,BoardState state) {

        for(Fruit fruit:state.getFruits()) {
            if(fruit.getLocation().getX() < 0 || fruit.getLocation().getY() < 0)
                continue;
            points.get( fruit.getLocation().getY()).get( fruit.getLocation().getX()).changeColor(Color.GREEN);
        }

        for (SnakeConsciousness snakeConsciousness:state.getBots()){
            Snake snake = snakeConsciousness.getSnake();
            points.get(snake.getLocation().getY()).get( snake.getLocation().getX()).changeColor(Color.BLACK);


            for (Point point : snake.getTail()) {
                points.get( point.getY()).get( point.getX()).changeColor(Color.GRAY);
            }

        }

        for(SnakeConsciousnessPlayer player: state.getPlayers()) {
            if(player.getId() == 1)
            {
                for (Point point : player.getSnake().getTail()) {
                    points.get( point.getY()).get( point.getX()).changeColor(Color.CYAN);
                }
                points.get(player.getSnake().getLocation().getY()).get( player.getSnake().getLocation().getX()).changeColor(Color.blue);


            }else
            {
                for (Point point : player.getSnake().getTail()) {
                    points.get( point.getY()).get( point.getX()).changeColor(Color.RED);
                }
                points.get(player.getSnake().getLocation().getY()).get( player.getSnake().getLocation().getX()).changeColor(Color.ORANGE);
            }
        }


        Toolkit.getDefaultToolkit().sync();
    }



    @Override
    public void actionPerformed(ActionEvent e) {

        repaint();
    }



    private void gameOver(Graphics g) {

        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 30);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.black);
        g.setFont(small);
        g.drawString(msg, (wWidth - metr.stringWidth(msg)) / 2, wHeight / 2);
        g.drawString(msg, 10, 10);
    }


}