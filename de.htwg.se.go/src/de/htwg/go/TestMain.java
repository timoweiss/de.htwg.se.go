package de.htwg.go;

import de.htwg.go.aview.TextUI;
import de.htwg.go.controller.impl.GoController;

public class TestMain {
	public static void main(final String args[]) {
		GoController controller = new GoController();
		new TextUI(controller);

		controller.createField();

		
		controller.setStone(3, 2, "b");
		controller.setStone(4, 2, "b");
		
		controller.setStone(2, 4, "b");
		controller.setStone(3, 4, "b");
		controller.setStone(4, 4, "b");
		
		controller.setStone(2, 3, "b");
		controller.setStone(4, 3, "b");
		
		controller.setStone(3, 5, "b");
		controller.setStone(3, 6, "b");
		controller.setStone(3, 7, "b");
		controller.setStone(2, 2, "b");
		


	}
}
