package de.htwg.go.controller.impl;

import com.google.inject.Inject;

import de.htwg.go.controller.IGoController;
import de.htwg.go.model.IGameField;
import de.htwg.go.model.impl.GameField;
import de.htwg.go.util.observer.Observable;

public class GoController extends Observable implements IGoController {

	private IGameField gamefield;
	private String statusLine;

	public GoController() {

	}

	@Override
	@Inject
	public void createField() {
		this.gamefield = new GameField();
		statusLine = "Gamefield successfully created, \n" + gamefield.getNext()
				+ " is next";
		notifyObservers();
	}

	@Override
	public String tuiToString() {
		return gamefield.toString();
	}

	@Override
	public boolean setStone(int x, int y) {
		boolean status;

		String next = gamefield.getNext();
		if (gamefield.setStone(x, y)) {
			statusLine = ("set " + next.toUpperCase() + " at (" + x + "," + y
					+ ") \n" + gamefield.getNext() + " is next");
			status = true;

		} else {
			statusLine = ("unable to set stone at (" + x + "," + y + ")\n"
					+ next + " is still next");
			status = false;
		}
		notifyObservers();
		return status;
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

	@Override
	public int getCellStatus(int x, int y) {
		return gamefield.getCellStatus(x, y);
	}

	@Override
	public String getNext() {
		return gamefield.getNext();
	}

	@Override
	public boolean pass() {
		boolean pass = gamefield.pass();

		statusLine = "Player passed, " + gamefield.getNext() + " is next";
		notifyObservers();
		return pass;
	}

	@Override
	public int getGameFieldSize() {
		return gamefield.getGameFieldSize();
	}

}
