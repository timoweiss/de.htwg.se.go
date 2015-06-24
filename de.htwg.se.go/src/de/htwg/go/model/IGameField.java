package de.htwg.go.model;

import java.util.Collection;
import java.util.Set;

/**
 * Represents the Gamefield of the Game.
 * Provides serveral methods for the general game functionality
 *
 */
public interface IGameField {
	
	/**
	 * returns which player is next (white or black)
	 * @return a string who is next
	 */
	String getNext();

    String getId();

	/**
	 * sets a stone at x,y
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @return true if setting the stone worked, false if not
	 */
	boolean setStone(int x, int y);

	/**
	 * sets a stone at x,y with a color
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @param color
	 */
	void setStone(int x, int y, int color);

	/**
	 * returns the cell status of a stone at x,y
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @return cell status in int (unplaced = 0, white = 1, black = 2)
	 */
	int getCellStatus(int x, int y);

	/**
	 * toString method of the gamefield for console output
	 * @return string of the gamefield
	 */
	String toString();

	/**
	 * checks whether a stone is surrounded or not
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @return boolean
	 */
	boolean fenced(int x, int y);

	/**
	 * returns the whitePlayer
	 * @return IPlayer
	 */
	IPlayer getwhitePlayer();

	/**
	 * returns the blackPlayer
	 * @return IPlayer
	 */
	IPlayer getblackPlayer();

	/**
	 * returns if a cell at x,y is already checked
	* @param x x-coordinate
	 * @param y y-coordinate
	 * @return boolean
	 */
	boolean checked(int x, int y);
	
	/**
	 * allows a player to pass
	 * @return true if both players passed, false if just one players passed
	 */
	boolean pass();
	
	/**
	 * returns the size of the gamefield x * x
	 * @return int
	 */
	int getGameFieldSize();

    boolean passed();


    public ICell[][] getGameField();

    public void setGameField(ICell[][] gameField);

    public boolean isWhiteIsNext();

    public void setWhiteIsNext(boolean whiteIsNext);

    public Set<ICell> getBlackList();

    public void setBlackList(Set<ICell> blackList);

    public Set<ICell> getWhiteList();

    public void setWhiteList(Set<ICell> whiteList);

    public Collection<Set<ICell>> getBlackRegions();

    public void setBlackRegions(Collection<Set<ICell>> blackRegions);

    public Collection<Set<ICell>> getWhiteRegions();

    public void setWhiteRegions(Collection<Set<ICell>> whiteRegions);

    public IPlayer getWhitePlayer();

    public void setWhitePlayer(IPlayer whitePlayer);

    public IPlayer getBlackPlayer();

    public void setBlackPlayer(IPlayer blackPlayer);

    public static int getNine();

    public int getLength();

    public void setLength(int length);
}
