package com.mono.snake.game;

import com.mono.snake.game.graphics.Window;
import com.mono.snake.game.logic.Game;

public class Main {
    public static void main(String[] args) {
        Window window = new Window();
        Game game = new Game(window);
        game.run();
    }
}
