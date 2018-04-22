package com.mono.snake.game.snake_consciousness;

import com.mono.snake.game.entity.Fruit;
import com.mono.snake.game.entity.Point;
import com.mono.snake.game.entity.Snake;
import com.mono.snake.game.entityEnum.DirectionEnum;
import com.mono.snake.game.entityEnum.MovementType;
import com.mono.snake.game.logic.entity.BoardState;
import com.mono.snake.game.logic.entity.GameSettings;
import com.mono.snake.game.utility.DirectionUtility;

import java.util.LinkedList;

/**
 * Snake consciousness class
 */
public class SnakeConsciousnessPlayer implements  SnakeConsciousness {

    /**
     * Snake
     */
    private Snake snake;

    /**
     * TurboData
     */
    private TurboData turboData;


    private DirectionEnum direction;
    private DirectionEnum previous;

    private long id;

    /**
     * SnakeConsciousness Constructor
     *
     * @param snake
     */
    public SnakeConsciousnessPlayer(Snake snake,long id) {
        this.snake = snake;
        this.turboData = new TurboData();
        this.id = id;
    }


    public Snake getSnake() {
        return snake;
    }

    @Override
    public DirectionEnum getPrevious() {
        return previous;
    }

    @Override
    public void setPrevious(DirectionEnum directionEnum) {
        previous = directionEnum;
    }


    @Override
    public MovementType getSpeed(long timestamp) {
        MovementType movementType = (turboData.getTurboRequest() + GameSettings.TURBO_TIMEOUT > timestamp) ? MovementType.Turbo : MovementType.Normal;

        if(movementType == MovementType.Turbo && turboData.getMovementType() == MovementType.Turbo)
        {
            long delta = timestamp-getTurboData().getLastTurbo() - timestamp;
            turboData.setTurboPenalty(turboData.getTurboPenalty()+delta);
        }

        if(movementType == MovementType.Turbo) {
            turboData.setLastTurbo(timestamp);
        }

        return movementType;
    }


    @Override
    public DirectionEnum move(BoardState boardState) {

        if(direction == DirectionUtility.getOpposite(getPrevious())) {
            previous = getPrevious(); // reduntant
            return getPrevious();
        }
        previous = direction;
        return direction;
    }

    @Override
    public TurboData getTurboData() {
        return turboData;
    }

    @Override
    public void executePenalty() {
        while(turboData.getTurboPenalty() > GameSettings.TURBO_CONSUME_PER_SECOND*1000)
        {
            getSnake().decrementSize(1);
            turboData.setTurboPenalty(turboData.getTurboPenalty()-GameSettings.TURBO_CONSUME_PER_SECOND*1000);
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

    public DirectionEnum getDirection() {
        return direction;
    }

    public void setDirection(DirectionEnum direction) {
        this.direction = direction;
    }

    public long getId() {
        return id;
    }

}
