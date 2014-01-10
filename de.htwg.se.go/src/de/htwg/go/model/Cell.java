package de.htwg.go.model;

import java.awt.Point;

public class Cell implements Comparable<Cell> {
	private int status;
	private Point coords;
	private boolean checked;

	public Cell(int x, int y) {
		setStatus(0);
		coords = new Point(x, y);
		checked = false;
	}

	public Cell(int x, int y, int color) {
		setStatus(color);
		coords = new Point(x, y);
		checked = false;
	}

	public int getStatus() {
		return status;
	}

	public Point getCoords() {
		return this.coords;
	}

	public final void setStatus(int color) {
		this.status = color;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public void resetCheck() {
		this.checked = false;
	}

	public String toString() {
		return (coords.x + "|" + coords.y);
	}

	public int compareTo(Cell cell) {
		if (this.coords.x == cell.coords.x && this.coords.y == cell.coords.y) {
			return 0;
		} else {
			return -1;
		}

	}

}
