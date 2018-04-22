package com.mono.snake.game.logic.entity;

import com.mono.snake.game.entityEnum.DirectionEnum;

public class KeyboardState {
    private DirectionEnum direction;
    private long turboRequestedAt;
    private long tickSet;


    public KeyboardState() {
        this.direction = DirectionEnum.RIGHT;
        this.turboRequestedAt = -200000;
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
