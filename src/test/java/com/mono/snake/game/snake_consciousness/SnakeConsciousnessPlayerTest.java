package com.mono.snake.game.snake_consciousness;

import com.mono.snake.game.entity.Point;
import com.mono.snake.game.entity.Snake;
import org.junit.Assert;
import org.junit.Test;

public class SnakeConsciousnessPlayerTest {

    @Test
    public void snakeConciousnessPlayerConstructorTest(){
        SnakeConsciousnessPlayer snakeConsciousnessAI = new SnakeConsciousnessPlayer(new Snake(new Point(3,4)),1);
        Assert.assertEquals(new Point(3,4),snakeConsciousnessAI.getSnake().getLocation());
        Assert.assertEquals(1,snakeConsciousnessAI.getSnake().getSize());
        Assert.assertNotEquals(null,snakeConsciousnessAI.getTurboData());
        Assert.assertNotEquals(null,snakeConsciousnessAI.getSnake());
        Assert.assertEquals(1,snakeConsciousnessAI.getId());
    }



    @Test
    public void getterSetterTest(){


    }




}
