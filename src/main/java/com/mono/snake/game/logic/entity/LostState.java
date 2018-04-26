package com.mono.snake.game.logic.entity;

/**
 * Lost state
 */
public class LostState {

    /**
     * Lost state constructor
     * @param multiPlayer If game was multiplayer
     * @param winnerIs
     * 0 - no one wins
     * 1 - player 1 wins
     * 2 - player 2 wins
     */
    public LostState(boolean multiPlayer, int winnerIs) {
        this.multiplayer = multiPlayer;
        this.winnerIs = winnerIs;
    }

    /**
     * MultiPlayer
     */
    private boolean multiplayer;
    /**
     * Winner identifier
     * 0 - no one wins
     * 1 - player 1 wins
     * 2 - player 2 wins
     */
    private int winnerIs;

    public boolean isMultiplayer() {
        return multiplayer;
    }

    public int getWinnerIs() {
        return winnerIs;
    }

}
