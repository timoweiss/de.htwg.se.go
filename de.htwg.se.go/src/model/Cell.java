package model;

public class Cell {
	private int status;

	public Cell() {
		setStatus(0);
	}
	
	
	public Cell(String x) {
		if (x.equals("w")) {
			setStatus(1);
		} else if (x.equals("b")) {
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
