package de.zalando.mazerunner.client.entities;

/**
 * Inspired by de.zalando.mazerunner.domain.Coordinate
 * @author Tasnim-Bz
 */

public class Coordinate {
    private int x;

    private int y;

    public Coordinate() { }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate move(Direction direction) {
        int x = this.x;
        int y = this.y;

        switch(direction) {
            case WEST:
                x--;
                break;
            case EAST:
                x++;
                break;
            case NORTH:
                y--;
                break;
            case SOUTH:
                y++;
                break;
        }

        return new Coordinate(x, y);
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinate that = (Coordinate) o;

        if (x != that.x) return false;
        if (y != that.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "("+x+","+y+")";
    }
   
}
