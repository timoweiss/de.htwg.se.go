package de.htwg.go.controller;

import de.htwg.go.util.observer.IObservable;

public interface IGoController extends IObservable {

	/**
	 * creates a gamefield to play
	 */
	void createField();


	
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
	
}
