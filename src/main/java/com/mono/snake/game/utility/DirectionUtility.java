package com.mono.snake.game.utility;

import com.mono.snake.game.entity.Point;
import com.mono.snake.game.entityEnum.DirectionEnum;
import com.mono.snake.game.entityEnum.KeyEnum;

/**
 * Direction utility, holds some static methods related to directions
 */
public class DirectionUtility {
    /**
     * Get vector from direction
     * @param directionEnum Direction
     * @return Vector Point
     */
    public static Point directionToVector(DirectionEnum directionEnum) {

        if (directionEnum == DirectionEnum.RIGHT) {
            return new Point(1, 0);
        } else if (directionEnum == DirectionEnum.LEFT) {
            return new Point(-1, 0);
        } else if (directionEnum == DirectionEnum.UP) {
            return new Point(0, -1);
        } else if (directionEnum == DirectionEnum.DOWN) {
            return new Point(0, 1);
        }

        return new Point();
    }

    /**
     * Get opposite direction
     * @param directionEnum Direction
     * @return Opposite direction
     */
    public static DirectionEnum getOpposite(DirectionEnum directionEnum)
    {
        if(directionEnum == DirectionEnum.RIGHT)
        {
            return DirectionEnum.LEFT;
        }else if(directionEnum == DirectionEnum.LEFT)
        {
            return DirectionEnum.RIGHT;
        }else if(directionEnum == DirectionEnum.UP)
        {
            return DirectionEnum.DOWN;
        }else if(directionEnum == DirectionEnum.DOWN)
        {
            return DirectionEnum.UP;
        }
        return DirectionEnum.NONE;

    }

    /**
     * Key to direction
     * @return Direction
     */
    public static DirectionEnum keyToDirection(KeyEnum keyEnum) {
        if(keyEnum == KeyEnum.DOWN) {
            return DirectionEnum.DOWN;
        }else if (keyEnum == KeyEnum.UP){
            return DirectionEnum.UP;
        }else if (keyEnum == KeyEnum.RIGHT) {
            return DirectionEnum.RIGHT;
        }else if (keyEnum == KeyEnum.LEFT) {
            return DirectionEnum.LEFT;
        }
        return DirectionEnum.NONE;
    }

}
