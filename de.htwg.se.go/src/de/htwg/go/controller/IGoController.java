package de.htwg.go.controller;

public interface IGoController {
	/*
	 * Create GameField
	 */
	void createField();
	
	/*
	 * Print Tui
	 * @return returns the Tui
	 */
	String tuiToString();	
	
	void setStone(int x, int y, String color);
}
