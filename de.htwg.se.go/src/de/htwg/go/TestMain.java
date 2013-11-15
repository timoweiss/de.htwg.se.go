package de.htwg.go;

import de.htwg.go.model.GameField;

public class TestMain {
	public static void main() {

		GameField feld = new GameField();
		feld.setStone(1, 1);
		feld.setStone(2, 1);
		feld.setStone(3, 1);
		feld.setStone(3, 2);
		feld.setStone(3, 3);
		feld.setStone(2, 3);
		feld.setStone(1, 3);
		
		System.out.println(feld.toString());
	}

}
