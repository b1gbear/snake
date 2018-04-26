package com.mono.snake.game.entity;

/**
 * Fruit class
 */
public class Fruit {

    /**
     * Fruit constructor
     * @param location location of fruit
     * @param size size of fruit
     */
    public Fruit(Point location, int size) {
        this.location = location;
        this.size = size;
    }

    /**
     * Fruit constructor
     * @param x X coordinate
     * @param y Y coordinate
     * @param size Size
     */
    public Fruit(int x,int y, int size)
    {
        this.location = new Point(x,y);
        this.size = size;
    }

    /**
     * Location of fruit
     */
    private Point location;

    /**
     * Size of fruit
     **/
    private int size;

    /*
     * Getters and Setters
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
}
