package com.mono.snake.game.snake_consciousness;

import com.mono.snake.game.entityEnum.MovementType;

public class TurboData {

    public TurboData()
    {
        this.movementType = MovementType.Normal;
        this.turboPenalty = 0;
        this.turboRequest = -2000000;
        this.lastTurbo = -2000000;
    }

    private MovementType movementType;

    private long turboRequest;

    private long lastTurbo;

    private double turboPenalty;


    public MovementType getMovementType() {
        return movementType;
    }

    public void setMovementType(MovementType movementType) {
        this.movementType = movementType;
    }

    public long getTurboRequest() {
        return turboRequest;
    }

    public void setTurboRequest(long turboRequest) {
        this.turboRequest = turboRequest;
    }

    public long getLastTurbo() {
        return lastTurbo;
    }

    public void setLastTurbo(long lastTurbo) {
        this.lastTurbo = lastTurbo;
    }

    public double getTurboPenalty() {
        return turboPenalty;
    }

    public void setTurboPenalty(double turboPenalty) {
        this.turboPenalty = turboPenalty;
    }
}
