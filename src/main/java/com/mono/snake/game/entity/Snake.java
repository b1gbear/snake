package com.mono.snake.game.entity;

import com.mono.snake.game.entityEnum.CollisionEnum;

import java.util.LinkedList;

/**
 * Snake class
 */
public class Snake {

    /**
     * Current location of snake
     */
    private Point location;

    /**
     * Size of snake
     */
    private int size;

    /**
     * Tail of snake
     */
    final private LinkedList<Point> tail;

    /**
     * Next tick
     */
    private long nextTick;

    public Snake(Point point) {
        this.location = point;
        this.tail = new LinkedList<>();
        this.size = 1;
        this.nextTick = 0;
    }

    /**
     * Move towards location
     *
     * @param vector Vector of move
     */
    public void moveTowardsVector(Point vector) {
        tail.addFirst(location);
        location = location.add(vector);
        while (tail.size() > size - 1) {
            tail.removeLast();
        }
    }


    public CollisionEnum colidesWithPoint(Point point) {

        if (location.equals(point))
            return CollisionEnum.HEAD;

        for (Point tailPoint : tail) {
            if (tailPoint.equals(point))
                return CollisionEnum.TAIL;
        }
        return CollisionEnum.NONE;
    }

    public void incrementSize(int delta)
    {
        this.size += delta;
    }

    public void decrementSize(int delta)
    {
        this.size -= delta;
    }

    /*
     * Getters and setters
     */

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public LinkedList<Point> getTail() {
        return tail;
    }

    public long getNextTick() {
        return nextTick;
    }

    public void setNextTick(long nextTick) {
        this.nextTick = nextTick;
    }
}
