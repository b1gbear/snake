package com.mono.snake.game.graphics.listener;


import com.mono.snake.game.entityEnum.KeyEnum;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Queue;
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
        parsKeyCode(e.getKeyCode());

    }

    public void parsKeyCode(int key)
    {
        if (key == KeyEvent.VK_LEFT) {
            keyEnums.add(KeyEnum.LEFT);
        } else if (key == KeyEvent.VK_RIGHT) {
            keyEnums.add(KeyEnum.RIGHT);
        } else if (key == KeyEvent.VK_UP) {
            keyEnums.add(KeyEnum.UP);
        } else if (key == KeyEvent.VK_DOWN) {
            keyEnums.add(KeyEnum.DOWN);
        } else if (key == KeyEvent.VK_ENTER) {
            keyEnums.add(KeyEnum.ENTER);
        } else if (key == KeyEvent.VK_M) {
            keyEnums.add(KeyEnum.SPEED_RIGT);
        } else if (key == KeyEvent.VK_W) {
            keyEnums.add(KeyEnum.UP);
        } else if (key == KeyEvent.VK_S) {
            keyEnums.add(KeyEnum.DOWN);
        } else if (key == KeyEvent.VK_A) {
            keyEnums.add(KeyEnum.LEFT);
        } else if (key == KeyEvent.VK_D) {
            keyEnums.add(KeyEnum.RIGHT);
        } else if (key == KeyEvent.VK_V) {
            keyEnums.add(KeyEnum.SPEED_LEFT);
        }


        else if (key == KeyEvent.VK_ESCAPE) {
            keyEnums.add(KeyEnum.ESC);
        }



    }


}


