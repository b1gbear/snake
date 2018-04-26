package com.mono.snake.game.logic.entity;

import com.mono.snake.game.logic.Game;
import org.junit.Assert;
import org.junit.Test;


public class LostStateTest {


    @Test
    public void testConstructor() {
        {
            LostState lostState = new LostState(true, 0);
            Assert.assertEquals(true, lostState.isMultiplayer());
            Assert.assertEquals(0, lostState.getWinnerIs());
        }
        {
            LostState lostState = new LostState(false, 333);
            Assert.assertEquals(false, lostState.isMultiplayer());
            Assert.assertEquals(333, lostState.getWinnerIs());
        }

    }

}
