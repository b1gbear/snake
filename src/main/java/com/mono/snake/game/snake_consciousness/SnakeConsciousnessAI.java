package com.mono.snake.game.snake_consciousness;

import com.mono.snake.game.entity.Fruit;
import com.mono.snake.game.entity.Point;
import com.mono.snake.game.entity.Snake;
import com.mono.snake.game.entityEnum.DirectionEnum;
import com.mono.snake.game.entityEnum.MovementType;
import com.mono.snake.game.logic.entity.BoardState;
import com.mono.snake.game.logic.entity.GameSettings;

import java.util.LinkedList;

/**
 * Snake consciousness class
 */
public class SnakeConsciousnessAI implements SnakeConsciousness {
    /**
     * Snake
     */
    private Snake snake;

    /**
     * TurboData
     */
    private TurboData turboData;

    /**
     * Direction Enum
     */
    private DirectionEnum previous;

    /**
     * SnakeConsciousness Constructor
     *
     * @param snake
     */
    public SnakeConsciousnessAI(Snake snake) {
        this.snake = snake;
        this.turboData = new TurboData();
    }


    public Snake getSnake() {
        return snake;
    }

    @Override
    public DirectionEnum getPrevious() {
        return null;
    }

    @Override
    public void setPrevious(DirectionEnum directionEnum) {

    }

    @Override
    public MovementType getSpeed(long timestamp) {
        return (turboData.getTurboRequest() + GameSettings.TURBO_TIMEOUT > timestamp) ? MovementType.Turbo : MovementType.Normal;
    }

    @Override
    public DirectionEnum move(BoardState boardState) {
        LinkedList<Fruit> fruits = boardState.getFruits();
        if (!fruits.isEmpty()) {
            Fruit min = fruits.getFirst();

            for (Fruit fruit : fruits) {
                if (fruit.getLocation().distance(snake.getLocation()) < min.getLocation().distance(snake.getLocation())) {
                    min = fruit;
                }
            }
            Point snakeLocation = snake.getLocation();
            Point eatLocation = min.getLocation();

            if (snakeLocation.getX() > eatLocation.getX()) {
                previous = DirectionEnum.LEFT;
                return DirectionEnum.LEFT;
            } else if (snakeLocation.getX() < eatLocation.getX()) {
                previous = DirectionEnum.RIGHT;
                return DirectionEnum.RIGHT;
            }
            if (snakeLocation.getY() > eatLocation.getY()) {
                previous = DirectionEnum.UP;
                return DirectionEnum.UP;
            } else if (snakeLocation.getY() < eatLocation.getY()) {
                previous = DirectionEnum.DOWN;
                return DirectionEnum.DOWN;
            }
        }
        previous = DirectionEnum.RIGHT;

        return DirectionEnum.RIGHT;


    }

    @Override
    public TurboData getTurboData() {
        return turboData;
    }

    @Override
    public void executePenalty() {
        while (turboData.getTurboPenalty() > GameSettings.TURBO_CONSUME_PER_SECOND * 1000) {
            getSnake().decrementSize(1);
            turboData.setTurboPenalty(turboData.getTurboPenalty() - GameSettings.TURBO_CONSUME_PER_SECOND * 1000);
        }
    }

    @Override
    public long getNextTick() {
        return snake.getNextTick();
    }

    @Override
    public void setNextTick(long tick) {
        snake.setNextTick(tick);
    }

}
