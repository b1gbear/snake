package com.mono.snake.game.logic;

import com.mono.snake.game.entity.Fruit;
import com.mono.snake.game.entity.Point;
import com.mono.snake.game.entity.Snake;
import com.mono.snake.game.entityEnum.*;
import com.mono.snake.game.graphics.Graphics;
import com.mono.snake.game.graphics.Window;
import com.mono.snake.game.graphics.listener.KeyboardKeyListener;
import com.mono.snake.game.graphics.listener.MenuListener;
import com.mono.snake.game.logic.entity.BoardState;
import com.mono.snake.game.snake_consciousness.SnakeConsciousnessAI;

import java.awt.event.KeyEvent;
import java.util.concurrent.ConcurrentLinkedDeque;


/**
 * Board - executes only game's com.mono.snake.game.logic
 */
public class Game {

    /**
     * Window
     */
    final private Graphics graphics;

    /**
     * Window
     */
    final private Window window;

    /**
     * KeyboardKeyListener
     */
    final private KeyboardKeyListener gameKeyListener;


    /**
     * Menu listener
     */
    final private MenuListener menuListener;

    /**
     * Queue of keys
     */
    final private ConcurrentLinkedDeque<KeyEnum> directionEnums;

    /**
     * Board
     */
    final private Board board;

    final private static int GAME_SPEED = 20;

    final private Point size;

    public Game(Window window) {
        this.size = new Point(50, 50);
        this.directionEnums = new ConcurrentLinkedDeque<>();
        this.gameKeyListener = new KeyboardKeyListener(directionEnums);
        this.menuListener = new MenuListener();
        this.board = new Board(new BoardState(size,GameState.MENU));
        this.window = window;
        this.graphics = new Graphics(window, this.size, board.getState(), GameState.GAME, menuListener, gameKeyListener);
        window.addKeyListener(gameKeyListener);
    }

    /**
     * Main run function
     */
    public void run() {
        board.getState().setGameState(GameState.MENU);
        int val = 1;
        long start = System.currentTimeMillis();
        while (true) {
            handleMenuListener();
            if (board.getState().getGameState() == GameState.GAME) {
                handleKeyboard(start);
                if (board.getState().getGameState() == GameState.PAUSE) {
                    continue;
                }
                if (val % 30 == 0) {
                    board.getState().getFruits().add(
                            new Fruit(new Point(
                                    randomWithRange(0, size.getX() - 2),
                                    randomWithRange(0, size.getY() - 2))
                                    , 1));
                }
                if (val % 100 == 0) {
                    board.getState().getBots().add(
                            new SnakeConsciousnessAI(new Snake(new Point(
                                    randomWithRange(0, size.getX() - 1),
                                    randomWithRange(0, size.getY() - 1))
                            ))
                    );
                }
                board.tick(System.currentTimeMillis() - start);
                val++;
            }
            graphics.printGameState(board.getState().getGameState());
            delay();
        }
    }

    private void handleKeyboard(long start) {
        while (!gameKeyListener.getKeyEnums().isEmpty()) {
            KeyEnum keyEnum =  gameKeyListener.getKeyEnums().removeFirst();
            if(keyEnum == KeyEnum.W){
                board.getKeysOne().setDirection(DirectionEnum.UP);
            }else if (keyEnum == KeyEnum.A){
                board.getKeysOne().setDirection(DirectionEnum.LEFT);
            }else if (keyEnum == KeyEnum.S){
                board.getKeysOne().setDirection(DirectionEnum.DOWN);
            }else if (keyEnum == KeyEnum.D){
                board.getKeysOne().setDirection(DirectionEnum.RIGHT);
            }else if (keyEnum == KeyEnum.SPEED_LEFT) {
                board.getKeysOne().setTurboRequestedAt(start);
            }else if(keyEnum == KeyEnum.UP){
                board.getKeysTwo().setDirection(DirectionEnum.UP);
            }else if (keyEnum == KeyEnum.LEFT){
                board.getKeysTwo().setDirection(DirectionEnum.LEFT);
            }else if (keyEnum == KeyEnum.RIGHT){
                board.getKeysTwo().setDirection(DirectionEnum.RIGHT);
            }else if (keyEnum == KeyEnum.DOWN){
                board.getKeysTwo().setDirection(DirectionEnum.DOWN);
            }else if (keyEnum == KeyEnum.SPEED_RIGT) {
                board.getKeysTwo().setTurboRequestedAt(start);
            }else if (keyEnum == KeyEnum.ESC) {
                board.getState().setGameState(GameState.PAUSE);
            }
        }
    }

    private void handleMenuListener() {
        while (!menuListener.getMenuActions().isEmpty()) {
            MenuAction menuAction = menuListener.getMenuActions().removeFirst();

            if (menuAction == MenuAction.GO_MENU) {
                board.getState().setGameState(GameState.MENU);
            } else if (menuAction == MenuAction.EXIT) {
                window.dispose();
                System.exit(0);
            } else if (menuAction == MenuAction.START_GAME_SINGLE) {
                board.startNewGame(GameTypre.SINLE);
            }
            if (menuAction == MenuAction.START_GAME_MULTI) {
                board.startNewGame(GameTypre.MULTI);
            } else if (menuAction == MenuAction.RESUME) {
                board.getState().setGameState(GameState.GAME);
            } else if (menuAction == MenuAction.HELP) {
                board.getState().setGameState(GameState.HELP);
            }

        }
    }


    /**
     * Delay - sets game pace...
     */
    private void delay() {
        try {
            Thread.sleep(GAME_SPEED);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static int randomWithRange(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }


}
