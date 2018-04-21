package com.mono.snake.game.entity;

import com.mono.snake.game.entityEnum.CollisionEnum;
import com.mono.snake.game.entityEnum.DirectionEnum;
import com.mono.snake.game.logic.Board;
import org.junit.Assert;
import org.junit.Test;

public class SnakeTest {

    @Test
    public void constructorTest(){
        Snake snake = new Snake(new Point(10,11));
        Assert.assertEquals(10,snake.getLocation().getX());
        Assert.assertEquals(11,snake.getLocation().getY());
        Assert.assertTrue(snake.getTail().isEmpty());
        Assert.assertEquals(1,snake.getSize());
        Assert.assertEquals(0,snake.getNextTick());
    }


    @Test
    public void getterSetterTest(){
        Snake snake = new Snake(new Point(10,11));

        snake.setLocation(new Point(11,12));
        Assert.assertEquals(new Point(11,12),snake.getLocation());

        snake.setSize(33);
        Assert.assertEquals(33,snake.getSize());

        snake.getTail().addFirst(new Point(1,1));
        Assert.assertEquals(1,snake.getTail().size());

        snake.setNextTick(333);
        Assert.assertEquals(1,snake.getNextTick());
    }



    @Test
    public void moveTowardsVectorTest(){
            Snake snake = new Snake(new Point(10,10));
            snake.getTail().addLast(new Point(9,10));
            snake.getTail().addLast(new Point(8,10));
            snake.getTail().addLast(new Point(7,10));
            snake.setSize(4);
            snake.moveTowardsVector(Board.directionToVector(DirectionEnum.UP));

            Assert.assertEquals(new Point(10,9),snake.getLocation());
            Assert.assertEquals(new Point(10,10),snake.getTail().getFirst());


    }

    @Test
    public void colidesWithPointTest(){
        Snake snake = new Snake(new Point(10,10));
        snake.getTail().addLast(new Point(9,10));
        snake.getTail().addLast(new Point(8,10));
        snake.getTail().addLast(new Point(7,10));
        snake.setSize(4);

        Assert.assertEquals(CollisionEnum.HEAD,snake.colidesWithPoint(new Point(10,10)));
        Assert.assertEquals(CollisionEnum.TAIL,snake.colidesWithPoint(new Point(8,10)));
        Assert.assertEquals(CollisionEnum.NONE,snake.colidesWithPoint(new Point(1,1)));

    }

    @Test
    public void incrementSizeTest(){
        Snake snake = new Snake(new Point(10,10));
        Assert.assertEquals(1,snake.getSize());
        snake.incrementSize(4);
        Assert.assertEquals(5,snake.getSize());

    }

}
