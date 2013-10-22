package de.htwg.go;
import de.htwg.go.model.GameField;

public class go {
	public static void main(final String args[]) {
		GameField testfeld = new GameField();
		
		testfeld.setStone(3, 7, "w");
		testfeld.printField();
		
	}
}
