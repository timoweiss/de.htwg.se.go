package de.htwg.go.model;

public class GameField {
	private Cell gameField[][];

	public GameField() {
		for (int i = 0; i < gameField.length; i++) {
			for (Cell element : gameField[i])
				element = new Cell();
		}
	}

}
