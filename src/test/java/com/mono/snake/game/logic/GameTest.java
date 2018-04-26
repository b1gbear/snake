package com.mono.snake.game.logic;

import com.mono.snake.game.entity.Point;
import com.mono.snake.game.entityEnum.DirectionEnum;
import com.mono.snake.game.utility.DirectionUtility;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;


public class GameTest {

    private static final int MAX_TEST_ITERATION = 4000;

    @Test
    public void testRandom() {
        int max = 100;
        int min = 0;

        for (int i = 0; i < MAX_TEST_ITERATION; i++) {
            int rand = Game.randomWithRange(min,max);
            Assert.assertTrue(rand >= min);
            Assert.assertTrue(rand <= max);
        }

    }

}
