package com.mono.snake.game.logic.entity;

/**
 * Board
 */
public class LostState {

    public LostState(boolean multiplayer, int winnerIs) {
        this.multiplayer = multiplayer;
        this.winnerIs = winnerIs;
    }

    private boolean multiplayer;
    private int winnerIs;


    public boolean isMultiplayer() {
        return multiplayer;
    }



    public int getWinnerIs() {
        return winnerIs;
    }

}
