package com.mono.snake.game.graphics;

import com.mono.snake.game.entity.Point;
import com.mono.snake.game.entityEnum.GameState;
import com.mono.snake.game.graphics.Scenes.*;
import com.mono.snake.game.graphics.listener.MenuListener;
import com.mono.snake.game.logic.Board;
import com.mono.snake.game.logic.entity.BoardState;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;

/**
 * Graphics
 */
public class Graphics {

    private Window window;

    private Point size;

    private GraphicBoard graphicBoard;
    private LostBoard lostBoard;
    private MenuBoard menuBoard;
    private PauseBoard pauseBoard;
    private HelpBoard helpBoard;

    private GameState currentGameState;

    private final static int wWidth = 1024;
    private final static int wHeight = 800;

    private final MenuListener menuListener;
    private final KeyAdapter keyListener;

    public Graphics(Window window, Point size, BoardState state, GameState gameState, MenuListener menuListener, KeyAdapter keyListener) {
        this.keyListener = keyListener;
        this.window = window;
        this.graphicBoard = new GraphicBoard(size, state, wWidth, wHeight,menuListener);
        this.lostBoard = new LostBoard(wWidth, wHeight,menuListener,state);
        this.menuBoard = new MenuBoard(wWidth, wHeight,menuListener);
        this.pauseBoard = new PauseBoard(wWidth, wHeight,menuListener);
        this.helpBoard = new HelpBoard(wWidth, wHeight,menuListener);
        this.menuListener = menuListener;
        window.setLayout(new BorderLayout());
        window.setSize(wWidth,wHeight);
        window.add(graphicBoard, BorderLayout.CENTER);
        window.setVisible(true);
        graphicBoard.repaint();
     //   graphicBoard.setVisible(true);
        window.pack();
        this.size = size;
        this.currentGameState = gameState;
        window.setVisible(true);

        window.addKeyListener(keyListener);

    }


    public void printGameState(GameState gameState) {
        if (gameState == currentGameState) {
            if (gameState == GameState.GAME) {
                graphicBoard.repaint();
            }
        } else {
         //   this.window.getContentPane().removeAll();

            window.remove(graphicBoard);
            window.remove(menuBoard);
            window.remove(lostBoard);
            window.remove(pauseBoard);
            window.remove(helpBoard);


            window.requestFocus();

            if (gameState == GameState.GAME) {
                window.add(graphicBoard);
            } else if (gameState == GameState.MENU) {
                window.add(menuBoard);
            }
            if (gameState == GameState.LOST) {
                window.add(lostBoard);
            }
            if (gameState == GameState.PAUSE) {
                window.add(pauseBoard);
            }if (gameState == GameState.HELP) {
                window.add(helpBoard);
            }
           window.pack();

           window.repaint();

        }

        currentGameState = gameState;

    }

}
