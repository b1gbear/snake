package com.mono.snake.game.snake_consciousness;

import com.mono.snake.game.entityEnum.MovementType;
import com.mono.snake.game.logic.entity.GameSettings;

/**
 * Turbo data
 */
public class TurboData {

    /**
     * Turbo Data Constructor
     */
    public TurboData()
    {
        this.movementType = MovementType.Normal;
        this.turboPenalty = 0;
        this.turboRequest = -GameSettings.TURBO_TIMEOUT * 10;
        this.lastTurbo = -GameSettings.TURBO_TIMEOUT * 10;
    }

    /**
     * Movement Type
     */
    private MovementType movementType;

    /**
     * Turbo requested timestamp
     */
    private long turboRequest;

    /**
     * Last turbo
     */
    private long lastTurbo;

    /**
     * Turbo penalty
     */
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
