package model;

public class Cell {
	private int status;

	public Cell() {
		status = 0;
	}
	
	
	public Cell(String x) {
		if (x.equals("w")) {
			status = 1;
		} else if (x.equals("b"));
	}

}
