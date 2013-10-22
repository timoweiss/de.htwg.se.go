package de.htwg.go.model;

public class Cell {
	private int status;

	public Cell() {
		setStatus(0);
	}

	public Cell(String color) {
		if (color.equals("w")) {
			setStatus(1);
		} else if (color.equals("b")) {
			setStatus(2);
		} else {
			setStatus(0);
		}
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
