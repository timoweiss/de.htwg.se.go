package de.htwg.go.controller;

import de.htwg.go.util.observer.IObservable;

/**	
 * Interface IGoController builds the bridge 
 * between view and model
 * 
 */

public interface IGoController extends IObservable {

	/**
	 * creates a gamefield to play (x * x)
	 */
	void createField(int x);


	
	/**
	 * returns the tui in String format
	 * @return tui string
	 */
	String tuiToString();


	
	/**
	 * Sets a stone and returns if the stoneset workd
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @return boolean
	 */
	boolean setStone(int x, int y);
	
	/**
	 * Sets a stone and wants to know which player is next
	 */
	void setStone(int x, int y, int status);

	
	/**
	 * returns the gamestatus
	 * @return string
	 */
	String getStatus();
	
	//returns the Score from whitePlayer
	
	/**
	 * returns the Score of white Player
	 * @return int
	 */
	int getwhitePlayerScore();
	
	/**
	 * returns the Score of black Player
	 * @return int
	 */
	int getblackPlayerScore();

	/**
	 * returns the cell status
	 * @param x-coordinate
	 * @param y-coordinate
	 * @return status in int
	 */
	int getCellStatus(int x, int y);
	
	
	/**
	 * returns which player is next
	 * @return string
	 */
	String getNext();
	
	/**
	 * allows a player to pass
	 * @return true if both players passed, false if just one players passed
	 */
	boolean pass();
	
	/**
	 * returns the size of the gamefield
	 * @return int
	 */
	int getGameFieldSize();
	
	/**
	 * stops the game
	 */
	void stop();
	
	/**
	 * returns if the game is still running
	 * @return boolean
	 */
	boolean getOperate();
}
