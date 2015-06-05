package de.zalando.mazerunner.client.entities;

public class MazeRunner {

	private Coordinate currentPosition;

	public Coordinate getcurrentPosition() {
		return currentPosition;
	}

	public void setcurrentPosition(final Coordinate currentPosition) {
		this.currentPosition = currentPosition;
	}
	
	public Coordinate move(Direction direction){
		return this.getcurrentPosition().move(direction);
	}
	
	public Coordinate move(Coordinate coordinates){
		 this.setcurrentPosition(coordinates);
		 return this.currentPosition;
	}

}
