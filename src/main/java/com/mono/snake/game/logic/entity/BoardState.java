package com.mono.snake.game.logic.entity;

import com.mono.snake.game.entity.Fruit;
import com.mono.snake.game.entity.Point;
import com.mono.snake.game.entity.Snake;
import com.mono.snake.game.entityEnum.DirectionEnum;
import com.mono.snake.game.entityEnum.GameState;
import com.mono.snake.game.entityEnum.GameTypre;
import com.mono.snake.game.snake_consciousness.SnakeConsciousness;
import com.mono.snake.game.snake_consciousness.SnakeConsciousnessPlayer;

import java.util.LinkedList;

/**
 * Board
 */
public class BoardState {

    public BoardState(Point boardSize, GameState gameState) {
        this.boardSize = boardSize;
        this.gameState = gameState;
        this.fruits = new LinkedList<>();
        this.bots = new LinkedList<>();
        this.players = new LinkedList<>();
        this.lostState = new LostState(false,1);
    }

    /**
     * My players
     */
    private LinkedList<SnakeConsciousnessPlayer> players;
    /**
     * All players
     */
    private LinkedList<SnakeConsciousness> bots;

    /**
     * Fruits
     */
    private LinkedList<Fruit> fruits;

    /**
     * Board size ranges from from 0...to N-1
     */
    private Point boardSize;

    /**
     * Current game state
     */
    private GameState gameState;

    /**
     * Current game state
     */
    private GameTypre gameType;


    private LostState lostState;


    public void reset(GameTypre gameType)
    {
        this.gameType = gameType;
        gameState = GameState.GAME;
        bots.clear();
        players.clear();
        fruits.clear();






        players.add(new SnakeConsciousnessPlayer(new Snake(new Point(10,10)),1 ));
        if(gameType == GameTypre.MULTI)
        {

            SnakeConsciousnessPlayer secondSnake =new SnakeConsciousnessPlayer( new Snake(new Point(boardSize.getX()-10,boardSize.getY()-10)),2);
            secondSnake.setDirection(DirectionEnum.LEFT);
            players.add(secondSnake);
        }
    }

    public LinkedList<SnakeConsciousnessPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(LinkedList<SnakeConsciousnessPlayer> players) {
        this.players = players;
    }

    public LinkedList<SnakeConsciousness> getBots() {
        return bots;
    }

    public void setBots(LinkedList<SnakeConsciousness> bots) {
        this.bots = bots;
    }

    public LinkedList<Fruit> getFruits() {
        return fruits;
    }

    public void setFruits(LinkedList<Fruit> fruits) {
        this.fruits = fruits;
    }

    public Point getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(Point boardSize) {
        this.boardSize = boardSize;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public GameTypre getGameType() {
        return gameType;
    }

    public void setGameType(GameTypre gameType) {
        this.gameType = gameType;
    }

    public LostState getLostState() {
        return lostState;
    }

    public void setLostState(LostState lostState) {
        this.lostState = lostState;
    }
}
