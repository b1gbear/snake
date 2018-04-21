package com.mono.snake.game.snake_consciousness;

import com.mono.snake.game.entity.Fruit;
import com.mono.snake.game.entity.Point;
import com.mono.snake.game.entity.Snake;
import com.mono.snake.game.entityEnum.DirectionEnum;

import java.util.LinkedList;

public interface SnakeConsciousness {

    Snake getSnake();
    DirectionEnum move(LinkedList<Fruit> fruits);

}
