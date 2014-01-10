package de.htwg.go.controller;

import de.htwg.go.util.observer.IObservable;

public interface IGoController extends IObservable {

	//Creates the Gamefield to play
	void createField();

	//returns the tui in String format
	String tuiToString();

	//Sets a stone and knows, whos next
	boolean setStone(int x, int y);
	
	//Sets a stone and wants to know which player is next
	void setStone(int x, int y, int status);

	//returns the statusline
	String getStatus();
	
	//returns the Score from whitePlayer
	int getwhitePlayerScore();
	
	//returns the Score from whitePlayer
	int getblackPlayerScore();

	//returns the status of a cell
	int getCellStatus(int x, int y);
	
	//return if white is next or not
	String getNext();
	
}
