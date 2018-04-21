package com.mono.snake.game.entity;

import java.util.Objects;

/**
 * Point class
 */
public class Point {

    /**
     * Point constructor
     * @param x first coordinate
     * @param y second coordinate
     */
    public Point(int x,int y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point point) {
        this.x = point.getX();
        this.y = point.getY();
    }


    /**
     * Default P(0,0) constructor
     */
    public Point() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * X variable
     */
    private int x;
    /**
     * Y variable
     */
    private int y;

    /**
     * Add points
     * @param second point to add
     * @return sum
     */
    public Point add(Point second){
        return new Point(this.x +second.getX(),this.y+second.getY());
    }

    public double distance(Point another)
    {
        double xd = this.x - another.getX();
        double yd = this.y - another.getY();
        return Math.sqrt(xd*xd+yd*yd);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x &&
                y == point.y;
    }

    @Override
    public int hashCode() {

        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }


    /*
     * Getters and setters
     */

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
