package com.mono.snake.game.snake_consciousness;

import com.mono.snake.game.entity.Fruit;
import com.mono.snake.game.entity.Point;
import com.mono.snake.game.entity.Snake;
import com.mono.snake.game.entityEnum.DirectionEnum;

import java.util.LinkedList;

/**
 * Snake consciousness class
 */
public class SnakeConsciousnessImpl implements SnakeConsciousness {
    /**
     * Snake
     */
    private Snake snake;


    /**
     * SnakeConsciousness Constructor
     *
     * @param snake
     */
    public SnakeConsciousnessImpl(Snake snake) {
        this.snake = snake;
    }


    public Snake getSnake() {
        return snake;
    }


    @Override
    public DirectionEnum move(LinkedList<Fruit> fruits) {
        if (!fruits.isEmpty()) {
            Fruit min = fruits.getFirst();

            for (Fruit fruit : fruits) {
                if (fruit.getLocation().distance(snake.getLocation()) < min.getLocation().distance(snake.getLocation())) {
                    min = fruit;
                }
            }

            Point snakeLocation = snake.getLocation();
            Point eatLocation = min.getLocation();


            if(snakeLocation.getX() > eatLocation.getX()) {
                return DirectionEnum.LEFT;
            }else if (snakeLocation.getX() < eatLocation.getX()) {
                return DirectionEnum.RIGHT;
            }


            if(snakeLocation.getY() > eatLocation.getY()) {
                return DirectionEnum.UP;

            }else if (snakeLocation.getY() < eatLocation.getY()) {
                return DirectionEnum.DOWN;

            }


        }
        return DirectionEnum.RIGHT;


    }
}
