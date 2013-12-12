package de.htwg.go.controller.impl;

import de.htwg.go.controller.IGoController;
import de.htwg.go.model.GameField;
import de.htwg.go.util.observer.Observable;

public class GoController extends Observable implements IGoController {

	private GameField gamefield;
	private String statusLine;

	public GoController() {

	}

	@Override
	public void createField() {
		gamefield = new GameField();
		statusLine = "Gamefield successfully created\n" + gamefield.getNext()
				+ " is next";
		notifyObservers();
	}

	@Override
	public String tuiToString() {
		return gamefield.toString();
	}

	@Override
	public void setStone(int x, int y) {
		String next = gamefield.getNext();
		if (gamefield.setStone(x, y)) {
			statusLine = ("set " + next.toUpperCase() + " at (" + x + "," + y
					+ ")\n" + gamefield.getNext() + " is next");
		} else {
			statusLine = ("unable to set stone at (" + x + "," + y + ")\n"
					+ next + " is still next");
		}
		notifyObservers();
	}

	public void setStone(int x, int y, int status) {
		gamefield.setStone(x, y, status);
		notifyObservers();
	}

	public String getStatus() {
		return statusLine;
	}

	@Override
	public int getwhitePlayerScore() {
		return gamefield.getwhitePlayer().getScore();
	}

	@Override
	public int getblackPlayerScore() {
		return gamefield.getblackPlayer().getScore();
	}

	

}
