package de.zalando.mazerunner.client.handler;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.simple.JSONObject;

import de.zalando.mazerunner.client.api.RestClientApi;
import de.zalando.mazerunner.client.entities.Coordinate;
import de.zalando.mazerunner.client.entities.Direction;
import de.zalando.mazerunner.client.entities.MoveResult;
import de.zalando.mazerunner.client.execption.MazeNotFoundException;
import de.zalando.mazerunner.client.execption.MoveNotValidException;


public class MazeRunnerClient {


	

	/**
	 * Return the start position of a given maze
	 * @param mazeCode
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static Coordinate getStartPosition(String mazeCode) throws MazeNotFoundException, ClientProtocolException, IOException {
		
		String response = RestClientApi.executeGetRequest( MazeRunnerClient.buildStartPositionRequest(mazeCode) );
		if(response== String.valueOf(404)){
			throw new MazeNotFoundException("the maze is not found");
		}
	
		return MazeRunnerClient.getStartPositionResponse( response);
	}
	
	
	
	/**
	 * Build the get start position response
	 * @param response
	 * @return
	 */
	private static  Coordinate getStartPositionResponse(String response){
		
		//here decode the json response and return the Coordinate coordinates
	
		return new Coordinate(0,1);
	}
	
	/**
	 * Build the get start position request
	 * @param mazeCode
	 * @return
	 */
	private static  String buildStartPositionRequest(String mazeCode){
		
		StringBuffer request = new StringBuffer();
		request.append("/mazes/");
		request.append(mazeCode);
		request.append("/position/start");
		return request.toString();
	}
	
	
	/**
	 * Return the next move position of a given maze
	 * @param mazeCode
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	@SuppressWarnings("unchecked")
	public static MoveResult getMoveResult(String mazeCode, Coordinate currentLocation, Direction direction) throws MazeNotFoundException, MoveNotValidException, IOException {
		
		 JSONObject jsonData = new JSONObject();
		 jsonData.put("from", currentLocation);
		 jsonData.put("direction", direction);
		 
		 
		String response = RestClientApi.executePostRequest( MazeRunnerClient.buildNextMovePositionRequest(mazeCode), jsonData.toString() );
		
		
		if(response== String.valueOf(404)){
			throw new MazeNotFoundException("the maze is not found");
		}
		
		if(response== String.valueOf(418)){
			throw new MoveNotValidException("The move is not valid");
		}
		
		return MazeRunnerClient.getNextMovePositionResponse( response);
	}
	
	
	
	/**
	 * Build the get next move position response
	 * @param response
	 * @return
	 */
	private static  MoveResult getNextMovePositionResponse(String response){
		
		//here decode the json response and return the Coordinate coordinates
	    MoveResult moveResult = new MoveResult();
	    moveResult.setField(MoveResult.EXIT);
	    moveResult.setPosition(new Coordinate(1,1));
		return moveResult;
	}
	
	/**
	 * Build the get next move position request
	 * @param mazeCode
	 * @return
	 */
	private static  String buildNextMovePositionRequest(String mazeCode ){
		
		StringBuffer request = new StringBuffer();
		request.append("/mazes/");
		request.append(mazeCode);
		request.append("/position/start");
		return request.toString();
	}

}
