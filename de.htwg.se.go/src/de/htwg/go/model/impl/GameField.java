package de.htwg.go.model.impl;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.Patterns;
import akka.util.Timeout;
import de.htwg.go.model.ICell;
import de.htwg.go.model.IGameField;
import de.htwg.go.model.IPlayer;
import de.htwg.go.util.observer.Observable;
import scala.concurrent.Await;
import scala.concurrent.Awaitable;
import scala.concurrent.duration.Duration;

import java.util.*;
import java.util.concurrent.Future;

/**
 * @author Timo Weiss, Michael Knoch
 * 
 */
public class GameField extends Observable implements IGameField {

	private boolean pass = false;

    private String id;
	private ICell gameField[][];
	private boolean whiteIsNext = true;

	private Set<ICell> blackList;
	private Set<ICell> whiteList;

	public Collection<Set<ICell>> blackRegions;
	public Collection<Set<ICell>> whiteRegions;

	private IPlayer whitePlayer;
	private IPlayer blackPlayer;

	private static final int NINE = 9;

	// size of the gamefield LENGTH x LENGTH
	private int length;


	public GameField(int length) {

        id = UUID.randomUUID().toString();
		randomNext();

		createField(length);

		this.whitePlayer = new Player();
		this.blackPlayer = new Player();

		blackList = new TreeSet<ICell>();
		whiteList = new TreeSet<ICell>();

		blackRegions = new LinkedList<Set<ICell>>();
		whiteRegions = new LinkedList<Set<ICell>>();

	}

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ICell[][] getGameField() {
        return gameField;
    }

    public void setGameField(ICell[][] gameField) {
        this.gameField = gameField;
    }

    public boolean isWhiteIsNext() {
        return whiteIsNext;
    }

    public void setWhiteIsNext(boolean whiteIsNext) {
        this.whiteIsNext = whiteIsNext;
    }

    public Set<ICell> getBlackList() {
        return blackList;
    }

    public void setBlackList(Set<ICell> blackList) {
        this.blackList = blackList;
    }

    public Set<ICell> getWhiteList() {
        return whiteList;
    }

    public void setWhiteList(Set<ICell> whiteList) {
        this.whiteList = whiteList;
    }

    public Collection<Set<ICell>> getBlackRegions() {
        return blackRegions;
    }

    public void setBlackRegions(Collection<Set<ICell>> blackRegions) {
        this.blackRegions = blackRegions;
    }

    public Collection<Set<ICell>> getWhiteRegions() {
        return whiteRegions;
    }

    public void setWhiteRegions(Collection<Set<ICell>> whiteRegions) {
        this.whiteRegions = whiteRegions;
    }

    public IPlayer getWhitePlayer() {
        return whitePlayer;
    }

    public void setWhitePlayer(IPlayer whitePlayer) {
        this.whitePlayer = whitePlayer;
    }

    public IPlayer getBlackPlayer() {
        return blackPlayer;
    }

    public void setBlackPlayer(IPlayer blackPlayer) {
        this.blackPlayer = blackPlayer;
    }

    public static int getNine() {
        return NINE;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    /*
         * initializes the field
         */
	// @inject ?
	private void createField(int x) {
		length = x;
		this.gameField = new Cell[length][length];
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

    @Override
    public String getId() {
        return this.id;
    }

    /*
     * sets a Stone with x and y
     */
	public boolean setStone(int x, int y) {

		if (x < 0 || y < 0) {
			return false;
		}
		
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

		if (length == NINE) {
			string.append("    0 1 2 3 4 5 6 7 8\n");
			string.append("    _ _ _ _ _ _ _ _ _\n");
		} else {
			string.append("    0 1 2 3 4\n");
			string.append("    _ _ _ _ _\n");
		}

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
		return length;
	}

    @Override
    public boolean passed() {
        return pass;
    }

}
