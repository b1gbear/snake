package com.mono.snake.game.graphics.listener;

import com.mono.snake.game.entityEnum.KeyEnum;
import org.junit.Assert;
import org.junit.Test;

import java.awt.event.KeyEvent;

public class KeyboardKeyListenerTest {

    @Test
    public void parsKeyCodeTest( )
    {
        // right keys
        Assert.assertEquals(KeyEnum.LEFT,KeyboardKeyListener.parsKeyCode(KeyEvent.VK_LEFT));
        Assert.assertEquals(KeyEnum.RIGHT,KeyboardKeyListener.parsKeyCode(KeyEvent.VK_RIGHT));
        Assert.assertEquals(KeyEnum.UP,KeyboardKeyListener.parsKeyCode(KeyEvent.VK_UP));
        Assert.assertEquals(KeyEnum.SPEED_RIGHT,KeyboardKeyListener.parsKeyCode(KeyEvent.VK_M));
        // left keys
        Assert.assertEquals(KeyEnum.W,KeyboardKeyListener.parsKeyCode(KeyEvent.VK_W));
        Assert.assertEquals(KeyEnum.A,KeyboardKeyListener.parsKeyCode(KeyEvent.VK_A));
        Assert.assertEquals(KeyEnum.S,KeyboardKeyListener.parsKeyCode(KeyEvent.VK_S));
        Assert.assertEquals(KeyEnum.D,KeyboardKeyListener.parsKeyCode(KeyEvent.VK_D));
        Assert.assertEquals(KeyEnum.SPEED_LEFT,KeyboardKeyListener.parsKeyCode(KeyEvent.VK_V));
        // Other keys
        Assert.assertEquals(KeyEnum.ESC,KeyboardKeyListener.parsKeyCode(KeyEvent.VK_ESCAPE));

    }

}
