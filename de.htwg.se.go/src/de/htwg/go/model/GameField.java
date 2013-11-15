package de.htwg.go.model;

import java.awt.Point;

import de.htwg.go.util.PrintErrors;
import de.htwg.go.util.observer.*;

/**
 * @author Timo Weiss, Michael Knoch
 * 
 */
public class GameField extends Observable {
	private Cell gameField[][];
	private boolean whiteIsNext = true;

	// size of the gamefield LENGTH x LENGTH
	private final static int lENGTH = 9;

	public GameField() {
		randomNext();
		createField();
	}

	/*
	 * initializes the field
	 */
	private void createField() {

		this.gameField = new Cell[lENGTH][lENGTH];
		for (int i = 0; i < this.gameField.length; ++i) {
			for (int j = 0; j < gameField[i].length; ++j) {
				gameField[i][j] = new Cell(i, j);
			}
		}
	}

	/*
	 * randoms whether white is next or not
	 */
	private void randomNext() {
		int random = (int) (Math.random() * 2) + 1;

		if (random == 1) {
			whiteIsNext = true;
		} else {
			whiteIsNext = false;
		}
	}

	/*
	 * returns the color of the player whos next
	 */
	public String getNext() {
		if (whiteIsNext) {
			return "white";
		} else {
			return "black";
		}
	}

	/*
	 * sets a Stone with x and y
	 */
	public boolean setStone(int x, int y) {
		if (gameField.length - 1 < x || gameField.length - 1 < y) {
			return false;
		} else if (getCellStatus(x, y) != 0) {
			return false;
		}

		if (whiteIsNext) {
			this.gameField[y][x].setStatus("w");
		} else {
			this.gameField[y][x].setStatus("b");
		}

		whiteIsNext = !whiteIsNext;
		return true;
	}

	/*
	 * Sets a stone with x,y and status
	 */
	public void setStone(int x, int y, String color) {
		this.gameField[y][x].setStatus(color);
	}

	public int getCellStatus(int x, int y) {
		if (gameField[y][x] == null) {
			PrintErrors.printErrorMessage(0);
			return -1;
		} else {
			return gameField[y][x].getStatus();
		}
	}

	public Boolean isSurrounded(int x, int y, String color) {

		return false;
	}

	public boolean buildForm(int x, int y) {
		int opponent;
		if (this.getCellStatus(x, y) == 1) {
			opponent = 2;
		} else {
			opponent = 1;
		}

		if (rbuildForm(x, y, opponent, x, y)) {
			return true;
		} else {
			return false;
		}
	}

	private boolean rbuildForm(int x, int y, int opponent, int startx,
			int starty) {

		if (this.getCellStatus(x, y) == opponent) {
			return false;
		} else {
			if (x == startx && x == starty) {
				return true;
			}
		}
		rbuildForm(x + 1, y, opponent, startx, starty);
		rbuildForm(x - 1, y, opponent, startx, starty);
		rbuildForm(x, y + 1, opponent, startx, starty);
		rbuildForm(x, y - 1, opponent, startx, starty);
		return false;
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();

		string.append("    0 1 2 3 4 5 6 7 8\n");
		string.append("    _ _ _ _ _ _ _ _ _\n");

		for (int i = 0; i < gameField.length; i++) {
			string.append(i + "  |");
			for (Cell element : gameField[i]) {
				string.append(element.getStatus() + " ");
			}
			string.append("\n");
		}
		return string.toString();
	}

}
