package de.zalando.mazerunner.client.entities;

/**
 * Inspired by de.zalando.mazerunner.domain.Move 
 * @author Tasnim-Bz
 */

public class Move {
	
	    private Coordinate from;

	    private Direction direction;

	    public Move(Coordinate from, final Direction direction) {
	    	this.from = from;
	    	this.direction = direction;
	    }

	  
	    public void setFrom(final Coordinate from) {
	        this.from = from;
	    }

	    public Coordinate getFrom() {
	        return from;
	    }

	    public void setDirection(final Direction direction) {
	        this.direction = direction;
	    }

	    public Direction getDirection() {
	        return direction;
	    }
}
