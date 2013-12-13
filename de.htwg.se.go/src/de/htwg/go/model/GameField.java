package de.htwg.go.model;

import java.awt.Point;
import java.util.LinkedList;
import java.util.TreeSet;

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
		System.out.println(isForm(x, y));
		return true;
	}

	/*
	 * Sets a stone with x,y and status
	 */
	public void setStone(int x, int y, String color) {
		this.gameField[y][x].setStatus(color);
		System.out.println(isForm(x, y));
	}

	public int getCellStatus(int x, int y) {
		if (gameField[y][x] == null) {
			PrintErrors.printErrorMessage(0);
			return -1;
		} else {
			return gameField[y][x].getStatus();
		}
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

	public void resetAllChecks() {
		for (int i = 0; i < this.gameField.length; ++i) {
			for (int j = 0; j < gameField[i].length; ++j) {
				gameField[i][j].resetCheck();
			}
		}
	}

	public boolean isForm(int x, int y) {
		TreeSet<Double> pointList = new TreeSet<Double>();
		TreeSet<Double> backPointList = new TreeSet<Double>();

		resetAllChecks();
		int status = getCellStatus(x, y);
		Point pointer = new Point(x, y);

		int counter = 0;
		Point backPointer = null;
		int backJumps = 0;

		while (true) {

			int cellStatusRight = getCellStatus(pointer.x + 1, pointer.y);
			int cellStatusLeft = getCellStatus(pointer.x - 1, pointer.y);
			int cellStatusDown = getCellStatus(pointer.x, pointer.y + 1);
			int cellStatusUp = getCellStatus(pointer.x, pointer.y - 1);

			// Get environment cells
			Cell right = gameField[pointer.x + 1][pointer.y];
			Cell left = gameField[pointer.x - 1][pointer.y];
			Cell down = gameField[pointer.x][pointer.y + 1];
			Cell up = gameField[pointer.x][pointer.y - 1];

			System.out.println(pointer);
			
			if (backPointer == null) {
				pointList.add(pointer.x + (pointer.y * 0.1));
			} else {
				backPointList.add(pointer.x + (pointer.y * 0.1));
}

			// returns true, if we have a closed form
			if (pointer.x == x && pointer.y == y && counter > 2) {
				pointList.addAll(backPointList);
				System.out.println(pointList);
				return true;
			}

			//
			if (counter < 2) {
				gameField[x][y].setChecked(true);
			} else if (counter >= 2) {
				gameField[x][y].setChecked(false);
			}

			// get count of possible way to go
			int countWays = 0;
			if (cellStatusRight == status && !right.isChecked()) {
				countWays++;
			}

			if (cellStatusLeft == status && !left.isChecked()) {
				countWays++;
			}

			if (cellStatusDown == status && !down.isChecked()) {
				countWays++;
			}

			if (cellStatusUp == status && !up.isChecked()) {
				countWays++;
			}

			// remember turning point, if available
			if (countWays > 1) {
				if (backPointer != null) {
					pointList.addAll(backPointList);
					
				}
				backPointer = new Point(pointer.x, pointer.y);
				System.out.println("new Backpointer: " + backPointer);
			}

			// here comes the magic
			// sets pointer to the new position
			if (cellStatusRight == status && !right.isChecked()) {
				right.setChecked(true);
				pointer.setLocation(pointer.x + 1, pointer.y);

			} else if (cellStatusDown == status && !down.isChecked()) {
				down.setChecked(true);
				pointer.setLocation(pointer.x, pointer.y + 1);

			} else if (cellStatusLeft == status && !left.isChecked()) {
				left.setChecked(true);
				pointer.setLocation(pointer.x - 1, pointer.y);

			} else if (cellStatusUp == status && !up.isChecked()) {
				up.setChecked(true);
				pointer.setLocation(pointer.x, pointer.y - 1);

			} else {
				// if we have no further opportunity to go ahead & if we have a
				// turningpoint
				// we jump back to the turningpoint
				if (backPointer != null && backJumps < 20) {
					backJumps++;
					pointer = new Point(backPointer.x, backPointer.y);
					backPointer = null;
					backPointList.clear();
				} else {
					return false;
				}

			}

			counter++;
		}

	}

}
