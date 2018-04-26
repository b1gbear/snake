package com.mono.snake.game.snake_consciousness;

import com.mono.snake.game.entity.Fruit;
import com.mono.snake.game.entity.Point;
import com.mono.snake.game.entity.Snake;
import com.mono.snake.game.entityEnum.DirectionEnum;
import com.mono.snake.game.entityEnum.MovementType;
import com.mono.snake.game.logic.entity.BoardState;

import java.util.LinkedList;

/**
 * Snake consciousness
 */
public interface SnakeConsciousness {


    /**
     * Get snake reference
     * @return Snake
     */
    Snake getSnake();

    /**
     * Get previous direction
     * @return Direction
     */
    DirectionEnum getPrevious( );

    /**
     * Set previous direction
     * @param directionEnum Direction
     */
    void setPrevious(DirectionEnum directionEnum);

    /**
     * Get speed
     * @param timestamp Timestamp
     * @return MovementType
     */
    MovementType getSpeed(long timestamp);

    /**
     * Move
     * @param boardState boardState
     * @return Direction
     */
    DirectionEnum move(BoardState boardState);

    /**
     * Get TurboData
     * @return TurboData
     */
    TurboData getTurboData();

    /**
     * Execute penalty
     */
    void executePenalty();

    /**
     * Get last tick
     * @return last tick
     */
    long getNextTick();

    /**
     * Set next tick
     * @param tick timestamp
     */
    void setNextTick(long tick);



}
