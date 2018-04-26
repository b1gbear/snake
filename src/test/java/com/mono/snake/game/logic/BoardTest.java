package com.mono.snake.game.logic;

import com.mono.snake.game.entity.Point;
import com.mono.snake.game.entityEnum.DirectionEnum;
import com.mono.snake.game.utility.DirectionUtility;
import org.junit.Assert;
import org.junit.Test;

public class BoardTest {
    @Test
    public void testKetToVector() {
        Assert.assertEquals(new Point(1, 0), DirectionUtility.directionToVector(DirectionEnum.RIGHT));
        Assert.assertEquals(new Point(-1, 0), DirectionUtility.directionToVector(DirectionEnum.LEFT));
        Assert.assertEquals(new Point(0, -1), DirectionUtility.directionToVector(DirectionEnum.UP));
        Assert.assertEquals(new Point(0, 1), DirectionUtility.directionToVector(DirectionEnum.DOWN));

    }


    @Test
    public void testPointCrossesBoardStatic() {

        Point size = new Point(10, 10);


        Assert.assertTrue(Board.pointCrossesBoard(new Point(9, 10), size));
        Assert.assertTrue(Board.pointCrossesBoard(new Point(-1, 2), size));
        Assert.assertTrue(Board.pointCrossesBoard(new Point(10, 2), size));

        Assert.assertFalse(Board.pointCrossesBoard(new Point(9, 9), size));
        Assert.assertFalse(Board.pointCrossesBoard(new Point(0, 0), size));
        Assert.assertFalse(Board.pointCrossesBoard(new Point(0, 9), size));
        Assert.assertFalse(Board.pointCrossesBoard(new Point(9, 0), size));


    }
}
