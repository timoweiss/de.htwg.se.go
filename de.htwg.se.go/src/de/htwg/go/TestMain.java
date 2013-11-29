package de.htwg.go;

import de.htwg.go.model.GameField;
import de.htwg.go.aview.TextUI;
import de.htwg.go.controller.impl.GoController;

public class TestMain {
	private static TextUI tui;
	public static void main(final String args[]) {
		GoController controller = new GoController();
		tui = new TextUI(controller);

		controller.createField();
			
		
		
		controller.setStone(4, 2, "b");
		controller.setStone(4, 6, "b");
		controller.setStone(4, 1, "b");
		controller.setStone(3, 1, "b");
		controller.setStone(4, 2, "b");
		controller.setStone(2, 1, "b");
		controller.setStone(5, 6, "b");
		controller.setStone(6, 6, "b");
		controller.setStone(3, 2, "b");
		controller.setStone(7, 3, "b");
		controller.setStone(7, 4, "b");
		
		controller.setStone(5, 5, "b");
		controller.setStone(4, 5, "b");
		controller.setStone(3, 5, "b");
		controller.setStone(3, 4, "b");
		controller.setStone(6, 4, "b");
		controller.setStone(6, 3, "b");
		controller.setStone(6, 5, "b");
		
		controller.setStone(3, 3, "b");
		controller.setStone(4, 3, "b");
		
		System.out.println("LastPoint:");
		controller.setStone(5, 3, "b");
		
		
		
		
	}
}
