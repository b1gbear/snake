package com.mono.snake.game.graphics.listener;


import com.mono.snake.game.entityEnum.KeyEnum;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * KeyAdapter that gets current pressed key
 */
public class KeyboardKeyListener extends KeyAdapter {

    /**
     * Queue of key events
     */
    private final ConcurrentLinkedDeque<KeyEnum> keyEnums;

    /**
     * Default constructor
     */
    public KeyboardKeyListener(ConcurrentLinkedDeque<KeyEnum>  keyEnums) {
        this.keyEnums = keyEnums;
    }

    /**
     * Get direction queue
     * @return Queue
     */
    public ConcurrentLinkedDeque<KeyEnum> getKeyEnums() {
        return keyEnums;
    }

    /**
     * Our keys handling
     */
    @Override
    public void keyPressed(KeyEvent e) {
        KeyEnum keyEnum = parsKeyCode(e.getKeyCode());
        if(keyEnum != KeyEnum.NONE){
            keyEnums.addLast(keyEnum);
        }


    }

    public static  KeyEnum parsKeyCode(int key)
    {
        if (key == KeyEvent.VK_LEFT) {
            return KeyEnum.LEFT;
        } else if (key == KeyEvent.VK_RIGHT) {
            return KeyEnum.RIGHT;
        } else if (key == KeyEvent.VK_UP) {
            return KeyEnum.UP;
        } else if (key == KeyEvent.VK_DOWN) {
            return KeyEnum.DOWN;
        } else if (key == KeyEvent.VK_ENTER) {
            return KeyEnum.ENTER;
        } else if (key == KeyEvent.VK_M) {
            return KeyEnum.SPEED_RIGHT;
        } else if (key == KeyEvent.VK_W) {
            return KeyEnum.W;
        } else if (key == KeyEvent.VK_S) {
            return KeyEnum.S;
        } else if (key == KeyEvent.VK_A) {
            return KeyEnum.A;
        } else if (key == KeyEvent.VK_D) {
            return KeyEnum.D;
        } else if (key == KeyEvent.VK_V) {
            return KeyEnum.SPEED_LEFT;
        } else if (key == KeyEvent.VK_ESCAPE) {
           return KeyEnum.ESC;
        }

        return KeyEnum.NONE;
    }


}


