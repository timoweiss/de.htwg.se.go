package de.htwg.go;

import de.htwg.go.aview.TextUI;
import de.htwg.go.controller.impl.GoController;


public class go {
	public static void main(final String args[]) {
		
		GoController controller = new GoController();
		TextUI textui = new TextUI(controller);
		controller.addObserver(textui);
		controller.setStone(1, 1, "w");
		
	}
}
