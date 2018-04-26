package com.mono.snake.game.logic.entity;

import com.mono.snake.game.entityEnum.DirectionEnum;

/**
 * Keyboard state
 */
public class KeyboardState {
    /**
     * Direction
     */
    private DirectionEnum direction;
    /**
     * TURBO requested at
     */
    private long turboRequestedAt;
    /**
     * Tick set (used for both arrows and WSAD handling)
     */
    private long tickSet;


    /**
     * KeyboardState Constructor
     */
    public KeyboardState() {
        this.direction = DirectionEnum.RIGHT;
        this.turboRequestedAt = GameSettings.TURBO_TIMEOUT *(-10);
        this.tickSet = 0;
    }


    public DirectionEnum getDirection() {
        return direction;
    }

    public void setDirection(DirectionEnum direction) {
        this.direction = direction;
    }

    public long getTurboRequestedAt() {
        return turboRequestedAt;
    }

    public void setTurboRequestedAt(long turboRequestedAt) {
        this.turboRequestedAt = turboRequestedAt;
    }

    public long getTickSet() {
        return tickSet;
    }

    public void setTickSet(long tickSet) {
        this.tickSet = tickSet;
    }
}
