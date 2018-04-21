package com.mono.snake.game.utility;

import com.mono.snake.game.entity.Point;
import com.mono.snake.game.entityEnum.DirectionEnum;

public class DirectionUtility {
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
}
