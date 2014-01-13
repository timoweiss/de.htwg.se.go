package de.htwg.go.model;

import java.awt.Point;

/**	
 * Interface ICell represents the elementary item of the Game.
 * 
 */

public interface ICell {

	/**
     * returns the status of a cell
     * @return status of a cell
     */
	int getStatus();

	/**
     * returns the coords of a cell
     * @return coords of a cell
     */
	Point getCoords();

	/**
     * Set the value of a cell.
     * @param color
     */
	void setStatus(int color);

	/**
     * returns whether the cell is checked or not
     * @return boolean if checked or not
     */
	boolean isChecked();

	/**
     * sets a cell to checked or not
     * @param checked
     */
	void setChecked(boolean checked);

	/**
     * resets the check of a cell
     */
	void resetCheck();

	/**
     * returns a string information for a cell
     * @return string of a cell
     */
	String toString();

}
