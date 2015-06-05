package de.zalando.mazerunner.client.handler;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.zalando.mazerunner.client.entities.Coordinate;
import de.zalando.mazerunner.client.entities.Direction;
import de.zalando.mazerunner.client.entities.MazeRunner;
import de.zalando.mazerunner.client.entities.Move;
import de.zalando.mazerunner.client.entities.MoveResult;
import de.zalando.mazerunner.client.execption.MazeNotFoundException;
import de.zalando.mazerunner.client.execption.MoveNotValidException;
import de.zalando.mazerunner.client.handler.MazeRunnerClient;


public class MazeRunnerHandler {
	
	//private static final Logger LOG = Logger.getLogger(MazeRunnerHandler.class);
	
	public MazeRunner mazeRunner;
	
	public String MazeCode;
	
	
	
	/**
	 * @return the mazeRunner
	 */
	public MazeRunner getMazeRunner() {
		return mazeRunner;
	}

	/**
	 * @return the mazeCode
	 */
	public String getMazeCode() {
		return MazeCode;
	}

	/**
	 * @param mazeCode the mazeCode to set
	 */
	public void setMazeCode(String mazeCode) {
		MazeCode = mazeCode;
	}

	

	/**
	 * @param mazeRunner
	 */
	public MazeRunnerHandler(MazeRunner mazeRunner) {
		this.mazeRunner = mazeRunner;
	}

	/**
	 * @param mazeRunner the mazeRunner to set
	 */
	public void setMazeRunner(MazeRunner mazeRunner) {
		this.mazeRunner = mazeRunner;
	}

	/**
	 * Get the start position of the given Maze
	 * @param mazeCode
	 * @return
	 * @throws MazeNotFoundException
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public Coordinate getStartPosition(String mazeCode) throws MazeNotFoundException, ClientProtocolException, IOException{
		
		
		try {
			Coordinate startMove= MazeRunnerClient.getStartPosition(mazeCode);
			return startMove;
		} catch (MazeNotFoundException e) {
			throw e;
		}
		
	}
	

	

	/**
	 * Initialise the Maze
	 * @throws IOException 
	 * @throws MazeNotFoundException 
	 * @throws ClientProtocolException 
	 */
	public void placeRunnerOnStartPosition() throws ClientProtocolException, MazeNotFoundException, IOException {
		
		Coordinate startCord = MazeRunnerClient.getStartPosition(this.getMazeCode());
		this.getMazeRunner().setcurrentPosition(startCord);
		System.out.println("Maze runner start location "+startCord.toString());
		
	}

	/**
	 * @throws IOException 
	 * @throws MoveNotValidException 
	 * @throws  
	 * @throws MazeNotFoundException 
	 * 
	 */
	public int makeRunnerMove() throws MazeNotFoundException, IOException, MoveNotValidException {
		
		MoveResult suggestedMoveResult = this.lookForGoodMove();
		
		
		while(suggestedMoveResult.getField() != MoveResult.EXIT && suggestedMoveResult.getField() != MoveResult.WALL ){
			
				// runner can go forward !
				System.out.println("Going forward :"+suggestedMoveResult.getPosition());
				this.getMazeRunner().move(suggestedMoveResult.getPosition());
				makeRunnerMove();
			}
			
			if(suggestedMoveResult.getField() == MoveResult.EXIT){
				// Maze solved !
				System.out.println("The maze runner is finnaly out!");
				return 0;
			}
			if(suggestedMoveResult.getField() == MoveResult.WALL){
				// Runner between four walls !
				System.out.println("Oh no he is trapped !!");
				return -1;
			}
		return -1;
		
	}

	private MoveResult lookForGoodMove() throws MazeNotFoundException, MoveNotValidException, IOException {
		
		MoveResult moveResult = null;
		
		Direction[] allDirections = Direction.values();
		for (Direction direction : allDirections) {
			
			moveResult =  MazeRunnerClient.getMoveResult(this.getMazeCode(),this.getMazeRunner().getcurrentPosition(),direction);
			if(moveResult.isGoodMove()){
				return moveResult;
			}
			
			
		}
		
		return moveResult;
	}

	

}
