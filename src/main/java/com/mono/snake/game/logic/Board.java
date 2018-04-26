package com.mono.snake.game.logic;

import com.mono.snake.game.entity.Fruit;
import com.mono.snake.game.entity.Point;
import com.mono.snake.game.entity.Snake;
import com.mono.snake.game.entityEnum.*;
import com.mono.snake.game.logic.entity.BoardState;
import com.mono.snake.game.logic.entity.GameSettings;
import com.mono.snake.game.logic.entity.KeyboardState;
import com.mono.snake.game.logic.entity.LostState;
import com.mono.snake.game.snake_consciousness.SnakeConsciousness;
import com.mono.snake.game.snake_consciousness.SnakeConsciousnessAI;
import com.mono.snake.game.snake_consciousness.SnakeConsciousnessPlayer;
import com.mono.snake.game.utility.DirectionUtility;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Board
 */
public class Board {


    /**
     * Board State
     */
    private final BoardState state;

    /**
     * KeyboardState for Player 1
     */
    private final KeyboardState keysOne;

    /**
     * KeyboardState for Player 2
     */
    private final KeyboardState keysTwo;


    /**
     * Board constructor
     * @param boardState BoardState
     */
    public Board(BoardState boardState) {
        this.state = boardState;
        this.keysOne = new KeyboardState();
        this.keysTwo = new KeyboardState();
    }


    /**
     * Start new game
     * @param gameType Game Type (single/multi)
     */
    public void startNewGame(GameType gameType) {
        keysOne.setDirection(DirectionEnum.RIGHT);
        keysTwo.setDirection(DirectionEnum.LEFT);
        state.setGameState(GameState.GAME);
        state.reset(gameType);
        addAI();
    }


    /**
     * ADD AI snakes
     */
    public void addAI() {
        for (int i = 0; i < 5; i++) {
            this.state.getBots().add(new SnakeConsciousnessAI(new Snake(new Point(i, i))));
        }
    }

    /**
     * Executes one games tick
     */
    public void tick(Long timestamp) {
        iKeyboardMove();
        iSnakeMove(timestamp);
        iCrossedBoard();
        if(state.getGameState() == GameState.LOST)
            return;
        iCrossedOtherSnake();
        if(state.getGameState() == GameState.LOST)
            return;
        AIsnakesDecide(timestamp);
        AIcrossedBoard();
        AIsnakesCrossEachOtherOrMe();
        anyOfSnakesEats();
    }


    /**
     * Keyboard move
     */
    private void iKeyboardMove() {

        if (GameType.SINGLE == state.getGameType()) {

            KeyboardState keyboardState =  keysOne ;
            SnakeConsciousnessPlayer player = state.getPlayers().get(0);

            if (player.getId() == 1) {
                player.setDirection(keyboardState.getDirection());
                player.getTurboData().setTurboRequest(keyboardState.getTurboRequestedAt());
            }

        } else {
            for (SnakeConsciousnessPlayer player : state.getPlayers()) {
                    KeyboardState keyboardState = (player.getId() == 1)?keysOne:keysTwo;
                    player.setDirection(keyboardState.getDirection());
                    player.getTurboData().setTurboRequest(keyboardState.getTurboRequestedAt());
                }

            }

    }

    /**
     * MY snake moves
     * @param timestamp Timestamp
     */
    private void iSnakeMove(long timestamp) {

        for (SnakeConsciousnessPlayer snakeMe : state.getPlayers()) {


            long tick = snakeMe.getSpeed(timestamp) == MovementType.TURBO ? GameSettings.TURBO_SPEED : GameSettings.NORMAL_SPEED;
            if (snakeMe.getNextTick() + tick < timestamp) {
                snakeMe.getSnake().moveTowardsVector(DirectionUtility.directionToVector(snakeMe.move(state)));
                snakeMe.setNextTick(timestamp);
            }
        }

        for (SnakeConsciousnessPlayer snakeMe : state.getPlayers()) {

            snakeMe.executePenalty();

        }

    }

    /**
     * I Crossed board
     */
    private void iCrossedBoard() {
        List<SnakeConsciousnessPlayer> toRemove = new ArrayList<>();
        Iterator<SnakeConsciousnessPlayer> snakeConsciousnessPlayerIterator = state.getPlayers().iterator();
        while (snakeConsciousnessPlayerIterator.hasNext()) {
            SnakeConsciousnessPlayer snakeConsciousnessPlayer = snakeConsciousnessPlayerIterator.next();
            if (snakeCrossesBoard(snakeConsciousnessPlayer.getSnake())) {

                    if (state.getGameType() == GameType.SINGLE) {
                        state.setGameState(GameState.LOST);
                        state.setLostState(new LostState(false, 0));
                    } else /* Multiplayer */ {
                        state.setGameState(GameState.LOST);
                        int winnerIs = snakeConsciousnessPlayer.getId() == 1 ? 2 : 1;
                        state.setLostState(new LostState(true, winnerIs));
                    }

            }
        }
        state.getPlayers().removeAll(toRemove);
    }

    /**
     * I Crossed other snake
     */
    private void iCrossedOtherSnake() {
        List<SnakeConsciousnessPlayer> toRemove = new ArrayList<>();
        for (SnakeConsciousness snakeConsciousness : state.getBots()) {

            for (SnakeConsciousnessPlayer me : state.getPlayers()) {
                if (snakeConsciousness.getSnake().colidesWithPoint(me.getSnake().getLocation()) == CollisionEnum.TAIL) {
                    toRemove.add(me);
                }
            }
        }

        for (SnakeConsciousnessPlayer otherPlayer : state.getPlayers()) {
            for (SnakeConsciousnessPlayer me : state.getPlayers()) {
                if (me.getId() == otherPlayer.getId()) {
                    continue;
                }
                if (otherPlayer.getSnake().colidesWithPoint(me.getSnake().getLocation()) == CollisionEnum.TAIL) {
                    toRemove.add(me);
                }
            }
        }

        if (state.getGameType() == GameType.SINGLE) {
            if (!toRemove.isEmpty()) {

                state.setLostState(new LostState(false, 0));
                state.setGameState(GameState.LOST);
            }
        } else {
            if (toRemove.size() >= 2) {
                state.setGameState(GameState.LOST);
                state.setLostState(new LostState(true, 0));
            } else if (toRemove.size() == 1) {
               SnakeConsciousnessPlayer snakeConsciousnessPlayer = toRemove.get(0);
               state.setGameState(GameState.LOST);
               state.setLostState(new LostState(true,snakeConsciousnessPlayer.getId() == 1?2:1));
            }
        }
    }

    /**
     * AI snakes crossed each other
     */
    private void AIsnakesCrossEachOtherOrMe() {
        Iterator<SnakeConsciousness> iterator = state.getBots().iterator();
        List<SnakeConsciousness> toRemove = new ArrayList<>();
        while (iterator.hasNext()) {
            SnakeConsciousness snakeConsciousness = iterator.next();

            for (SnakeConsciousness snake : state.getPlayers()) {
                if (snake.getSnake().colidesWithPoint(snakeConsciousness.getSnake().getLocation()) == CollisionEnum.TAIL) {
                    state.getFruits().addAll(createFruitsBasedOnSnake(snakeConsciousness.getSnake(), GameSettings.FRUIT_INTERVAL));
                    toRemove.add(snakeConsciousness);
                }
            }

            for (SnakeConsciousness snakeConsciousnessTwo : state.getBots()) {

                if (snakeConsciousness.getSnake().getLocation().equals(snakeConsciousnessTwo.getSnake().getLocation()))
                    continue;

                if (snakeConsciousnessTwo.getSnake().colidesWithPoint(snakeConsciousness.getSnake().getLocation()) == CollisionEnum.TAIL) {
                    state.getFruits().addAll(createFruitsBasedOnSnake(snakeConsciousness.getSnake(), GameSettings.FRUIT_INTERVAL));
                    toRemove.add(snakeConsciousness);
                }

            }
        }
        state.getBots().removeAll(toRemove);

    }


    /**
     * AI Snakes decide
     * @param timestamp Timestamp
     */
    private void AIsnakesDecide(long timestamp) {

        for (int i = 0; i < state.getBots().size(); i++) {
            SnakeConsciousness snakeConsciousness = state.getBots().get(i);
            if (snakeConsciousness.getSnake().getNextTick() < timestamp) {
                snakeConsciousness.getSnake().moveTowardsVector(DirectionUtility.directionToVector(snakeConsciousness.move(state)));
                snakeConsciousness.getSnake().setNextTick(timestamp + GameSettings.NORMAL_SPEED);
            }
        }
    }

    /**
     * Any of snakes eats
     */
    public void anyOfSnakesEats() {
        Iterator<Fruit> iterator = state.getFruits().iterator();
        while (iterator.hasNext()) {
            Fruit fruit = iterator.next();

            boolean leave = false;
            for (SnakeConsciousness player : state.getPlayers()) {


                if (player.getSnake().colidesWithPoint(fruit.getLocation()) == CollisionEnum.HEAD) {
                    player.getSnake().incrementSize(fruit.getSize());
                    iterator.remove();
                    leave = true;
                }

            }
            if (leave) {
                continue;
            }

            for (SnakeConsciousness snakeConsciousness : state.getBots()) {
                if (snakeConsciousness.getSnake().colidesWithPoint(fruit.getLocation()) == CollisionEnum.HEAD) {
                    snakeConsciousness.getSnake().incrementSize(fruit.getSize());
                    iterator.remove();
                    break;
                }
            }


        }
    }

    /**
     * AI crosses board
     */
    public void AIcrossedBoard() {
        Iterator<SnakeConsciousness> iterator = state.getBots().iterator();
        while (iterator.hasNext()) {
            SnakeConsciousness snakeConsciousness = iterator.next();
            if (snakeCrossesBoard(snakeConsciousness.getSnake())) {
                state.getFruits().addAll(createFruitsBasedOnSnake(snakeConsciousness.getSnake(), GameSettings.FRUIT_INTERVAL));
                iterator.remove();
            }
        }

    }

    /**
     * Create Fruits Based on snake
     * @param snake Snake
     * @param interval Interval > 0
     * @return Fruit list
     */
    public static  List<Fruit> createFruitsBasedOnSnake(Snake snake, int interval) {
        List<Fruit> fruits = new ArrayList<>();
        if (snake.getSize() <= 1) {
            fruits.add(new Fruit(new Point(snake.getLocation()), 1));
        } else {

            fruits.add(new Fruit(new Point(snake.getLocation()), interval));
            for (int i = 0; i < snake.getTail().size(); i++) {
                if ((i + 1) % interval == 0) {
                    fruits.add(new Fruit(new Point(snake.getTail().get(i)), interval));

                }
            }
        }
        return fruits;
    }

    /**
     * Check if snake crosses board
     *
     * @param snake Snake
     * @return true if snake has lost
     */
    public boolean snakeCrossesBoard(Snake snake) {
        return pointCrossesBoard(snake.getLocation(), state.getBoardSize());
    }

    /**
     * Point crosses board
     */
    static boolean pointCrossesBoard(Point point, Point size) {
        return point.getX() >= size.getX() || point.getX() < 0 || point.getY() >= size.getY() || point.getY() < 0;
    }


    public BoardState getState() {
        return state;
    }

    public KeyboardState getKeysOne() {
        return keysOne;
    }

    public KeyboardState getKeysTwo() {
        return keysTwo;
    }
}
