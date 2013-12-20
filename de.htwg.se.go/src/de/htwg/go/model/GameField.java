package de.htwg.go.model;

import java.util.LinkedList;
import java.util.Set;
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

	private Set<Cell> blackList;
	private Set<Cell> whiteList;

	private LinkedList<Set<Cell>> blackRegions;
	private LinkedList<Set<Cell>> whiteRegions;

	private Player whitePlayer;
	private Player blackPlayer;

	// size of the gamefield LENGTH x LENGTH
	private final static int lENGTH = 9;

	public GameField() {
		randomNext();
		createField();

		whitePlayer = new Player();
		blackPlayer = new Player();

		blackList = new TreeSet<Cell>();
		whiteList = new TreeSet<Cell>();

		blackRegions = new LinkedList<Set<Cell>>();
		whiteRegions = new LinkedList<Set<Cell>>();
	}

	/*
	 * initializes the field
	 */
	private void createField() {

		this.gameField = new Cell[lENGTH][lENGTH];
		for (int i = 0; i < this.gameField.length; ++i) {
			for (int j = 0; j < gameField[i].length; ++j) {
				gameField[i][j] = new Cell(j, i);
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
			this.gameField[y][x].setStatus(1);
			whiteList.add(gameField[y][x]);
			whitePlayer.addScore(1);
			moveEnd();
		} else {
			this.gameField[y][x].setStatus(2);
			blackList.add(gameField[y][x]);
			blackPlayer.addScore(1);
			moveEnd();
		}

		whiteIsNext = !whiteIsNext;
		return true;
	}

	/*
	 * Sets a stone with x,y and status
	 */
	public void setStone(int x, int y, int color) {
		this.gameField[y][x].setStatus(color);
		if (color == 1) {
			whiteList.add(gameField[y][x]);
			moveEnd();
		} else {
			blackList.add(gameField[y][x]);
			moveEnd();
		}

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

	public boolean fenced(int x, int y) {
		TreeSet<Cell> region = new TreeSet<Cell>();

		int status = getCellStatus(x, y);
		int gegner = 100;


		if (status == 1) {
			gegner = 2;
		} else {
			gegner = 1;

			
		}

		try {
			boolean fenced = deepSearch(x, y, gegner, region);

			if (fenced) {
				if (status == 1) {
					blackRegions.add(region);
				} else {
					whiteRegions.add(region);
				}
			}

			return fenced;

		} catch (IndexOutOfBoundsException e) {
			return false;
		} finally {
			resetAllChecks();

		}
	}

	private boolean deepSearch(int x, int y, int gegner, TreeSet<Cell> region) {

		if (getCellStatus(x, y) != gegner && !gameField[y][x].isChecked()) {

			rememberMe(x, y);
			region.add(gameField[y][x]);

			deepSearch(x, y + 1, gegner, region); // unten
			deepSearch(x - 1, y, gegner, region); // links
			deepSearch(x, y - 1, gegner, region); // oben
			deepSearch(x + 1, y, gegner, region); // rechts
		}
		return true;
	}

	private void rememberMe(int x, int y) {
		gameField[y][x].setChecked(true);
	}

	// Method will be called after a stone is set
	private void moveEnd() {
		
		
		LinkedList<Cell> allCells = new LinkedList<Cell>();
		allCells.addAll(whiteList);
		allCells.addAll(blackList);

		for (Cell x : allCells) {
			fenced(x.getCoords().x, x.getCoords().y);
		}

		for (Set<Cell> list : blackRegions) {
			for (Cell cell : list) {
								
				if (whiteList.remove(cell)) {
					whitePlayer.addScore(-1);
					gameField[cell.getCoords().y][cell.getCoords().x].setStatus(-1);
					
					
				}

			}
		}

		for (Set<Cell> list : whiteRegions) {
			for (Cell cell : list) {
				gameField[cell.getCoords().y][cell.getCoords().x].setStatus(-2);
				
				if (blackList.remove(cell)) {
					blackPlayer.addScore(-1);
					gameField[cell.getCoords().y][cell.getCoords().x].setStatus(-1);
					
					
				}

			}
		}
		
	}

	public Player getwhitePlayer() {
		return whitePlayer;
	}

	public Player getblackPlayer() {
		return blackPlayer;
	}
	
	public boolean checked(int x, int y) {
		return gameField[x][y].isChecked();
	}

}
