package de.htwg.go;

import de.htwg.go.aview.TextUI;
import de.htwg.go.controller.impl.GoController;

public class TestMain {
	public static void main(final String args[]) {
		GoController controller = new GoController();
		new TextUI(controller);

		controller.createField();

		
		controller.setStone(3, 2, 2);
		controller.setStone(4, 2, 2);
		
		controller.setStone(2, 4, 2);
		controller.setStone(3, 4, 2);
		controller.setStone(4, 4, 2);
		
		controller.setStone(2, 3, 2);
	
		
		controller.setStone(3, 5, 2);
		controller.setStone(3, 6, 2);
		controller.setStone(3, 7, 2);
		
		controller.setStone(2, 2, 2);
		
		controller.setStone(3, 3, 1);
		
		
		controller.setStone(5, 2, 2);
		controller.setStone(5, 3, 2);
		controller.setStone(5, 4, 2);
		

	}
}
