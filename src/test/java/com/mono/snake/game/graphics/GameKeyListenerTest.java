package com.mono.snake.game.graphics;

import com.mono.snake.game.entityEnum.KeyEnum;
import com.mono.snake.game.graphics.listener.KeyboardKeyListener;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.ConcurrentLinkedDeque;

@RunWith(MockitoJUnitRunner.class)
public class GameKeyListenerTest {


    @Mock
    Component component;

    @Test
    public void cunstructionTest(){
        ConcurrentLinkedDeque<KeyEnum> keyEnums = new ConcurrentLinkedDeque<>();
        KeyboardKeyListener gameKeyListener = new KeyboardKeyListener(keyEnums);


    }

    @Test
    public void functionTest(){
        ConcurrentLinkedDeque<KeyEnum> keyEnums = new ConcurrentLinkedDeque<>();
        KeyboardKeyListener gameKeyListener = new KeyboardKeyListener(keyEnums);

        gameKeyListener.parsKeyCode(KeyEvent.VK_LEFT);
        Assert.assertEquals(KeyEnum.LEFT, gameKeyListener.getKeyEnums().getFirst());

    }

}
