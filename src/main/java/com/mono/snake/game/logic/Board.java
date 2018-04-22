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


    final BoardState state;

    final KeyboardState keysOne;

    final KeyboardState keysTwo;


    public BoardState getState() {
        return state;
    }

    public Board(BoardState boardStatea) {
        this.state = boardStatea;
        this.keysOne = new KeyboardState();
        this.keysTwo = new KeyboardState();
    }


    public void startNewGame(GameTypre gameType) {
        keysOne.setDirection(DirectionEnum.RIGHT);
        keysTwo.setDirection(DirectionEnum.LEFT);
        state.setGameState(GameState.GAME);
        state.reset(gameType);
        addAI();
    }


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



    private void iKeyboardMove() {

        if (GameTypre.SINLE == state.getGameType()) {

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

    private void iSnakeMove(long timestamp) {

        for (SnakeConsciousnessPlayer snakeMe : state.getPlayers()) {


            long tick = snakeMe.getSpeed(timestamp) == MovementType.Turbo ? GameSettings.TURBO_SPEED : GameSettings.NORMAL_SPEED;
            if (snakeMe.getNextTick() + tick < timestamp) {
                snakeMe.getSnake().moveTowardsVector(DirectionUtility.directionToVector(snakeMe.move(state)));
                snakeMe.setNextTick(timestamp);
            }
        }

        for (SnakeConsciousnessPlayer snakeMe : state.getPlayers()) {

            snakeMe.executePenalty();

        }

    }

    private void iCrossedBoard() {
        List<SnakeConsciousnessPlayer> toRemove = new ArrayList<>();
        Iterator<SnakeConsciousnessPlayer> snakeConsciousnessPlayerIterator = state.getPlayers().iterator();
        while (snakeConsciousnessPlayerIterator.hasNext()) {
            SnakeConsciousnessPlayer snakeConsciousnessPlayer = snakeConsciousnessPlayerIterator.next();
            if (snakeCrossesBoard(snakeConsciousnessPlayer.getSnake())) {

                    if (state.getGameType() == GameTypre.SINLE) {
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


        if (state.getGameType() == GameTypre.SINLE) {
            if (!toRemove.isEmpty()) {

                state.setLostState(new LostState(false, 0));
                state.setGameState(GameState.LOST);
            }
        } else {
            if (toRemove.size() >= 2) {
                state.setGameState(GameState.LOST);
                state.setLostState(new LostState(true, 0));
            } else if (toRemove.size() == 1) {



//                if (state.getPlayers().size() == 1) {
//
//                    state.setLostState(new LostState(true, (toRemove.get(0).getId() != 1) ? 1 : 2));
//                    state.setGameState(GameState.LOST);
//
//                } else {
//                    state.getPlayers().removeAll(toRemove);
//                    state.getFruits().addAll(createFruitsBasedOnSnake(toRemove.get(0).getSnake(), GameSettings.FRUIT_INTERVAL));
//                }

               SnakeConsciousnessPlayer snakeConsciousnessPlayer = toRemove.get(0);
               state.setGameState(GameState.LOST);
               state.setLostState(new LostState(true,snakeConsciousnessPlayer.getId() == 1?2:1));


            }
        }
    }

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


    public List<Fruit> createFruitsBasedOnSnake(Snake snake, int interval) {
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

    public KeyboardState getKeysOne() {
        return keysOne;
    }

    public KeyboardState getKeysTwo() {
        return keysTwo;
    }
}
