package de.htwg.go.model.impl;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

import com.google.inject.Inject;

import de.htwg.go.model.ICell;
import de.htwg.go.model.IGameField;
import de.htwg.go.model.IPlayer;
import de.htwg.go.util.observer.*;

/**
 * @author Timo Weiss, Michael Knoch
 * 
 */
public class GameField extends Observable implements IGameField {

	private boolean pass = false;

	private ICell gameField[][];
	private boolean whiteIsNext = true;

	private Set<ICell> blackList;
	private Set<ICell> whiteList;

	private Collection<Set<ICell>> blackRegions;
	private Collection<Set<ICell>> whiteRegions;

	private IPlayer whitePlayer;
	private IPlayer blackPlayer;

	// size of the gamefield LENGTH x LENGTH
	private int LENGTH;
	
	// hell of injections?
	@Inject
	public GameField() {
		randomNext();
		createField(5);

		whitePlayer = new Player();
		blackPlayer = new Player();
		
		//same instance??
//		this.whitePlayer = iPlayer;
//		this.blackPlayer = iPlayer;
		
		blackList = new TreeSet<ICell>();
		whiteList = new TreeSet<ICell>();

		blackRegions = new LinkedList<Set<ICell>>();
		whiteRegions = new LinkedList<Set<ICell>>();
	}

	/*
	 * initializes the field
	 */
	//@inject ?
	private void createField(int x) {
		LENGTH = x;
		this.gameField = new Cell[LENGTH][LENGTH];
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
		pass = false;
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
		pass = false;

	}

	public int getCellStatus(int x, int y) {
		return gameField[y][x].getStatus();
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();

		string.append("    0 1 2 3 4 5 6 7 8\n");
		string.append("    _ _ _ _ _ _ _ _ _\n");

		for (int i = 0; i < gameField.length; i++) {
			string.append(i + "  |");
			for (ICell element : gameField[i]) {
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

		final int highNumber = 100;
		Set<ICell> region = new TreeSet<ICell>();

		int status = getCellStatus(x, y);
		int gegner = highNumber;

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

	private boolean deepSearch(int x, int y, int gegner, Set<ICell> region) {

		if (getCellStatus(x, y) != gegner && !gameField[y][x].isChecked()) {

			rememberMe(x, y);
			region.add(gameField[y][x]);

			// unten
			deepSearch(x, y + 1, gegner, region);
			// links
			deepSearch(x - 1, y, gegner, region);
			// oben
			deepSearch(x, y - 1, gegner, region);
			// rechts
			deepSearch(x + 1, y, gegner, region);
		}
		return true;
	}

	private void rememberMe(int x, int y) {
		gameField[y][x].setChecked(true);
	}

	// Method will be called after a stone is set
	private void moveEnd() {
		final int blackRemoved = -2;
		final int whiteRemoved = -1;
		final int minusone = -1;

		LinkedList<ICell> allCells = new LinkedList<ICell>();
		allCells.addAll(whiteList);
		allCells.addAll(blackList);

		for (ICell x : allCells) {
			fenced(x.getCoords().x, x.getCoords().y);
		}

		// check Black Regions
		for (Set<ICell> list : blackRegions) {
			for (ICell cell : list) {

				if ((this.getCellStatus(cell.getCoords().x, cell.getCoords().y) == 1)) {

					whitePlayer.addScore(minusone);
					blackPlayer.addScore(1);
					gameField[cell.getCoords().y][cell.getCoords().x]
							.setStatus(whiteRemoved);

				}

			}
			whiteList.removeAll(list);
		}

		// check white Regions
		for (Set<ICell> list : whiteRegions) {

			for (ICell cell : list) {

				if (this.getCellStatus(cell.getCoords().x, cell.getCoords().y) == 2) {

					blackPlayer.addScore(minusone);
					whitePlayer.addScore(1);
					gameField[cell.getCoords().y][cell.getCoords().x]
							.setStatus(blackRemoved);

				}

			}

			blackList.removeAll(list);
		}

	}

	public IPlayer getwhitePlayer() {
		return whitePlayer;
	}

	public IPlayer getblackPlayer() {
		return blackPlayer;
	}

	public boolean checked(int x, int y) {
		return gameField[x][y].isChecked();
	}

	public boolean pass() {

		whiteIsNext = !whiteIsNext;

		if (pass) {
			return true;
		}

		pass = true;

		return false;

	}
	
	public int getGameFieldSize() {
		return LENGTH;
	}

}
