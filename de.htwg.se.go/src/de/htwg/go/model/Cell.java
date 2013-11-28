package de.htwg.go.model;

import java.awt.Point;

public class Cell {
	private int status;
	private Point coords;
	private boolean checked;

	public Cell(int x, int y) {
		setStatus("0");
		coords = new Point(x, y);
	}

	public Cell(int x, int y, String color) {
		setStatus(color);
		coords = new Point(x, y);
	}

	public int getStatus() {
		return status;
	}

	public Point getCoords() {
		return this.coords;
	}
	
	public final void setStatus(String color) {
	
		if (color.equals("w")) {
			this.status = 1;
		} else if (color.equals("b")) {
			this.status = 2;
		} else if (color.equals("0")) {
			this.status = 0;
		} else if (color.equals("-1")) {
			this.status = -1;
		}
		
		
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
	

}
