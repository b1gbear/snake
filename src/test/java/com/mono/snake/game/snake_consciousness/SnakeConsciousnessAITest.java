package com.mono.snake.game.snake_consciousness;

import com.mono.snake.game.entity.Fruit;
import com.mono.snake.game.entity.Point;
import com.mono.snake.game.entity.Snake;
import com.mono.snake.game.entityEnum.DirectionEnum;
import com.mono.snake.game.entityEnum.GameState;
import com.mono.snake.game.logic.entity.BoardState;
import org.junit.Assert;
import org.junit.Test;

public class SnakeConsciousnessAITest {

    @Test
    public void snakeConciousnessConstructorTest(){
        SnakeConsciousnessAI snakeConsciousnessAI = new SnakeConsciousnessAI(new Snake(new Point(3,4)));
        Assert.assertEquals(new Point(3,4),snakeConsciousnessAI.getSnake().getLocation());
        Assert.assertEquals(1,snakeConsciousnessAI.getSnake().getSize());
        Assert.assertNotEquals(null,snakeConsciousnessAI.getTurboData());
        Assert.assertNotEquals(null,snakeConsciousnessAI.getSnake());
    }

    @Test
    public void moveTest(){
        SnakeConsciousnessAI snakeConsciousnessAI = new SnakeConsciousnessAI(new Snake(new Point(3,4)));
        Assert.assertEquals(DirectionEnum.RIGHT,snakeConsciousnessAI.move(mockBoardState(5,4,10)));
        Assert.assertEquals(DirectionEnum.LEFT,snakeConsciousnessAI.move(mockBoardState(1,4,10)));
        Assert.assertEquals(DirectionEnum.UP,snakeConsciousnessAI.move(mockBoardState(3,2,10)));
        Assert.assertEquals(DirectionEnum.DOWN,snakeConsciousnessAI.move(mockBoardState(3,6,10)));
    }

    public BoardState mockBoardState(int x, int y, int size){
        BoardState boardState = new BoardState(new Point(10,10), GameState.GAME);
        boardState.getFruits().add(new Fruit(new Point(x,y),size));
        return boardState;
    }
}
