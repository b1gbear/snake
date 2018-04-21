package com.mono.snake.game.graphics.Scenes;

import com.mono.snake.game.entity.Fruit;
import com.mono.snake.game.entity.Point;
import com.mono.snake.game.entity.Snake;
import com.mono.snake.game.entityEnum.GameState;
import com.mono.snake.game.graphics.entity.GraphicPoint;
import com.mono.snake.game.graphics.listener.MenuListener;
import com.mono.snake.game.logic.Board;
import com.mono.snake.game.snake_consciousness.SnakeConsciousness;

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
    private Board board;
    private final MenuListener menulistener;

    public GraphicBoard(Point size, Board board, int wWidth, int wHeight, MenuListener menuListener) {
        this.menulistener = menuListener;
        this.board = board;
        this.size = size;
        this.wHeight = wHeight;
        this.wWidth = wWidth;
        this.points = new ArrayList<>();
        initBoard();
    }

    private void initBoard( ) {
        this.setLayout(new GridLayout(size.getY(),size.getX(),0,0));

        for(int i = 0 ; i < size.getX();i++)
        {
            points.add(new ArrayList<>());
            for (int j = 0; j < size.getY(); j++) {
                GraphicPoint graphicPoint = new GraphicPoint();
                points.get(i).add(graphicPoint);
                this.add(graphicPoint);
            };
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
        doDrawing(g,board);
    }

    public void doDrawing(Graphics g,Board board) {

        for(Fruit fruit:board.getFruits()) {
            points.get( fruit.getLocation().getY()).get( fruit.getLocation().getX()).changeColor(Color.GREEN);
        }

        for (SnakeConsciousness snakeConsciousness:board.getSnakeConsciousnesses()){
            Snake snake = snakeConsciousness.getSnake();
            points.get(snake.getLocation().getY()).get( snake.getLocation().getX()).changeColor(Color.BLACK);


            for (Point point : snake.getTail()) {
                points.get( point.getY()).get( point.getX()).changeColor(Color.GRAY);
            }

        }

        for (Point point : board.getSnake().getTail()) {
           points.get( point.getY()).get( point.getX()).changeColor(Color.CYAN);
        }

        points.get(board.getSnake().getLocation().getY()).get( board.getSnake().getLocation().getX()).changeColor(Color.blue);




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