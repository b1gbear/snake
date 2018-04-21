package com.mono.snake.game.logic;

import com.mono.snake.game.entity.Fruit;
import com.mono.snake.game.entity.Point;
import com.mono.snake.game.entity.Snake;
import com.mono.snake.game.entityEnum.CollisionEnum;
import com.mono.snake.game.entityEnum.DirectionEnum;
import com.mono.snake.game.entityEnum.GameState;
import com.mono.snake.game.snake_consciousness.SnakeConsciousness;
import com.mono.snake.game.snake_consciousness.SnakeConsciousnessImpl;
import com.mono.snake.game.utility.DirectionUtility;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Board
 */
public class Board {

    /**
     * My snake
     */
    private Snake snake;
    /**
     * All snakes
     */
    private LinkedList<SnakeConsciousness> snakeConsciousnesses;

    /**
     * Fruits
     */
    private LinkedList<Fruit> fruits;

    /**
     * Board size ranges from from 0...to N-1
     */
    private Point boardSize;

    /**
     * Current direction of snake
     */
    private DirectionEnum directionEnum;


    /**
     * Current game state
     */
    private GameState gameState;


    private static final int FRUIT_INTERVAL = 2;


    private static final int NORMAL_SPEED = 100;

    // Turbo settings
    private static final int TURBO_SPEED = 60;

    private static final int TURBO_TIMEOUT = 1000;

    private static final double TURBO_CONSUME_PER_SECOND = 0.5;

    private  long  previousSpeed = NORMAL_SPEED;

    private long turboRequest = 0;
    private long lastTurbo = 0;

    private DirectionEnum previousDirection;

    private double turboPenalty = 0;
    /**
     * Board constructor
     *
     * @param boardSize size of board
     */
    public Board(Point boardSize) {
        this.boardSize = boardSize;
        startNewGame();
    }


    public void  startNewGame()
    {
        clean();
        addAI();
        setGameState(GameState.GAME);
        this.turboRequest = -100 *TURBO_TIMEOUT;
    }


    /**
     * Clean state of game
     */
    public void clean() {
        this.snake = new Snake(new Point(boardSize.getX() / 2, boardSize.getY() / 2));
        this.snakeConsciousnesses = new LinkedList<>();
        this.fruits = new LinkedList<>();
        this.directionEnum = DirectionEnum.RIGHT;
        this.previousDirection = DirectionEnum.RIGHT;

    }


    public void addAI() {
        for (int i = 0; i < 5; i++) {
            this.snakeConsciousnesses.add(new SnakeConsciousnessImpl(new Snake(new Point(i, i))));
        }

    }


    /**
     * Executes one games tick
     */
    public void tick(Long timestamp) {
        if(snake.getSize() > 1)
             previousDirection = filterAgainstPrevious(directionEnum);
        else
            previousDirection = directionEnum;

        Point point = DirectionUtility.directionToVector(previousDirection);

        long speed = Math.max(getSpeed(timestamp),snake.getSize() <= 1?NORMAL_SPEED:TURBO_SPEED);


        if(previousSpeed == TURBO_SPEED && speed == TURBO_SPEED)
        {
            turboPenalty += (timestamp - lastTurbo);
        }

        while(snake.getSize() >1 && turboPenalty > TURBO_CONSUME_PER_SECOND*1000 ) {
            snake.decrementSize(1);
            turboPenalty -= TURBO_CONSUME_PER_SECOND * 1000;
        }

        previousSpeed = speed;

        if(speed == TURBO_SPEED)
        {
            lastTurbo = timestamp;
        }

        if(snake.getNextTick() < timestamp)
        {
            snake.moveTowardsVector(point);
            snake.setNextTick(timestamp+speed);
        }

        if (snakeCrossesBoard(snake)) {
            gameState = GameState.LOST;
            return;
        }

        iCrossedOtherSnake(snake);

        AIsnakesDecide(timestamp);
        AIsnakesCrossEachOtherOrMe();
        anyOfSnakesEats();
        AIanyOfConsiousnessLost();
    }

    private void iCrossedOtherSnake(Snake snake) {
        for (SnakeConsciousness snakeConsciousness :
                snakeConsciousnesses) {
            if (snakeConsciousness.getSnake().colidesWithPoint(snake.getLocation()) == CollisionEnum.TAIL) {
                setGameState(GameState.LOST);
            }
        }

    }


    private void AIsnakesCrossEachOtherOrMe() {
        Iterator<SnakeConsciousness> iterator = snakeConsciousnesses.iterator();


        List<SnakeConsciousness> toRemove = new ArrayList<>();
        while (iterator.hasNext()) {
            SnakeConsciousness snakeConsciousness = iterator.next();
            if (snake.colidesWithPoint(snakeConsciousness.getSnake().getLocation()) == CollisionEnum.TAIL) {
                fruits.addAll(createFruitsBasedOnSnake(snakeConsciousness.getSnake(), FRUIT_INTERVAL));
                toRemove.add(snakeConsciousness);
                continue;
            }

            for (SnakeConsciousness snakeConsciousnessTwo :
                    snakeConsciousnesses) {

                if (snakeConsciousness.getSnake().getLocation().equals(snakeConsciousnessTwo.getSnake().getLocation()))
                    continue;

                if (snakeConsciousnessTwo.getSnake().colidesWithPoint(snakeConsciousness.getSnake().getLocation()) == CollisionEnum.TAIL) {
                    fruits.addAll(createFruitsBasedOnSnake(snakeConsciousness.getSnake(), FRUIT_INTERVAL));
                    toRemove.add(snakeConsciousness);
                }

            }


        }
        snakeConsciousnesses.removeAll(toRemove);

    }




    private void AIsnakesDecide(long timestamp) {

        for (int i = 0; i < snakeConsciousnesses.size(); i++) {
            SnakeConsciousness snakeConsciousness = snakeConsciousnesses.get(i);
            if(snakeConsciousness.getSnake().getNextTick() < timestamp)
            {
                snakeConsciousness.getSnake().moveTowardsVector(DirectionUtility.directionToVector(snakeConsciousness.move(fruits)));
                snakeConsciousness.getSnake().setNextTick(timestamp+NORMAL_SPEED);
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
        return pointCrossesBoard(snake.getLocation(), boardSize);
    }

    /**
     * Point crosses board
     */
    static boolean pointCrossesBoard(Point point, Point size) {
        return point.getX() >= size.getX() || point.getX() < 0 || point.getY() >= size.getY() || point.getY() < 0;
    }




    public void anyOfSnakesEats() {
        Iterator<Fruit> iterator = fruits.iterator();
        while (iterator.hasNext()) {
            Fruit fruit = iterator.next();

            if (snake.colidesWithPoint(fruit.getLocation()) == CollisionEnum.HEAD) {
                snake.incrementSize(fruit.getSize());
                iterator.remove();
            } else {
                for (SnakeConsciousness snakeConsciousness : snakeConsciousnesses) {
                    if (snakeConsciousness.getSnake().colidesWithPoint(fruit.getLocation()) == CollisionEnum.HEAD) {
                        snakeConsciousness.getSnake().incrementSize(fruit.getSize());
                        iterator.remove();
                        break;
                    }
                }

            }

        }
    }


    public void AIanyOfConsiousnessLost() {

        Iterator<SnakeConsciousness> iterator = snakeConsciousnesses.iterator();
        while (iterator.hasNext()) {
            SnakeConsciousness snakeConsciousness = iterator.next();
            if (snakeCrossesBoard(snakeConsciousness.getSnake())) {
                fruits.addAll(createFruitsBasedOnSnake(snakeConsciousness.getSnake(), FRUIT_INTERVAL));
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


    public long getSpeed(long l){
        if(turboRequest + TURBO_TIMEOUT > l){
            return TURBO_SPEED;
            }else
        {
            return NORMAL_SPEED;
        }
    }

    public void setWantSpeed(long l) {
        turboRequest = l;
    }


    public DirectionEnum filterAgainstPrevious(DirectionEnum direction)
    {
        if (DirectionUtility.getOpposite(direction).equals(previousDirection))
        {
            return previousDirection;
        }else {
            previousDirection = (direction);
            return direction;
        }
    }




    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public LinkedList<SnakeConsciousness> getSnakeConsciousnesses() {
        return snakeConsciousnesses;
    }

    public void setSnakeConsciousnesses(LinkedList<SnakeConsciousness> snakeConsciousnesses) {
        this.snakeConsciousnesses = snakeConsciousnesses;
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

    public DirectionEnum getDirectionEnum() {
        return directionEnum;
    }

    public void setDirectionEnum(DirectionEnum directionEnum) {
        this.directionEnum = directionEnum;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }


}
