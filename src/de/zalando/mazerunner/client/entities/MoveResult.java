package de.zalando.mazerunner.client.entities;

/**
 * Inspired by de.zalando.mazerunner.domain.MoveResult 
 * @author Tasnim-Bz
 */



public class MoveResult {
	
    public static final char WALL = '#';
    public static final char EXIT = 'x';
    public static final char WAY = '.';
	
	
    private Coordinate position;

    private Character field;

    public MoveResult() { }

    public MoveResult(final Coordinate position, final Character field) {
        this.position = position;
        this.field = field;
    }



    public Coordinate getPosition() {
        return position;
    }

    public void setPosition(final Coordinate position) {
        this.position = position;
    }

    public Character getField() {
        return field;
    }

    public void setField(final Character field) {
        this.field = field;
    }

	public boolean isGoodMove() {
		if(this.field == WAY || this.field == EXIT){
		return true;
		}else{
			return false;
		}
	}
}
