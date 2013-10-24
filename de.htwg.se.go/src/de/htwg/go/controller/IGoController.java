package de.htwg.go.controller;

import de.htwg.go.util.observer.IObservable;

public interface IGoController extends IObservable {
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
