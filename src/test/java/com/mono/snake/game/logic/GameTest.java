package com.mono.snake.game.logic;


import org.junit.Assert;
import org.junit.Test;


public class GameTest {

    private static final int MAX_TEST_ITERATION = 700;

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
