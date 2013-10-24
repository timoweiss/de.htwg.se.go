package de.htwg.go.model;

import de.htwg.go.util.PrintErrors;
import de.htwg.go.util.observer.*;

public class GameField extends Observable {
	private Cell gameField[][];

	public GameField() {
		this.gameField = new Cell[9][9];
		for (int i = 0; i < this.gameField.length; ++i) {
			for (int j = 0; j < gameField[i].length; ++j) {
				gameField[i][j] = new Cell(i, j);
				
				
			}
		}
	}

	public void setStone(int x, int y, String color) {
		this.gameField[y][x].setStatus(color);
	}
	
	public int getCellStatus(int x, int y) {
		if (gameField[y][x].equals(null)) {
			PrintErrors.printErrorMessage(0);
			return -1;
		} else {
			return gameField[y][x].getStatus();
		}
		
	}


	public void printField() {
		for (int i = 0; i < gameField.length; i++) {
			for (Cell element : gameField[i]) {
				System.out.print(element.getStatus() + " ");
			}
			System.out.print("\n");
		}
	}
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		for (int i = 0; i < gameField.length; i++) {
			for (Cell element : gameField[i]) {
				string.append(element.getStatus() + " ");
			}
			string.append("\n");
		}
		return string.toString();
		
	}
}
