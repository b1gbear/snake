package com.mono.snake.game.snake_consciousness;

import com.mono.snake.game.entity.Fruit;
import com.mono.snake.game.entity.Point;
import com.mono.snake.game.entity.Snake;
import com.mono.snake.game.entityEnum.DirectionEnum;
import com.mono.snake.game.entityEnum.MovementType;
import com.mono.snake.game.logic.entity.BoardState;

import java.util.LinkedList;

public interface SnakeConsciousness {


    Snake getSnake();

    DirectionEnum getPrevious( );
    void setPrevious(DirectionEnum directionEnum);

    MovementType getSpeed(long timestamp);
    DirectionEnum move(BoardState boardState);

    TurboData getTurboData();
    void executePenalty();
    long getNextTick();
    void setNextTick(long tick);



}
