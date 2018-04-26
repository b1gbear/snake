package com.mono.snake.game.entity;

import org.junit.Assert;
import org.junit.Test;

public class FruitTest {

    @Test
    public void parameterConstructorTest() {
        Fruit fruit = new Fruit(new Point(5, 6), 3);
        Assert.assertEquals(5,fruit.getLocation().getX());
        Assert.assertEquals(6,fruit.getLocation().getY());
        Assert.assertEquals(3,fruit.getSize());
    }

    @Test
    public void simpleConstructorTest() {
        Fruit fruit = new Fruit(5, 6, 3);
        Assert.assertEquals(5,fruit.getLocation().getX());
        Assert.assertEquals(6,fruit.getLocation().getY());
        Assert.assertEquals(3,fruit.getSize());
    }

    @Test
    public void getterSetterTest() {
        Fruit fruit = new Fruit(new Point(1, 2), 3);

        fruit.setLocation(new Point(11,22));
        Assert.assertEquals(new Point(11,22),fruit.getLocation());

        fruit.setSize(33);
        Assert.assertEquals(33, fruit.getSize());
    }





}
