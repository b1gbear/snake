package com.mono.snake.game.utility;

import com.mono.snake.game.entity.Point;
import com.mono.snake.game.entityEnum.DirectionEnum;
import org.junit.Assert;
import org.junit.Test;

public class DirectionUtilityTest {

    @Test
    public void testDirectionToVector() {
        Assert.assertEquals(new Point(1, 0), DirectionUtility.directionToVector(DirectionEnum.RIGHT));
        Assert.assertEquals(new Point(-1, 0), DirectionUtility.directionToVector(DirectionEnum.LEFT));
        Assert.assertEquals(new Point(0, -1), DirectionUtility.directionToVector(DirectionEnum.UP));
        Assert.assertEquals(new Point(0, 1), DirectionUtility.directionToVector(DirectionEnum.DOWN));
    }

    @Test
    public void getOppositeTest() {
        Assert.assertEquals(DirectionEnum.RIGHT,DirectionUtility.getOpposite(DirectionEnum.LEFT));
        Assert.assertEquals(DirectionEnum.LEFT,DirectionUtility.getOpposite(DirectionEnum.RIGHT));
        Assert.assertEquals(DirectionEnum.UP,DirectionUtility.getOpposite(DirectionEnum.DOWN));
        Assert.assertEquals(DirectionEnum.DOWN,DirectionUtility.getOpposite(DirectionEnum.UP));
        Assert.assertEquals(DirectionEnum.NONE,DirectionUtility.getOpposite(DirectionEnum.NONE));
    }


//    @Test
//    public void keyToDirectionTest() {
//        // UNUSED ANYMORE
//        Assert.assertTrue(true);
//    }

}
