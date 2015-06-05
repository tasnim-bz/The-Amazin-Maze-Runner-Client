package de.zalando.mazerunner.client.main;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import de.zalando.mazerunner.client.entities.MazeRunner;
import de.zalando.mazerunner.client.execption.MazeNotFoundException;
import de.zalando.mazerunner.client.execption.MoveNotValidException;
import de.zalando.mazerunner.client.handler.MazeRunnerHandler;

public class Main {

	public static void main(String[] args) {
		
		try{
			
			final String  mazeCode = "maze-1" ;
			
			MazeRunner mazeRunner = new MazeRunner();
			MazeRunnerHandler mazeRunnerHandler = new MazeRunnerHandler(mazeRunner);
			mazeRunnerHandler.setMazeCode(mazeCode);
			
			mazeRunnerHandler.placeRunnerOnStartPosition();
			mazeRunnerHandler.makeRunnerMove();
			
			
		}catch(MazeNotFoundException e){
			System.out.println("Maze not found !");
		}
		catch (IOException e) {
			System.out.println("Error while communicating with REST server !");
		} catch (MoveNotValidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

}
