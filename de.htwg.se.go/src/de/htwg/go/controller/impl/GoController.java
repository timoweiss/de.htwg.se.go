package de.htwg.go.controller.impl;

import de.htwg.go.controller.IGoController;
import de.htwg.go.model.GameField;
import de.htwg.go.util.observer.Observable;

public class GoController extends Observable implements IGoController {

	private GameField gamefield;

	public GoController() {
		this.gamefield = new GameField();
		notifyObservers();
	}

	@Override
	public void createField() {
		gamefield = new GameField();
		notifyObservers();
	}

	@Override
	public String tuiToString() {
		return gamefield.toString();
	}

	@Override
	public void setStone(int x, int y, String color) {
		gamefield.setStone(x, y, color);
		notifyObservers();
	}
	


}
