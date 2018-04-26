package com.mono.snake.game.entity;

import org.junit.Assert;
import org.junit.Test;

public class PointTest {

    @Test
    public void defaultConstructorTest() {
        Point point = new Point();
        Assert.assertEquals(0, point.getX());
        Assert.assertEquals(0, point.getY());
    }

    @Test
    public void xyConstructorTest() {
        Point point = new Point(11, 22);
        Assert.assertEquals(11, point.getX());
        Assert.assertEquals(22, point.getY());
    }

    @Test
    public void pointConstructorTest() {
        Point point = new Point(new Point(11, 22));
        Assert.assertEquals(11, point.getX());
        Assert.assertEquals(22, point.getY());
    }


    @Test
    public void getterSetterTest() {
        Point point = new Point();
        point.setX(11);
        Assert.assertEquals(11, point.getX());
        point.setY(22);
        Assert.assertEquals(22, point.getY());

    }

    @Test
    public void equalsTest() {

        {
            Point one = new Point();
            Point two = new Point();
            Assert.assertEquals(one, two);
        }

        {
            Point one = new Point(1, 3);
            Point two = new Point(1, 3);
            Assert.assertEquals(one, two);
        }


        {
            Point one = new Point(1, 0);
            Point two = new Point();
            Assert.assertNotEquals(one, two);
        }

        {
            Point one = new Point(1, 3);
            Point two = new Point(1, 4);
            Assert.assertNotEquals(one, two);
        }
    }


    @Test
    public void hashTest() {

        {
            Point one = new Point();
            Point two = new Point();
            Assert.assertEquals(one.hashCode(), two.hashCode());
        }

        {
            Point one = new Point(1, 3);
            Point two = new Point(1, 3);
            Assert.assertEquals(one.hashCode(), two.hashCode());
        }


        {
            Point one = new Point(1, 0);
            Point two = new Point();
            Assert.assertNotEquals(one.hashCode(), two.hashCode());
        }

        {
            Point one = new Point(1, 3);
            Point two = new Point(1, 4);
            Assert.assertNotEquals(one.hashCode(), two.hashCode());
        }
    }


    @Test
    public void distanceTest() {
        {
            Point point = new Point();
            Point another = new Point(4, 3);
            Assert.assertEquals(5, point.distance(another), 0.01);
        }
        {
            Point point = new Point();
            Point another = new Point(0, 0);
            Assert.assertEquals(0, point.distance(another), 0.01);
        }
        {
            Point point = new Point(4, 3);
            Point another = new Point(0, 0);
            Assert.assertEquals(5, point.distance(another), 0.01);
        }
    }

    @Test
    public void addTest() {
        Point first = new Point(1, 3);
        Point second = new Point(4, 6);
        Point expected = new Point(5, 9);

        Assert.assertEquals(expected, first.add(second));
        Assert.assertEquals(expected, second.add(first));

    }


    @Test
    public void toStringTest(){
        Point point = new Point(1,3);

        Assert.assertTrue(point.toString().length() > 7);
        Assert.assertTrue(point.toString().contains("Point{"));
        Assert.assertTrue(point.toString().contains("}"));


    }
}
