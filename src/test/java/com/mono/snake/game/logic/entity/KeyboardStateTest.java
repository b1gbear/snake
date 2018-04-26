package com.mono.snake.game.logic.entity;

import com.mono.snake.game.entityEnum.DirectionEnum;
import com.mono.snake.game.logic.Game;
import org.junit.Assert;
import org.junit.Test;


public class KeyboardStateTest {

    @Test
    public void constructorTest() {
        KeyboardState keyboardState = new KeyboardState();
        Assert.assertEquals(0,keyboardState.getTurboRequestedAt());
        Assert.assertEquals(DirectionEnum.RIGHT,keyboardState.getDirection());
        Assert.assertEquals(0,keyboardState.getTickSet());

    }

}
