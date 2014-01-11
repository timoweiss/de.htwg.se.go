package de.htwg.go.model;

import java.awt.Point;

public interface ICell {

	/**
     * returns the status of a cell
     * @return status of a cell
     */
	public int getStatus();

	/**
     * returns the coords of a cell
     * @return coords of a cell
     */
	public Point getCoords();

	/**
     * Set the value of a cell.
     * @param color
     */
	public void setStatus(int color);

	/**
     * returns whether the cell is checked or not
     * @return boolean if checked or not
     */
	public boolean isChecked();

	/**
     * sets a cell to checked or not
     * @param checked
     */
	public void setChecked(boolean checked);

	/**
     * resets the check of a cell
     */
	public void resetCheck();

	/**
     * returns a string information for a cell
     * @return string of a cell
     */
	public String toString();

}
