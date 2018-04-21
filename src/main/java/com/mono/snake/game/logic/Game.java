package com.mono.snake.game.logic;

import com.mono.snake.game.entity.Fruit;
import com.mono.snake.game.entity.Point;
import com.mono.snake.game.entity.Snake;
import com.mono.snake.game.entityEnum.DirectionEnum;
import com.mono.snake.game.entityEnum.GameState;
import com.mono.snake.game.entityEnum.KeyEnum;
import com.mono.snake.game.entityEnum.MenuAction;
import com.mono.snake.game.graphics.listener.KeyboardKeyListener;
import com.mono.snake.game.graphics.Graphics;
import com.mono.snake.game.graphics.Window;
import com.mono.snake.game.graphics.listener.MenuListener;
import com.mono.snake.game.snake_consciousness.SnakeConsciousnessImpl;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.security.Key;
import java.util.concurrent.ConcurrentLinkedDeque;



/**
 * Board - executes only game's com.mono.snake.game.logic
 *
 */
public class Game  {

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

    public Game(Window window )
    {
        this.size = new Point(50,50);
        this.directionEnums= new ConcurrentLinkedDeque<>();
        this.gameKeyListener = new KeyboardKeyListener(directionEnums);
        this.menuListener = new MenuListener();
        this.board = new Board(this.size);
        this.window = window;
        this.graphics = new Graphics(window,this.size,board,GameState.GAME,menuListener,gameKeyListener);
        window.addKeyListener(gameKeyListener);
    }

    /**
     * Main run function
     */
    public void run(){
        board.setGameState(GameState.MENU);
        int val = 1;
        long start =  System.currentTimeMillis();
        while(true){

            handleMenuListener();
            if(board.getGameState() == GameState.GAME)
            {
                handleKeyboard(start);
                if(board.getGameState() == GameState.PAUSE){
                    continue;
                }

                if (val % 30 == 0)
                {
                    board.getFruits().add(
                            new Fruit(new Point(
                            randomWithRange(0,size.getX()-2),
                            randomWithRange(0,size.getY()-2))
                    ,1));
                }

                if (val % 100 == 0)
                {
                    board.getSnakeConsciousnesses().add(
                            new SnakeConsciousnessImpl(new Snake(new Point(
                                    randomWithRange(0,size.getX()-1),
                                    randomWithRange(0,size.getY()-1))
                                    ))
                    );
                }





                board.tick(System.currentTimeMillis()-start);
                val++;
            }
            graphics.printGameState(board.getGameState());
            delay();
        }
    }

    private void handleKeyboard(long start) {
        while(!gameKeyListener.getKeyEnums().isEmpty()) {

            KeyEnum keyEnum = gameKeyListener.getKeyEnums().removeFirst();
            DirectionEnum direction = keyToDirection(keyEnum);

            if(direction != DirectionEnum.NONE) {
                board.setDirectionEnum(direction);
            }
            if(keyEnum == KeyEnum.ESC)
            {
                board.setGameState(GameState.PAUSE);
                ;
            }
            if(keyEnum == KeyEnum.SPACE)
            {
                board.setWantSpeed(System.currentTimeMillis()-start);
            }
        }
    }

    private void handleMenuListener() {
        while(!menuListener.getMenuActions().isEmpty())
        {
            MenuAction menuAction = menuListener.getMenuActions().removeFirst();

            if(menuAction == MenuAction.GO_MENU){
                board.setGameState(GameState.MENU);
            }else   if(menuAction == MenuAction.EXIT){
                window.dispose();
                System.exit(0);
            }else  if(menuAction == MenuAction.START_GAME) {

                board.startNewGame();
            }else if (menuAction == MenuAction.RESUME)
            {
                board.setGameState(GameState.GAME);
            }else if (menuAction == MenuAction.HELP)
            {
                board.setGameState(GameState.HELP);
            }

        }
    }


    /**
     * Delay - sets game pace...
     */
    private void delay()
    {
        try {
            Thread.sleep(GAME_SPEED);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Is turbo
     * @param keyEnum Pressed Key
     * @return true if turbo is requested
     */
    public boolean isTurbo(KeyEnum keyEnum){
        return keyEnum == KeyEnum.SPACE;
    }

    /**
     * Key to direction
     * @return Direction
     */
    public DirectionEnum keyToDirection(KeyEnum keyEnum) {
        if(keyEnum == KeyEnum.DOWN) {
            return DirectionEnum.DOWN;
        }else if (keyEnum == KeyEnum.UP){
            return DirectionEnum.UP;
        }else if (keyEnum == KeyEnum.RIGHT) {
            return DirectionEnum.RIGHT;
        }else if (keyEnum == KeyEnum.LEFT) {
            return DirectionEnum.LEFT;
        }
        return DirectionEnum.NONE;
    }


    static int randomWithRange(int min, int max)
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }


}
